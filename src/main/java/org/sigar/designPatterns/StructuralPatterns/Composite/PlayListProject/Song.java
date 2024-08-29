package org.sigar.designPatterns.StructuralPatterns.Composite.PlayListProject;


import lombok.AllArgsConstructor;

//@Setter
@AllArgsConstructor
public class Song implements Component{

    String songName;
    String artist;
    @Override
    public void play() {
        System.out.println("Playing " + getName());
    }

    @Override
    public String getName() {
        return songName + " By " + artist ;
    }
}
