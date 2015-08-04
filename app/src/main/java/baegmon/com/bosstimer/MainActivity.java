package baegmon.com.bosstimer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;


public class MainActivity extends Activity  {

    int[] boss_icon = {R.drawable.boss_1, R.drawable.boss_2, R.drawable.boss_3,
            R.drawable.boss_4, R.drawable.boss_5, R.drawable.boss_6, R.drawable.boss_7,
            R.drawable.boss_8, R.drawable.boss_9, R.drawable.boss_10, R.drawable.boss_11,
            R.drawable.boss_12, R.drawable.boss_13, R.drawable.boss_14, R.drawable.boss_15,
            R.drawable.boss_16, R.drawable.boss_17, R.drawable.boss_18, R.drawable.boss_19,
            R.drawable.boss_20, R.drawable.boss_21, R.drawable.boss_22, R.drawable.boss_23, R.drawable.boss_24,
            R.drawable.boss_25, R.drawable.boss_26, R.drawable.boss_27, R.drawable.boss_28, R.drawable.boss_29,
            R.drawable.boss_30};

    String[] boss_title = {"다크 지란트", "머쉬맘", "마크52 알파", "깡패 바라하", "데블린 워리어",
            "닉시" , "에피", "자이언트 라바아이", "둔둔", "레버넌트 좀비", "우르자", "부기콜리",
    "그리폰", "프랑케네뜨", "경비대장 차우", "그리피나", "매드오네뜨", "자이언트 터틀", "알파 터틀",
    "마노", "로로와 무무스", "우르판다", "분노의 바포메트", "스텀피", "킹슬라임", "소환술사 라툰",
            "좀비머쉬맘", "레르노스", "바야르 수문장", "바람술사 라팽",


    };
    ArrayList<Boss> bossList;
    int bossID;
    BossAdapter adapter;
    Handler handler;
    EditText search_view;
    ListView listView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdView ad = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        ad.loadAd(adRequest);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        String currentTime = format.format(c.getTime());
        System.out.println(currentTime);
        createListview();

    }



    private String getBossLevel(String bossName){
        String level = "";
        if(bossName.equals("레버넌트 좀비")){
            level = "22";
        }
        if(bossName.equals("에피")){
            level =  "13";
        }

        if(bossName.equals("닉시")){
            level = "15";
        }

        if(bossName.equals("다크 지란트")){
            level = "30";
        }

        if (bossName.equals("마크52 알파")) {
            level = "27";
        }

        if(bossName.equals("자이언트 라바아이")){
            level = "30";
        }

        if(bossName.equals("부기콜리")){
            level = "3";
        }

        if(bossName.equals("자이언트 터틀")){
            level = "18";
        }

        if(bossName.equals("경비대장 차우")){
            level = "12";
        }

        if(bossName.equals("깡패 바라하")){
            level = "24";
        }

        if(bossName.equals("우르자")){
            level = "8";
        }

        if(bossName.equals("머쉬맘")){
            level = "21";
        }

        if(bossName.equals("데블린 워리어")){
            level = "21";
        }

        if(bossName.equals("둔둔")){
            level = "15";
        }

        if(bossName.equals("그리폰")){
            level = "23";
        }

        if(bossName.equals("그리피나")){
            level = "30";
        }

        if (bossName.equals("매드오네뜨")){
            level = "17";

        }

        if (bossName.equals("프랑케네뜨")){
            level = "26";
        }

        if (bossName.equals("분노의 바포메트") || bossName.equals("레르노스") || bossName.equals("우르판다")
                || bossName.equals("소환술사 라툰")){
            level = "40";
        }

        if (bossName.equals("바람술사 라팽")){
            level = "38";
        }

        if (bossName.equals("알파 터틀") || bossName.equals("마노")){
            level = "37";
        }

        if (bossName.equals("바야르 수문장") || bossName.equals("킹슬라임")){
            level = "35";
        }

        if (bossName.equals("스텀피")){
            level = "34";
        }

        if (bossName.equals("로로와 무무스") || bossName.equals("좀비머쉬맘")){
            level = "32";
        }

        return level;
    }


    private int getID(String name){
        int id = 99;

        switch(name){
            case "다크 지란트":
                id = 0;
                return id;
            case "머쉬맘":
                id = 1;
                return id;
            case "마크52 알파":
                id = 2;
                return id;
            case "깡패 바라하":
                id = 3;
                return id;
            case "데블린 워리어":
                id = 4;
                return id;
            case "닉시":
                id = 5;
                return id;
            case "에피":
                id = 6;
                return id;
            case "자이언트 라바아이":
                id = 7;
                return id;
            case "둔둔":
                id = 8;
                return id;
            case "레버넌트 좀비":
                id = 9;
                return id;
            case "우르자":
                id = 10;
                return id;
            case "부기콜리":
                id = 11;
                return id;
            case "그리폰":
                id = 12;
                return id;
            case "프랑케네뜨":
                id = 13;
                return id;
            case "경비대장 차우":
                id = 14;
                return id;
            case "그리피나":
                id = 15;
                return id;
            case "매드오네뜨":
                id = 16;
                return id;
            case "자이언트 터틀":
                id = 17;
                return id;
            case "알파 터틀":
                id = 18;
                return id;
            case "마노":
                id = 19;
                return id;
            case "로로와 무무스":
                id = 20;
                return id;
            case "우르판다":
                id = 21;
                return id;
            case "분노의 바포메트":
                id = 22;
                return id;
            case "스텀피":
                id = 23;
                return id;
            case "킹슬라임":
                id = 24;
                return id;
            case "소환술사 라툰":
                id = 25;
                return id;
            case "좀비머쉬맘":
                id = 26;
                return id;
            case "레르노스":
                id = 27;
                return id;
            case "바야르 수문장":
                id = 28;
                return id;
            case "바람술사 라팽":
                id = 29;
                return id;
        }

        return id;
    }


    private void createListview(){
        listView = (ListView) findViewById(R.id.list_view);
        search_view = (EditText)findViewById(R.id.search_text);

        bossList = new ArrayList<Boss>();
        adapter = new BossAdapter(getApplicationContext(), bossList);


        listView.setAdapter(adapter);
        listView.setTextFilterEnabled(true);


        for (String boss : boss_title) {
            bossID = getID(boss);
            String bossAppearance = getBossAppearance(boss);
            String bossTime = getKoreanTime(boss);
            String bossLevel = getBossLevel(boss);

            if(bossAppearance.substring(0, 2).contains("24")) {

                bossAppearance = "00" + bossAppearance.substring(2, 5);
            }

            if(bossAppearance.length() == 4){
                bossAppearance = "0" + bossAppearance;
            }


            Boss bossObject = new Boss(boss_icon[bossID], boss, bossTime, bossAppearance, bossLevel);
            bossList.add(bossObject);

        }
        // set action when custom list view is clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3)
            {
                Boss b = (Boss) listView.getItemAtPosition(position);
                String bossName = b.getBoss_name();
                Intent i = new Intent(getApplicationContext(), DetailActivity.class);
                i.putExtra("bossName", bossName);
                startActivity(i);


            }
        });


        handler = new Handler();
        Runnable update = new Runnable() {
            @Override
            public void run() {


                int count = adapter.getCount();
                for (int i = 0; i < count; i++) {
                    String boss = ((Boss) adapter.getItem(i)).getBoss_name();
                    String bossTime = getKoreanTime(boss);
                    ((Boss) adapter.getItem(i)).setBoss_time(bossTime); // Re-set time
                }

                adapter.notifyDataSetChanged(); // Notify our update
                handler.postDelayed(this, 1000);
            }
        };
        handler.postDelayed(update, 10);


        EditText myFilter = (EditText) findViewById(R.id.search_text);
        myFilter.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s.toString());
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public String getKoreanTime(String bossName){
        String boss_appearance;
        String format_string;
        String return_string = "";
        Calendar seoulTime = new GregorianCalendar(TimeZone.getTimeZone("Asia/Seoul"));

        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, seoulTime.get(Calendar.HOUR_OF_DAY));
        c.set(Calendar.MINUTE, seoulTime.get(Calendar.MINUTE));
        c.set(Calendar.SECOND, seoulTime.get(Calendar.SECOND));


        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        String currentTime = format.format(c.getTime());
        Date date1;
        try {
            Date date2 = format.parse(currentTime);
            boss_appearance = getBossAppearance(bossName);
            if(!boss_appearance.isEmpty()){
                format_string = boss_appearance + ":00";
                date1 = format.parse(format_string);

                Long diff = date1.getTime() - date2.getTime();
                long diffSeconds = diff / 1000 % 60;
                long diffMinutes = diff / (60 * 1000) % 60;
                long diffHours = diff / (60 * 60 * 1000) % 24;
                String finalTime = "";

                if(diffMinutes <= 9 && diffHours <= 9 && diffSeconds <= 9){
                    finalTime =  "0" + diffHours + ":0" + diffMinutes + ":0" + diffSeconds;
                } else if (diffMinutes <= 9 && diffHours <= 9){
                    finalTime =  "0" + diffHours + ":0" + diffMinutes + ":" + diffSeconds;
                } else if (diffMinutes <=9 && diffSeconds <= 9){
                    finalTime =  diffHours + ":0" + diffMinutes + ":0" + diffSeconds;
                } else if (diffHours <= 9 && diffSeconds <= 9){
                    finalTime =  "0" + diffHours + ":" + diffMinutes + ":0" + diffSeconds;
                } else if (diffHours <= 9){
                    finalTime =  "0" + diffHours + ":" + diffMinutes + ":" + diffSeconds;
                } else if (diffMinutes <= 9){
                    finalTime =  diffHours + ":0" + diffMinutes + ":" + diffSeconds;
                } else if (diffSeconds <= 9){
                    finalTime =   diffHours + ":" + diffMinutes + ":0" + diffSeconds;
                } else {
                    finalTime =  diffHours + ":" + diffMinutes + ":" + diffSeconds;
                }
                return_string = finalTime;
            }
        } catch (ParseException e){
            e.printStackTrace();
        }

        return return_string;
    }


    public String getBossAppearance(String bossName){
        String boss_appearance = "";

        Calendar seoulTime = new GregorianCalendar(TimeZone.getTimeZone("Asia/Seoul"));

        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, seoulTime.get(Calendar.HOUR_OF_DAY));
        c.set(Calendar.MINUTE, seoulTime.get(Calendar.MINUTE));
        c.set(Calendar.SECOND, seoulTime.get(Calendar.SECOND));

        SimpleDateFormat hour = new SimpleDateFormat("HH");
        SimpleDateFormat minute = new SimpleDateFormat("mm");
        String formattedHour = hour.format(c.getTime());
        String formattedMinute = minute.format(c.getTime());
        Long hourValue = Long.parseLong(formattedHour);
        Long minuteValue = Long.parseLong(formattedMinute);

        if(bossName == "다크 지란트"){
            if(hourValue >= 0  && hourValue <= 8){
                if(minuteValue < 40){
                    boss_appearance =  hourValue +  ":40";
                } else {
                    boss_appearance =  hourValue + 1 + ":40";
                }
            } else if (hourValue >= 9 && hourValue <= 11){
                if(minuteValue < 40){
                    if(hourValue == 9){
                        boss_appearance =   hourValue +  ":40";
                    } else {
                        boss_appearance =   hourValue +  ":40";
                    }

                } else {
                    boss_appearance =  hourValue + 1 + ":40";
                }
            } else if (hourValue == 12){
                if(minuteValue < 40){
                    boss_appearance = hourValue + ":40";
                } else if (minuteValue >= 40){
                    boss_appearance = hourValue + 1 + ":10";
                }
            } else if (hourValue >= 13 && hourValue <= 21) {
                if (minuteValue < 10) {
                    boss_appearance = hourValue + ":10";
                } else if (minuteValue >= 10 && minuteValue < 40) {
                    boss_appearance = hourValue + ":40";
                } else {
                    boss_appearance = hourValue + 1 + ":10";
                }
            } else if (hourValue == 22){
                if (minuteValue < 10) {
                    boss_appearance = hourValue + ":10";
                } else if (minuteValue >= 10 && minuteValue < 40){
                    boss_appearance = hourValue + ":40";
                } else{
                    boss_appearance = hourValue+1 + ":40";
                }
            } else if (hourValue == 23){
                if(minuteValue < 40){
                    boss_appearance =  hourValue +  ":40";
                } else {
                    boss_appearance =   hourValue + 1 + ":40";
                }
            }
        }

        if (bossName == "머쉬맘"){
            if(hourValue >= 0  && hourValue <= 8){
                if(minuteValue < 40){
                    boss_appearance = hourValue +  ":40";
                } else {
                    boss_appearance = hourValue + 1 + ":40";
                }
            } else if (hourValue >= 9 && hourValue <= 11){
                if(minuteValue < 40){
                    if(hourValue == 9){
                        boss_appearance =  hourValue +  ":40";
                    } else {
                        boss_appearance =   hourValue +  ":40";
                    }

                } else {
                    boss_appearance =  hourValue + 1 + ":40";
                }
            } else if (hourValue == 12){
                if(minuteValue < 40){
                    boss_appearance = hourValue + ":40";
                } else if (minuteValue >= 40){
                    boss_appearance = hourValue + 1 + ":10";
                }
            } else if (hourValue >= 13 && hourValue <= 21) {
                if (minuteValue < 10) {
                    boss_appearance = hourValue + ":10";
                } else if (minuteValue >= 10 && minuteValue < 40) {
                    boss_appearance = hourValue + ":40";
                } else {
                    boss_appearance = hourValue + 1 + ":10";
                }
            } else if (hourValue == 22){
                if (minuteValue < 10) {
                    boss_appearance = hourValue + ":10";
                } else if (minuteValue >= 10 && minuteValue < 40){
                    boss_appearance = hourValue + ":40";
                } else{
                    boss_appearance = hourValue+1 + ":40";
                }
            } else if (hourValue == 23){
                if(minuteValue < 40){
                    boss_appearance =  hourValue +  ":40";
                } else {
                    boss_appearance =  hourValue + 1 + ":40";
                }
            }
        }


        if(bossName == "마크52 알파"){

            if(hourValue == 0){
                if(minuteValue < 45){
                    boss_appearance =  hourValue + ":45";
                } else {
                    boss_appearance = hourValue+2 + ":15";
                }
            } else if (hourValue == 1){
                boss_appearance =  hourValue+1 + ":15";
            } else if (hourValue == 2){
                if(minuteValue < 15){
                    boss_appearance =hourValue + ":45";
                } else {
                    boss_appearance =  hourValue+1 + ":45";
                }
            } else if (hourValue == 3){
                if(minuteValue < 45){
                    boss_appearance =  hourValue + ":45";
                } else {
                    boss_appearance = hourValue+2 + ":15";
                }
            } else if (hourValue == 4){
                boss_appearance =  hourValue+1 + ":15";
            } else if (hourValue == 5){
                if(minuteValue < 15){
                    boss_appearance =  hourValue + ":15";
                } else {
                    boss_appearance =  hourValue+1 + ":45";
                }
            } else if (hourValue == 6){
                if(minuteValue < 45){
                    boss_appearance =  hourValue + ":45";
                } else {
                    boss_appearance =  hourValue+2 + ":15";
                }
            } else if (hourValue == 7){
                boss_appearance = hourValue+1 + ":15";
            } else if (hourValue == 8){
                if(minuteValue < 15){
                    boss_appearance = hourValue + ":15";
                } else {
                    boss_appearance = hourValue+1 + ":45";
                }
            } else if (hourValue == 9){
                if(minuteValue < 45){
                    boss_appearance = hourValue + ":45";
                } else {
                    boss_appearance = hourValue+2 + ":15";
                }
            } else if (hourValue == 10){
                boss_appearance = hourValue+1 + ":15";
            } else if (hourValue == 11){
                if(minuteValue < 15){
                    boss_appearance = hourValue + ":15";
                } else {
                    boss_appearance = hourValue+1 + ":45";
                }
            } else if (hourValue >= 12 && hourValue <= 20){
                if(minuteValue < 45){
                    boss_appearance = hourValue + ":45";
                } else {
                    boss_appearance = hourValue+1 + ":45";
                }
            } else if (hourValue == 21){
                if(minuteValue < 45){
                    boss_appearance = hourValue + ":45";
                } else {
                    boss_appearance = hourValue+2 + ":15";
                }
            } else if (hourValue == 22){
                boss_appearance = hourValue+1 + ":15";
            } else if (hourValue == 23){
                if(minuteValue < 15){
                    boss_appearance = hourValue + ":15";
                } else {
                    boss_appearance = hourValue+1 + ":45";
                }
            }

        }

        if(bossName == "깡패 바라하"){
            if(hourValue >= 0 && hourValue <= 9){
                if(minuteValue < 55){
                    boss_appearance =  hourValue + ":55";
                } else {
                    if(hourValue == 9){
                        boss_appearance = hourValue+1 + ":55";
                    } else {
                        boss_appearance =  hourValue+1 + ":55";
                    }
                }
            } else if (hourValue >= 10 && hourValue <= 11){
                if(minuteValue < 55){
                    boss_appearance =  hourValue + ":55";
                } else {
                    boss_appearance = hourValue+1 + ":55";
                }
            } else if (hourValue == 12){
                if (minuteValue < 55){
                    boss_appearance = hourValue + ":55";
                } else {
                    boss_appearance = hourValue + 1 + ":25";
                }
            } else if (hourValue >= 13 && hourValue <= 21){
                if(minuteValue < 25){
                    boss_appearance = hourValue + ":25";
                } else if (minuteValue >= 25 && minuteValue < 55){
                    boss_appearance = hourValue + ":55";
                } else {
                    boss_appearance = hourValue+1 + ":25";
                }
            } else if (hourValue == 22){
                if(minuteValue < 25){
                    boss_appearance = hourValue + ":25";
                } else if (minuteValue >= 25 && minuteValue < 55){
                    boss_appearance = hourValue + ":55";
                } else {
                    boss_appearance = hourValue+1 + ":55";
                }
            } else if (hourValue == 23){
                if(minuteValue < 55){
                    boss_appearance = hourValue + ":55";
                } else {
                    boss_appearance = hourValue + 1 + ":55";
                }

            }

        }

        if(bossName == "데블린 워리어") {
            if (hourValue == 0) {
                if (minuteValue < 55) {
                    boss_appearance =  hourValue + ":55";
                } else {
                    boss_appearance =  hourValue + 2 + ":55";
                }
            } else if (hourValue == 1) {
                boss_appearance =  hourValue + 1 + ":25";
            } else if (hourValue == 2) {
                if (minuteValue < 25) {
                    boss_appearance =  hourValue + ":25";
                } else {
                    boss_appearance =  hourValue + 1 + ":55";
                }
            } else if (hourValue == 3) {
                if (minuteValue < 55) {
                    boss_appearance =  hourValue + ":55";
                } else {
                    boss_appearance =  hourValue + 2 + ":25";
                }
            } else if (hourValue == 4){
                boss_appearance =  hourValue+1 + ":25";
            } else if (hourValue == 5){
                if(minuteValue < 25){
                    boss_appearance =  hourValue + ":25";
                } else {
                    boss_appearance =  hourValue+1 + ":55";
                }
            } else if (hourValue == 6){
                if(minuteValue < 55){
                    boss_appearance =  hourValue + ":55";
                } else {
                    boss_appearance =  hourValue+2 + ":25";
                }
            } else if (hourValue == 7){
                boss_appearance =  hourValue+1 + ":25";
            } else if (hourValue == 8){
                if(minuteValue < 25){
                    boss_appearance =  hourValue + ":25";
                } else {
                    boss_appearance =  hourValue+1 + ":55";
                }
            } else if (hourValue == 9){
                if(minuteValue < 55){
                    boss_appearance =  hourValue + ":55";
                } else {
                    boss_appearance = hourValue +2+ ":25";
                }
            } else if (hourValue == 10){
                boss_appearance = hourValue+1 + ":25";
            } else if (hourValue == 11){
                if(minuteValue < 25){
                    boss_appearance = hourValue + ":25";
                } else {
                    boss_appearance = hourValue+1 + ":55";
                }
            } else if (hourValue >= 12 && hourValue <= 20){
                if(minuteValue < 55){
                    boss_appearance = hourValue + ":55";
                } else {
                    boss_appearance = hourValue+1 + ":55";
                }
            } else if (hourValue == 21){
                if(minuteValue < 55){
                    boss_appearance = hourValue + ":55";
                } else {
                    boss_appearance = hourValue+2 + ":25";
                }
            } else if (hourValue == 22){
                boss_appearance = hourValue+1 + ":25";
            } else if (hourValue == 23){
                if(minuteValue < 25){
                    boss_appearance = hourValue + ":25";
                } else {
                    boss_appearance = hourValue+1 + ":55";
                }
            }

        }

        if(bossName == "닉시"){

            if(hourValue >= 0 && hourValue <= 12){
                if(hourValue >= 0 && hourValue <= 9){
                    if(minuteValue < 5){
                        boss_appearance =  hourValue + ":05";
                    } else{
                        if(hourValue < 9){
                            boss_appearance =   hourValue + 1 + ":05";
                        } else {
                            boss_appearance =   hourValue + 1 + ":05";
                        }

                    }
                } else{
                    if(minuteValue < 5){
                        boss_appearance = hourValue + ":05";
                    } else{
                        boss_appearance = hourValue + 1 + ":05";
                    }
                }
            } else if (hourValue >= 13 && hourValue <= 22){
                if(minuteValue < 5){
                    boss_appearance = hourValue + ":05";
                } else if (minuteValue >= 5 && minuteValue < 35){
                    boss_appearance = hourValue + ":35";
                } else{
                    boss_appearance = hourValue + 1 + ":05";
                }
            } else if (hourValue == 23){
                if(minuteValue < 5){
                    boss_appearance = hourValue + ":05";
                } else{
                    boss_appearance = hourValue + 1 + ":05";
                }
            }

        }

        if(bossName == "에피"){
            if(hourValue >= 0 && hourValue <= 12){
                if(hourValue >= 0 && hourValue <= 9){
                    if(minuteValue < 5){
                        boss_appearance =  hourValue + ":05";
                    } else{
                        if(hourValue < 9){
                            boss_appearance =  hourValue + 1 + ":05";
                        } else {
                            boss_appearance = hourValue + 1 + ":05";
                        }

                    }
                } else {
                    if(minuteValue < 5){
                        boss_appearance = hourValue + ":05";
                    } else {
                        boss_appearance = hourValue + 1 + ":05";
                    }

                }
            } else if (hourValue >= 13 && hourValue <= 22){
                if(minuteValue < 5){
                    boss_appearance = hourValue + ":05";
                } else if (minuteValue >= 5 && minuteValue < 35){
                    boss_appearance = hourValue + ":35";
                } else{
                    boss_appearance = hourValue + 1 + ":05";
                }
            } else if (hourValue == 23){
                if(minuteValue < 5){
                    boss_appearance = hourValue + ":05";
                } else{
                    boss_appearance = hourValue + 1 + ":05";
                }
            }
        }

        if(bossName == "자이언트 라바아이"){
            if(hourValue >= 0 && hourValue <= 12){
                if(hourValue >= 0 && hourValue <= 9){
                    if(minuteValue < 5){
                        boss_appearance =  hourValue + ":05";
                    } else{
                        if(hourValue < 9){
                            boss_appearance =  hourValue + 1 + ":05";
                        }else {
                            boss_appearance = hourValue + 1 + ":05";
                        }

                    }
                } else {
                    if(minuteValue < 5){
                        boss_appearance = hourValue + ":05";
                    } else{
                        boss_appearance = hourValue + 1 + ":05";
                    }
                }
            } else if (hourValue >= 13 && hourValue <= 22){
                if(minuteValue < 5){
                    boss_appearance = hourValue + ":05";
                } else if (minuteValue >= 5 && minuteValue < 35){
                    boss_appearance = hourValue + ":35";
                } else{
                    boss_appearance = hourValue + 1 + ":05";
                }
            } else if (hourValue == 23){
                if(minuteValue < 5){
                    boss_appearance = hourValue + ":05";
                } else{
                    boss_appearance = hourValue + 1 + ":05";
                }
            }
        }

        if(bossName == "둔둔"){
            if(hourValue == 0){
                if(minuteValue < 5){
                    boss_appearance = hourValue + ":05";
                } else {
                    boss_appearance =   hourValue+1 + ":35";
                }
            } else if (hourValue == 1){
                if(minuteValue < 35){
                    boss_appearance =  hourValue + ":35";
                } else {
                    boss_appearance =  hourValue +2+ ":05";
                }
            } else if (hourValue == 2){
                boss_appearance =   hourValue+1 + ":05";
            } else if (hourValue == 3){
                if(minuteValue < 5){
                    boss_appearance =  hourValue + ":05";
                } else {
                    boss_appearance =  hourValue+1 + ":35";
                }
            } else if (hourValue == 4){
                if(minuteValue < 35){
                    boss_appearance =   hourValue + ":35";
                } else {
                    boss_appearance =   hourValue+2 + ":05";
                }
            } else if (hourValue == 5){
                boss_appearance =   hourValue+1 + ":05";
            } else if (hourValue == 6){
                if(minuteValue < 5){
                    boss_appearance =  hourValue + ":05";
                } else {
                    boss_appearance =   hourValue+1 + ":35";
                }
            } else if (hourValue == 7){
                if(minuteValue < 35){
                    boss_appearance =   hourValue + ":35";
                } else {
                    boss_appearance =   hourValue+2 + ":05";
                }
             } else if (hourValue == 8){
                boss_appearance =   hourValue+1 + ":05";
            } else if (hourValue == 9){
                if(minuteValue < 5){
                    boss_appearance =   hourValue + ":05";
                } else {
                    boss_appearance = hourValue+11 + ":35";
                }
            } else if (hourValue == 10){
                if(minuteValue < 35){
                    boss_appearance = hourValue + ":35";
                } else {
                    boss_appearance = hourValue+2 + ":05";
                }
            } else if (hourValue == 11){
                boss_appearance = hourValue+1 + ":05";
            } else if (hourValue >= 12 && hourValue <= 20){
                if(minuteValue < 5){
                    boss_appearance = hourValue + ":05";
                } else {
                    boss_appearance = hourValue+1 + ":05";
                }
            } else if (hourValue == 21){
                if(minuteValue < 5){
                    boss_appearance = hourValue + ":05";
                } else {
                    boss_appearance = hourValue +1+ ":35";
                }
            } else if (hourValue == 22){
                if(minuteValue < 35){
                    boss_appearance = hourValue + ":35";
                } else {
                    boss_appearance = hourValue+2 + ":05";
                }
            } else if (hourValue == 23){
                boss_appearance = hourValue+1 + ":05";
            }
        }

        if(bossName == "레버넌트 좀비"){
            if(hourValue >= 0 && hourValue <= 12){
                if(hourValue >= 0 && hourValue <= 9){
                    if(minuteValue < 15){
                        boss_appearance = hourValue + ":15";
                    } else {
                        if(hourValue < 9){
                            boss_appearance =  hourValue +1 + ":15";
                        } else {
                            boss_appearance = hourValue +1 + ":15";
                        }
                    }
                } else {
                    if(minuteValue < 15){
                        boss_appearance = hourValue + ":15";
                    } else {
                        boss_appearance = hourValue +1 + ":15";
                    }
                }
            } else if (hourValue >= 13 && hourValue <= 22){
                if(minuteValue < 15){
                    boss_appearance = hourValue + ":15";
                } else if (minuteValue >= 15 && minuteValue < 45){
                    boss_appearance = hourValue + ":45";
                } else {
                    boss_appearance = hourValue+1 + ":15";
                }
            } else if (hourValue == 23){
                if(minuteValue < 15){
                    boss_appearance = hourValue + ":15";
                } else {
                    boss_appearance = hourValue +1  + ":15";
                }
            }

        }

        if(bossName == "우르자"){
            if(minuteValue < 5){
                boss_appearance =  hourValue + ":05";
            } else if (minuteValue >= 5 && minuteValue < 15){
                boss_appearance =  hourValue + ":15";
            } else if (minuteValue >= 15 && minuteValue < 25){
                boss_appearance =  hourValue + ":25";
            } else if (minuteValue >= 25 && minuteValue < 35){
                boss_appearance =  hourValue + ":35";
            } else if (minuteValue >= 35 && minuteValue < 45){
                boss_appearance = + hourValue + ":45";
            } else if (minuteValue >= 45 && minuteValue < 55){
                boss_appearance =  + hourValue + ":55";
            } else {
                boss_appearance =  + hourValue+1 + ":05";
            }

        }

        if(bossName == "부기콜리"){
            if(minuteValue < 5){
                boss_appearance =  hourValue + ":05";
            } else if (minuteValue >= 5 && minuteValue < 15){
                boss_appearance =  hourValue + ":15";
            } else if (minuteValue >= 15 && minuteValue < 25){
                boss_appearance =  hourValue + ":25";
            } else if (minuteValue >= 25 && minuteValue < 35){
                boss_appearance =  hourValue + ":35";
            } else if (minuteValue >= 35 && minuteValue < 45){
                boss_appearance =  hourValue + ":45";
            } else if (minuteValue >= 45 && minuteValue < 55){
                boss_appearance =  hourValue + ":55";
            } else {
                boss_appearance =  hourValue+1 + ":05";
            }

        }

        if(bossName == "그리폰") {
            if (hourValue == 0) {
                if (minuteValue < 15) {
                    boss_appearance =  hourValue + ":15";
                } else {
                    boss_appearance =  hourValue + 1 + ":45";
                }
            } else if (hourValue == 1) {
                if (minuteValue < 45) {
                    boss_appearance =  hourValue + ":45";
                } else {
                    boss_appearance =  hourValue + 2 + ":15";
                }
            } else if (hourValue == 2) {
                boss_appearance =  hourValue + 1 + ":15";
            } else if (hourValue == 3) {
                if (minuteValue < 15) {
                    boss_appearance =  hourValue + ":15";
                } else {
                    boss_appearance =  hourValue + 1 + ":45";
                }
            } else if (hourValue == 4) {
                if (minuteValue < 45) {
                    boss_appearance =  hourValue + ":45";
                } else {
                    boss_appearance =  hourValue + 2 + ":15";
                }
            } else if (hourValue == 5) {
                boss_appearance =  hourValue + 1 + ":15";
            } else if (hourValue == 6) {
                if (minuteValue < 15) {
                    boss_appearance =  hourValue + ":15";
                } else {
                    boss_appearance =  hourValue + 1 + ":45";
                }
            } else if (hourValue == 7) {
                if (minuteValue < 45) {
                    boss_appearance =  hourValue + ":45";
                } else {
                    boss_appearance =  hourValue + 2 + ":15";
                }
            } else if (hourValue == 8) {
                boss_appearance =  hourValue + 1 + ":15";
            } else if (hourValue == 9) {
                if (minuteValue < 15) {
                    boss_appearance =  hourValue + ":15";
                } else {
                    boss_appearance = hourValue + 1 + ":45";
                }
            } else if (hourValue == 10) {
                if (minuteValue < 45) {
                    boss_appearance = hourValue + ":45";
                } else {
                    boss_appearance = hourValue + 2 + ":15";
                }
            } else if (hourValue == 11) {
                boss_appearance = hourValue + 1 + ":15";
            } else if (hourValue >= 12 && hourValue <= 20) {
                if (minuteValue < 15) {
                    boss_appearance = hourValue + ":15";
                } else {
                    boss_appearance = hourValue + 1 + ":15";
                }
            } else if (hourValue == 21) {
                if (minuteValue < 15) {
                    boss_appearance = hourValue + ":15";
                } else {
                    boss_appearance = hourValue + 1 + ":45";
                }
            } else if (hourValue == 22) {
                if (minuteValue < 45) {
                    boss_appearance = hourValue + ":45";
                } else {
                    boss_appearance = hourValue + 2 + ":15";
                }
            } else if (hourValue == 23) {
                boss_appearance = hourValue + 1 + ":15";
            }
        }

        if(bossName == "프랑케네뜨"){
            if(hourValue >= 0 && hourValue <= 12){

                if(hourValue >= 0 && hourValue <= 9){
                    if(minuteValue < 20){
                        boss_appearance = hourValue + ":20";
                    } else {
                        if(hourValue < 9){
                            boss_appearance =  hourValue+1 + ":20";
                        } else {
                            boss_appearance = hourValue+1 + ":20";
                        }

                    }
                } else if (hourValue >= 10 && hourValue <= 12){
                    if(minuteValue < 20){
                        boss_appearance = hourValue + ":20";
                    } else {
                        boss_appearance = hourValue+1 + ":20";
                    }
                }

            } else if (hourValue >= 13 && hourValue <= 22){
                if(minuteValue < 20){
                    boss_appearance = hourValue + ":20";
                } else if (minuteValue >= 20 && minuteValue < 50){
                    boss_appearance = hourValue + ":50";
                } else {
                    boss_appearance = hourValue +1 + ":20";
                }
            } else if (hourValue == 23){
                if(minuteValue < 20){
                    boss_appearance = hourValue + ":20";
                } else {
                    boss_appearance = hourValue+1 + ":20";
                }
            }

        }

        if(bossName == "경비대장 차우"){

            if(minuteValue < 5){
                boss_appearance = hourValue + ":05";
            } else if (minuteValue >= 5 && minuteValue < 20){
                boss_appearance = hourValue + ":20";
            } else if (minuteValue >= 20 && minuteValue < 35){
                boss_appearance = hourValue + ":35";
            } else if (minuteValue >= 35 && minuteValue < 50){
                boss_appearance = hourValue + ":50";
            } else {
                boss_appearance = hourValue+1 + ":05";
            }
        }

        if(bossName == "그리피나"){

            if(hourValue == 0){
                if(minuteValue < 25){
                    boss_appearance =hourValue + ":25";
                } else {
                    boss_appearance =  hourValue+1 + ":55";
                }
            } else if (hourValue == 1){
                if(minuteValue < 55){
                    boss_appearance = hourValue + ":55";
                } else {
                    boss_appearance =hourValue+2 + ":25";
                }
            } else if (hourValue == 2){
                boss_appearance = hourValue+1 + ":25";
            } else if (hourValue == 3){
                if(minuteValue < 25){
                    boss_appearance = hourValue + ":25";
                } else {
                    boss_appearance = hourValue+1 + ":55";
                }
            } else if (hourValue == 4){
                if(minuteValue < 55){
                    boss_appearance = hourValue + ":55";
                } else {
                    boss_appearance = hourValue+2 + ":25";
                }
            } else if (hourValue == 5){
                boss_appearance =  hourValue+1 + ":25";
            } else if (hourValue == 6){
                if(minuteValue < 25){
                    boss_appearance = hourValue + ":25";
                } else {
                    boss_appearance = hourValue+1 + ":55";
                }
            } else if (hourValue == 7){
                if(minuteValue < 55){
                    boss_appearance = hourValue + ":55";
                } else {
                    boss_appearance = hourValue+2 + ":25";
                }
            } else if (hourValue == 8){
                boss_appearance = hourValue+1 + ":25";
            } else if (hourValue == 9){
                if(minuteValue < 25){
                    boss_appearance = hourValue + ":25";
                } else {
                    boss_appearance = hourValue+1 + ":55";
                }
            } else if (hourValue == 10){
                if(minuteValue < 55){
                    boss_appearance = hourValue + ":55";
                } else {
                    boss_appearance = hourValue+2 + ":25";
                }
            } else if (hourValue == 11){
                boss_appearance = hourValue+1 + ":25";
            } else if (hourValue >= 12 && hourValue <= 20){
                if(minuteValue < 25){
                    boss_appearance = hourValue + ":25";
                } else {
                    boss_appearance = hourValue+1 + ":25";
                }
            } else if (hourValue == 21){
                if(minuteValue < 25){
                    boss_appearance = hourValue + ":25";
                } else {
                    boss_appearance = hourValue+1 + ":55";
                }
            } else if (hourValue == 22){
                if(minuteValue < 55){
                    boss_appearance = hourValue + ":55";
                } else {
                    boss_appearance = hourValue+2 + ":25";
                }
            } else if (hourValue == 23){
                boss_appearance = hourValue+1 + ":25";
            }

        }

        if(bossName == "매드오네뜨"){
            if(hourValue >= 0 && hourValue <= 11){
                if(hourValue >= 0 && hourValue <= 9){

                    if(minuteValue < 30){
                        boss_appearance = hourValue + ":30";
                    } else {
                        if(hourValue < 9){
                            boss_appearance = hourValue+1 + ":30";
                        } else {
                            boss_appearance = hourValue+1 + ":30";
                        }

                    }
                } else if (hourValue >= 10 && hourValue <= 11){

                    if(minuteValue < 30){
                        boss_appearance = hourValue + ":30";
                    } else {
                        boss_appearance = hourValue+1 + ":30";
                    }
                }
            } else if (hourValue == 12) {
                if (minuteValue < 30) {
                    boss_appearance = hourValue + ":30";
                } else {
                    boss_appearance = hourValue+1 + ":00";
                }
            } else if (hourValue >= 13 && hourValue <= 21){
                if (minuteValue >= 0 && minuteValue < 30){
                    boss_appearance = hourValue + ":30";
                } else {
                    boss_appearance = hourValue+1 + ":00";
                }
            } else if (hourValue == 22){
                if(minuteValue >= 0 && minuteValue < 30){
                    boss_appearance = hourValue + ":30";
                } else {
                    boss_appearance = hourValue + 1 +  ":30";
                }
            } else if (hourValue == 23){
                if(minuteValue < 30){
                    boss_appearance = hourValue + ":30";
                } else {
                    boss_appearance = hourValue + 1+ ":30";
                }
            }

        }

        if(bossName == "자이언트 터틀"){
            if(hourValue == 0){
                if(minuteValue < 35){
                    boss_appearance = hourValue + ":35";
                } else {
                    boss_appearance = hourValue+2 + ":05";
                }
            } else if (hourValue == 1){
                boss_appearance = hourValue+1 + ":05";
            } else if (hourValue == 2){
                if(minuteValue < 5){
                    boss_appearance = hourValue + ":05";
                } else {
                    boss_appearance = hourValue+1 + ":35";
                }
            } else if (hourValue == 3){
                if(minuteValue < 35){
                    boss_appearance = hourValue + ":35";
                } else {
                    boss_appearance = hourValue +2+ ":05";
                }
            } else if (hourValue == 4){
                boss_appearance = hourValue+1 + ":05";
            } else if (hourValue == 5){
                if(minuteValue < 5){
                    boss_appearance = hourValue + ":05";
                } else {
                    boss_appearance = hourValue+1 + ":35";
                }
            } else if (hourValue == 6){
                if (minuteValue < 35){
                    boss_appearance = hourValue + ":35";
                } else {
                    boss_appearance = hourValue+2 + ":05";
                }
            } else if (hourValue == 7){
                boss_appearance = hourValue+1 + ":05";
            } else if (hourValue == 8){
                if(minuteValue < 5){
                    boss_appearance = hourValue + ":05";
                } else {
                    boss_appearance = hourValue+1 + ":35";
                }
            } else if (hourValue == 9){
                if(minuteValue < 35){
                    boss_appearance = hourValue + ":35";
                } else {
                    boss_appearance = hourValue+2 + ":05";
                }
            } else if (hourValue == 10){
                boss_appearance = hourValue+1 + ":05";
            } else if (hourValue == 11){
                if(minuteValue < 5){
                    boss_appearance = hourValue + ":05";
                } else {
                    boss_appearance = hourValue+1 + ":35";
                }
            } else if (hourValue >= 12 && hourValue <= 20){
                if(minuteValue < 35){
                    boss_appearance = hourValue + ":35";
                } else {
                    boss_appearance = hourValue + 1 +":35";
                }
            }  else if (hourValue == 21){
                if(minuteValue < 35){
                    boss_appearance = hourValue + ":35";
                } else {
                    boss_appearance = hourValue+2 + ":05";
                }
            } else if (hourValue == 22){
                boss_appearance = hourValue+1 + ":05";
            } else if (hourValue == 23){
                if(minuteValue < 5 ){
                    boss_appearance = hourValue + ":05";
                } else {
                    boss_appearance = hourValue+1 + ":35";
                }
            }
        }

        if(bossName == "우르판다"){
            if(hourValue >= 0 && hourValue <= 12){
                if(minuteValue < 10){
                    boss_appearance = hourValue + ":10";
                } else {
                    boss_appearance = hourValue+1 + ":10";
                }
            } else if (hourValue >= 13 && hourValue <= 21){
                if(minuteValue < 10){
                    boss_appearance = hourValue + ":10";
                } else if (minuteValue >= 10 && minuteValue < 40){
                    boss_appearance = hourValue + ":40";
                } else {
                    boss_appearance = hourValue+1 + ":10";
                }
            } else if (hourValue == 22){
                if(minuteValue < 10){
                    boss_appearance = hourValue + ":10";
                } else if (minuteValue >= 10 && minuteValue <40){
                    boss_appearance = hourValue + ":40";
                } else {
                    boss_appearance = hourValue+1 + ":10";
                }
            } else if (hourValue == 23){
                if(minuteValue < 10){
                    boss_appearance = hourValue + ":10";
                } else {
                    boss_appearance = hourValue+1 + ":10";
                }
            }
        }

        if(bossName == "마노"){
            if(hourValue >= 0 && hourValue <= 12){
                if(minuteValue < 15){
                    boss_appearance = hourValue + ":15";
                } else {
                    boss_appearance = hourValue +1+ ":15";
                }
            } else if (hourValue >= 13 && hourValue <= 22){
                if(minuteValue < 15){
                    boss_appearance = hourValue + ":15";
                } else if (minuteValue >= 15 && minuteValue < 45){
                    boss_appearance = hourValue + ":45";
                } else {
                    boss_appearance = hourValue+1 + ":15";
                }
            } else if (hourValue == 23){
                if(minuteValue < 15){
                    boss_appearance = hourValue + ":15";
                } else {
                    boss_appearance = hourValue+1 + ":15";
                }
            }
        }

        if(bossName == "좀비머쉬맘"){
            if(hourValue >= 0 && hourValue <= 12){
                if(minuteValue < 25){
                    boss_appearance = hourValue + ":25";
                } else {
                    boss_appearance = hourValue +1+ ":25";
                }
            } else if (hourValue >= 13 && hourValue <= 22){
                if(minuteValue < 25){
                    boss_appearance = hourValue + ":25";
                } else if (minuteValue >= 25 && minuteValue < 55){
                    boss_appearance = hourValue + ":55";
                } else {
                    boss_appearance = hourValue+1 + ":25";
                }
            } else if (hourValue == 23){
                if(minuteValue < 25){
                    boss_appearance = hourValue + ":25";
                } else {
                    boss_appearance = hourValue+1 + ":25";
                }
            }
        }

        if(bossName == "스텀피" || bossName == "바람술사 라팽"){
            if(hourValue >= 0 && hourValue <= 12){
                if(minuteValue < 5){
                    boss_appearance = hourValue + ":05";
                } else {
                    boss_appearance = hourValue +1+ ":05";
                }
            } else if (hourValue >= 13 && hourValue <= 21) {
                if (minuteValue < 5) {
                    boss_appearance = hourValue + ":05";
                } else if (minuteValue >= 5 && minuteValue < 35) {
                    boss_appearance = hourValue + ":35";
                } else {
                    boss_appearance = hourValue + 1 + ":05";
                }
            } else if (hourValue == 22){
                if (minuteValue < 5) {
                    boss_appearance = hourValue + ":05";
                } else if (minuteValue >= 5 && minuteValue < 35) {
                    boss_appearance = hourValue + ":35";
                } else {
                    boss_appearance = hourValue + 1 + ":35";
                }

            } else if (hourValue == 23){
                if(minuteValue < 25){
                    boss_appearance = hourValue + ":05";
                } else {
                    boss_appearance = hourValue+1 + ":05";
                }
            }
        }

        if(bossName == "소환술사 라툰"){
            if(hourValue >= 0 && hourValue <= 12){
                if(minuteValue < 25){
                    boss_appearance = hourValue + ":25";
                } else {
                    boss_appearance = hourValue +1+ ":25";
                }
            } else if (hourValue >= 13 && hourValue <= 21) {
                if (minuteValue < 25) {
                    boss_appearance = hourValue + ":25";
                } else if (minuteValue >= 25 && minuteValue < 55) {
                    boss_appearance = hourValue + ":55";
                } else {
                    boss_appearance = hourValue + 1 + ":25";
                }
            } else if (hourValue == 22){
                if(minuteValue  < 25){
                    boss_appearance = hourValue + ":25";
                } else if (minuteValue >= 25 && minuteValue < 55){
                    boss_appearance = hourValue + ":55";
                } else {
                    boss_appearance = hourValue+1 + ":55";
                }
            } else if (hourValue == 23){
                if(minuteValue < 25){
                    boss_appearance = hourValue + ":05";
                } else {
                    boss_appearance = hourValue+1 + ":05";
                }
            }
        }



        if(bossName == "킹슬라임"){
            if(hourValue >= 0 && hourValue <= 11){
                if(minuteValue < 45){
                    boss_appearance = hourValue + ":45";
                } else {
                    boss_appearance = hourValue +1+ ":45";
                }
            } else if (hourValue == 12){
                if(minuteValue < 45){
                    boss_appearance = hourValue + ":45";
                } else {
                    boss_appearance = hourValue + ":15";
                }
            } else if (hourValue >= 13 && hourValue <= 21){
                if(minuteValue < 15){
                    boss_appearance = hourValue + ":15";
                } else if (minuteValue >= 15 && minuteValue < 45){
                    boss_appearance = hourValue + ":45";
                } else {
                    boss_appearance = hourValue+1 + ":15";
                }
            } else if(hourValue == 22) {
                if(minuteValue <15){
                    boss_appearance = hourValue + ":15";
                } else if (minuteValue >= 15 && minuteValue < 45){
                    boss_appearance = hourValue + ":45";
                } else {
                    boss_appearance = hourValue+1 + ":45";
                }
            } else if (hourValue == 23){
                if(minuteValue < 25){
                    boss_appearance = hourValue + ":25";
                } else {
                    boss_appearance = hourValue+1 + ":25";
                }
            }
        }

        if(bossName == "바야르 수문장"){
            if(hourValue == 0){
                if(minuteValue < 35){
                    boss_appearance = hourValue + ":35";
                } else {
                    boss_appearance = hourValue+2 + ":05";
                }
            } else if (hourValue == 1){
                boss_appearance = hourValue+1 + ":05";
            } else if (hourValue == 2){
                if(minuteValue < 5){
                    boss_appearance = hourValue + ":05";
                } else {
                    boss_appearance = hourValue+1 + ":35";
                }
            } else if (hourValue == 3){
                if(minuteValue < 35){
                    boss_appearance = hourValue + ":35";
                } else {
                    boss_appearance = hourValue+2 + ":05";
                }
            } else if (hourValue == 4){
                boss_appearance = hourValue+1 + ":05";
            } else if (hourValue == 5){
                if(minuteValue < 5){
                    boss_appearance = hourValue + ":05";
                } else {
                    boss_appearance = hourValue+1 + ":35";
                }
            } else if (hourValue == 6){
                if(minuteValue < 35){
                    boss_appearance = hourValue + ":35";
                } else {
                    boss_appearance = hourValue +2+ ":05";
                }
            } else if (hourValue == 7){
                boss_appearance = hourValue+1 + ":05";
            } else if (hourValue == 8){
                if(minuteValue < 5){
                    boss_appearance = hourValue + ":05";
                } else {
                    boss_appearance = hourValue+1 + ":35";
                }
            } else if (hourValue == 9){
                if(minuteValue < 35){
                    boss_appearance = hourValue + ":35";
                } else{
                    boss_appearance = hourValue+2 + ":05";
                }
            } else if (hourValue == 10){
                boss_appearance = hourValue+1 + ":05";
            } else if (hourValue == 11){
                if(minuteValue < 5){
                    boss_appearance = hourValue + ":25";
                } else{
                    boss_appearance = hourValue+1 + ":35";
                }
            } else if (hourValue >= 12 && hourValue <= 20){
                if(minuteValue < 35){
                    boss_appearance = hourValue + ":35";
                } else {
                    boss_appearance = hourValue+1 + ":35";
                }
            } else if (hourValue == 21){
                if(minuteValue < 35){
                    boss_appearance = hourValue + ":35";
                } else {
                    boss_appearance = hourValue+2 + ":05";
                }
            } else if (hourValue == 22){
                boss_appearance = hourValue+1 + ":05";
            } else if (hourValue == 23){
                if(minuteValue < 5){
                    boss_appearance = hourValue + ":05";
                } else {
                    boss_appearance = hourValue+1 + ":35";
                }
            }
        }

        if(bossName == "로로와 무무스"){
            if(hourValue == 0){
                if(minuteValue < 5){
                    boss_appearance = hourValue + ":05";
                } else {
                    boss_appearance = hourValue+1 + ":35";
                }
            } else if (hourValue == 1){
                if(minuteValue < 35){
                    boss_appearance = hourValue + ":35";
                } else {
                    boss_appearance = hourValue+2 + ":05";
                }
            } else if (hourValue == 2){
                boss_appearance = hourValue+1 + ":05";
            } else if (hourValue == 3){
                if(minuteValue < 5){
                    boss_appearance = hourValue + ":05";
                } else {
                    boss_appearance = hourValue+1 + ":35";
                }
            } else if (hourValue == 4){
                if(minuteValue < 35){
                    boss_appearance = hourValue + ":35";
                } else {
                    boss_appearance = hourValue+2 + ":05";
                }
            } else if (hourValue == 5){
                boss_appearance = hourValue+1 + ":05";
            } else if (hourValue == 6){
                if(minuteValue < 5){
                    boss_appearance = hourValue + ":05";
                } else {
                    boss_appearance = hourValue+1 + ":35";
                }
            } else if (hourValue == 7){
                if(minuteValue < 35){
                    boss_appearance = hourValue + ":35";
                } else {
                    boss_appearance = hourValue+2 + ":05";
                }
            } else if (hourValue == 8){
                boss_appearance = hourValue+1 + ":05";
            } else if (hourValue == 9){
                if(minuteValue < 5){
                    boss_appearance = hourValue + ":05";
                } else {
                    boss_appearance = hourValue + 1 + ":35";
                }
            } else if (hourValue == 10){
                if(minuteValue < 35){
                    boss_appearance = hourValue + ":35";
                } else {
                    boss_appearance = hourValue+2 + ":05";
                }
            } else if (hourValue == 11){
                boss_appearance = hourValue+1 + ":05";
            } else if (hourValue >= 12 && hourValue <=20){
                if(minuteValue < 5){
                    boss_appearance = hourValue + ":05";
                } else {
                    boss_appearance = hourValue+1 + ":05";
                }
            } else if (hourValue == 21){
                if(minuteValue < 5){
                    boss_appearance = hourValue + ":05";
                } else {
                    boss_appearance = hourValue +1+ ":35";
                }
            } else if (hourValue == 22){
                if(minuteValue < 35){
                    boss_appearance = hourValue + ":35";
                } else {
                    boss_appearance = hourValue+2 + ":05";
                }
            } else if (hourValue == 23){
                boss_appearance = hourValue+1 + ":05";
            }
        }


        if(bossName == "알파 터틀"){
            if(hourValue == 0){
                if(minuteValue < 10){
                    boss_appearance = hourValue + ":10";
                } else {
                    boss_appearance = hourValue+1 + ":40";
                }
            } else if (hourValue == 1){
                if(minuteValue < 40){
                    boss_appearance = hourValue + ":40";
                } else {
                    boss_appearance = hourValue+2 + ":10";
                }
            } else if (hourValue == 2){
                boss_appearance = hourValue+1 + ":10";
            } else if (hourValue == 3){
                if(minuteValue < 10){
                    boss_appearance = hourValue + ":10";
                } else {
                    boss_appearance = hourValue+1 + ":40";
                }
            } else if (hourValue == 4){
                if(minuteValue < 40){
                    boss_appearance = hourValue + ":40";
                } else {
                    boss_appearance = hourValue+2 + ":10";
                }
            } else if (hourValue == 5){
                boss_appearance = hourValue + ":10";
            } else if (hourValue == 6){
                if(minuteValue < 10){
                    boss_appearance = hourValue + ":10";
                } else{
                    boss_appearance = hourValue+1 + ":40";
                }
            } else if (hourValue == 7){
                if(minuteValue < 40){
                    boss_appearance = hourValue + ":40";
                } else {
                    boss_appearance = hourValue+2 + ":10";
                }
            } else if (hourValue == 8){
                boss_appearance = hourValue+1 + ":10";
            } else if (hourValue == 9){
                if(minuteValue < 10){
                    boss_appearance = hourValue + ":10";
                } else {
                    boss_appearance = hourValue+1 + ":40";
                }
            } else if (hourValue == 10){
                if(minuteValue < 40){
                    boss_appearance = hourValue + ":40";
                } else {
                    boss_appearance = hourValue+2 + ":10";
                }
            } else if (hourValue == 11){
                boss_appearance = hourValue+1 + ":10";
            } else if (hourValue >= 12 && hourValue <= 20){
                if(minuteValue < 10){
                    boss_appearance = hourValue + ":10";
                } else {
                    boss_appearance = hourValue+1 + ":10";
                }
            } else if (hourValue == 21){
                if(minuteValue < 10){
                    boss_appearance = hourValue + ":10";
                } else {
                    boss_appearance = hourValue+1 + ":40";
                }
            } else if (hourValue == 22){
                if(minuteValue < 40){
                    boss_appearance = hourValue + ":40";
                } else {
                    boss_appearance = hourValue+2 + ":10";
                }
            } else if (hourValue == 23){
                boss_appearance = hourValue+1 + ":10";
            }

        }

        if(bossName == "레르노스"){

            if(hourValue == 0){
                if(minuteValue < 45){
                    boss_appearance = hourValue + ":45";
                } else {
                    boss_appearance = hourValue+2 + ":15";
                }
            } else if (hourValue == 1){
                boss_appearance = hourValue+1 + ":15";
            } else if (hourValue == 2){
                if(minuteValue < 15){
                    boss_appearance = hourValue + ":15";
                } else {
                    boss_appearance = hourValue+1 + ":45";
                }
            } else if (hourValue == 3){
                if(minuteValue < 45){
                    boss_appearance = hourValue + ":45";
                } else {
                    boss_appearance = hourValue+2 + ":15";
                }
            } else if (hourValue == 4){
                boss_appearance = hourValue+1 + ":15";
            } else if (hourValue == 5){
                if(minuteValue < 15){
                    boss_appearance = hourValue + ":15";
                } else {
                    boss_appearance = hourValue+1 + ":45";
                }
            } else if (hourValue == 6){
                if(minuteValue < 45){
                    boss_appearance = hourValue + ":45";
                } else {
                    boss_appearance = hourValue+2 + ":15";
                }
            } else if (hourValue == 7){
                boss_appearance = hourValue+1 + ":15";
            } else if (hourValue == 8){
                if(minuteValue < 15){
                    boss_appearance = hourValue + ":15";
                } else {
                    boss_appearance = hourValue+1 + ":45";
                }
            } else if (hourValue == 9){
                if(minuteValue < 45){
                    boss_appearance = hourValue + ":45";
                } else {
                    boss_appearance = hourValue+2 + ":15";
                }
            } else if (hourValue == 10){
                boss_appearance = hourValue+1 + ":15";
            } else if (hourValue == 11){
                if(minuteValue < 15){
                    boss_appearance = hourValue + ":15";
                } else {
                    boss_appearance = hourValue+1 + ":45";
                }
            } else if (hourValue >=12 && hourValue <= 20){
                if(minuteValue < 45){
                    boss_appearance = hourValue + ":45";
                } else {
                    boss_appearance = hourValue+1 + ":45";
                }
            } else if (hourValue == 21){
                if(minuteValue < 45){
                    boss_appearance = hourValue + ":45";
                } else {
                    boss_appearance = hourValue+2 + ":15";
                }
            } else if (hourValue == 22){
                boss_appearance = hourValue+1 + ":15";
            } else if (hourValue == 23){
                if(minuteValue < 15){
                    boss_appearance = hourValue + ":15";
                } else {
                    boss_appearance = hourValue+1 + ":45";
                }
            }
        }


        if(bossName == "분노의 바포메트"){
            if(hourValue == 0){
                if(minuteValue < 5){
                    boss_appearance = hourValue + ":05";
                } else {
                    boss_appearance = hourValue+1 + ":35";
                }
            } else if (hourValue == 1){
                if(minuteValue < 35){
                    boss_appearance = hourValue + ":35";
                } else {
                    boss_appearance = hourValue+2 + ":05";
                }
            } else if (hourValue == 2){
                boss_appearance = hourValue+1 + ":05";
            } else if (hourValue == 3){
                if(minuteValue < 5){
                    boss_appearance = hourValue + ":05";
                } else {
                    boss_appearance = hourValue+1 + ":35";
                }
            } else if (hourValue == 4){
                if(minuteValue < 35){
                    boss_appearance = hourValue + ":35";
                } else {
                    boss_appearance = hourValue+2 + ":05";
                }
            } else if (hourValue == 5){
                boss_appearance = hourValue+1 + ":05";
            } else if (hourValue == 6){
                if(minuteValue < 5){
                    boss_appearance = hourValue + ":05";
                } else {
                    boss_appearance = hourValue+1 + ":35";
                }
            } else if (hourValue == 7){
                if(minuteValue < 35){
                    boss_appearance = hourValue + ":05";
                } else {
                    boss_appearance = hourValue+2 + ":05";
                }
            } else if (hourValue == 8){
                boss_appearance = hourValue+1 + ":05";
            } else if (hourValue == 9){
                if(minuteValue < 5){
                    boss_appearance = hourValue + ":05";
                } else {
                    boss_appearance = hourValue+1 + ":35";
                }
            } else if (hourValue == 10){
                if(minuteValue < 35){
                    boss_appearance = hourValue + ":05";
                } else {
                    boss_appearance = hourValue+2 + ":05";
                }
            } else if (hourValue == 11){
                boss_appearance = hourValue+1 + ":05";
            } else if (hourValue >= 12 && hourValue < 20){
                if(minuteValue < 5){
                    boss_appearance = hourValue + ":05";
                } else {
                    boss_appearance = hourValue+1 + ":05";
                }
            } else if (hourValue == 21){
                if(minuteValue < 5){
                    boss_appearance = hourValue + ":05";
                } else {
                    boss_appearance = hourValue+1 + ":35";
                }
            } else if (hourValue == 22){
                if(minuteValue < 35){
                    boss_appearance = hourValue + ":35";
                } else {
                    boss_appearance = hourValue+2 + ":05";
                }
            } else if (hourValue == 23){
                boss_appearance = hourValue+1 + ":05";
            }
        }




        return boss_appearance;

        }

}

