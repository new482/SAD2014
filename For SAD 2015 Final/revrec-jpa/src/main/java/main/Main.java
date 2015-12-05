package main;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import revrec.datatype.MfDate;
import revrec.datatype.Money;
import revrec.domain.Contract;
import revrec.domain.Product;
import revrec.service.EMService;

public class Main
{
  public static void main(String[] args)
  {
    EntityManager em = EMService.getEntityManager();
    EntityTransaction etx = em.getTransaction();
    etx.begin();
    Product p1 = new Product("Test DB 1", Product.D_TYPE);
    p1.save(em);
    
    etx.commit();
  }
  
  public void testSaveProduct()
  {
    EntityManager em = EMService.getEntityManager();
    EntityTransaction etx = em.getTransaction();
    etx.begin();
    Product p1 = new Product("Test DB 1", Product.D_TYPE);
    p1.save(em);
    
    etx.commit();
  }
}
