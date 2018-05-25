package lpp.valparaiso.msgbroker.java.sender;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import lpp.valparaiso.msgbroker.java.ConfigurationManager;

public class Sender {
	
	private ConnectionFactory factory;
	
	public Sender () {
		
		factory = new ConnectionFactory();
		String host = ConfigurationManager
				.getInstance()
				.getProperties()
				.getProperty(ConfigurationManager.RMQHOST_PROP);
		factory.setHost(host);
		
	}
	
	public void sendMsg(String queueName, String msg) {
		try {
			
			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();
			
			channel.queueDeclare(queueName, false, false, false, null);
			channel.basicPublish("", queueName, null, msg.getBytes());
			
			System.out.println(" [x] Sent > " + msg + " <");
			
			channel.close();
			connection.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
	}
	
}
