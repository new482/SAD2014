package revrec.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

import revrec.datatype.*;

@Entity
public class Contract implements Serializable
{
  private static final long serialVersionUID = 1L;
  
  @Id @GeneratedValue
  private long contractId;
  @ManyToOne(cascade=CascadeType.MERGE)
  private Product product;
  @Embedded
  private Money revenue;
  
  @OneToMany(cascade={CascadeType.ALL},fetch=FetchType.EAGER)
  private Collection<RevenueRecognition> recognitions = new ArrayList<RevenueRecognition>();
  
  @Transient
  private MfDate whenSigned;
  @Temporal(TemporalType.DATE)
  private Date sqlDateWhenSigned;
  
  public Contract()
  {
  }
  
  @PostLoad
  public void setMfDateFromSqlDate()
  {
    this.whenSigned = new MfDate(this.sqlDateWhenSigned);
  }
  
  public Contract(Product product, Money revenue, MfDate whenSigned)
  {
    this.product = product;
    this.revenue = revenue;
    this.whenSigned = whenSigned;
    this.sqlDateWhenSigned = this.whenSigned.toSqlDate();
  }
  
  public Contract(Product product, Money revenue, Date sqlDateWhenSigned)
  {
    this(product, revenue, new MfDate(sqlDateWhenSigned));
  }

  public long getContractId()
  {
    return contractId;
  }

  public void setContractId(long contractId)
  {
    this.contractId = contractId;
  }

  public Product getProduct()
  {
    return product;
  }

  public void setProduct(Product product)
  {
    this.product = product;
  }

  public Money getRevenue()
  {
    return revenue;
  }

  public void setRevenue(Money revenue)
  {
    this.revenue = revenue;
  }

  public MfDate getWhenSigned()
  {
    return whenSigned;
  }

  public void setWhenSigned(MfDate whenSigned)
  {
    this.whenSigned = whenSigned;
    this.sqlDateWhenSigned = whenSigned.toSqlDate();
  }
  
  public Date getSqlDateWhenSigned()
  {
    return sqlDateWhenSigned;
  }

  public void setSqlDateWhenSigned(Date sqlDateWhenSigned)
  {
    this.sqlDateWhenSigned = sqlDateWhenSigned;
    this.whenSigned = new MfDate(sqlDateWhenSigned);
  }
  
  public Collection<RevenueRecognition> getRecognitions()
  {
    return recognitions;
  }

  public void setRecognitions(Collection<RevenueRecognition> recognitions)
  {
    this.recognitions = recognitions;
  }
  
  public void calculateRecognitions()
  {
    product.calculateRevenueRecognitions(this);
  }
  
  public void addRevenueRecognition(RevenueRecognition recognition)
  {
    this.recognitions.add(recognition);
  }
  
  public void save(EntityManager em)
  {
    em.persist(this);
  }
  
  public static Contract find(EntityManager em, long contractNumber)
  {
    Contract contract = (Contract) em.find(Contract.class, contractNumber);
    return contract;
  }
  
  public String toString()
  {
    String result = String.format("[Contract] ID: %d, Revenue: %s, WhenSigned: %s, Product: %s\n", this.contractId, this.revenue, this.whenSigned, this.product);
    for (RevenueRecognition r : recognitions)
    {
      result += r.toString() + "\n";
    }
    return result;
  }
}
