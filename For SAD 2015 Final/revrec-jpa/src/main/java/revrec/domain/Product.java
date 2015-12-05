package revrec.domain;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.persistence.*;

import revrec.service.EMService;

@Entity
@Table(name = "PRODUCTS")
public class Product implements Serializable
{
  private static final long serialVersionUID = 1L;

  private static final Logger logger = Logger.getLogger("Product");

  @Id
  @GeneratedValue
  private long productId;
  private String name;
  private String type;

  @ManyToOne(cascade=CascadeType.PERSIST)
  private RecognitionStrategy recognitionStrategy;

  @Transient
  public static final String W_TYPE = "W";
  @Transient
  public static final String S_TYPE = "S";
  @Transient
  public static final String D_TYPE = "D";

  public Product()
  {
  }

  public Product(String name, String type)
  {
    this.name = name;
    this.type = type;
    
    this.setRecognitionStrategyFromType();
  }

  public long getProductId()
  {
    return productId;
  }

  public void setProductId(long productId)
  {
    this.productId = productId;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getType()
  {
    return type;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public RecognitionStrategy getRecognitionStrategy()
  {
    return recognitionStrategy;
  }

  public void setRecognitionStrategy(RecognitionStrategy recognitionStrategy)
  {
    this.recognitionStrategy = recognitionStrategy;
  }

  private void setRecognitionStrategyFromType()
  {
    String _type = this.type.toUpperCase();
    if (_type.equals("S"))
    {
      this.recognitionStrategy = new ThreeWayRecognitionStrategy(60, 90);
    }
    else if (_type.equals("W"))
    {
      this.recognitionStrategy = new CompleteRecognitionStrategy();
    }
    else if (_type.equals("D"))
    {
      this.recognitionStrategy = new ThreeWayRecognitionStrategy(30, 60);
    }
  }
  
  public void calculateRevenueRecognitions(Contract contract)
  {
    recognitionStrategy.calculateRevenueRecognitions(contract);
  }
  
  public void save(EntityManager em)
  {
    RecognitionStrategy strategy = this.getRecognitionStrategy();

    // An attempt to reuse existing revenue recognition strategy
    if (strategy instanceof CompleteRecognitionStrategy)
    {
      Query query = em.createQuery("SELECT s FROM RecognitionStrategy s WHERE TYPE(s) = :type");
      query.setParameter("type", CompleteRecognitionStrategy.class);
      RecognitionStrategy _strategy = null;

      try
      {
        _strategy = (RecognitionStrategy) query.getSingleResult();
      }
      catch (NoResultException e)
      {
      }

      if (_strategy != null)
      {
        this.setRecognitionStrategy(_strategy);
      }
    }
    else if (strategy instanceof ThreeWayRecognitionStrategy)
    {
      ThreeWayRecognitionStrategy _twStrategy = (ThreeWayRecognitionStrategy) strategy;

      Query query = em.createQuery("SELECT s FROM ThreeWayRecognitionStrategy s WHERE s.firstRecognitionOffset = :firstOffset AND s.secondRecognitionOffset = :secondOffset");
      query.setParameter("firstOffset", _twStrategy.getFirstRecognitionOffset());
      query.setParameter("secondOffset", _twStrategy.getSecondRecognitionOffset());

      RecognitionStrategy _strategy = null;

      try
      {
        _strategy = (RecognitionStrategy) query.getSingleResult();
      }
      catch (NoResultException e)
      {
      }

      if (_strategy != null)
      {
        this.setRecognitionStrategy(_strategy);
      }
    }

    em.persist(this);
  }
  
  
  public static Product find(EntityManager em, long productId)
  {
    Product product = (Product) em.find(Product.class, productId);
    return product;
  }

  public String toString()
  {
    return String.format("[Product] ID: %d, Name: %s, Type: %s, RecognitionStrategy: %s", this.productId, this.name, this.type, this.recognitionStrategy);
  }

}
