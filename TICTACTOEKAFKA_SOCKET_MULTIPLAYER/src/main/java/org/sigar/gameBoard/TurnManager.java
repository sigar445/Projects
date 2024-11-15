package org.sigar.gameBoard;

import org.sigar.model.player.Player;

import java.util.ArrayDeque;
import java.util.Deque;

public class TurnManager {
    Deque<Player> players;
    public TurnManager(){
        players = new ArrayDeque<>();
    }
    public void addPlayer(Player player){
        players.addLast(player);
    }
    public Player getCurrentPlayer(){
        if(players != null &&  players.size() >=1){
            return players.getFirst();
        }
        return null;
    }
    public void switchTurn() {
        if (players != null && players.size() >= 2) {
            addPlayer(players.pollFirst());
        }
    }


}
