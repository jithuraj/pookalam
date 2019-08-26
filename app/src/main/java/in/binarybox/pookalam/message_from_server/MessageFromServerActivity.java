package in.binarybox.pookalam.message_from_server;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import in.binarybox.pookalam.BuildConfig;
import in.binarybox.pookalam.R;

public class MessageFromServerActivity extends AppCompatActivity {
    private CardView btnPrimary, btnSecondary;
    private TextView tvPrimary, tvSecondary;
    private TextView tvheading, tvDescription;
    private String heading, description, app_install_package_name;
    private Boolean mandatory_update = false;
    private int currentMode = 1;
    private TextView tvVersion;
    private int mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_from_server);

        setIdsToViewsFn();
        getValuesFromIntentFn();
        checkMessageModelFn();
        onClickListenersFn();
        setVersionNameFn();

    }

    private void setVersionNameFn() {
        tvVersion.setText(""+ BuildConfig.VERSION_NAME);
    }


    private void checkMessageModelFn() {

        switch (mode){
            case 0: //do nothing
                break;
            case 1:
                currentMode=1;
                updateFn();
                break;
            case 2:
                currentMode=2;
                messageFn();
                break;
            case 3:
                currentMode=3;
                appInstallFn();
                break;
        }

    }

    private void appInstallFn() {
        tvheading.setText(heading);
        tvDescription.setText(description);
        tvPrimary.setText("INSTALL");
        btnSecondary.setVisibility(View.VISIBLE);
        tvSecondary.setText("CLOSE");
    }

    private void messageFn() {

        tvheading.setText(heading);
        tvDescription.setText(description);
        tvPrimary.setText("close");
        btnSecondary.setVisibility(View.INVISIBLE);


    }

    private void updateFn() {

        if (mandatory_update) {
            btnSecondary.setVisibility(View.INVISIBLE);
        } else {

        }
        tvheading.setText(heading);
        tvDescription.setText(description);
        tvPrimary.setText("update");
        tvSecondary.setText("skip");

    }


    @Override
    public void onBackPressed() {

        if (mandatory_update) {
            Toast.makeText(this, "You should update pookalam app to the latest version to continue.", Toast.LENGTH_LONG).show();
        } else {
            super.onBackPressed();

        }

    }

    private void onClickListenersFn() {

        btnPrimary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (currentMode) {

                    case 1:
                        try {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse("market://details?id=in.binarybox.pookalam"));
                            startActivity(intent);
                        } catch (Exception e) {
                            Toast.makeText(MessageFromServerActivity.this, "we can't find google playstore installed on your device.", Toast.LENGTH_SHORT).show();
                        }

                        break;
                    case 2:
                        finish();
                        break;
                    case 3:

                        try {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse("market://details?id="+app_install_package_name));
                            startActivity(intent);
                        } catch (Exception e) {
                            Toast.makeText(MessageFromServerActivity.this, "we can't find google playstore installed on your device.", Toast.LENGTH_SHORT).show();
                        }

                        break;
                }

            }
        });

        btnSecondary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (currentMode){
                    case 1:
                        if (mandatory_update){
                            Toast.makeText(MessageFromServerActivity.this, "You should update pookalam app to the latest version to continue.", Toast.LENGTH_SHORT).show();
                        }else {
                            finish();
                        }
                        break;
                    case 2:
                        finish();
                        break;
                    case 3:
                        finish();
                        break;
                }

            }
        });
    }



    private void getValuesFromIntentFn() {
        mode=getIntent().getIntExtra("mode",0);
        app_install_package_name = getIntent().getStringExtra("app_install_package_name");
        mandatory_update = getIntent().getBooleanExtra("mandatory_update", false);
        heading = getIntent().getStringExtra("heading");
        description = getIntent().getStringExtra("description");

    }

    private void setIdsToViewsFn() {
        btnPrimary = findViewById(R.id.btnPrimary);
        btnSecondary = findViewById(R.id.btnSecondary);
        tvheading = findViewById(R.id.tvHeading);
        tvDescription = findViewById(R.id.tvDescription);
        tvPrimary = findViewById(R.id.tvPrimary);
        tvSecondary = findViewById(R.id.tvSecondary);
        tvVersion=findViewById(R.id.tvVersion);
    }
}
