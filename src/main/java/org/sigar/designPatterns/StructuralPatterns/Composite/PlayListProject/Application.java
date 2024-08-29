package org.sigar.designPatterns.StructuralPatterns.Composite.PlayListProject;

import java.util.ArrayList;
import java.util.Arrays;

public class Application {
    public static void main(String[] args) {
        ArrayList<Component> songs = new ArrayList<>(Arrays.asList(
                    new Song("Hey Jude","Beatles"),
                    new Song("Never Walk Alone","Johny")));

        PlayList list = new PlayList("OldSongs",songs);

//        PlayList tier1 = new PlayList("Old",songs);
//        list.addSong(tier1);
        list.addSong(new Song("sdf","sfdds"));

        list.play();


    }
}
