package com.example.videodemo;

import android.content.Context;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.VideoView;
import android.media.MediaPlayer;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mPlayer;
    AudioManager audioManager;
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

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);


        SeekBar volumeControl = (SeekBar) findViewById(R.id.seekBar);
        volumeControl.setMax(maxVolume);
        volumeControl.setProgress(currVolume);

        final SeekBar mediaControl = (SeekBar) findViewById(R.id.seekBar2);
        mediaControl.setMax(mPlayer.getDuration());

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                //run this code immediately and every second
                mediaControl.setProgress(mPlayer.getCurrentPosition());
            }
        },0,100);

        mediaControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mPlayer.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mPlayer.pause();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mPlayer.start();

            }
        });

        volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                System.out.println("Seeker Value" + progress);
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
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
