package com.azadljy.jardinsecret.page.singlechoice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.azadljy.jardinsecret.R;
import com.azadljy.jardinsecret.adapter.SingleChoiceAdapter;
import com.azadljy.jardinsecret.base.BaseActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

public class SingleChoiceActivity extends BaseActivity {

    List<SingleChoiceAdapter.SingleChoiceModel> models = new ArrayList<>();
    SingleChoiceAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_choice);
        recyclerView = findViewById(R.id.rv_single_choice);
        initData();
    }

    @Override
    public void initData() {
        super.initData();
        for (int i = 1; i < 20; i++) {
            models.add(new SingleChoiceAdapter.SingleChoiceModel("条目" + i));
        }
        adapter = new SingleChoiceAdapter(R.layout.item_single_choice, models);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter a, View view, int position) {
                adapter.setChoiceItem(position);
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
