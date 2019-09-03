package com.bawie.model;

import android.util.Log;

import com.bawie.app.App;
import com.bawie.contract.IHomeContract;
import com.bawie.utils.HttpUtils;

import java.io.IOException;

public class HomeModel implements IHomeContract.Imodel {
    public final String TAG="HomeModel";
    @Override
    public void getHomeData(String path, IModelCallback iModelCallback) throws IOException {
        if (HttpUtils.getInstance().getnet(App.context)){
            HttpUtils.getInstance().getData(path);
        }else {
            Log.d(TAG, "getHomeData: ");
        }
    }
}
