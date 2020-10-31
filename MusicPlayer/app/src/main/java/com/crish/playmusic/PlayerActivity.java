package com.crish.playmusic;

import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.crish.playmusic.BackgroundThread.LooperPreparedListener;
import com.crish.playmusic.Models.MusicFiles;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import static com.crish.playmusic.MainActivity.musicFiles;

public class PlayerActivity extends AppCompatActivity implements LooperPreparedListener {
    static ArrayList<MusicFiles> listOfSongs = new ArrayList<>();
    static Uri uri;
    static MediaPlayer mediaPlayer;
    TextView songName, artistName, durationPlayed, durationTotal;
    ImageView coverArt;
    ImageButton nextBtn, prevBtn, backBtn, shuffleBtn, repeatbtn;
    FloatingActionButton playPauseBtn;
    SeekBar seekBar;
    boolean isLooperPrepared;
    private Thread playThread,prevThread,nextThread;

    int position = -1;
    private Handler handler = new Handler();

    @Override
    protected void onResume() {
        playMusic();
        prevMusic();
        nextMusic();
        super.onResume();


    }

    private void nextMusic() {
    }

    private void prevMusic() {
    }

    private void playMusic() {
        playThread = new Thread(){
            @Override
            public void run() {
                super.run();
                playPauseBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        playPause();
                    }
                });
            }
        };
        playThread.start();
    }

    private void playPause() {
        if (mediaPlayer.isPlaying()){
            playPauseBtn.setImageResource(R.drawable.ic_play);
            mediaPlayer.pause();
            seekBar.setMax(mediaPlayer.getDuration()/1000);
            PlayerActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mediaPlayer != null) {
                        int mCurrent = mediaPlayer.getCurrentPosition() / 1000;
                        seekBar.setProgress(mCurrent);

                    }
                    handler.postDelayed(this,1000);
                }
            });
        }
        else {
            playPauseBtn.setImageResource(R.drawable.ic_pause);
            mediaPlayer.start();
            seekBar.setMax(mediaPlayer.getDuration()/1000);
            PlayerActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mediaPlayer != null) {
                        int mCurrent = mediaPlayer.getCurrentPosition() / 1000;
                        seekBar.setProgress(mCurrent);

                    }
                    handler.postDelayed(this,1000);
                }
            });


        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        initViews();
        getPosition();
        songName.setText(listOfSongs.get(position).getTitle());
        artistName.setText(listOfSongs.get(position).getArtist());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (mediaPlayer != null && fromUser) {
                    mediaPlayer.seekTo(progress * 1000);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        PlayerActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    int mCurrent = mediaPlayer.getCurrentPosition() / 1000;
                    seekBar.setProgress(mCurrent);
                    durationPlayed.setText(formatedTime(mCurrent));
                }
                handler.postDelayed(this,1000);
            }
        });
    }


    private String formatedTime(int mCurrent) {
        String totalOut = "";
        String totalNew = "";
        String seconds = String.valueOf(mCurrent % 60);
        String min = String.valueOf(mCurrent / 60);
        totalOut = min + ":" + seconds;
        totalNew = min + ":" + "0" + seconds;
        if (seconds.length() == 1) {
            return totalNew;
        } else return totalOut;
    }

    private void getPosition() {
        position = getIntent().getIntExtra("position", -1);
        listOfSongs = musicFiles;
        if (listOfSongs != null) {
            playPauseBtn.setImageResource(R.drawable.ic_pause);
            uri = Uri.parse(listOfSongs.get(position).getPath());
        }
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
            mediaPlayer.start();
        } else {
            mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
            mediaPlayer.start();
        }
        seekBar.setMax(mediaPlayer.getDuration() / 1000);
        metaData(uri);
    }

    private void initViews() {
        songName = findViewById(R.id.songName);
        artistName = findViewById(R.id.artistName);
        durationPlayed = findViewById(R.id.durationPlayed);
        durationTotal = findViewById(R.id.totalDuration);
        coverArt = findViewById(R.id.coverArt);
        nextBtn = findViewById(R.id.nextBtn);
        prevBtn = findViewById(R.id.prevBtn);
        backBtn = findViewById(R.id.backBtn);
        shuffleBtn = findViewById(R.id.shuffleBtn);
        repeatbtn = findViewById(R.id.repeatBtn);
        playPauseBtn = findViewById(R.id.playPause);
        seekBar = findViewById(R.id.seekBar);
    }

    private void metaData(Uri uri){
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(uri.toString());
        int durTotal = Integer.parseInt(listOfSongs.get(position).getDuration())/1000;
        durationTotal.setText(formatedTime(durTotal));
        byte[] art = retriever.getEmbeddedPicture();
        if (art!=null){
            Glide.with(this).asBitmap()
                    .load(art)
                    .into(coverArt);
        }
        else {
            Glide.with(this).asBitmap()
                    .load(R.drawable.ic_music_notes)
                    .into(coverArt);

        }
    }

    @Override
    public void onLooperPrepared() {
        isLooperPrepared = true;

    }
}