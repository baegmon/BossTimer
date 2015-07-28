package baegmon.com.bosstimer;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Locale;


public class BossAdapter extends BaseAdapter implements Filterable{

    Context context;
    ArrayList<Boss> bossList;
    ArrayList<Boss> bossStringFilterList;
    BossFilter filter;


     BossAdapter(Context context, ArrayList<Boss> bossList) {
         this.context = context;
         this.bossList = bossList;
         bossStringFilterList = bossList;

    }

    @Override
    public int getCount(){
       return bossList.size();
    }

    @Override
    public Object getItem(int position){
        return bossList.get(position);
    }

    @Override
    public long getItemId(int position){
        return bossList.indexOf(getItem(position));
    }

    @Override
    public Filter getFilter() {
        if (filter == null){
            filter  = new BossFilter();
        }
        return filter;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = null;

        if(convertView == null){
            convertView = vi.inflate(R.layout.boss_layout, null);

            ImageView bossIcon = (ImageView)convertView.findViewById(R.id.boss_icon);
            TextView name = (TextView)convertView.findViewById(R.id.boss_name);
            TextView time = (TextView)convertView.findViewById(R.id.boss_time);
            TextView appearance = (TextView)convertView.findViewById(R.id.boss_appearance);

            Boss boss = bossList.get(position);

            bossIcon.setImageResource(boss.getBoss_icon());
            name.setText(boss.getBoss_name());
            time.setText("(~" + boss.getBoss_time() + ")");
            appearance.setText("다음출현: " + boss.getBoss_appearance());

        }
        return convertView;
    }

    private class BossFilter extends Filter{
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults results = new FilterResults();

            constraint = constraint.toString().toLowerCase(Locale.getDefault());
            ArrayList<Boss> filterList = new ArrayList<Boss>();


            if(constraint != null && constraint.length() > 0){

                for(int i =0; i < bossStringFilterList.size(); i++){
                    if((bossStringFilterList.get(i).getBoss_name().toLowerCase()).contains(constraint.toString().toLowerCase())){

                        Boss boss = new Boss(bossStringFilterList.get(i).getBoss_icon(),
                                bossStringFilterList.get(i).getBoss_name(),
                                bossStringFilterList.get(i).getBoss_time(),
                                bossStringFilterList.get(i).getBoss_appearance());


                        filterList.add(boss);

                    }
                }

                results.count = filterList.size();
                results.values = filterList;

            } else {
                results.count = bossStringFilterList.size();
                results.values = bossStringFilterList;
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results){
            bossList = (ArrayList<Boss>)results.values;
            notifyDataSetChanged();

        }
    }
}
