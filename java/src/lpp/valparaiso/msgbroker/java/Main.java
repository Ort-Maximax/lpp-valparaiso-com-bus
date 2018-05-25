package lpp.valparaiso.msgbroker.java;

import lpp.valparaiso.msgbroker.java.consumer.Receiver;
import lpp.valparaiso.msgbroker.java.queue.Queue;
import lpp.valparaiso.msgbroker.java.sender.Sender;

public class Main {

	public static void main(String[] args) {
		
		Sender sender = new Sender();
		String msg = "toto";
		sender.sendMsg(Queue.QUEUE_NAME_HELLO.getValue(), msg);
		
		Receiver receiver = new Receiver();
		receiver.receiveMsg(Queue.QUEUE_NAME_HELLO.getValue());
		
	}

}
