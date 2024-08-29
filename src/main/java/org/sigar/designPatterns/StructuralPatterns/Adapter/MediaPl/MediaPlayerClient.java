package org.sigar.designPatterns.StructuralPatterns.Adapter.MediaPl;

public class MediaPlayerClient implements MediaPlayer{

        private MediaAdapter mediaAdapter;

        @Override
        public void play(String audioType, String fileName) {
            // Inbuilt support to play mp3 music files
            if (audioType.equalsIgnoreCase("mp3")) {
                System.out.println("Playing mp3 file. Name: " + fileName);
            }
            // MediaAdapter is providing support to play other file formats
            else if (audioType.equalsIgnoreCase("mp4") || audioType.equalsIgnoreCase("vlc")) {
                if(mediaAdapter == null) {
                    mediaAdapter = new MediaAdapter(audioType);
                }
                mediaAdapter.play(audioType, fileName);
            } else {
                System.out.println("Invalid media. " + audioType + " format not supported");
            }
        }
    }


