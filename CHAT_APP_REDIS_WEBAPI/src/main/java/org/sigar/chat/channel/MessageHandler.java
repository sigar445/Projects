package org.sigar.chat.channel;

@FunctionalInterface
public interface MessageHandler {
    void handleMessage(String source, String message);
}
