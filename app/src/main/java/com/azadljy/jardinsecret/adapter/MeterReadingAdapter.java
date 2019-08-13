package com.azadljy.jardinsecret.adapter;

import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.azadljy.jardinsecret.R;
import com.azadljy.jardinsecret.page.meterreadingtest.UserBookInfo;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class MeterReadingAdapter extends BaseQuickAdapter<UserBookInfo, BaseViewHolder> {


    public MeterReadingAdapter(int layoutResId, @Nullable List<UserBookInfo> data) {
        super(layoutResId, data);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e(TAG, "onCreateViewHolder: ");
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserBookInfo item) {
        helper.setText(R.id.tv_meter_reading_num, "用户id：" + item.getId());
        helper.setText(R.id.et_username, item.getCustomername());
        helper.setText(R.id.et_user_address, item.getCustomeraddress());
        helper.setText(R.id.et_user_type, item.getAccounttype());
        helper.setText(R.id.et_user_meter_reading, item.getLastmeterdata() + "");
        int color = 0;
        switch (helper.getLayoutPosition() % 4) {
            case 0:
                color = mContext.getResources().getColor(R.color.cl_faaf54);
                break;
            case 1:
                color = mContext.getResources().getColor(R.color.cl_2774d6);
                break;
            case 2:
                color = mContext.getResources().getColor(R.color.cl_389b62);
                break;
            case 3:
                color = mContext.getResources().getColor(R.color.cl_ec5c54);
                break;
        }
        helper.setBackgroundColor(R.id.item_constraintlayout, color);

    }


}
