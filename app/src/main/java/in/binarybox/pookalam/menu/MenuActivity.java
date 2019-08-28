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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import in.binarybox.pookalam.BuildConfig;
import in.binarybox.pookalam.message_from_server.MessageFromServerActivity;
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
//    private SharedPreferences sharedPreferences;
//    private SharedPreferences.Editor editor;


    private int mode, latestVersion;
    private String appInstallPackgaName, heading, description;
    private Boolean mandatoryUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        setIdsToViewsFn();

        checkForNewMessageFromServerFn();

//        setupBgMusicFn();
        
//        getSoundModeFn();


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

//        btnSound.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (soundOn){
//                    mediaPlayer.pause();
//                    ivSoundSwitch.setBackgroundResource(R.drawable.menu_icon_sound_off);
//                    soundOn=false;
//                    editor.putBoolean(SHARED_PREFS_KEY,soundOn);
//                    editor.apply();
//                }else {
//                    mediaPlayer.start();
//                    ivSoundSwitch.setBackgroundResource(R.drawable.menu_icon_sound_on);
//                    soundOn=true;
//                    editor.putBoolean(SHARED_PREFS_KEY,soundOn);
//                    editor.apply();
//                }
//            }
//        });


    }

    private void setIdsToViewsFn() {

        layout1=findViewById(R.id.layout1);
        layout2=findViewById(R.id.layout2);
        layout3=findViewById(R.id.layout3);
        layout4=findViewById(R.id.layout4);
//        btnSound=findViewById(R.id.soundLayout);
//        ivSoundSwitch=findViewById(R.id.ivSoundSwitch);

    }

//    private void getSoundModeFn() {
//
//        sharedPreferences=getSharedPreferences(SHARED_PREFS_ID,MODE_PRIVATE);
//        editor= getSharedPreferences(SHARED_PREFS_ID,MODE_PRIVATE).edit();
//
//        soundOn=sharedPreferences.getBoolean(SHARED_PREFS_KEY,true);
//
//        if (soundOn){
//            mediaPlayer.start();
//            ivSoundSwitch.setBackgroundResource(R.drawable.menu_icon_sound_on);
//        }else {
//            ivSoundSwitch.setBackgroundResource(R.drawable.menu_icon_sound_off);
//        }
//    }

//    private void setupBgMusicFn() {
//        mediaPlayer = MediaPlayer.create(this,R.raw.bg_music);
//        mediaPlayer.setLooping(true);
//
//        AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
//        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 5, 0);
//    }

//    @Override
//    protected void onPause() {
//
//        if (mediaPlayer.isPlaying()){
//            mediaPlayer.pause();
//        }else {
//
//        }
//
//        super.onPause();
//    }

//    @Override
//    protected void onResume() {
//
//        if (soundOn){
//            mediaPlayer.start();
//        }else {
//            mediaPlayer.pause();
//        }
//
//        super.onResume();
//    }


    private void checkForNewMessageFromServerFn() {


        StringRequest stringRequest = new StringRequest(Request.Method.GET
                , "https://binarybox.in/apps/pookalam/json_files/message/message_data.json"
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    //get data from server
                    JSONObject object = new JSONObject(response);
                    mode = object.getInt("mode");
                    latestVersion = object.getInt("latest_version");
                    appInstallPackgaName = object.getString("app_install_package_name");
                    mandatoryUpdate = object.getBoolean("mandatory_update");
                    heading = object.getString("heading");
                    description = object.getString("description");


                    if (mode == 0) {
                        //do nothing
                    } else if (mode == 1) {

                        if (latestVersion > BuildConfig.VERSION_CODE) {
                            startMessageActivityFn();
                        } else {
                            //this is the latest version
                        }

                    } else {
                        startMessageActivityFn();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void startMessageActivityFn() {

        Intent intent = new Intent(MenuActivity.this, MessageFromServerActivity.class);
        intent.putExtra("mode", mode);
        intent.putExtra("latest_version", latestVersion);
        intent.putExtra("app_install_package_name", appInstallPackgaName);
        intent.putExtra("mandatory_update", mandatoryUpdate);
        intent.putExtra("heading", heading);
        intent.putExtra("description", description);
        startActivity(intent);

    }
}
