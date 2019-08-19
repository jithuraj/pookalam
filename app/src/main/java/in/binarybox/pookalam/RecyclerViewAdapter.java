package in.binarybox.pookalam;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.ArrayList;
import java.util.List;

import in.binarybox.pookalam.photo.ViewPagerActivity;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> thumbnailUrls = new ArrayList<>();
    private Activity activity;
    private int width;


    public RecyclerViewAdapter(Activity activity,List<String> thumbnailUrls,int width) {
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

        myViewHolder myViewHolder = (RecyclerViewAdapter.myViewHolder)viewHolder;

        myViewHolder.ivThumbnail.getLayoutParams().height=(width/2);

        Glide.with(activity).load(thumbnailUrls.get(i)).transition(DrawableTransitionOptions.withCrossFade()).into(myViewHolder.ivThumbnail);

        myViewHolder.ivThumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity, ViewPagerActivity.class);
//                intent.putExtra("thumbnailUrl",thumbnailUrls.get(i));

                intent.putStringArrayListExtra("thumbnailUrls", (ArrayList<String>) thumbnailUrls);
                intent.putExtra("position",i);

                activity.startActivity(intent);
            }
        });

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


}
