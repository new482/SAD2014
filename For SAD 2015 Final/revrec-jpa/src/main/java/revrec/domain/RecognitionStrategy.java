package revrec.domain;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="RECOGNITION_STRATEGIES")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="STRATEGY_TYPE")
public abstract class RecognitionStrategy implements Serializable
{
  private static final long serialVersionUID = 1L;
  
  @Id @GeneratedValue
  private long id;

  public long getId()
  {
    return this.id;
  }
  
  public void setId(long id)
  {
    this.id = id;
  }
  
  public abstract void calculateRevenueRecognitions(Contract contract);
}
