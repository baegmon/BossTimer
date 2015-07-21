package baegmon.com.bosstimer;

import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MainActivity extends ActionBarActivity {
    ListView listView;
    int[] boss_icon = {R.drawable.boss_1, R.drawable.boss_2, R.drawable.boss_3,
            R.drawable.boss_4, R.drawable.boss_5, R.drawable.boss_6};
    String[] boss_title;
    BossAdapter adapter;

    String[] bossTime;
    Handler handler;
    String[] bossAppearance;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list_view);

        boss_title = getResources().getStringArray(R.array.boss_array);
        adapter = new BossAdapter(getApplicationContext(), R.layout.boss_layout);
        listView.setAdapter(adapter);
        bossTime = getTime();

        int i = 0;
        // Moved our object creation here, so that it should only be done once.
        for (String boss : boss_title) {
            bossAppearance = bossAppearance(i);
            Boss bossObject = new Boss(boss_icon[i], boss, bossTime[i], bossAppearance[i]);
            adapter.add(bossObject);
            i++;
        }

        handler = new Handler();
        Runnable update = new Runnable() {
            @Override
            public void run() {
                bossTime = getTime();
                int i = 0;
                bossAppearance = bossAppearance(i);
                int count = adapter.getCount();
                for (i = 0; i < count; i++) {
                    ((Boss) adapter.getItem(i)).setBoss_time(bossTime[i]); // Re-set time
                    ((Boss) adapter.getItem(i)).setBoss_appearance(bossAppearance[i]);
                }
                adapter.notifyDataSetChanged(); // Notify our update
                handler.postDelayed(this , 1000);
            }
        };
        handler.postDelayed(update, 10);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public String[] getTime(){

        String[] time_array = new String[6];
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        String formattedDate = df.format(c.getTime());

        for(int i = 0; i < 6; i++){
            time_array[i] = formattedDate;
        }

        return time_array;

    }

    public String getHour(){

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("HH");
        String formattedHour = df.format(c.getTime());
        return formattedHour;

    }

    public String getMinute(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("mm");
        String formattedMinute = df.format(c.getTime());

        return formattedMinute;
    }

    public String getSecond(){

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("ss");
        String formattedSecond = df.format(c.getTime());

        return formattedSecond;
    }

    public String[] bossAppearance(int i){
        String[] boss_appearance = new String[6];
        int hour,minute,second;
        hour = Integer.parseInt(getHour());
        minute = Integer.parseInt(getMinute());
        second = Integer.parseInt(getSecond());
        switch(i){
            case 0: // 다크 지란트 타이밍

                // 오후 1시 ~ 오후 10시 사이
                if (hour >= 13 && hour <= 22){
                    // 10분전
                    if(minute >= 0 && minute <= 10){
                        boss_appearance[i] = (getHour() + ":10");
                    } else if (minute >= 10 && minute <= 40){
                        // 10분 ~ 40분 사이
                        boss_appearance[i] = (getHour() + ":40");
                    } else {
                        // 40분 이후
                        if(hour == 22){
                            boss_appearance[i] = ((getHour()+1) + ":40");
                        } else {
                            boss_appearance[i] = ((getHour()+1) + ":10");
                        }
                    }
                } else {
                    boss_appearance[i] = (getHour() +  ":40");
                }


                break;
            case 1:
                boss_appearance[i] = (getHour() + "40");
                break;
            case 2:
                boss_appearance[i] = (getHour() + "40");
                break;
            case 3:
                boss_appearance[i] = (getHour() + "40");
                break;
            case 4:
                boss_appearance[i] = (getHour() + "40");
                break;
            case 5:
                boss_appearance[i] = (getHour() + "40");
                break;

            default:
                System.out.println("ERROR");
        }



        return boss_appearance;
    }
}


