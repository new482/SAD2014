import static org.junit.Assert.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.junit.Test;

import asia.ait.sad.cameltest.Person;

public class TestPerson
{

    @Test
    public void test() throws JAXBException
    {
	Person person = new Person();
	person.setCity("City");
	person.setCustomerNumber(123);
	person.setFirstName("Natthapat");
	person.setLastName("Sotthisopha");
	person.setPhone("123456789");
	person.setPostalCode("10120");
	person.setProvince("Bangkok");
	person.setStreet("Charoenkrung road");

	JAXBContext context = JAXBContext.newInstance(Person.class);
	Marshaller m = context.createMarshaller();
	m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

	m.marshal(person, System.out);
    }
}
