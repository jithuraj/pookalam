package in.binarybox.pookalam;

import android.animation.LayoutTransition;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> thumbnailUrls = new ArrayList<>();
    private Context context;


    public RecyclerViewAdapter(Context context,List<String> thumbnailUrls) {
        this.thumbnailUrls=thumbnailUrls;
        this.context=context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_item,viewGroup,false);


        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        myViewHolder myViewHolder = (RecyclerViewAdapter.myViewHolder)viewHolder;

        Glide.with(context).load(thumbnailUrls.get(i)).transition(DrawableTransitionOptions.withCrossFade()).into(myViewHolder.ivThumbnail);

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
        }
    }


}
