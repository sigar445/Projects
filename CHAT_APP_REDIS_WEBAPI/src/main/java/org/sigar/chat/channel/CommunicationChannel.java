package org.sigar.chat.channel;

public interface    CommunicationChannel {
    public void connect();
    public void disconnect();
    public void sendMessage(String destination, String message);
    public void receiveMessage(String source, MessageHandler messageHandler);
}




//    default String requestResponse(String destination, String request) {
//        throw new UnsupportedOperationException("Request-response not supported by this channel");
//    }
