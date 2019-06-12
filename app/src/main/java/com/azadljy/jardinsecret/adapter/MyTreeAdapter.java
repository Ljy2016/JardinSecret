package com.azadljy.jardinsecret.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.azadljy.jardinsecret.R;
import com.azadljy.pleasantlibrary.adapter.BaseTreeAdapter;
import com.azadljy.pleasantlibrary.model.Node;

import java.util.List;

public class MyTreeAdapter extends BaseTreeAdapter {

    public final static int NodeTypeOne = 1;
    public final static int NodeTypeTwo = 2;
    public final static int NodeTypeThree = 3;

    public MyTreeAdapter(List<Node> data) {
        super(data);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        switch (visibleNodes.get(i).getType()) {
            case NodeTypeOne:
                View listItem = layoutInflater.inflate(R.layout.item_jtest, viewGroup, false);

                return new ViewHolderOne(listItem);
            case NodeTypeTwo:
                View listItem1 = layoutInflater.inflate(R.layout.item_jtest, viewGroup, false);

                return new ViewHolderTwo(listItem1);
            case NodeTypeThree:
                View listItem2 = layoutInflater.inflate(R.layout.item_jtest, viewGroup, false);

                return new ViewHolderThree(listItem2);
        }
        return new ViewHolderOne(null);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {

        if (viewHolder instanceof ViewHolderOne) {
            ((ViewHolderOne) viewHolder).item_content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    expandOrCollapse(i);
                }
            });
            ((ViewHolderOne) viewHolder).tv_test_name.setText(visibleNodes.get(i).getName());
        } else if (viewHolder instanceof ViewHolderTwo) {
            ((ViewHolderTwo) viewHolder).item_content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    expandOrCollapse(i);
                }
            });
            ((ViewHolderTwo) viewHolder).tv_test_name.setText(visibleNodes.get(i).getName());
        } else if (viewHolder instanceof ViewHolderThree) {
            ((ViewHolderThree) viewHolder).item_content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    expandOrCollapse(i);
                }
            });
            ((ViewHolderThree) viewHolder).tv_test_name.setText(visibleNodes.get(i).getName());
        }

    }


    public static class ViewHolderOne extends RecyclerView.ViewHolder {

        TextView tv_test_name;
        RelativeLayout item_content;

        public ViewHolderOne(@NonNull View itemView) {
            super(itemView);
            tv_test_name = itemView.findViewById(R.id.tv_test_name);
            item_content = itemView.findViewById(R.id.item_content);
        }
    }

    public static class ViewHolderTwo extends RecyclerView.ViewHolder {
        TextView tv_test_name;
        RelativeLayout item_content;

        public ViewHolderTwo(@NonNull View itemView) {
            super(itemView);
            tv_test_name = itemView.findViewById(R.id.tv_test_name);
            item_content = itemView.findViewById(R.id.item_content);
        }
    }

    public static class ViewHolderThree extends RecyclerView.ViewHolder {
        TextView tv_test_name;
        RelativeLayout item_content;

        public ViewHolderThree(@NonNull View itemView) {
            super(itemView);
            tv_test_name = itemView.findViewById(R.id.tv_test_name);
            item_content = itemView.findViewById(R.id.item_content);
        }
    }
}
