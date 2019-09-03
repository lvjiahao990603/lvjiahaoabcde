package com.bawie.gzg;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bawie.adapte.Myadapter;
import com.bawie.base.BaseActivity;
import com.bawie.bean.Bean;
import com.bawie.contract.IHomeContract;
import com.bawie.utils.HttpUtils;
import com.bwei.xlistview.XlistView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements IHomeContract.Iview {

    private XlistView xlistView;
    private HttpUtils httpUtils=HttpUtils.getInstance();
    private ArrayList<Bean.ResultBean>arrayList=new ArrayList<>();
    private int aLong=1;
    @Override
    protected void initData() {
        //调用
        boolean getnet = httpUtils.getnet(MainActivity.this);
        if (getnet!=false){
            new myasync().execute();
            Toast.makeText(MainActivity.this,"网络请求成功",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(MainActivity.this,"网络请求失败",Toast.LENGTH_LONG).show();
        }
        xlistView.setPullLoadEnable(true);
        xlistView.setPullRefreshEnable(true);
        xlistView.setXListViewListener(new XlistView.IXListViewListener() {
            @Override
            public void onRefresh() {
                aLong=1;
                arrayList.clear();
                new myasync().execute();
                xlistView.stopRefresh();
            }

            @Override
            public void onLoadMore() {
                aLong++;
                new myasync().execute();
                xlistView.stopLoadMore();
            }
        });
    }

    @Override
    public void onHomeSuccess() {

    }

    @Override
    public void onHomeFailure(Throwable e) {

    }

    class myasync extends AsyncTask<Integer,Integer,String>{

        private String data;

        @Override
        protected String doInBackground(Integer... integers) {
            String path="http://172.17.8.100/small/commodity/v1/findCommodityByKeyword?keyword=1&page="+aLong+"&count=5";
            data = httpUtils.getData(path);
            return data;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Gson gson = new Gson();
            Bean bean = gson.fromJson(s, Bean.class);
            List<Bean.ResultBean> result = bean.getResult();
            arrayList.addAll(result);
            xlistView.setAdapter(new Myadapter(MainActivity.this,arrayList));
        }
    }

    @Override
    protected void initView() {
        xlistView = findViewById(R.id.xlist);
    }

    @Override
    protected int getlayout() {
        return R.layout.activity_main;
    }
}
