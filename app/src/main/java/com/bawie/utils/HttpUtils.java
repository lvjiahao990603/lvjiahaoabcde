package com.bawie.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {
    //单例模式
    private static HttpUtils httpUtils = new HttpUtils();

    public HttpUtils(){

    }
    public static HttpUtils getInstance(){
        return httpUtils;
    }
    //网络判断
    public boolean getnet(Context context){
        if (context!=null){
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo!=null){
                return networkInfo.isAvailable();
            }
        }
        return false;
    }
    //网络请求
    public String getData(String s){
        try {
            //new个Url
            URL url=new URL(s);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            //设置参数
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(5000);
            urlConnection.setReadTimeout(5000);
            int code = urlConnection.getResponseCode();
            if (code==200){
                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String str="";
                StringBuilder builder = new StringBuilder();
                while ((str=reader.readLine())!=null){
                    builder.append(str);
                }
                reader.close();
                return builder.toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
