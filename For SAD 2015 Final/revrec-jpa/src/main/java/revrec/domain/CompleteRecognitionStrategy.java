package revrec.domain;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="C")
public class CompleteRecognitionStrategy extends RecognitionStrategy implements Serializable
{

  public String toString()
  {
    return "[Complete strategy]";
  }

  @Override
  public void calculateRevenueRecognitions(Contract contract)
  {
    contract.addRevenueRecognition(new RevenueRecognition(contract.getRevenue(), contract.getWhenSigned()));
  }

}
