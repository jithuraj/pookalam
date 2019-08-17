package in.binarybox.pookalam;

import android.animation.LayoutTransition;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> imgUrls = new ArrayList<>();
    private Context context;


    public RecyclerViewAdapter(Context context,List<String> imgUrls) {
        this.imgUrls=imgUrls;
        this.context=context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_item,viewGroup,false);

        myViewHolder viewHolder = new myViewHolder(viewGroup);

//        viewHolder.ivThumbnail.setBackgroundColor();

        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return imgUrls.size();
    }


    static class myViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivThumbnail;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            ivThumbnail=itemView.findViewById(R.id.ivThumbnail);
        }
    }


}
