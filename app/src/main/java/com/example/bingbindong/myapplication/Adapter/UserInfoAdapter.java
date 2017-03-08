package com.example.bingbindong.myapplication.Adapter;

import java.util.ArrayList;
import java.util.List;


import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;

import com.example.bingbindong.myapplication.NextActivity;
import com.example.bingbindong.myapplication.R;


/**
 * Created by bingbin.dong on 2016/10/21.
 */

public class UserInfoAdapter extends BaseAdapter {

    private LayoutInflater myLayoutInflater = null;
    private Context context = null;
    private Button.OnClickListener myButtonListener = null;
    private ArrayList<UserInfo> _users=null;



    private static class ViewHolder{//创建一个内部类ViewHolder，设置选项布局中的元素
        public TextView dynamicTextView = null;
        public Button checkButton = null;
    }
    public void setItemCheckButtonListener(Button.OnClickListener myButtonListener){
        this.myButtonListener = myButtonListener;
    }
    public UserInfoAdapter(ArrayList<UserInfo> users,Context context)
    {
        this.context = context;

        this._users = users;
        this.myLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        int count = 0;
        if(this._users==null){
            return count;
        }else
            return this._users.size();
    }

    @Override
    public Object getItem(int position) {
        //return this._users.get(position);
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null)
        {
            holder = new  ViewHolder();
            convertView = myLayoutInflater.inflate(R.layout.activity_userinfo_cell_layout,null);
            holder.checkButton = (Button)convertView.findViewById(R.id.my_button);
            holder.dynamicTextView = (TextView)convertView.findViewById(R.id.my_tv);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.dynamicTextView.setText(this._users.get(position).get_name());
        holder.checkButton.setText(this._users.get(position).get_age());
        holder.checkButton.setTag(position);

        holder.checkButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                showDetail(position);
            }
        });
        return convertView;
    }

    private void showDetail(int position)
    {
        Toast.makeText(context, this._users.get(position).get_name(), Toast.LENGTH_SHORT).show();
    }

}
