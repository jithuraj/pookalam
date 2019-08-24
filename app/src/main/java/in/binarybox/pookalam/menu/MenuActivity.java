package in.binarybox.pookalam.menu;

import android.content.Context;
import android.content.Intent;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;

import in.binarybox.pookalam.photo.recyclerView.PhotosRecyclerViewActivity;
import in.binarybox.pookalam.R;
import in.binarybox.pookalam.about.AboutActivity;
import in.binarybox.pookalam.photo.sort.SortPhotosActivity;
import in.binarybox.pookalam.share.ShareActivity;

public class MenuActivity extends AppCompatActivity {
    private ConstraintLayout layout1,layout2,layout3,layout4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        layout1=findViewById(R.id.layout1);
        layout2=findViewById(R.id.layout2);
        layout3=findViewById(R.id.layout3);
        layout4=findViewById(R.id.layout4);

        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MenuActivity.this, PhotosRecyclerViewActivity.class);
                startActivity(intent);
            }
        });
        layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MenuActivity.this, "Coming soon...", Toast.LENGTH_SHORT).show();

//                Intent intent= new Intent(getApplicationContext(), SortPhotosActivity.class);
//                startActivity(intent);
            }
        });
        layout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(getApplicationContext(), ShareActivity.class);
                startActivity(intent);
            }
        });
        layout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(intent);
            }
        });

        playBgMusicFn();

    }

    private void playBgMusicFn() {
        MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.bg_music);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 10, 0);
    }
}
