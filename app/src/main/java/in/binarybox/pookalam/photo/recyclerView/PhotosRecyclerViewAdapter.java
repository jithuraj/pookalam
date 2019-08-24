package in.binarybox.pookalam.photo.recyclerView;

import android.app.Activity;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.ArrayList;
import java.util.List;

import in.binarybox.pookalam.R;
import in.binarybox.pookalam.photo.viewPager.PhotosViewPagerActivity;

public class PhotosRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> thumbnailUrls = new ArrayList<>();
    private Activity activity;
    private int width;
    private Animation itemInAnim;
    private int currentColorNumber=1;


    public PhotosRecyclerViewAdapter(Activity activity, List<String> thumbnailUrls, int width) {
        this.thumbnailUrls=thumbnailUrls;
        this.activity=activity;
        this.width=width;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(activity).inflate(R.layout.recycler_view_item,viewGroup,false);


        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {

        myViewHolder myViewHolder = (PhotosRecyclerViewAdapter.myViewHolder)viewHolder;

        changeCardBackgroundColorFn(myViewHolder.ivThumbnail);
        showRecyclerViewAnimFn(myViewHolder.ivThumbnail);

        myViewHolder.ivThumbnail.getLayoutParams().height=(width/2);

        Glide.with(activity).load(thumbnailUrls.get(i)).transition(DrawableTransitionOptions.withCrossFade()).into(myViewHolder.ivThumbnail);

        myViewHolder.ivThumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity, PhotosViewPagerActivity.class);
//                intent.putExtra("thumbnailUrl",thumbnailUrls.get(i));

                intent.putStringArrayListExtra("thumbnailUrls", (ArrayList<String>) thumbnailUrls);
                intent.putExtra("position",i);

                activity.startActivity(intent);
            }
        });

    }

    private void showRecyclerViewAnimFn(View view) {

        itemInAnim= AnimationUtils.loadAnimation(activity,R.anim.photos_recycler_view_items_in_anim);
        itemInAnim.setDuration(1000);
        itemInAnim.setInterpolator(new OvershootInterpolator());
        view.startAnimation(itemInAnim);
    }

    @Override
    public int getItemCount() {
        return thumbnailUrls.size();
    }


    static class myViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivThumbnail;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            ivThumbnail=itemView.findViewById(R.id.ivThumbnail);
            ivThumbnail.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
    }

    private void changeCardBackgroundColorFn(ImageView imageView) {

        if (currentColorNumber>20){
            currentColorNumber=1;
        }
        else {
            currentColorNumber++;
        }

        switch (currentColorNumber){
            case 1:imageView.setBackgroundResource(R.color.bg_color_1);break;
            case 2:imageView.setBackgroundResource(R.color.bg_color_2);break;
            case 3:imageView.setBackgroundResource(R.color.bg_color_3);break;
            case 4:imageView.setBackgroundResource(R.color.bg_color_4);break;
            case 5:imageView.setBackgroundResource(R.color.bg_color_5);break;
            case 6:imageView.setBackgroundResource(R.color.bg_color_6);break;
            case 7:imageView.setBackgroundResource(R.color.bg_color_7);break;
            case 8:imageView.setBackgroundResource(R.color.bg_color_8);break;
            case 9:imageView.setBackgroundResource(R.color.bg_color_9);break;
            case 10:imageView.setBackgroundResource(R.color.bg_color_10);break;
            case 11:imageView.setBackgroundResource(R.color.bg_color_11);break;
            case 12:imageView.setBackgroundResource(R.color.bg_color_12);break;
            case 13:imageView.setBackgroundResource(R.color.bg_color_13);break;
            case 14:imageView.setBackgroundResource(R.color.bg_color_14);break;
        }


    }




}
