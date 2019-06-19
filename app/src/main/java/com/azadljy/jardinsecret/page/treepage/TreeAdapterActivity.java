package com.azadljy.jardinsecret.page.treepage;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.azadljy.jardinsecret.R;
import com.azadljy.jardinsecret.adapter.MyTreeAdapter;
import com.azadljy.jardinsecret.base.BaseActivity;
import com.azadljy.jardinsecret.lifecircle.SingleInstanceLifeObserver;
import com.azadljy.pleasantlibrary.model.Node;

import java.util.ArrayList;
import java.util.List;

public class TreeAdapterActivity extends BaseActivity {

    private RecyclerView rv_tree_adapter;
    private MyTreeAdapter myTreeAdapter;
    private List<Node> nodes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_adapter);
        rv_tree_adapter = findViewById(R.id.rv_tree_adapter);
        initData();
        myTreeAdapter = new MyTreeAdapter(nodes);
        rv_tree_adapter.setLayoutManager(new LinearLayoutManager(this));
        rv_tree_adapter.setAdapter(myTreeAdapter);
        getLifecycle().addObserver(SingleInstanceLifeObserver.getInstance());
    }

    private void initData() {
        nodes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Node node = new Node();
            node.setName("一级列表" + i);
            node.setType(MyTreeAdapter.NodeTypeOne);
            List<Node> childList = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                Node child = new Node();
                child.setName("        二级列表" + j);
                child.setParent(node);
                child.setType(MyTreeAdapter.NodeTypeTwo);
                List<Node> childList1 = new ArrayList<>();
                for (int z = 0; z < 10; z++) {
                    Node child1 = new Node();
                    child1.setName("                 三级列表" + z);
                    child1.setParent(child);
                    child1.setType(MyTreeAdapter.NodeTypeThree);
                    childList1.add(child1);
                    nodes.add(child1);
                }
                child.setChildren(childList1);
                childList.add(child);
                nodes.add(child);
            }
            node.setChildren(childList);
            nodes.add(node);
        }
    }
}
