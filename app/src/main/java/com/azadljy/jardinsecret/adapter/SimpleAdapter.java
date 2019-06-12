package com.azadljy.jardinsecret.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.azadljy.jardinsecret.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class SimpleAdapter extends BaseQuickAdapter<SimpleAdapter.SimpleModel, BaseViewHolder> {


    public SimpleAdapter(int layoutResId, @Nullable List<SimpleModel> data) {
        super(layoutResId, data);
    }

    public SimpleAdapter(@Nullable List<SimpleModel> data) {
        super(R.layout.item_simple, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SimpleModel item) {
        helper.setText(R.id.tv_simple_name, item.getName());
    }

    public static class SimpleModel {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
