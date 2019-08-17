package in.binarybox.pookalam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private List<String> imgUrls= new ArrayList<>();

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

        adapter.notifyDataSetChanged();
    }
}
