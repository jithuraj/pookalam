package in.binarybox.pookalam.about;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import in.binarybox.pookalam.BuildConfig;
import in.binarybox.pookalam.R;


public class AboutActivity extends AppCompatActivity {
    private ImageView btnPicasso, btnPhotoView, btnAVLoadingIndicator, btnGlide,btnFlaticon, btnDexter;
    private String url;
    private EditText etFeedback;
    private ImageView btnSend;
    private DatabaseReference mDatabase;
    private TextView tvInstatus,tvVersion;
    private TextView btnDisclaimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setIdsToViewsFn();


        mDatabase = FirebaseDatabase.getInstance().getReference().child("feedback");

        tvVersion.setText("ver "+ BuildConfig.VERSION_NAME);

        btnFlaticon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                url = "https://flaticon.com";
                startIntent();

            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etFeedback.getText().toString().matches("")) {
                    Toast.makeText(AboutActivity.this, "Write something...", Toast.LENGTH_SHORT).show();
                }else {
                    mDatabase.push().setValue(etFeedback.getText().toString());
                    Toast.makeText(AboutActivity.this, "Thanks for your feedback", Toast.LENGTH_SHORT).show();
                    etFeedback.setText("");
                }

            }
        });

        btnPhotoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                url = "https://github.com/chrisbanes/PhotoView";
                startIntent();
            }
        });
        btnPicasso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                url = "https://github.com/square/picasso";
                startIntent();
            }
        });
        btnAVLoadingIndicator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                url = "https://github.com/81813780/AVLoadingIndicatorView";
                startIntent();
            }
        });
        btnGlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                url = "https://github.com/bumptech/glide";
                startIntent();
            }
        });
        btnDexter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                url = "https://github.com/Karumi/Dexter";
                startIntent();
            }
        });

        tvInstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AboutActivity.super.onBackPressed();
            }
        });

        btnDisclaimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(AboutActivity.this, "Some of the images shown in this app are collected from various sources over the internet and we do not express any Ownership of these images. If you are the owner or any copyright issues, please mail us on apps.binarybox@gmail.com", Toast.LENGTH_LONG).show();

            }
        });
    }

    private void setIdsToViewsFn() {

        setContentView(R.layout.activity_about);
        btnPicasso =  findViewById(R.id.btnPicasso);
        btnPhotoView = findViewById(R.id.btnPhotoView);
        btnAVLoadingIndicator =  findViewById(R.id.btnAvloading);
        btnGlide =  findViewById(R.id.btnGlide);
        etFeedback = findViewById(R.id.etFeedback);
        btnSend =  findViewById(R.id.btnSend);
        btnFlaticon=findViewById(R.id.btnFlatiocon);
        tvInstatus=findViewById(R.id.tvInstatus);
        tvVersion=findViewById(R.id.tvVersion);
        btnDexter=findViewById(R.id.btnDexter);
        btnDisclaimer=findViewById(R.id.btnDisclaimer);
    }

    private void startIntent() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

}
