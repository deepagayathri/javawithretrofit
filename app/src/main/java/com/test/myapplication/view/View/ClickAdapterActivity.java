package com.test.myapplication.view.View;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.test.myapplication.R;

public class ClickAdapterActivity extends AppCompatActivity {
    String str_review_count, str_plan_id;
    TextView TV_id, TV_lastname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter_click);
        init();
    }

    private void init() {
        TV_id = (TextView) findViewById(R.id.TV_id);
        TV_lastname = (TextView) findViewById(R.id.TV_lastname);
        getIntentDetails();

    }

    private void getIntentDetails() {
//        Bundle bundle = getIntent.getExtras();
//        if (bundle != null) {
//            str_review_count = getIntent().getStringExtra("review_count");
//            str_plan_id = getIntent().getStringExtra("plan_id");
//            TV_id.setText(str_review_count);
//            TV_lastname.setText(str_plan_id);
//        }
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            str_review_count = getIntent().getStringExtra("id");
            str_plan_id = getIntent().getStringExtra("last_name");
            TV_id.setText(str_review_count);
            TV_lastname.setText(str_plan_id);
        }

    }


}
