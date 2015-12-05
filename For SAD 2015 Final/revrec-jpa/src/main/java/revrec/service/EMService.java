package revrec.service;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EMService
{
  private EntityManager em;
  
  private EMService()
  {
  }
  
  public static EntityManager getEntityManager()
  {
    return Persistence.createEntityManagerFactory("revrec-jpa").createEntityManager();
  }
}
