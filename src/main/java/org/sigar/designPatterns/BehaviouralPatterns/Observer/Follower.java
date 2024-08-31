package org.sigar.designPatterns.BehaviouralPatterns.Observer;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Follower implements Observer{

    private String name;
    @Override
    public void update(String status) {
        System.out.println(name + " received update: " + status);
        play(); // Simulate the follower reacting to the update
    }

    public void play(){
        System.out.println(name + " is playing the latest content");
    };

}
