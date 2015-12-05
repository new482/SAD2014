package revrec.domain;

import java.io.Serializable;

import javax.persistence.*;

import revrec.datatype.Money;

@Entity
@DiscriminatorValue(value="T")
public class ThreeWayRecognitionStrategy extends RecognitionStrategy implements Serializable
{
  private int firstRecognitionOffset;
  private int secondRecognitionOffset;
  
  public ThreeWayRecognitionStrategy()
  {
  }
  
  public ThreeWayRecognitionStrategy(int firstRecognitionOffset, int secondRecognitionOffset)
  {
    this.firstRecognitionOffset = firstRecognitionOffset;
    this.secondRecognitionOffset = secondRecognitionOffset;
  }
  
  public int getFirstRecognitionOffset()
  {
    return firstRecognitionOffset;
  }

  public void setFirstRecognitionOffset(int firstRecognitionOffset)
  {
    this.firstRecognitionOffset = firstRecognitionOffset;
  }

  public int getSecondRecognitionOffset()
  {
    return secondRecognitionOffset;
  }

  public void setSecondRecognitionOffset(int secondRecognitionOffset)
  {
    this.secondRecognitionOffset = secondRecognitionOffset;
  }

  public String toString()
  {
    return String.format("[3-way strategy] Offset: %d, %d", this.firstRecognitionOffset, this.secondRecognitionOffset);
  }

  @Override
  public void calculateRevenueRecognitions(Contract contract)
  {
    Money[] allocation = contract.getRevenue().allocate(3);
    
    contract.addRevenueRecognition(new RevenueRecognition(allocation[0], contract.getWhenSigned()));
    contract.addRevenueRecognition(new RevenueRecognition(allocation[1], contract.getWhenSigned().addDays(firstRecognitionOffset)));
    contract.addRevenueRecognition(new RevenueRecognition(allocation[2], contract.getWhenSigned().addDays(secondRecognitionOffset)));
  }

}
