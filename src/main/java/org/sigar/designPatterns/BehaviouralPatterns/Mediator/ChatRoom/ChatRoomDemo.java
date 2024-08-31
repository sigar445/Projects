package org.sigar.designPatterns.BehaviouralPatterns.Mediator.ChatRoom;

public class ChatRoomDemo {
    public static void main(String[] args) {
        chatRoom();
    }
    public static void chatRoom(){

        ChatRoom chatRoom = new ChatRoom();
        User tim = new ChatUser(chatRoom,"Tim");

        User ron = new ChatUser(chatRoom,"Ron");
        User harry = new ChatUser(chatRoom,"Harry");
        User jim = new ChatUser(chatRoom,"Jim");
        User pam = new ChatUser(chatRoom,"Pam");

        chatRoom.addUser(tim);


        chatRoom.addUser(jim);

        chatRoom.addUser(pam);

        chatRoom.addUser(harry);

        chatRoom.addUser(ron);

        ron.send("Sending Hello from ron");

    }
}
