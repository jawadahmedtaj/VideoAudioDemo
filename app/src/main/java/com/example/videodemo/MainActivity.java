package com.example.videodemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;
import android.media.MediaPlayer;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mPlayer;
    public void playAudio (View view){


        mPlayer.start();

    }

    public void pauseAudio (View view){
        mPlayer.pause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPlayer = MediaPlayer.create(this, R.raw.laugh);
    /*
        Video code
        VideoView videoview = (VideoView) findViewById(R.id.videoView);

        videoview.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.videoplayback);

        MediaController mediacontroller = new MediaController(this);

        mediacontroller.setAnchorView(videoview);

        videoview.setMediaController(mediacontroller);

        videoview.start();
        */

    }
}
