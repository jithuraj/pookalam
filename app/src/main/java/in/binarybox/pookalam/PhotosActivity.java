package in.binarybox.pookalam;

import android.Manifest;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PhotosActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private List<String> thumbnailUrls= new ArrayList<>();
    private List<String> thumbnailNames= new ArrayList<>();
    private String URL_DATA = "https://binarybox.in/apps/pookalam/php/get_data.php";
    private String THUMBNAIL_PATH ="https://binarybox.in/apps/pookalam/photos/thumbnails/";
    private int width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerView);

        //get device screen width
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        width = displayMetrics.widthPixels;

        adapter = new RecyclerViewAdapter(this,thumbnailUrls,width);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        getDataFromServerFn();

        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            //permission granted

                        } else {
                            //permission denied
                        }

                        if (report.isAnyPermissionPermanentlyDenied()) {
                            //permanent permission denial
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                })
                .onSameThread()
                .check();

    }

    private void getDataFromServerFn() {

        StringRequest stringRequest =new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    
                    for (int i=0;i<jsonArray.length();i++){
                        thumbnailNames.add(jsonArray.getString(i));
                    }

                    thumbnailNameToUrlFn();



                } catch (JSONException e) {

                    showInternetConnecttionErrorFn();

                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                showInternetConnecttionErrorFn();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void thumbnailNameToUrlFn() {

        for (String item : thumbnailNames){
            thumbnailUrls.add(THUMBNAIL_PATH+item);
        }

        recyclerView.setVisibility(View.VISIBLE);
        Collections.shuffle(thumbnailUrls);
        adapter.notifyDataSetChanged();
    }

    private void showInternetConnecttionErrorFn() {
       TextView btnRetry =  findViewById(R.id.btnRetry);
       ConstraintLayout noInternetConnectionLayout =  findViewById(R.id.noInternetConnectionLayout);
        noInternetConnectionLayout.setVisibility(View.VISIBLE);
        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }



    //todo don't forget to add privacy policy in playstore


}
