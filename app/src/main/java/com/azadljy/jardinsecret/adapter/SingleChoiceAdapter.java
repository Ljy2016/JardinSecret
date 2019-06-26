package com.azadljy.jardinsecret.adapter;

import androidx.annotation.Nullable;

import com.azadljy.jardinsecret.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class SingleChoiceAdapter extends BaseQuickAdapter<SingleChoiceAdapter.SingleChoiceModel, BaseViewHolder> {

    int currentChoiceItem = -1;


    public SingleChoiceAdapter(int layoutResId, @Nullable List<SingleChoiceModel> data) {
        super(layoutResId, data);
    }

    public SingleChoiceAdapter(@Nullable List<SingleChoiceModel> data) {
        super(data);
    }

    public SingleChoiceAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, SingleChoiceModel item) {
        helper.setChecked(R.id.cb_choice, item.isChoice);
        helper.setText(R.id.tv_simple_name, item.getKey());
    }


    public void setChoiceItem(int position) {
        getData().get(position).setChoice();
        notifyItemChanged(position);
        if (currentChoiceItem != -1 && currentChoiceItem != position) {
            getData().get(currentChoiceItem).setChoice(false);
            notifyItemChanged(currentChoiceItem);
        }
        currentChoiceItem = position;
    }


    public static class SingleChoiceModel {
        boolean isChoice;
        String key;

        public SingleChoiceModel() {
        }

        public SingleChoiceModel(String key) {
            this.key = key;
        }


        public boolean isChoice() {
            return isChoice;
        }

        public void setChoice() {
            isChoice = !isChoice;
        }

        public void setChoice(boolean isChoice) {
            this.isChoice = isChoice;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }
}
