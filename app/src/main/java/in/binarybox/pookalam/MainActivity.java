package in.binarybox.pookalam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private List<String> imgUrls= new ArrayList<>();
    private String URL_DATA = "https://binarybox.in/apps/pookalam/php/get_data.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerView);

        adapter = new RecyclerViewAdapter(this,imgUrls);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        imgUrls.add("hai");
        imgUrls.add("hai");
        imgUrls.add("hai");
        imgUrls.add("hai");
        imgUrls.add("hai");
        imgUrls.add("hai");
        imgUrls.add("hai");
        imgUrls.add("hai");

        getDataFromServerFn();

    }

    private void getDataFromServerFn() {

        StringRequest stringRequest =new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    Toast.makeText(MainActivity.this, "total_photos = "+jsonObject.getInt("total_photos"), Toast.LENGTH_SHORT).show();

                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {

                    Toast.makeText(MainActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
