package in.binarybox.pookalam.photo;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import in.binarybox.pookalam.R;


public class PhotosViewPagerActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private PhotosViewPagerAdapter adapter;
    private List<String> thumbnailUrls = new ArrayList<>();
    private int position;
    private ImageView btnShare, btnDownload, btnWhatsapp;
    private int buttonClicked;
    private String photoUrl;
    private String photoName;
    private File dir;
    private InterstitialAd mInterstitialAd;
    private Boolean showAds = true;
    private ProgressBar progressBar1, progressBar2, progressBar3;
    private Animation shareDownloadWhatsappButtonClickedAnim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_viewer);

        setIdsToViewsFn();
        interstitialAdsFn();

        thumbnailUrls = getIntent().getStringArrayListExtra("thumbnailUrls");
        position = getIntent().getExtras().getInt("position");


        adapter = new PhotosViewPagerAdapter(this, thumbnailUrls);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(position);

//        viewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
//            @Override
//            public void transformPage(View page, float position) {
//                page.setRotationY(position * -50);
//
//            }
//        });

        createDirectoryFn();

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnShare.startAnimation(bottomButtonsClickedAnimFn());
                buttonClicked = 1;
                String[] temp1 = thumbnailUrls.get(viewPager.getCurrentItem()).split("thumbnails/tn_");
                photoUrl = temp1[0] + temp1[1];
                new DownloadFileFromURL().execute(photoUrl);


            }
        });
        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnDownload.startAnimation(bottomButtonsClickedAnimFn());
                buttonClicked = 2;
                String[] temp1 = thumbnailUrls.get(viewPager.getCurrentItem()).split("thumbnails/tn_");
                photoUrl = temp1[0] + temp1[1];
                new DownloadFileFromURL().execute(photoUrl);
            }
        });
        btnWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnWhatsapp.startAnimation(bottomButtonsClickedAnimFn());
                buttonClicked = 3;
                String[] temp1 = thumbnailUrls.get(viewPager.getCurrentItem()).split("thumbnails/tn_");
                photoUrl = temp1[0] + temp1[1];
                new DownloadFileFromURL().execute(photoUrl);

            }
        });

    }

    private void setIdsToViewsFn() {


        btnShare =  findViewById(R.id.btnShare);
        btnDownload =  findViewById(R.id.btnDownload);
        btnWhatsapp =  findViewById(R.id.btnWhatsapp);
        progressBar1 =  findViewById(R.id.progressBar1);
        progressBar2 =  findViewById(R.id.progressBar2);
        progressBar3 =  findViewById(R.id.progressBar3);
        viewPager = findViewById(R.id.viewPager);
    }

    @Override
    public void onBackPressed() {

//        if (mInterstitialAd.isLoaded()){
//            mInterstitialAd.show();
//        }
//        else {
//
//        }

        super.onBackPressed();
    }

    private void interstitialAdsFn() {
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(this.getString(R.string.interstitialAdUnitId));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

    }

    private void showInterstitialAdFn() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
        }
    }

    private void createDirectoryFn() {
        try {
            dir = new File(Environment.getExternalStorageDirectory() + "/pookalam");
            dir.mkdir();
        } catch (Exception ignored) {

        }
    }

    @SuppressLint("StaticFieldLeak")
    class DownloadFileFromURL extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {

            switch (buttonClicked) {
                case 1:
                    toggleProgressBarFn(1, true);
                    break;
                case 2:
                    toggleProgressBarFn(2, true);
                    break;
                case 3:
                    toggleProgressBarFn(3, true);
                    break;
            }

            String[] temp =thumbnailUrls.get(viewPager.getCurrentItem()).split("thumbnails/tn_");
            photoName=temp[1];

            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... f_url) {

            int count;
            String destinationPath = Environment.getExternalStorageDirectory().toString() + "/pookalam/" + photoName;

            try {
                URL url = new URL(f_url[0]);
                URLConnection conection = url.openConnection();
                conection.connect();
                int lenghtOfFile = conection.getContentLength();
                InputStream input = new BufferedInputStream(url.openStream(), 8192);
                OutputStream output = new FileOutputStream(destinationPath);
                byte[] data = new byte[1024];
                long total = 0;
                while ((count = input.read(data)) != -1) {
                    total += count;
                    publishProgress("" + (int) ((total * 100) / lenghtOfFile));
                    output.write(data, 0, count);
                }
                output.flush();
                output.close();
                input.close();

            } catch (Exception ignored) {
            }

            return null;
        }

        protected void onProgressUpdate(String... progress) {

        }

        @Override
        protected void onPostExecute(String file_url) {

            if (showAds) {
                showInterstitialAdFn();
            } else {

            }

            File filePath = new File(Environment.getExternalStorageDirectory().toString() + "/pookalam/" + photoName);
            refreshGalleryFn(filePath);

            Intent intent = new Intent();

            //for oreo and newer versions

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Uri photoURI = FileProvider.getUriForFile(getApplicationContext(), getApplicationContext().getPackageName() + ".my.package.name.provider", filePath);
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_STREAM, photoURI);
                intent.putExtra(Intent.EXTRA_TEXT, "\nPookalam app");
                intent.setType("image/*");
            } else {

                Uri uri2 = Uri.fromFile(filePath);
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_STREAM, uri2);
                intent.putExtra(Intent.EXTRA_TEXT, "\nPookalam app");
                intent.setType("image/*");
            }


            switch (buttonClicked) {
                case 1:
                    startActivity(Intent.createChooser(intent, "share via"));
                    break;
                case 2:
                    customToast("Download completed");
                    break;
                case 3:
                    intent.setPackage("com.whatsapp");
                    try {
                        startActivity(intent);
                    } catch (Exception e) {
                        try {
                            intent.setPackage("com.gbwhatsapp");
                            startActivity(intent);
                        } catch (Exception e1) {
                            Toast.makeText(getApplicationContext(), "I think WhatsApp is not installed in your in your phone!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    break;
            }

            switch (buttonClicked) {
                case 1:
                    toggleProgressBarFn(1, false);
                    break;
                case 2:
                    toggleProgressBarFn(2, false);
                    break;
                case 3:
                    toggleProgressBarFn(3, false);
                    break;
            }

        }
    }

    private void toggleProgressBarFn(int item, Boolean isShowing) {
        if (isShowing) {
            switch (item) {
                case 1:
                    progressBar1.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    progressBar2.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    progressBar3.setVisibility(View.VISIBLE);
                    break;
            }
        } else {
            switch (item) {
                case 1:
                    progressBar1.setVisibility(View.GONE);
                    break;
                case 2:
                    progressBar2.setVisibility(View.GONE);
                    break;
                case 3:
                    progressBar3.setVisibility(View.GONE);
                    break;
            }
        }
    }



    private void refreshGalleryFn(File file) {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.DATA, file.getAbsolutePath());
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
    }


    private void customToast(String string) {
        Context context = getApplicationContext();
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_toast_layout, null);
        TextView tvMessage = view.findViewById(R.id.tvMessage);
        Toast toast = new Toast(context);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        tvMessage.setText(string);
        toast.show();
    }

    private Animation bottomButtonsClickedAnimFn() {
        shareDownloadWhatsappButtonClickedAnim = AnimationUtils.loadAnimation(this, R.anim.share_download_whatsapp_button_clicked_anim);
        shareDownloadWhatsappButtonClickedAnim.setDuration(1500);
        shareDownloadWhatsappButtonClickedAnim.setInterpolator(new OvershootInterpolator());
        return shareDownloadWhatsappButtonClickedAnim;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
