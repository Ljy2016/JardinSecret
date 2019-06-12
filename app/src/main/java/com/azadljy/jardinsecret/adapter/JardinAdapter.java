package com.azadljy.jardinsecret.adapter;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;

import androidx.annotation.Nullable;

import com.azadljy.jardinsecret.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class JardinAdapter extends BaseQuickAdapter<JardinAdapter.JardinModel, BaseViewHolder> {


    public JardinAdapter(int layoutResId, @Nullable List<JardinModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, JardinModel item) {
        helper.setText(R.id.tv_project_name, item.getProjectName());
//        int colors[] = {0xff255779, 0xff3e7492, 0xffa6c0cd};//分别为开始颜色，中间夜色，结束颜色
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, item.getColors());
//        helper.setBackgroundColor(R.id.item_content, item.getBackGround());
        helper.getView(R.id.item_content).setBackground(gradientDrawable);
    }

    public static class JardinModel {
        private String projectName;
        private int icon;
        private int backGround;
        private int[] colors;

        public JardinModel(String projectName, int backGround, int[] colors) {
            this.projectName = projectName;
            this.backGround = backGround;
            this.colors = colors;
        }

        public JardinModel() {
        }

        public String getProjectName() {
            return projectName;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }

        public int getIcon() {
            return icon;
        }

        public void setIcon(int icon) {
            this.icon = icon;
        }

        public int getBackGround() {
            return backGround;
        }

        public void setBackGround(int backGround) {
            this.backGround = backGround;
        }

        public int[] getColors() {
            return colors;
        }

        public void setColors(int[] colors) {
            this.colors = colors;
        }
    }
}
