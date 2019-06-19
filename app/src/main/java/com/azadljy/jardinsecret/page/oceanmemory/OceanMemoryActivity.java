package com.azadljy.jardinsecret.page.oceanmemory;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.FrameLayout;

import com.azadljy.jardinsecret.R;
import com.azadljy.jardinsecret.base.BaseActivity;
import com.azadljy.pleasantlibrary.widget.MemoryUnitView;
import com.azadljy.pleasantlibrary.widget.OceanView;

public class OceanMemoryActivity extends BaseActivity {

    private FrameLayout fl_ocean_memory;
    private OceanView my_oceanView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocean_memory);
        fl_ocean_memory = findViewById(R.id.fl_ocean_memory);
        my_oceanView = findViewById(R.id.my_ocean);

        for (int i = 0; i < 9; i++) {
            MemoryUnitView memoryUnitView = new MemoryUnitView(this);
            memoryUnitView.setText("point" + i);
            memoryUnitView.setBackgroundResource(R.color.cl_FEB70D);
            my_oceanView.addView(memoryUnitView);
            memoryUnitView.getLayoutParams().height = 100;
            memoryUnitView.getLayoutParams().width = 200;
            memoryUnitView.setGravity(Gravity.CENTER);
        }


//        my_ocean.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////               my_ocean.scrollBy(100,0);
//            }
//        });

    }
}
