package com.azadljy.jardinsecret.page;

import android.content.Intent;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import com.azadljy.jardinsecret.R;
import com.azadljy.jardinsecret.adapter.JardinAdapter;
import com.azadljy.jardinsecret.base.BaseActivity;
import com.azadljy.jardinsecret.lifecircle.SingleInstanceLifeObserver;
import com.azadljy.jardinsecret.page.dialogpage.DialogActivity;
import com.azadljy.jardinsecret.page.map.MapActivity;
import com.azadljy.jardinsecret.page.meterreadingtest.MeterReadingTestActivity;
import com.azadljy.jardinsecret.page.oceanmemory.OceanMemoryActivity;
import com.azadljy.jardinsecret.page.singlechoice.SingleChoiceActivity;
import com.azadljy.jardinsecret.page.treepage.TreeAdapterActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

public class JardinActivity extends BaseActivity {

    RecyclerView recyclerView;
    private List<JardinAdapter.JardinModel> models = new ArrayList<>();

    private JardinAdapter jardinAdapter;
    private Toolbar toolbar;
    private List<String> strings = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jardin);
        recyclerView = findViewById(R.id.rv_project);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initProjectInfo();
        jardinAdapter = new JardinAdapter(R.layout.item_jardin, models);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(jardinAdapter);
        jardinAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                switch (position) {
                    case 5:
                        startActivity(new Intent(JardinActivity.this, TreeAdapterActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(JardinActivity.this, DialogActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(JardinActivity.this, OceanMemoryActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(JardinActivity.this, SingleChoiceActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(JardinActivity.this, MapActivity.class));
                        break;
                    case 0:
                        startActivity(new Intent(JardinActivity.this, MeterReadingTestActivity.class));
                        break;
                }
            }
        });
        getLifecycle().addObserver(SingleInstanceLifeObserver.getInstance());
    }

    private int COLOR_STARE = 0xffecf5ff;

    private void initProjectInfo() {
//        strings.add("TreeAdapter");
//        strings.add("Dialog");
//        strings.add("OceanMemory");
//        strings.add("SingleChoice");
//        strings.add("Map");
        strings.add("MeterReadingTest");
        strings.add("测试");
        strings.add("测试");
        strings.add("测试");
        strings.add("测试");
        strings.add("测试");
        strings.add("测试");
        strings.add("测试");
        strings.add("测试");
        strings.add("测试");
        strings.add("测试");
        strings.add("测试");
        strings.add("测试");
        strings.add("测试");
        strings.add("测试");
        strings.add("测试");
        strings.add("测试");
        strings.add("测试");
        strings.add("测试");
        strings.add("测试");
        strings.add("测试");
        strings.add("测试");
        strings.add("测试");
        strings.add("测试");
        strings.add("测试");
        strings.add("测试");

        for (int i = 0; i < strings.size(); i++) {
            JardinAdapter.JardinModel model = new JardinAdapter.JardinModel();
            model.setProjectName(strings.get(i));
            if ((COLOR_STARE - (0xff1A0C00 * 3 * (i + 1)) > 0)) {
                int[] colors = new int[]{COLOR_STARE - (0xff1A0C00 * 3 * i), COLOR_STARE - (0xff1A0C00 * 3 * (i + 1))};
                model.setColors(colors);
                models.add(model);
            } else {
                break;
            }
        }


    }

}
