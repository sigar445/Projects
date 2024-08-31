package org.sigar.designPatterns.BehaviouralPatterns.Observer;

public class ChannelDemo {
        public static void main(String[] args) {
            Channel channel = new Channel("Tech Insights");

            Observer follower1 = new Follower("Alice");
            Observer follower2 = new Follower("Bob");
            Observer follower3 = new Follower("Charlie");

            channel.register(follower1);
            channel.register(follower2);
            channel.register(follower3);

            // Simulate a new update on the channel
            channel.setStatus("New video uploaded: 'Observer Pattern Explained'");

            // Simulate removing an observer
            channel.remove(follower2);

            // Another update on the channel
            channel.setStatus("Live stream starting: 'Design Patterns Deep Dive'");
        }

}
