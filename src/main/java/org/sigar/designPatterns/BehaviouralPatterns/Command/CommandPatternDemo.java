package org.sigar.designPatterns.BehaviouralPatterns.Command;

public class CommandPatternDemo {
    public static void main(String[] args) {
        // Create Receiver objects
        Light livingRoomLight = new Light("Living Room");
        Fan bedroomFan = new Fan("Bedroom");

        // Create Command objects
        Command livingRoomLightOn = new LightOnCommand(livingRoomLight);
        Command livingRoomLightOff = new LightOffCommand(livingRoomLight);
        Command bedroomFanOn = new FanOnCommand(bedroomFan);
        Command bedroomFanOff = new FanOffCommand(bedroomFan);

        // Create Invoker object
        RemoteControl remote = new RemoteControl(2); // Two slots for two devices

        // Set Commands to the remote slots
        remote.setCommand(0, livingRoomLightOn, livingRoomLightOff);
        remote.setCommand(1, bedroomFanOn, bedroomFanOff);

        // Simulate button presses
        remote.pressOnButton(0); // Turns on the living room light
        remote.pressOffButton(0); // Turns off the living room light
        remote.pressUndoButton(); // Undo: Turns on the living room light again

        remote.pressOnButton(1); // Turns on the bedroom fan
        remote.pressOffButton(1); // Turns off the bedroom fan
        remote.pressUndoButton(); // Undo: Turns on the bedroom fan again
    }
}
