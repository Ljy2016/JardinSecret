package com.azadljy.pleasantlibrary.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.azadljy.pleasantlibrary.R;
import com.azadljy.pleasantlibrary.utils.PositionUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 需要整理的功能：
 * 显示子view
 * 子view之间的连线
 * 拖动，手势
 * 子view的大小计算
 */
public class OceanView extends ViewGroup {
    public static final String TAG = "OceanView";

    private List<Point> childPointList = new ArrayList<>();
    private Point centerPoint;
    private Paint linePaint;

    public OceanView(Context context) {
        super(context);
        init();
    }

    public OceanView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public OceanView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        linePaint = new Paint();
        linePaint.setColor(getResources().getColor(R.color.cl_333333));
        linePaint.setAntiAlias(true);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        childPointList.clear();
        int count = getChildCount();
        int angel = 360 / (count - 1);
        int radius;

        for (int i = 0; i < count; i++) {
            radius = new Random().nextInt(200) + 300;
            View childView = getChildAt(i);
            LayoutParams params = childView.getLayoutParams();
            int cl, ct, cr, cb;
            int childX, childY;
            if (i == 0) {
                cl = (r - l - params.width) / 2;
                cr = (r - l + params.width) / 2;
                ct = (b - t - params.height) / 2;
                cb = (b - t + params.height) / 2;
                centerPoint = new Point((r + l) / 2, (b + t) / 2);
            } else {
//                Log.e(TAG, "onLayout: " + i);
//                Log.e(TAG, "onLayout: " + angel);
//                Log.e(TAG, "onLayout: " + radius);
                childX = PositionUtil.getXOnCircle((r - l) / 2, radius, angel * i);
                childY = PositionUtil.getYOnCircle((b - t) / 2, radius, angel * i);
//                Log.e(TAG, "onLayout: " + childX + "---" + childY);
                childPointList.add(new Point(childX, childY));
                cl = childX - (params.width) / 2;
                cr = childX + (params.width) / 2;
                ct = childY - (params.height) / 2;
                cb = childY + (params.height) / 2;


            }
            childView.layout(cl, ct, cr, cb);
        }
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return super.generateLayoutParams(p);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        /*
//             获得此ViewGroup上级容器为其推荐的宽和高，以及计算模式
//        */
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
//
//        //计算出所有的childView的宽和高
//        measureChildren(widthMeasureSpec, heightMeasureSpec);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //若没有设置背景，OnDraw不会调用
        Log.e(TAG, "onDraw: " + childPointList.size());

        super.onDraw(canvas);

    }

    Point movePoint;
    Point lastPoint;


    //先拦截，再处理
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e(TAG, "onInterceptTouchEvent: " + ev.getAction());
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "onTouchEvent: " + event.getAction());

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastPoint = new Point((int) event.getX(), (int) event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                movePoint = new Point((int) event.getX(), (int) event.getY());
                int x, y;

                x = lastPoint.x - movePoint.x;
                y = lastPoint.y - movePoint.y;
                if (Math.abs(x) > 50 || Math.abs(y) > 50) {
                    scrollBy(x, y);
                    lastPoint = movePoint;
                }
                break;
            case MotionEvent.ACTION_UP:
                lastPoint = null;
                movePoint = null;
                break;
        }
        return true;
    }


    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        for (int i = 0; i < childPointList.size(); i++) {
            Point childPoint = childPointList.get(i);
            canvas.drawLine(centerPoint.x, centerPoint.y, childPoint.x, childPoint.y, linePaint);
        }
    }
}
