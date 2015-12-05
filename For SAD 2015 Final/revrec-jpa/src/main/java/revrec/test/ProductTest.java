package revrec.test;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import revrec.domain.Product;
import revrec.service.EMService;

public class ProductTest
{
  private static EntityManager em;
  private static EntityTransaction etx;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception
  {
    em = EMService.getEntityManager();
    etx = em.getTransaction();
    etx.begin();
  }

  @Test
  public void testSaveProduct()
  {
    Product p1 = new Product("Test DB 1", Product.D_TYPE);
    long productIdBeforePersisted = p1.getProductId();
    p1.save(em);
    long productIdAfterPersisted = p1.getProductId();
    
    assertNotEquals(productIdBeforePersisted, productIdAfterPersisted);
  }
  
  @Test
  public void testFindProduct()
  {
    Product p1 = new Product("Test DB 1", Product.D_TYPE);
    p1.save(em);
    Product p1Found = Product.find(em, p1.getProductId());
    
    assertEquals(p1.getProductId(), p1Found.getProductId());
    assertEquals(p1.getName(), p1Found.getName());
    assertEquals(p1.getType(), p1Found.getType());
  }

  @AfterClass
  public static void cleanUpAfterClass()
  {
    etx.rollback();
  }

}
