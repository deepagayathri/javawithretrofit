package com.test.myapplication.view.View;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.test.myapplication.R;

public class MainPage extends AppCompatActivity {
TextView Tv_Recy_Head;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        init();
    }

    private void init() {
        Tv_Recy_Head=(TextView)findViewById(R.id.Tv_Recy_Head);
        Tv_Recy_Head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Recy_Page.class);
                startActivity(intent);
            }
        });
    }
}
