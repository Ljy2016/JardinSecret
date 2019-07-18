package com.azadljy.jardinsecret.page.map;

import android.os.Bundle;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.LocationSource;
import com.azadljy.jardinsecret.R;
import com.azadljy.jardinsecret.base.BaseMapActivity;
import com.azadljy.jardinsecret.util.LocationUtil;
import com.azadljy.pleasantlibrary.utils.PermissionUtil;

public class MapActivity extends BaseMapActivity {

    AMap aMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aMap = mMapView.getMap();
        PermissionUtil.callLocation(mContext, new PermissionUtil.PermissionListener() {
            @Override
            public void onSuccess() {
                elog("已经获取定位权限");
                LocationUtil.showLocationMarker(aMap);
            }

            @Override
            public void onFailure() {
                elog("无法获取定位权限");
            }
        });

    }

    @Override
    public int initContentView() {
        return R.layout.activity_map;
    }

    @Override
    public void initMapView() {
        mMapView = findViewById(R.id.map);
    }


    LocationSource.OnLocationChangedListener mListener;
    AMapLocationClient mlocationClient;
    AMapLocationClientOption mLocationOption;
    AMapLocationListener locationListener;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }
}
