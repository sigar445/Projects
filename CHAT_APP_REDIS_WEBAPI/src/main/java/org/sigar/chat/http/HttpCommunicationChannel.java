package org.sigar.chat.http;

import org.sigar.chat.channel.CommunicationChannel;
import org.sigar.chat.channel.MessageHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
@ConditionalOnProperty(name = "chat.communication.channel", havingValue = "http")
public class HttpCommunicationChannel implements CommunicationChannel {
    private final RestTemplate restTemplate;
    @Value("${chat.http.base-url}")
    private String baseUrl;
    public HttpCommunicationChannel(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void connect() {
        System.out.println("HTTP channel connected");
    }

    @Override
    public void disconnect() {
        System.out.println("HTTP channel disconnected");
    }

    @Override
    public void sendMessage(String destination, String message) {
        String url = String.format("%s/%s", baseUrl, destination); // Construct the full URL
        System.out.printf("Sending %s to %s",message,url);
        restTemplate.postForObject(url, message, Void.class);
    }

    @Override
    public void receiveMessage(String source, MessageHandler messageHandler) {
       // throw new UnsupportedOperationException("HTTP does not support continuous message subscription");
        return;
    }
}
