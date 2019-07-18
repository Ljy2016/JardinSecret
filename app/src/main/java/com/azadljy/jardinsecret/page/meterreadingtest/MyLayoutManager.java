package com.azadljy.jardinsecret.page.meterreadingtest;

import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyLayoutManager extends RecyclerView.LayoutManager {
    public static final String TAG = "MyLayoutManager";
    int mTotalHeight = 0;
    /**
     * 自动选中动画
     */
    private ValueAnimator selectAnimator;
    private long autoSelectMinDuration = 100;
    private long autoSelectMaxDuration = 300;

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }


    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
//        super.onLayoutChildren(recycler, state);
        if (state.getItemCount() == 0) {
            //没有Item可布局，就回收全部临时缓存 (参考自带的LinearLayoutManager)
            //这里的没有Item，是指Adapter里面的数据集，
            //可能临时被清空了，但不确定何时还会继续添加回来
            Log.e("TAG", "onLayoutChildren: 没有item");
            removeAndRecycleAllViews(recycler);
            return;
        }

        if (onceCompleteScrollLength == -1) {
            //因为mFirstVisiPos在下面可能会被改变，所以用tempPosition暂存一下。
            View tempView = recycler.getViewForPosition(0);
            measureChildWithMargins(tempView, 0, 0);
            onceCompleteScrollLength = getDecoratedMeasurementHorizontal(tempView);
//            totalLength = onceCompleteScrollLength * getItemCount();
            mLastVisiPos = getItemCount() - 1;
        }
        //暂时分离和回收全部有效的Item
        detachAndScrapAttachedViews(recycler);

        layoutChildren(recycler, 0);
    }


    int mFirstVisiPos = 0;
    int mLastVisiPos = -1;
    int currentPosition = 0;
    int onceCompleteScrollLength = -1;
    int mHorizontalOffset = 0;
    int totalLength;

    private void initLayout() {
        for (int i = mLastVisiPos; i >= mFirstVisiPos; i--) {

        }
    }


    private int layoutChildren(RecyclerView.Recycler recycler, int dx) {


        //----------------1、边界检测-----------------
        if (dx < 0) {
            //已达左边界
            if (mHorizontalOffset < 0) {
                mHorizontalOffset = dx = 0;
            }
        }

        if (dx > 0) {
            if (mHorizontalOffset > (getItemCount()) * onceCompleteScrollLength - getWidth()) {
                mHorizontalOffset -= dx;
                dx = 0;
            }
        }
        //分离全部的view，放入临时缓存
        detachAndScrapAttachedViews(recycler);


        //当前"一次完整的聚焦滑动"所在的进度百分比.百分比增加方向为向着堆叠移动的方向（即如果为FOCUS_LEFT，从右向左移动fraction将从0%到100%）
        float fraction =
                (Math.abs(mHorizontalOffset) % onceCompleteScrollLength) / (onceCompleteScrollLength * 1.0f);
        //普通区域view偏移量。在一次完整的聚焦滑动期间，其总位移量是一个onceCompleteScrollLength
        float normalViewOffset = onceCompleteScrollLength * fraction;
        boolean isNormalViewOffsetSetted = false;
        boolean isCurrentViewOffsetSetted = false;
//        修正第一个可见的view：mFirstVisiPos。已经滑动了多少个完整的onceCompleteScrollLength就代表滑动了多少个item
        currentPosition = (int) Math.floor(Math.abs(mHorizontalOffset) / onceCompleteScrollLength); //向下取整

        Log.e(TAG, "layoutChildren: " + currentPosition);
        totalLength = onceCompleteScrollLength * mLastVisiPos;

        for (int i = mLastVisiPos > currentPosition + 3 ? currentPosition + 3 : mLastVisiPos; i >= mFirstVisiPos; i--) {

            if (i > currentPosition + 1) {
                View item = recycler.getViewForPosition(i);
                addView(item);
                measureChildWithMargins(item, 0, 0);
                int l, t, r, b;
                l = (int) ((i - 1 - currentPosition) * onceCompleteScrollLength - (fraction * onceCompleteScrollLength));
                t = getPaddingTop();
                r = (int) (l + getDecoratedMeasurementHorizontal(item));
                b = getPaddingTop() + getDecoratedMeasurementVertical(item);
                layoutDecoratedWithMargins(item, l, t, r, b);
            } else if (i == currentPosition + 1) {
                View item = recycler.getViewForPosition(i);
                addView(item);
                measureChildWithMargins(item, 0, 0);
                int l, t, r, b;
                l = 0;
                t = getPaddingTop();
                r = (int) (l + getDecoratedMeasurementHorizontal(item));
                b = getPaddingTop() + getDecoratedMeasurementVertical(item);
                layoutDecoratedWithMargins(item, l, t, r, b);
            } else if (currentPosition == i) {
                View item = recycler.getViewForPosition(i);
                addView(item);
                measureChildWithMargins(item, 0, 0);
                int l, t, r, b;
                l = (int) (0 - (onceCompleteScrollLength * fraction));
                t = getPaddingTop();
                r = l + getDecoratedMeasurementHorizontal(item);
                b = getPaddingTop() + getDecoratedMeasurementVertical(item);
                layoutDecoratedWithMargins(item, l, t, r, b);
            } else {
                if (i < currentPosition - 2) {
                    break;
                }
                View item = recycler.getViewForPosition(i);
                addView(item);
                measureChildWithMargins(item, 0, 0);
                int l, t, r, b;
                l = (int) ((i - currentPosition) * onceCompleteScrollLength - (fraction * onceCompleteScrollLength));
                t = getPaddingTop();
                r = l + getDecoratedMeasurementHorizontal(item);
                b = getPaddingTop() + getDecoratedMeasurementVertical(item);
                layoutDecoratedWithMargins(item, l, t, r, b);
            }
        }
        recycleChildren(recycler);
        return dx;
    }

    /**
     * 回收需回收的Item。
     */
    private void recycleChildren(RecyclerView.Recycler recycler) {
        List<RecyclerView.ViewHolder> scrapList = recycler.getScrapList();
        for (int i = 0; i < scrapList.size(); i++) {
            RecyclerView.ViewHolder holder = scrapList.get(i);
            removeAndRecycleView(holder.itemView, recycler);
        }
    }

    @Override
    public boolean canScrollHorizontally() {
        return true;
    }

    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        //手指从右向左滑动，dx > 0; 手指从左向右滑动，dx < 0;
        Log.e(TAG, "scrollHorizontallyBy: " + dx);
        //位移0、没有子View 当然不移动
        if (dx == 0 || getChildCount() == 0) {
            return 0;
        }
        float fraction =
                (Math.abs(mHorizontalOffset) % onceCompleteScrollLength) / (onceCompleteScrollLength * 1.0f);
        Log.e(TAG, "scrollHorizontallyBy:fraction " + fraction);

        //控制滑动距离，每次只能滑动一页
        if (dx > 0) {//向左滑
            if (fraction * onceCompleteScrollLength + dx >= getWidth()) {
                dx = (int) (getWidth() - fraction * onceCompleteScrollLength);
            }
        } else {//右滑 dx<0
            if (fraction > 0) {
                if ((1 - fraction) * onceCompleteScrollLength - dx > getWidth()) {
                    dx = -(int) (getWidth() - (1 - fraction) * onceCompleteScrollLength);
                }
            }
        }

        mHorizontalOffset += dx;//累加实际滑动距离


        dx = layoutChildren(recycler, dx);

        return dx;
    }


    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
        switch (state) {
            case RecyclerView.SCROLL_STATE_DRAGGING:
                //当手指按下时，停止当前正在播放的动画
                cancelAnimator();
                break;
            case RecyclerView.SCROLL_STATE_IDLE:
                //当列表滚动停止后，判断一下自动选中是否打开

                //找到离目标落点最近的item索引
                smoothScrollToPosition(findShouldSelectPosition());

                break;
            default:
                break;
        }
    }

    /**
     * 平滑滚动到某个位置
     *
     * @param position 目标Item索引
     */
    public void smoothScrollToPosition(int position) {
        if (position > -1 && position < getItemCount()) {
            startValueAnimator(position);
        }
    }

    private void startValueAnimator(int position) {
        cancelAnimator();

        final float distance = getScrollToPositionOffset(position);


        long minDuration = autoSelectMinDuration;
        long maxDuration = autoSelectMaxDuration;
        long duration;
        float distanceFraction = (Math.abs(distance) / (onceCompleteScrollLength));
        if (distance <= onceCompleteScrollLength) {
            duration = (long) (minDuration + (maxDuration - minDuration) * distanceFraction);
        } else {
            duration = (long) (maxDuration * distanceFraction);
        }

        selectAnimator = ValueAnimator.ofFloat(0.0f, distance);
        selectAnimator.setDuration(duration);
        selectAnimator.setInterpolator(new LinearInterpolator());
        final float startedOffset = mHorizontalOffset;

        selectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                if (mHorizontalOffset < 0) {
                    mHorizontalOffset = (int) Math.floor(startedOffset + (float) animation.getAnimatedValue());
                } else {
                    mHorizontalOffset = (int) Math.ceil(startedOffset + (float) animation.getAnimatedValue());
                }
                requestLayout();


            }
        });
        selectAnimator.start();

    }


    public void cancelAnimator() {
        if (selectAnimator != null && (selectAnimator.isStarted() || selectAnimator.isRunning())) {
            selectAnimator.cancel();
        }
    }


    /**
     * 返回应当选中的position
     *
     * @return
     */
    private int findShouldSelectPosition() {
        if (onceCompleteScrollLength == -1 || mFirstVisiPos == -1) {
            return -1;
        }
        int remainder = -1;

        remainder = (int) (Math.abs(mHorizontalOffset) % onceCompleteScrollLength);


        if (remainder >= onceCompleteScrollLength / 2.0f) { //超过一半，应当选中下一项
            if (currentPosition + 1 <= getItemCount() - 1) {
                return currentPosition + 1;
            }
        }

        return currentPosition;
    }


    /**
     * 返回移动到position所需的偏移量
     *
     * @param position
     * @return
     */
    private float getScrollToPositionOffset(int position) {
        return (position * onceCompleteScrollLength - Math.abs(mHorizontalOffset));
    }


    int mVerticalOffset = 0;


    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.
            State state) {
        return 0;
    }

    public int getVerticalVisibleHeight() {
        return getHeight() - getPaddingTop() - getPaddingBottom();
    }


    //模仿LLM Horizontal 源码

    /**
     * 获取某个childView在水平方向所占的空间
     *
     * @param view
     * @return
     */
    public int getDecoratedMeasurementHorizontal(View view) {
        final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)
                view.getLayoutParams();
        return getDecoratedMeasuredWidth(view) + params.leftMargin
                + params.rightMargin;
    }

    /**
     * 获取某个childView在竖直方向所占的空间
     *
     * @param view
     * @return
     */
    public int getDecoratedMeasurementVertical(View view) {
        final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)
                view.getLayoutParams();
        return getDecoratedMeasuredHeight(view) + params.topMargin
                + params.bottomMargin;
    }

    public int getVerticalSpace() {
        return getHeight() - getPaddingTop() - getPaddingBottom();
    }

    public int getHorizontalSpace() {
        return getWidth() - getPaddingLeft() - getPaddingRight();
    }


    @Override
    public void scrollToPosition(int position) {
        Log.e(TAG, "scrollToPosition: "+position);
        Log.e(TAG, "scrollToPosition: "+onceCompleteScrollLength);
        mHorizontalOffset = position * onceCompleteScrollLength;
        requestLayout();
    }

    public int getCurrentPosition() {
        return currentPosition;
    }
}
