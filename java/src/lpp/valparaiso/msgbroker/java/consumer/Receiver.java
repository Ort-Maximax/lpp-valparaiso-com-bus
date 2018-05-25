package lpp.valparaiso.msgbroker.java.consumer;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import lpp.valparaiso.msgbroker.java.ConfigurationManager;

public class Receiver {

	private ConnectionFactory factory;
	
	public Receiver() {
		
		factory = new ConnectionFactory();
		String host = ConfigurationManager
				.getInstance()
				.getProperties()
				.getProperty(ConfigurationManager.RMQHOST_PROP);
		factory.setHost(host);
		
	}
	
	public void receiveMsg(String queueName) {
		
		try {
			Connection connection = factory.newConnection();
		    Channel channel = connection.createChannel();
	
		    channel.queueDeclare(queueName, false, false, false, null);
		    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
	
		    Consumer consumer = new DefaultConsumer(channel) {
		    	
		    	@Override
			      public void handleDelivery(String consumerTag, Envelope envelope,
			    		  AMQP.BasicProperties properties, byte[] body)
			          throws IOException {
			    	  
			        String message = new String(body, "UTF-8");
			        System.out.println(" [x] Received '" + message + "'");
			        
			      }
		      
		    };
			channel.basicConsume(queueName, true, consumer);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
	    
	}
	
}
