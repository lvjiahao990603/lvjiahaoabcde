package com.bawie.adapte;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawie.bean.Bean;
import com.bawie.gzg.MainActivity;
import com.bawie.gzg.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Myadapter extends BaseAdapter {
    ArrayList<Bean.ResultBean> list;
    Context context;
    public Myadapter(MainActivity mainActivity, ArrayList<Bean.ResultBean> arrayList) {
        this.list=arrayList;
        this.context=mainActivity;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        switch (getItemViewType(position)){
            case 0:
                ViewHolder holder;
                if (convertView == null){
                    convertView = View.inflate(context, R.layout.lay1,null);
                    holder = new ViewHolder();
                    holder.text_1 = convertView.findViewById(R.id.text_1);
                    holder.img_1 = convertView.findViewById(R.id.img_1);
                    convertView.setTag(holder);
                }else{
                    holder = (ViewHolder) convertView.getTag();
                }
                holder.text_1.setText(list.get(position).getCommodityName());
                Glide.with(context).load(list.get(position).getMasterPic()).into(holder.img_1);
                break;
            case 1:
                ViewHolder1 holder1;
                if (convertView == null){
                    convertView = View.inflate(context, R.layout.lay2,null);
                    holder1 = new ViewHolder1();
                    holder1.text_2 = convertView.findViewById(R.id.text_2);
                    holder1.img_2 = convertView.findViewById(R.id.img_2);
                    convertView.setTag(holder1);
                }else{
                    holder1 = (ViewHolder1) convertView.getTag();
                }
                holder1.text_2.setText(list.get(position).getCommodityName());
                Glide.with(context).load(list.get(position).getMasterPic()).into(holder1.img_2);
                break;
        }
        return convertView;
    }
    //多条目的类型
    @Override
    public int getItemViewType(int position) {
        return position%2;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    class ViewHolder{
        private TextView text_1;
        private ImageView img_1;
    }
    class ViewHolder1{
        private ImageView img_2;
        private TextView text_2;
    }
}
