package com.azadljy.jardinsecret.page.meterreadingtest;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.azadljy.jardinsecret.R;
import com.azadljy.jardinsecret.adapter.MeterReadingAdapter;
import com.azadljy.jardinsecret.base.BaseActivity;
import com.azadljy.jardinsecret.dialog.DialogUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.azadljy.jardinsecret.page.meterreadingtest.FocusLayoutManager.dp2px;

public class MeterReadingTestActivity extends BaseActivity {

    private MyRecyclerView recyclerView;
    private PagingScrollHelper scrollHelper;
    private SwipeCardLayoutManager swipeCardLayoutManager;
//    PagerSnapHelper

    private MyLayoutManager myLayoutManager;
    private LinearLayoutManager linearLayoutManager;
    private OverFlyingLayoutManager overFlyingLayoutManager;
    FocusLayoutManager focusLayoutManager;
    private MeterReadingAdapter adapter;
    private List<MeterReadingAdapter.MeterReadingModel> modelList;
    private Button btn_save;
    int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meter_reading_test);
        recyclerView = findViewById(R.id.rv_meter_reading_info);
        btn_save = findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPosition = myLayoutManager.getCurrentPosition();
                EditText editTextPressure = (EditText) adapter.getViewByPosition(currentPosition, R.id.et_meter_reading_pressure);
                EditText editTextFlux = (EditText) adapter.getViewByPosition(currentPosition, R.id.et_meter_reading_flux);
                String pressure = editTextPressure.getText().toString();
                String flux = editTextFlux.getText().toString();
                String record = modelList.get(currentPosition).getRecord();
                record = "保存读数:压力: " + pressure + ",流量: " + flux + "\n" + record;
                modelList.get(currentPosition).setPressure(pressure);
                modelList.get(currentPosition).setFlux(flux);
                modelList.get(currentPosition).setRecord(record);

                adapter.notifyItemChanged(currentPosition);
            }
        });
        modelList = new ArrayList<>();
        adapter = new MeterReadingAdapter(R.layout.item_meter_reading_test, modelList);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.btn_save:
                        EditText editTextPressure = (EditText) adapter.getViewByPosition(position, R.id.et_meter_reading_pressure);
                        EditText editTextFlux = (EditText) adapter.getViewByPosition(position, R.id.et_meter_reading_flux);
                        String pressure = editTextPressure.getText().toString();
                        String flux = editTextFlux.getText().toString();
                        String record = modelList.get(position).getRecord();
                        record = "保存读数:压力: " + pressure + ",流量: " + flux + "\n" + record;
                        modelList.get(position).setPressure(pressure);
                        modelList.get(position).setFlux(flux);
                        modelList.get(position).setRecord(record);

                        adapter.notifyItemChanged(position);

                        Log.e(TAG, "onItemChildClick: " + position);
                        break;
                }
            }
        });
        adapter.bindToRecyclerView(recyclerView);
        recyclerView.setAdapter(adapter);
        initMeterReadingData();
//        recyclerView.setOnFlingListener(new RecyclerView.OnFlingListener() {
//            @Override
//            public boolean onFling(int velocityX, int velocityY) {
//                velocityX=1080;
//                return false;
//            }
//        });
//        setRecyclerViewUi();
        setRecyclerViewUI1();
//        setRecyclerViewUI2();
//        setRecyclerViewUiOverFlying();
//        scrollHelper.setUpRecycleView(recyclerView);

    }


    public void setRecyclerViewUi() {
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
//                outRect.right = -2000;
            }
        });

    }

    public void setRecyclerViewUiOverFlying() {
        overFlyingLayoutManager = new OverFlyingLayoutManager(RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(overFlyingLayoutManager);
        SnapHelper snapHelper = new PagerSnapHelper();
//        snapHelper.attachToRecyclerView(recyclerView);
    }

    public void setRecyclerViewUI1() {
        myLayoutManager = new MyLayoutManager();
        recyclerView.setLayoutManager(myLayoutManager);

//        SnapHelper snapHelper = new PagerSnapHelper();
//        snapHelper.attachToRecyclerView(recyclerView);
    }


    public void setRecyclerViewUI2() {
        focusLayoutManager =
                new FocusLayoutManager.Builder()
                        .focusOrientation(FocusLayoutManager.FOCUS_LEFT)
                        .isAutoSelect(true)
                        .maxLayerCount(3)
                        .setOnFocusChangeListener(new FocusLayoutManager.OnFocusChangeListener() {
                            @Override
                            public void onFocusChanged(int focusdPosition, int lastFocusdPosition) {

                            }
                        })
                        .build();
        recyclerView.setLayoutManager(focusLayoutManager);
    }


    private void initMeterReadingData() {

//        DialogUtil.createSimpleDialog(mContext);

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5000; i++) {
                    MeterReadingAdapter.MeterReadingModel model = new MeterReadingAdapter.MeterReadingModel("仪表编号00" + i);
                    modelList.add(model);
                }
                Log.e(TAG, "run: over");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        DialogUtil.dismiss(mContext);
                        adapter.notifyDataSetChanged();
//                        moveToPosition(linearLayoutManager,500000);
                        Log.e(TAG, "run: over");
                    }
                });
            }
        }).start();

    }


    /**
     * RecyclerView 移动到当前位置，
     *
     * @param manager 设置RecyclerView对应的manager
     * @param n       要跳转的位置
     */
    public static void moveToPosition(LinearLayoutManager manager, int n) {
        manager.scrollToPositionWithOffset(n, 0);
        manager.setStackFromEnd(true);
    }


}
