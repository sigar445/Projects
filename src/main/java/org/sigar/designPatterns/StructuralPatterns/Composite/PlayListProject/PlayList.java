package org.sigar.designPatterns.StructuralPatterns.Composite.PlayListProject;

import lombok.AllArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
public class PlayList implements Component{
    String playListName;
    ArrayList<Component> songs;

    @Override
    public void play() {
        System.out.println("Playing playlist "  + getName());
        songs.forEach(Component::play);
    }

    @Override
    public String getName() {
        return playListName;
    }

    public void addSong(Component song){
        songs.add(song);
    }
    public void removeSong(Component song){
        songs.remove(song);
    }

}
