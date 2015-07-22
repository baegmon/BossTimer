package baegmon.com.bosstimer;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BossAdapter extends ArrayAdapter {
    List list = new ArrayList<>();


    public BossAdapter(Context context, int resource) {
        super(context, resource);
    }

    static class dataHandler{
        ImageView boss;
        TextView name;
        TextView time;
        TextView appearance;
    }

    @Override
    public void add(Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Object getItem(int position) {
        return this.list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row;
        row = convertView;
        dataHandler handler;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.boss_layout, parent, false);
            handler = new dataHandler();
            handler.boss = (ImageView)row.findViewById(R.id.boss_icon);
            handler.name = (TextView)row.findViewById(R.id.boss_name);
            handler.time = (TextView)row.findViewById(R.id.boss_time);
            handler.appearance = (TextView)row.findViewById(R.id.boss_appearance);

            row.setTag(handler);
        } else {
            handler = (dataHandler) row.getTag();
        }

        Boss boss;
        boss = (Boss)this.getItem(position);

        handler.boss.setImageResource(boss.getBoss_icon());
        handler.name.setText(boss.getBoss_name());
        handler.time.setText("(~" + boss.getBoss_time() + ")");
        handler.appearance.setText("다음출현: " + boss.getBoss_appearance());

        return row;
    }
}
