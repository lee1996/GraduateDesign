package Adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.leet.graduatedesign.R;

import java.util.List;
import java.util.Map;

/**
 * Created by leet on 17-11-30.
 */

public class MyAdapter extends BaseAdapter {
    private  Context context;
    private List<Map<String,String>> list;
    public MyAdapter(Context context,List<Map<String,String>> list){
        this.context=context;
        this.list=list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view==null){
            view= LayoutInflater.from(context).inflate(R.layout.list_item,viewGroup,false);
            viewHolder=new ViewHolder();
            viewHolder.name=view.findViewById(R.id.name);
            viewHolder.number=view.findViewById(R.id.number);
            view.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) view.getTag();
        }
        Map<String,String> map=list.get(i);
        viewHolder.name.setText(map.get("name"));
        viewHolder.number.setText(map.get("number"));
        return view;
    }

    private static class ViewHolder{
        public TextView name;
        public TextView number;
    }
}
