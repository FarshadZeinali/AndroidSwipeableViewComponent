package com.example.myappTest;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.component.view.androidSwipeableViewComponent.SwipeableView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 7 on 7/4/2016.
 */
public class ListViewCustomAdapter extends BaseAdapter
{
    private Activity activity;
    ArrayList<Items> itemses=null;
    public ListViewCustomAdapter(Activity activity,ArrayList<Items> itemses)
    {
        this.activity=activity;
        this.itemses=itemses;
    }

    @Override
    public int getCount() {
        return itemses.size();
    }

    @Override
    public Object getItem(int position) {
        return itemses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return itemses.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder viewHolder=null;
        if(convertView==null)
        {
            LayoutInflater inflater= LayoutInflater.from(activity);
            convertView=inflater.inflate(R.layout.list_item,null);
            viewHolder=new ViewHolder();
            viewHolder.img= (ImageView) convertView.findViewById(R.id.img);
            viewHolder.txt= (TextView) convertView.findViewById(R.id.txt);
            viewHolder.btn_hide= (Button) convertView.findViewById(R.id.btn_hide);
            viewHolder.btn_show= (Button) convertView.findViewById(R.id.btn_show);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder= (ViewHolder) convertView.getTag();
        }

        viewHolder.img.setImageResource(itemses.get(position).getImg());
        viewHolder.txt.setText(itemses.get(position).getName());


        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.btn_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalViewHolder.txt.setVisibility(View.INVISIBLE);
                finalViewHolder.img.setVisibility(View.INVISIBLE);
            }
        });


        viewHolder.btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalViewHolder.txt.setVisibility(View.VISIBLE);
                finalViewHolder.img.setVisibility(View.VISIBLE);
            }
        });

        return convertView;
    }



    public class ViewHolder
    {
        public ImageView img;
        public TextView txt;
        public Button btn_hide;
        public Button btn_show;
    }
}
