package revrec.datatype;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

import javax.persistence.*;

@Embeddable
public class Money implements Comparable<Money>, Serializable
{
  private static final long serialVersionUID = 1L;
  
  private Currency currency;
  private BigDecimal amount;

  @Transient
  public final static int MONEY_SCALE = 2;
  @Transient
  private static Currency currencyDollar = Currency.getInstance("USD");
  
  public Money()
  {
  }

  private Money(Currency currency, BigDecimal amount)
  {
    this.currency = currency;
    this.amount = amount.setScale(MONEY_SCALE, RoundingMode.CEILING);
  }

  public static Money dollars(BigDecimal amount)
  {
    return new Money(currencyDollar, amount);
  }

  public static Money dollars(double amount)
  {
    return new Money(currencyDollar, new BigDecimal(doubleToTwoDigitString(amount)));
  }

  public static Money dollars(String amount)
  {
    return new Money(currencyDollar, new BigDecimal(amount));
  }

  public Money add(Money money)
  {
    return Money.dollars(amount.add(money.getAmount()));
  }

  public Money add(double amount)
  {
    return Money.dollars(this.amount.add(BigDecimal.valueOf(amount)));
  }

  public Money add(BigDecimal amount)
  {
    return Money.dollars(this.amount.add(amount));
  }

  public Money[] allocate(int times)
  {
    Money[] allocations = new Money[times];

    BigDecimal dividedAmount = amount.divide(new BigDecimal(times), 0, RoundingMode.HALF_EVEN);
    for (int i = 0; i < allocations.length - 1; ++i)
    {
      allocations[i] = Money.dollars(dividedAmount);
    }

    allocations[allocations.length - 1] = Money.dollars(amount.subtract(dividedAmount.multiply(new BigDecimal(times - 1))));

    return allocations;
  }

  public BigDecimal getAmount()
  {
    // BigDecimal is immutable, so it's OK to return as is.
    return amount;
  }

  public Currency getCurrency()
  {
    return currency;
  }

  public String toString()
  {
    return String.format("%s %s", amount.toString(), currency);
  }

  private static String doubleToTwoDigitString(double number)
  {
    String formatString = String.format("%%.%sf", String.format("%d", Money.MONEY_SCALE));
    return String.format(formatString, number);
  }

  @Override
  public int compareTo(Money money)
  {
    return this.amount.setScale(MONEY_SCALE, RoundingMode.HALF_EVEN).compareTo(money.amount.setScale(MONEY_SCALE, RoundingMode.HALF_EVEN));
  }

  public boolean equals(Money money)
  {
    return this.compareTo(money) == 0;
  }
  
  public static void main(String[] args)
  {
    Money m = Money.dollars(100);
    
    Money[] a = m.allocate(3);
    for (Money money : a)
    {
      System.out.println(money);
    }
  }

}
