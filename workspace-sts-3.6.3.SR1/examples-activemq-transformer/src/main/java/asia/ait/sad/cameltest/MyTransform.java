package asia.ait.sad.cameltest;
import java.util.Date;
import java.util.logging.Logger;

public class MyTransform
{
   private static final transient Logger logger = Logger
      .getLogger(MyTransform.class.getName());

   public Object transform(Object body)
   {
	   Person person = (Person)body;
       person.setFirstName("POP");
       String answer = "MyTransform set body:  " + new Date();
       logger.info("Wow >>>> " + answer);
       return person;
	   
	   
	   
      //String answer = "MyTransform set body:  " + new Date();
      //logger.info(">>>> " + answer);
      //return answer;
   }
}