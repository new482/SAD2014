package com.titan.travel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.titan.travel.domain.Cabin;
import com.titan.travel.service.TravelAgentService;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class TravelAgentTests {

	@PersistenceContext
	private EntityManager entityManager;

	@Test
	@Transactional
	public void testSaveOrderWithItems() throws Exception {
		Order order = new Order();
		order.getItems().add(new Item());
		entityManager.persist(order);
		entityManager.flush();
		assertNotNull(order.getId());
	}

	@Test
	@Transactional
	public void testSaveAndGet() throws Exception {
		Order order = new Order();
		order.getItems().add(new Item());
		entityManager.persist(order);
		entityManager.flush();
		// Otherwise the query returns the existing order (and we didn't set the
		// parent in the item)...
		entityManager.clear();
		Order other = (Order) entityManager.find(Order.class, order.getId());
		assertEquals(1, other.getItems().size());
		assertEquals(other, other.getItems().iterator().next().getOrder());
	}

	@Test
	@Transactional
	public void testSaveAndFind() throws Exception {
		Order order = new Order();
		Item item = new Item();
		item.setProduct("foo");
		order.getItems().add(item);
		entityManager.persist(order);
		entityManager.flush();
		// Otherwise the query returns the existing order (and we didn't set the
		// parent in the item)...
		entityManager.clear();
		Order other = (Order) entityManager
				.createQuery(
						"select o from Order o join o.items i where i.product=:product")
				.setParameter("product", "foo").getSingleResult();
		assertEquals(1, other.getItems().size());
		assertEquals(other, other.getItems().iterator().next().getOrder());
	}
	
	
	
	@Autowired
	private TravelAgentService travelAgentService;

	@Test
	public void testCabin() throws Exception
	{
	   Cabin cabin = new Cabin();
	   cabin.setShipId(3);
	   cabin.setDeckLevel(2);
	   cabin.setName("Master Suite");
	   cabin.setBedCount(4);
	   long id = travelAgentService.createCabin(cabin);

	   Cabin cabin2 = travelAgentService.findCabin(id);
	   assertEquals("Persistent object not equal to original object.", cabin, cabin2);
	}

}

