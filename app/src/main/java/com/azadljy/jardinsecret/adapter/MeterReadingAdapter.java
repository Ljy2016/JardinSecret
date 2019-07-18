package com.azadljy.jardinsecret.adapter;

import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.azadljy.jardinsecret.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class MeterReadingAdapter extends BaseQuickAdapter<MeterReadingAdapter.MeterReadingModel, BaseViewHolder> {


    public MeterReadingAdapter(int layoutResId, @Nullable List<MeterReadingModel> data) {
        super(layoutResId, data);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e(TAG, "onCreateViewHolder: ");
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    protected void convert(BaseViewHolder helper, MeterReadingModel item) {
        helper.setText(R.id.tv_meter_reading_name, item.getProjectName());
        helper.setText(R.id.et_meter_reading_pressure, item.getPressure());
        helper.setText(R.id.et_meter_reading_flux, item.getFlux());
        helper.setText(R.id.tv_record, item.getRecord());
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
        helper.addOnClickListener(R.id.btn_save);

    }

    public static class MeterReadingModel {
        private String projectName;
        private String pressure;
        private String flux;
        private String record = "";

        public MeterReadingModel(String projectName) {
            this.projectName = projectName;

        }

        public MeterReadingModel() {
        }

        public String getPressure() {
            return pressure;
        }

        public void setPressure(String pressure) {
            this.pressure = pressure;
        }

        public String getFlux() {
            return flux;
        }

        public void setFlux(String flux) {
            this.flux = flux;
        }

        public String getRecord() {
            return record;
        }

        public void setRecord(String record) {
            this.record = record;
        }

        public String getProjectName() {
            return projectName;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }

    }
}
