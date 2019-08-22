package in.binarybox.pookalam.menu;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import in.binarybox.pookalam.PhotosActivity;
import in.binarybox.pookalam.R;

public class MenuActivity extends AppCompatActivity {
    private ConstraintLayout layout1,layout2,layout3,layout4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        layout1=findViewById(R.id.layout1);
        layout2=findViewById(R.id.layout2);
        layout3=findViewById(R.id.layout3);
        layout4=findViewById(R.id.layout4);

        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, PhotosActivity.class);
                startActivity(intent);
            }
        });

    }
}
