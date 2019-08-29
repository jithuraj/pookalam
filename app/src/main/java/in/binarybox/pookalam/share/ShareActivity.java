package in.binarybox.pookalam.share;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import in.binarybox.pookalam.BuildConfig;
import in.binarybox.pookalam.R;

public class ShareActivity extends AppCompatActivity {
    private ImageView btnMessenger,btnWhatsapp,btnShare,btnCopy;
    private TextView tvPookalam,tvVersion;
    private Animation anim1,anim2,anim3,anim4,animR1,animR2,animR3,animR4;
    private int animationDuration=500,animationOffsetDuration=300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        addIdsToViewsFn();

        addVersionCodeFn();

        onClickListenersFn();

        animationsFn();


    }

    private void animationsFn() {

        anim1=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.share_icons_in_anim);
        anim1.setDuration(animationDuration);
        anim2=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.share_icons_in_anim);
        anim2.setDuration(animationDuration);
        anim3=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.share_icons_in_anim);
        anim3.setDuration(animationDuration);
        anim4=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.share_icons_in_anim);
        anim4.setDuration(animationDuration);

        animR1=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.share_icons_out_anim);
        animR1.setDuration(animationDuration);
        animR2=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.share_icons_out_anim);
        animR2.setDuration(animationDuration);
        animR3=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.share_icons_out_anim);
        animR3.setDuration(animationDuration);
        animR4=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.share_icons_out_anim);
        animR4.setDuration(animationDuration);



        anim1.setStartOffset(0);
        anim2.setStartOffset(animationOffsetDuration);
        anim3.setStartOffset(animationOffsetDuration*2);
        anim4.setStartOffset(animationOffsetDuration*3);

        animR1.setStartOffset(0);
        animR2.setStartOffset(animationOffsetDuration);
        animR3.setStartOffset(animationOffsetDuration*2);
        animR4.setStartOffset(animationOffsetDuration*3);


        animationInFn();


    }

    private void animationOutFn() {

        btnMessenger.startAnimation(animR1);
        btnWhatsapp.startAnimation(animR2);
        btnShare.startAnimation(animR3);
        btnCopy.startAnimation(animR4);

        animR4.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animationInFn();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void animationInFn() {
        btnMessenger.startAnimation(anim1);
        btnWhatsapp.startAnimation(anim2);
        btnShare.startAnimation(anim3);
        btnCopy.startAnimation(anim4);

        anim4.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animationOutFn();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void addVersionCodeFn() {
        tvVersion.setText("ver "+BuildConfig.VERSION_NAME);
    }

    private void onClickListenersFn() {

        btnMessenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "ഒരു പൂക്കളത്തിനു ഒരുനൂറ് സൈറ്റ് നോക്കുന്ന കാലം കഴിഞ്ഞു... \n" +
                        "ഒരു app 500 ഇൽ പരം ഡിസൈൻ & its TOTALLY FREE " +
                        "\n" +
                        "https://play.google.com/store/apps/details?id=in.binarybox.pookalam");


                try {

                    intent.setPackage("com.facebook.orca");
                    startActivity(Intent.createChooser(intent, "share via"));

                }catch (Exception e){

                    try {

                        intent.setPackage("com.facebook.mlite");
                        startActivity(Intent.createChooser(intent, "share via"));

                    }catch (Exception e1){

                        Toast.makeText(ShareActivity.this, "facebook messenger not installed!", Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });
        btnWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "ഒരു പൂക്കളത്തിനു ഒരുനൂറ് സൈറ്റ് നോക്കുന്ന കാലം കഴിഞ്ഞു... \n" +
                        "ഒരു app 500 ഇൽ പരം ഡിസൈൻ & *its* *TOTALLY* *FREE* " +
                        "\n" +
                        "https://play.google.com/store/apps/details?id=in.binarybox.pookalam");
                try {

                    intent.setPackage("com.whatsapp");
                    startActivity(Intent.createChooser(intent, "share via"));

                }catch (Exception ignored){

                }

            }
        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "ഒരു പൂക്കളത്തിനു ഒരുനൂറ് സൈറ്റ് നോക്കുന്ന കാലം കഴിഞ്ഞു... \n" +
                        "ഒരു app 500 ഇൽ പരം ഡിസൈൻ & its TOTALLY FREE " +
                        "\n" +
                        "https://play.google.com/store/apps/details?id=in.binarybox.pookalam");
                startActivity(Intent.createChooser(intent, "share via"));

            }
        });

        btnCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("","ഒരു പൂക്കളത്തിനു ഒരുനൂറ് സൈറ്റ് നോക്കുന്ന കാലം കഴിഞ്ഞു... \n" +
                        "ഒരു app 500 ഇൽ പരം ഡിസൈൻ & its TOTALLY FREE " +
                        "\n" +
                        "https://play.google.com/store/apps/details?id=in.binarybox.pookalam" );
                clipboardManager.setPrimaryClip(clipData);

                Toast.makeText(ShareActivity.this, "Copied to clipboard.", Toast.LENGTH_SHORT).show();

            }
        });


        tvPookalam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareActivity.super.onBackPressed();
            }
        });

    }

    private void addIdsToViewsFn() {
        btnMessenger=findViewById(R.id.btnMessenger);
        btnWhatsapp=findViewById(R.id.btnWhatsapp);
        btnShare=findViewById(R.id.btnShare);
        btnCopy=findViewById(R.id.btnCopy);
        tvPookalam=findViewById(R.id.tvPookalam);
        tvVersion=findViewById(R.id.tvVersion);
    }

}
