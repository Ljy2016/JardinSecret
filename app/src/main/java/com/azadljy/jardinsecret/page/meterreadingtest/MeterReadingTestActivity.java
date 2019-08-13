package com.azadljy.jardinsecret.page.meterreadingtest;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.azadljy.jardinsecret.App;
import com.azadljy.jardinsecret.R;
import com.azadljy.jardinsecret.adapter.MeterReadingAdapter;
import com.azadljy.jardinsecret.base.BaseActivity;
import com.azadljy.jardinsecret.dialog.DialogUtil;
import com.azadljy.pleasantlibrary.utils.FileUtil;
import com.azadljy.pleasantlibrary.utils.PermissionUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qw.soul.permission.SoulPermission;
import com.qw.soul.permission.callbcak.GoAppDetailCallBack;

import java.util.ArrayList;
import java.util.List;

import static com.azadljy.jardinsecret.page.meterreadingtest.FocusLayoutManager.dp2px;

public class MeterReadingTestActivity extends BaseActivity {

    private MyRecyclerView recyclerView;


    private MyLayoutManager myLayoutManager;

    private MeterReadingAdapter adapter;
    private List<UserBookInfo> userBookInfos = new ArrayList<>();
    private Button btn_save;
    private DBManager dbManager;
    private SQLiteDatabase sqLiteDatabase;
    int currentPosition;
private ViewPager viewPager;


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
                EditText editName = (EditText) adapter.getViewByPosition(currentPosition, R.id.et_username);
                EditText editAddress = (EditText) adapter.getViewByPosition(currentPosition, R.id.et_user_address);
                EditText editType = (EditText) adapter.getViewByPosition(currentPosition, R.id.et_user_type);
                EditText editNumber = (EditText) adapter.getViewByPosition(currentPosition, R.id.et_user_meter_reading);
                String name = editName.getText().toString();
                String address = editAddress.getText().toString();
                String type = editType.getText().toString();
                int number = Integer.parseInt(editNumber.getText().toString());
                userBookInfos.get(currentPosition).setCustomername(name);
                userBookInfos.get(currentPosition).setAccounttype(type);
                userBookInfos.get(currentPosition).setCustomeraddress(address);
                userBookInfos.get(currentPosition).setLastmeterdata(number);
                adapter.notifyItemChanged(currentPosition);
                dbManager.updata(sqLiteDatabase, userBookInfos.get(currentPosition));
            }
        });
        dbManager = new DBManager(mContext);

        adapter = new MeterReadingAdapter(R.layout.item_meter_reading_test, userBookInfos);

        adapter.bindToRecyclerView(recyclerView);
        recyclerView.setAdapter(adapter);

        initMeterReadingData();


        setRecyclerViewUi();

viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
});
    }


    public void setRecyclerViewUi() {
        myLayoutManager = new MyLayoutManager();

        recyclerView.setLayoutManager(myLayoutManager);
    }


    private void initMeterReadingData() {
        PermissionUtil.callReadAndWrite(this, new PermissionUtil.PermissionListener() {
            @Override
            public void onSuccess() {
                sqLiteDatabase = dbManager.getDatabase();
                userBookInfos.addAll(dbManager.query(sqLiteDatabase, null, null, null));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure() {

            }
        });
//        DialogUtil.createSimpleDialog(mContext);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 5000; i++) {
//                    MeterReadingAdapter.MeterReadingModel model = new MeterReadingAdapter.MeterReadingModel("仪表编号00" + i);
//                    modelList.add(model);
//                }
//                Log.e(TAG, "run: over");
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
////                        DialogUtil.dismiss(mContext);
//                        adapter.notifyDataSetChanged();
////                        moveToPosition(linearLayoutManager,500000);
//                        Log.e(TAG, "run: over");
//                    }
//                });
//            }
//        }).start();

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
