package revrec.datatype;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;

// Objects of MfDate class should be immutable.
public class MfDate extends GregorianCalendar implements Serializable
{
  private static final long serialVersionUID = 1L;

  public MfDate(Date date)
  {
    if (date != null)
      this.setTimeInMillis(date.getTime());
  }
  
  public MfDate(java.util.Date date)
  {
    this.setTimeInMillis(date.getTime());
  }

  public MfDate(long date)
  {
    this.setTimeInMillis(date);
  }

  public MfDate(GregorianCalendar date)
  {
    this.setTimeInMillis(date.getTimeInMillis());
  }

  public MfDate(Calendar calendar)
  {
    this.setTimeInMillis(calendar.getTimeInMillis());
  }

  public Date toSqlDate()
  {
    return new Date(this.getTimeInMillis());
  }

  public static MfDate now()
  {
    return new MfDate(GregorianCalendar.getInstance());
  }

  public MfDate addDays(int day)
  {
    // MfDate is a mutable datatype!
    MfDate newDate = new MfDate(new Date(this.getTimeInMillis()));
    newDate.add(MfDate.DATE, day);
    return newDate;
  }
  
  @Override
  public String toString()
  {
    return this.toSqlDate().toString();
  }
}