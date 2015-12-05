package revrec.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import revrec.datatype.*;

@Entity
public class RevenueRecognition implements Serializable
{
  private static final long serialVersionUID = 1L;

  @Id @GeneratedValue
  private long id;
  
  @Embedded
  private Money amount;
  @Transient
  private MfDate date;
  @Temporal(TemporalType.DATE)
  private Date sqlDate;
  
  public RevenueRecognition()
  {
  }

  public RevenueRecognition(Money amount, MfDate date)
  {
    this.amount = amount;
    this.date = date;
    this.sqlDate = date.toSqlDate();
  }
  
  public RevenueRecognition(Money amount, Date sqlDate)
  {
    this(amount, new MfDate(sqlDate));
  }
  
  @PostLoad
  public void setDateFromSqlDate()
  {
    this.date = new MfDate(this.sqlDate);
  }

  public Money getAmount()
  {
    return this.amount;
  }
  
  public void setAmount(Money amount)
  {
    this.amount = amount;
  }

  public MfDate getDate()
  {
    return this.date;
  }
  
  public void setDate(MfDate date)
  {
    this.date = date;
    this.sqlDate = date.toSqlDate();
  }
  
  public Date getSqlDate()
  {
    return this.sqlDate;
  }
  
  public void setSqlDate(Date date)
  {
    this.sqlDate = date;
    this.date = new MfDate(this.sqlDate);
  }
  
  public boolean isRecognizableBy(MfDate asOf)
  {
    return asOf.after(date) || asOf.equals(date);
  }

  public String toString()
  {
    return String.format("[RevenueRecognition] Amount: %s Date: %s", amount, date.toSqlDate());
  }
}
