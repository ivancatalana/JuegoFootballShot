package com.example.juegofootballshot;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class VideoPlayer extends MediaView {

    private MediaPlayer mediaPlayer;

    public VideoPlayer(String videoFile) {
        Media media = new Media(getClass().getResource(videoFile).toString());
        mediaPlayer = new MediaPlayer(media);
        setMediaPlayer(mediaPlayer);
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
    }

    public void play() {
        mediaPlayer.play();
    }

    public void stop() {
        mediaPlayer.stop();
    }
}