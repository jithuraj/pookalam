package in.binarybox.pookalam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.github.chrisbanes.photoview.PhotoView;

public class PhotoViewerActivity extends AppCompatActivity {
    private PhotoView ivPhoto;
    private String thumbnailUrl,photoUrl;
    private String PHOTO_PATH="https://binarybox.in/apps/pookalam/photos/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_viewer);

        ivPhoto = findViewById(R.id.ivPhoto);


        getValuesFromIntentFn();

        thumbnailUrlToPhotoUrlFn();

//        ivPhoto.setImageResource(R.drawable.sample_image);

        Glide.with(this).load(photoUrl).transition(DrawableTransitionOptions.withCrossFade()).into(ivPhoto);
    }

    private void getValuesFromIntentFn() {
        thumbnailUrl=getIntent().getStringExtra("thumbnailUrl");
    }

    private void thumbnailUrlToPhotoUrlFn() {

        String[] temp = thumbnailUrl.split("tn_");

        photoUrl=PHOTO_PATH+temp[1];

    }
}
