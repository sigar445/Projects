package org.sigar.channel;

public interface CommunicationChannel {
    public void connect();
    public void disconnect();
    public void sendMessage(String message);
    public void recieveMessage();
}
