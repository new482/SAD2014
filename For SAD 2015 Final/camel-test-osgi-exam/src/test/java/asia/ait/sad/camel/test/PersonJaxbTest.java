package asia.ait.sad.camel.test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.junit.Test;

import asia.ait.sad.cameltest.Person;

public class PersonJaxbTest {

	@Test
	public void test() throws JAXBException {
		Person person = new Person();
		person.setCity("Bangkok");
		person.setCustomerNumber(123);
		person.setFirstName("Matt");
		person.setLastName("Daily");
		person.setPhone("1234567");
		person.setPostalCode("10510");
		person.setProvince("Pathumthani");
		person.setStreet("P.O. Box 4, Klong Luang");

		JAXBContext context = JAXBContext.newInstance(Person.class);

		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		m.marshal(person, System.out);
	}

}
