package com.azadljy.jardinsecret.page.dialogpage;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.azadljy.jardinsecret.R;
import com.azadljy.jardinsecret.adapter.SimpleAdapter;
import com.azadljy.jardinsecret.base.BaseActivity;
import com.azadljy.jardinsecret.dialog.DialogUtil;
import com.azadljy.jardinsecret.lifecircle.SingleInstanceLifeObserver;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

public class DialogActivity extends BaseActivity {

    RecyclerView recyclerView;
    private List<SimpleAdapter.SimpleModel> models = new ArrayList<>();

    private SimpleAdapter simpleAdapter;
    private Toolbar toolbar;
    private List<String> strings = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        recyclerView = findViewById(R.id.rv_project);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initProjectInfo();
        simpleAdapter = new SimpleAdapter(models);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(simpleAdapter);
        simpleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        Log.e(TAG, "onItemClick: 触发了");
                        DialogUtil.createSimpleDialog(mContext);
                        break;
                    case 1:
                        break;
                }
            }
        });
        getLifecycle().addObserver(SingleInstanceLifeObserver.getInstance());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private int COLOR_STARE = 0xffecf5ff;

    private void initProjectInfo() {
        strings.add("Dialog");
        strings.add("算法");
        strings.add("算法");
        strings.add("算法");
        strings.add("算法");
        strings.add("算法");
        strings.add("算法");
        strings.add("算法");
        strings.add("算法");
        strings.add("算法");
        strings.add("算法");
        strings.add("算法");
        strings.add("算法");
        strings.add("算法");
        strings.add("算法");
        strings.add("算法");
        strings.add("算法");
        strings.add("算法");
        strings.add("算法");
        strings.add("算法");
        strings.add("算法");
        strings.add("算法");
        strings.add("算法");
        strings.add("算法");
        strings.add("算法");
        strings.add("算法");
        strings.add("算法");
        strings.add("算法");
        strings.add("算法");
        for (int i = 0; i < strings.size(); i++) {
            SimpleAdapter.SimpleModel model = new SimpleAdapter.SimpleModel();
            model.setName(strings.get(i));
            models.add(model);
        }
    }

}
