package in.binarybox.pookalam.photo;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import in.binarybox.pookalam.R;

/**
 * Created by jithuraj on 1/23/2018.
 */

public class PhotosViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<String> thumbnailUrls = new ArrayList<>();
    private String photoUrl;

    public PhotosViewPagerAdapter(Context context, List<String> thumbnailUrls) {
        this.context = context;
        this.thumbnailUrls = thumbnailUrls;
    }

    @Override
    public int getCount() {
        return thumbnailUrls.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.photos_view_pager_item, container, false);
        ImageView imageView = view.findViewById(R.id.ivPhoto);

        String temp = thumbnailUrls.get(position);
        String[] temp1 = temp.split("thumbnails/tn_");
        photoUrl = temp1[0] + temp1[1];
        Glide.with(context).load(photoUrl).into(imageView);

        container.addView(view);
        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

}

