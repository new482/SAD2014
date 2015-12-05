package asia.ait.sad.cameltest;
import java.util.Date;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MyTransform
{
   private static final transient Logger logger = Logger
      .getLogger(MyTransform.class.getName());

   public Object transform(Object body)
   {
       Person person = (Person)body;

       EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-example");
       EntityManager em = emf.createEntityManager();
       emf.close();
       
       em.getTransaction().begin();
       em.persist(person);
       em.getTransaction().commit();

       em.close();

       person.setFirstName("John");
       
       return person;
   }

}
