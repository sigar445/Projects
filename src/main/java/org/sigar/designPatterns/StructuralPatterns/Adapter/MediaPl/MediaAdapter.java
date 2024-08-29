package org.sigar.designPatterns.StructuralPatterns.Adapter.MediaPl;

public class MediaAdapter implements MediaPlayer{

    private Mp4Player mp4Player;
    private VlcPlayer vlcPlayer;

    public MediaAdapter(String audioType) {
        if (audioType.equalsIgnoreCase("mp4")) {
            mp4Player = new Mp4Player();
        } else if (audioType.equalsIgnoreCase("vlc")) {
            vlcPlayer = new VlcPlayer();
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("mp4")) {
            mp4Player.play(fileName);
        } else if (audioType.equalsIgnoreCase("vlc")) {
            vlcPlayer.play(fileName);
        }
    }
}
