package in.binarybox.pookalam.menu;

import android.content.Context;
import android.content.Intent;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;

import in.binarybox.pookalam.photo.recyclerView.PhotosRecyclerViewActivity;
import in.binarybox.pookalam.R;
import in.binarybox.pookalam.about.AboutActivity;
import in.binarybox.pookalam.photo.sort.SortPhotosActivity;
import in.binarybox.pookalam.share.ShareActivity;

public class MenuActivity extends AppCompatActivity {
    private ConstraintLayout layout1,layout2,layout3,layout4,btnSound;
    private Boolean soundOn=true;
    private MediaPlayer mediaPlayer;
    private ImageView ivSoundSwitch;
    private String SHARED_PREFS_ID = "bg_music";
    private String SHARED_PREFS_KEY = "is_on";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        layout1=findViewById(R.id.layout1);
        layout2=findViewById(R.id.layout2);
        layout3=findViewById(R.id.layout3);
        layout4=findViewById(R.id.layout4);
        btnSound=findViewById(R.id.soundLayout);
        ivSoundSwitch=findViewById(R.id.ivSoundSwitch);

        setupBgMusicFn();
        
        getSoundModeFn();


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

        btnSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (soundOn){
                    mediaPlayer.pause();
                    ivSoundSwitch.setBackgroundResource(R.drawable.menu_icon_sound_off);
                    soundOn=false;
                    editor.putBoolean(SHARED_PREFS_KEY,soundOn);
                    editor.apply();
                }else {
                    mediaPlayer.start();
                    ivSoundSwitch.setBackgroundResource(R.drawable.menu_icon_sound_on);
                    soundOn=true;
                    editor.putBoolean(SHARED_PREFS_KEY,soundOn);
                    editor.apply();
                }
            }
        });


    }

    private void getSoundModeFn() {

        sharedPreferences=getSharedPreferences(SHARED_PREFS_ID,MODE_PRIVATE);
        editor= getSharedPreferences(SHARED_PREFS_ID,MODE_PRIVATE).edit();

        soundOn=sharedPreferences.getBoolean(SHARED_PREFS_KEY,true);

        if (soundOn){
            mediaPlayer.start();
            ivSoundSwitch.setBackgroundResource(R.drawable.menu_icon_sound_on);
        }else {
            ivSoundSwitch.setBackgroundResource(R.drawable.menu_icon_sound_off);
        }
    }

    private void setupBgMusicFn() {
        mediaPlayer = MediaPlayer.create(this,R.raw.bg_music);
        mediaPlayer.setLooping(true);

        AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 5, 0);
    }

    @Override
    protected void onPause() {

        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }else {

        }

        super.onPause();
    }

    @Override
    protected void onResume() {

        if (soundOn){
            mediaPlayer.start();
        }else {
            mediaPlayer.pause();
        }

        super.onResume();
    }
}
