package Given;


import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MfDate extends GregorianCalendar{
    private static final long serialVersionUID = 1L;

    public MfDate(Date date)
    {
	this.setTime(date);
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
    
    public static MfDate today()
    {
    	return new MfDate(GregorianCalendar.getInstance());
    }
    
    public MfDate addDays(int day)
    {
	this.add(MfDate.DATE, day);
	return this;
    }
    
    public static void main(String args[])
    {
	MfDate date = new MfDate(new Date(GregorianCalendar.getInstance().getTimeInMillis()));
	
	System.out.println(date.toSqlDate());
	date.addDays(5);
	System.out.println(date.toSqlDate());
	date.addDays(6);
	System.out.println(date.toSqlDate());
    }
}
