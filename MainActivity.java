package com.example.mp3;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer player;
    int length =0 , simpan;
    String[] songTitles ;
    String song;
    Button button, button1, button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        songTitles = getResources().getStringArray(R.array.song_titles);
        ListView list = (ListView) findViewById(R.id.song_titles);
        button =  (Button) findViewById(R.id.prev_button);
        button1 = (Button) findViewById(R.id.next_button);
        button2 = (Button) findViewById(R.id.stop_button);
        button.setEnabled(false);
        button1.setEnabled(false);
        button2.setEnabled(false);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                song = songTitles[position].toLowerCase().replace(" ","");
                playSong(song, position);
            }
        });
    }

    public void playSong(String song, int index){
        simpan = index;
        int songID = getResources().getIdentifier(song, "raw" ,getPackageName());
        stopSong();
        button.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        player = MediaPlayer.create(MainActivity.this, songID);
        player.start();
    }
    public void stopSong(){
        if(player != null){
            player.stop();
            player = null;
        }
        button.setEnabled(false);
        button1.setEnabled(false);
        button2.setEnabled(false);
    }
    public void onClickPlay(View view){
        if(player == null){
            song = songTitles[0].toLowerCase().replace(" ","");
            playSong(song, 0);
        }else {
            if(length == 0 ){
                player.pause();
                length = player.getCurrentPosition();
            }else {
                player.seekTo(length);
                player.start();
                length = 0;
            }
        }
    }
    public void onClickPrev(View view ){
        song = songTitles[simpan - 1].toLowerCase().replace(" ","");
        playSong(song, simpan - 1);
    }

    public void onClickNext(View view){
        song = songTitles[simpan + 1].toLowerCase().replace(" ","");
        playSong(song, simpan + 1);
    }
    public void onClickStop(View view){
        stopSong();
    }

}