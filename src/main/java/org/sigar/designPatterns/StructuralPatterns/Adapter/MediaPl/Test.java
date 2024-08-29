package org.sigar.designPatterns.StructuralPatterns.Adapter.MediaPl;

public class Test {
    public static void main(String[] args) {
        MediaPlayerClient audioPlayer = new MediaPlayerClient();

        audioPlayer.play("mp3", "beyond_the_horizon.mp3");
        // Output: Playing mp3 file. Name: beyond_the_horizon.mp3

        audioPlayer.play("mp4", "alone.mp4");
        // Output: Playing mp4 file. Name: alone.mp4

        audioPlayer.play("vlc", "far_far_away.vlc");
        // Output: Playing vlc file. Name: far_far_away.vlc

        audioPlayer.play("avi", "mind_me.avi");
    }
}
