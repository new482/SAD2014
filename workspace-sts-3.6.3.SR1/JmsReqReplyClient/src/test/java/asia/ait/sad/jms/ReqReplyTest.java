package asia.ait.sad.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class ReqReplyTest {
   protected String jmsServerUrl = "tcp://0.0.0.0:61616";

   private static final String REQUEST_QUEUE_NAME = "jms/RequestQueue";
   private static final String REPLY_QUEUE_NAME = "jms/ReplyQueue";
   private static final String INVALID_QUEUE_NAME = "jms/InvalidQueue";

   private Connection connection;
   private Session session;
   private Destination replyQueue;
   private Destination invalidQueue;
   private MessageProducer requestProducer;
   private MessageConsumer replyConsumer;
   private MessageConsumer invalidConsumer;

   // Setup run before every test case

   @Before
   public void setUp() throws JMSException {
      // Get connection factory for the local JMS server
      ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
            jmsServerUrl);

      // Get connection to the JMS server
      System.out.println("Creating connection");
      connection = connectionFactory.createConnection();

      // Create a session on the connection that is not transacted
      System.out.println("Creating session");
      session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

      // Set up a message producer or consumer for each queue
      Destination requestQueue = session.createQueue(REQUEST_QUEUE_NAME);
      requestProducer = session.createProducer(requestQueue);
      replyQueue = (Destination) session.createQueue(REPLY_QUEUE_NAME);
      replyConsumer = session.createConsumer(replyQueue);
      invalidQueue = (Destination)session.createQueue(INVALID_QUEUE_NAME);
      invalidConsumer = session.createConsumer(invalidQueue);

      // Enable receipt of messages on this connection
      connection.start();

      // Clear any stale messages in the queues
      clearQueue(invalidConsumer);
      clearQueue(replyConsumer);
   }

   // Test the request-reply pattern

   @Test
   public void testReqReply() throws JMSException {

      // Send a message using the request queue
      TextMessage requestMessage = session.createTextMessage();
      requestMessage.setText("Hello world.");
      requestMessage.setJMSReplyTo(replyQueue);
      requestProducer.send(requestMessage);

      // Output sent message
      System.out.println("Sent request");
      System.out.println("    Time:       " + System.currentTimeMillis()
            + " ms");
      System.out.println("    Message ID: " + requestMessage.getJMSMessageID());
      System.out.println("    Correl. ID: "
            + requestMessage.getJMSCorrelationID());
      System.out.println("    Reply to:   " + requestMessage.getJMSReplyTo());
      System.out.println("    Contents:   " + requestMessage.getText());

      // Block until a reply is received on the reply queue
      System.out.println("Waiting for response...");
      Message msg = replyConsumer.receive();
      if (!(msg instanceof TextMessage)) {
         System.out.println("Got message: " + msg);
      }
      Assert.assertTrue(msg instanceof TextMessage);

      // Output the reply message
      TextMessage replyMessage = (TextMessage) msg;
      System.out.println("Received reply ");
      System.out.println("    Time:       " + System.currentTimeMillis()
            + " ms");
      System.out.println("    Message ID: " + replyMessage.getJMSMessageID());
      System.out.println("    Correl. ID: "
            + replyMessage.getJMSCorrelationID());
      System.out.println("    Reply to:   " + replyMessage.getJMSReplyTo());
      System.out.println("    Contents:   " + replyMessage.getText());
   }

   // Test invalid message

   @Test
   public void testInvalid() throws JMSException {

      // Send a message using the request queue
      ObjectMessage requestMessage = session.createObjectMessage();
      requestMessage.setObject("Hello world.");
      requestMessage.setJMSReplyTo(replyQueue);
      requestProducer.send(requestMessage);

      // Output sent message
      System.out.println("Sent request");
      System.out.println("    Time:       " + System.currentTimeMillis()
            + " ms");
      System.out.println("    Message ID: " + requestMessage.getJMSMessageID());
      System.out.println("    Correl. ID: "
            + requestMessage.getJMSCorrelationID());
      System.out.println("    Reply to:   " + requestMessage.getJMSReplyTo());
      System.out.println("    Contents:   " + requestMessage.getObject());

      // Block until a message is received on the invalid message queue
      System.out.println("Waiting for response...");
      Message msg = invalidConsumer.receive();
      if (!(msg instanceof TextMessage)) {
         System.out.println("Got message: " + msg);
      }
      Assert.assertTrue(msg instanceof TextMessage);

      // Output the reply message
      TextMessage invalidMessage = (TextMessage) msg;
      System.out.println("Received reply ");
      System.out.println("    Time:       " + System.currentTimeMillis()
            + " ms");
      System.out.println("    Message ID: " + invalidMessage.getJMSMessageID());
      System.out.println("    Correl. ID: "
            + invalidMessage.getJMSCorrelationID());
      System.out.println("    Reply to:   " + invalidMessage.getJMSReplyTo());
      System.out.println("    Contents:   " + invalidMessage.getText());
   }

   @After
   public void tearDown() throws JMSException {
      System.out.println("Closing connection.");
      connection.close();
   }

   // Consume and discard all messages from a given queue, to ensure it is
   // clean

   private void clearQueue(MessageConsumer consumer) throws JMSException {
      Message msg;
      do {
         System.out.println("Checking for stale message");
         msg = consumer.receive(1);
         if (msg != null) {
            System.out.println("Got message " + msg);
         }
      } while (msg != null);
   }
}
