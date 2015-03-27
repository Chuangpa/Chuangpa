package com.chuangpa.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.chuangpa.bean.HomeInfo;

import java.util.List;

import chuangpa.com.chuangpa.R;

/**
 * Created by Lan on 2015-03-27.
 */
public class HomeListViewAdapter extends BaseAdapter{

    private ListView listView;
    private List list;
    private Context context;
    private LayoutInflater layoutInflater;

    public HomeListViewAdapter(Context context,ListView listView,List list){
        this.context = context;
        this.list = list;
        this.listView = listView;
        this.layoutInflater = LayoutInflater.from(context);
    }

    private static class ListItemView{
        public ImageView img;
        public TextView txtContent;
        public TextView txtName;
        public TextView txtType;
        public TextView txtHot;

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
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListItemView listItemView = null;
        if(convertView==null){
            convertView = layoutInflater.inflate(R.layout.item_layout,null);
            listItemView = new ListItemView();
            listItemView.img = (ImageView)convertView.findViewById(R.id.user_head);
            listItemView.txtName=(TextView)convertView.findViewById(R.id.user_name);
            listItemView.txtContent=(TextView)convertView.findViewById(R.id.txt_content);
            listItemView.txtType = (TextView)convertView.findViewById(R.id.content_type);
            listItemView.txtHot = (TextView)convertView.findViewById(R.id.content_hot);
            convertView.setTag(listItemView);
        }else{
            listItemView = (ListItemView)convertView.getTag();
        }
        HomeInfo homeInfo = (HomeInfo)(list.get(position));
        listItemView.txtContent.setText(Html.fromHtml(homeInfo.getContent()));
        listItemView.txtName.setText(homeInfo.getName());

        if(homeInfo.getType()==0){
            listItemView.txtType.setText("日\n志");
            listItemView.txtType.setBackgroundResource(R.drawable.icon_tip);
            listItemView.txtHot.setVisibility(View.INVISIBLE);

        }else if(homeInfo.getType()==1){
            listItemView.txtType.setText("点\n子");
            listItemView.txtType.setBackgroundResource(R.drawable.icon_tip2);
            listItemView.txtHot.setVisibility(View.INVISIBLE);
        }else if(homeInfo.getType()==2){
            listItemView.txtType.setText("招\n募");
            listItemView.txtType.setBackgroundResource(R.drawable.icon_tip3);
            listItemView.txtHot.setVisibility(View.VISIBLE);
        }





        return convertView;
    }

    public void refreshData(List list){
        this.list = list;
        notifyDataSetChanged();
    }
}
