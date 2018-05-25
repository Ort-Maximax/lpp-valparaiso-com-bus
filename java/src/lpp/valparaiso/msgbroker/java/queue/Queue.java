package lpp.valparaiso.msgbroker.java.queue;

public enum Queue {
	
	QUEUE_NAME_HELLO ("helloworld");
	
	private String value = "";
	
	Queue(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
