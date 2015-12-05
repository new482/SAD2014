package revrec.test;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import revrec.datatype.MfDate;
import revrec.datatype.Money;
import revrec.domain.Contract;
import revrec.domain.Product;
import revrec.service.EMService;

public class ContractTest
{
  private static EntityManager em;
  private static EntityTransaction etx;
  
  private static Product newProduct;
  private static Contract newContract;
  
  @BeforeClass
  public static void setUpBeforeClass() throws Exception
  {
    em = EMService.getEntityManager();
    etx = em.getTransaction();
    etx.begin();
    
    newProduct = new Product("Test DB 1", Product.D_TYPE);
    newProduct.save(em);
  }

  @Test
  public void testSaveContract()
  {
    newContract = new Contract(newProduct, Money.dollars(100), MfDate.now());
    newContract.calculateRecognitions();
    long beforeSaveContractId = newContract.getContractId();
    newContract.save(em);
    
    assertNotEquals(beforeSaveContractId, newContract.getContractId());
  }
  
  @Test
  public void testFindContract()
  {
    Contract foundContract = Contract.find(em, newContract.getContractId());

    assertEquals(newContract.getContractId(), foundContract.getContractId());
    assertEquals(newContract.getProduct().getProductId(), foundContract.getProduct().getProductId());
    assertEquals(newContract.getRecognitions().size(), foundContract.getRecognitions().size());
  }
  
  @AfterClass
  public static void cleanUpAfterClass()
  {
    etx.rollback();
  }

}
