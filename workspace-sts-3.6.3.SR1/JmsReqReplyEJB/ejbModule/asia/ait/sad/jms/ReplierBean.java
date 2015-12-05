package asia.ait.sad.jms;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * Message-Driven Bean implementation class for: ReplierBean
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue"), @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "jms/RequestQueue")
		}, 
		mappedName = "jms/RequestQueue")
public class ReplierBean implements MessageListener {
	@Resource(name = "jms/ConnectionFactory")
	  private ConnectionFactory connectionFactory;

	  private static final String INVALID_QUEUE_NAME = "jms/InvalidQueue";

	  public void onMessage(Message message) {
	      try {
	          // Get JMS connection and session
	          Connection connection = connectionFactory.createConnection();
	          Session session = connection.createSession(true, 0);

	          // Check validity of message
	          if ((message instanceof TextMessage)
	                  && (message.getJMSReplyTo() != null)) {
	              TextMessage requestMessage = (TextMessage) message;

	              System.out.println("Received request");
	              System.out.println("   Time:       "
	                      + System.currentTimeMillis() + " ms");
	              System.out.println("   Message ID: "
	                      + requestMessage.getJMSMessageID());
	              System.out.println(" Correl. ID: "
	                      + requestMessage.getJMSCorrelationID());
	              System.out.println(" Reply to:   "
	                      + requestMessage.getJMSReplyTo());
	              System.out
	                  .println("   Contents:   " + requestMessage.getText());

	              String contents = requestMessage.getText();
	              Destination replyDestination = message.getJMSReplyTo();
	              MessageProducer replyProducer = session
	                      .createProducer(replyDestination);
	              TextMessage replyMessage = session.createTextMessage();
	              replyMessage.setText(contents);
	              replyMessage.setJMSCorrelationID(requestMessage
	                      .getJMSMessageID());
	              replyProducer.send(replyMessage);

	              System.out.println("Sent reply");
	              System.out.println("  Time:       "
	                      + System.currentTimeMillis() + " ms");
	              System.out.println("   Message ID: "
	                      + replyMessage.getJMSMessageID());
	              System.out.println("   Correl. ID: "
	                      + replyMessage.getJMSCorrelationID());
	              System.out.println("   Reply to:   "
	                      + replyMessage.getJMSReplyTo());
	              System.out.println("  Contents:   " + replyMessage.getText());
	          } else {
	              System.out.println("Invalid message detected");
	              System.out.println("    Type:       "
	                      + message.getClass().getName());
	              System.out.println("  Time:       "
	                      + System.currentTimeMillis() + " ms");
	              System.out.println("   Message ID: "
	                      + message.getJMSMessageID());
	              System.out.println("   Correl. ID: "
	                      + message.getJMSCorrelationID());
	              System.out.println(" Reply to:   " + message.getJMSReplyTo());

	              Destination invalidDestination = session
	                      .createQueue(INVALID_QUEUE_NAME);
	              MessageProducer invalidProducer = session
	                      .createProducer(invalidDestination);
	              TextMessage textMessage = session.createTextMessage();
	              textMessage.setText("Invalid message received");
	              textMessage.setJMSCorrelationID(message.getJMSMessageID());
	              invalidProducer.send(textMessage);

	              System.out.println("Sent to invalid message queue");
	              System.out.println("  Type:       "
	                      + message.getClass().getName());
	              System.out.println("  Time:       "
	                      + System.currentTimeMillis() + " ms");
	              System.out.println("   Message ID: "
	                      + message.getJMSMessageID());
	              System.out.println("   Correl. ID: "
	                      + message.getJMSCorrelationID());
	              System.out.println(" Reply to:   " + message.getJMSReplyTo());
	          }
	          session.close();
	          connection.close();
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	  }

}
