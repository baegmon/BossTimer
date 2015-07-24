package baegmon.com.bosstimer;

import android.app.Activity;
import android.os.Handler;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ListView;
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
    ListView listView;
    int[] boss_icon = {R.drawable.boss_1, R.drawable.boss_2, R.drawable.boss_3,
            R.drawable.boss_4, R.drawable.boss_5, R.drawable.boss_6, R.drawable.boss_7,
            R.drawable.boss_8, R.drawable.boss_9, R.drawable.boss_10, R.drawable.boss_11,
            R.drawable.boss_12, R.drawable.boss_13, R.drawable.boss_14, R.drawable.boss_15,
            R.drawable.boss_16, R.drawable.boss_17, R.drawable.boss_18};
    String[] boss_title=  new String[18];
    BossAdapter adapter;

    String[] bossTime=  new String[18];
    Handler handler;
    String[] bossAppearance=  new String[18];
    EditText search_view;
    ArrayList<Boss> bossList;
    int bossID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdView ad = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        ad.loadAd(adRequest);


        createListview();

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
            case "MARK52 알파":
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
        }

        return id;
    }
    private void createListview(){
        listView = (ListView) findViewById(R.id.list_view);
        search_view = (EditText)findViewById(R.id.search_text);

        boss_title = getResources().getStringArray(R.array.boss_array);

        bossList = new ArrayList<Boss>();
        adapter = new BossAdapter(getApplicationContext(), bossList);


        listView.setAdapter(adapter);
        listView.setTextFilterEnabled(true);
        bossTime = getKoreanTime();


        for (String boss : boss_title) {
            bossID = getID(boss);
            bossAppearance = bossAppearance(bossID);


            Boss bossObject = new Boss(boss_icon[bossID], boss, bossTime[bossID], bossAppearance[bossID]);
            bossList.add(bossObject);

        }


        handler = new Handler();
        Runnable update = new Runnable() {
            @Override
            public void run() {
                bossTime = getKoreanTime();


                int count = adapter.getCount();
                for (int i = 0; i < count; i++) {
                    bossAppearance = bossAppearance(i);
                    ((Boss) adapter.getItem(i)).setBoss_time(bossTime[i]); // Re-set time
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


    public String[] getKoreanTime(){
        String[] boss_appearance;
        String[] format_array = new String[boss_icon.length];
        String[] return_array = new String[boss_icon.length];
        Calendar c = Calendar.getInstance();

        Calendar seoulTime = new GregorianCalendar(TimeZone.getTimeZone("Asia/Seoul"));

        c.set(Calendar.HOUR, seoulTime.get(Calendar.HOUR));
        c.set(Calendar.MINUTE, seoulTime.get(Calendar.MINUTE));
        c.set(Calendar.SECOND, seoulTime.get(Calendar.SECOND));

        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        String currentTime = format.format(c.getTime());
        Date date1;
        try {
            Date date2 = format.parse(currentTime);
            for(int i =0 ; i < 18; i++){
                boss_appearance = bossAppearance(i);
                format_array[i] = boss_appearance[i] + ":00";
                date1 = format.parse(format_array[i]);

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
                return_array[i] = finalTime;

            }
        } catch (ParseException e){
            e.printStackTrace();
        }

        return return_array;
    }



    public String[] bossAppearance(int i){
        String[] boss_appearance = new String[boss_icon.length];

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



        switch(i) {
            case 0: // 다크 지란트
                if(hourValue >= 0 && hourValue <= 11){
                    if(minuteValue < 40){
                        boss_appearance[i] = hourValue + ":40";
                    } else {
                        boss_appearance[i] = hourValue+1 + ":10";
                    }
                } else if (hourValue >= 11 && hourValue <= 22){
                    if(hourValue == 12){
                        if(minuteValue < 40){
                            boss_appearance[i] = hourValue + ":40";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":10";
                        }
                    } else {
                        if(hourValue != 12 && minuteValue < 10){
                            boss_appearance[i] = hourValue + ":10";
                        } else if (hourValue != 12 && minuteValue >= 10 && minuteValue < 40){
                            boss_appearance[i] = hourValue + ":40";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":10";
                        }
                    }
                } else if (hourValue == 23){
                    if(minuteValue < 40){
                        boss_appearance[i] = hourValue + ":40";
                    } else {
                        boss_appearance[i] = hourValue+1 + ":40";
                    }
                }



                break;
            case 1: // 머쉬맘
                if(hourValue >= 0 && hourValue <= 11){
                    if(minuteValue < 40){
                        boss_appearance[i] = hourValue + ":40";
                    } else {
                        boss_appearance[i] = hourValue+1 + ":10";
                    }
                } else if (hourValue >= 11 && hourValue <= 22){
                    if(hourValue == 12){
                        if(minuteValue < 40){
                            boss_appearance[i] = hourValue + ":40";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":10";
                        }
                    } else {
                        if(hourValue != 12 && minuteValue < 10){
                            boss_appearance[i] = hourValue + ":10";
                        } else if (hourValue != 12 && minuteValue >= 10 && minuteValue < 40){
                            boss_appearance[i] = hourValue + ":40";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":10";
                        }
                    }
                } else if (hourValue == 23){
                    if(minuteValue < 40){
                        boss_appearance[i] = hourValue + ":40";
                    } else {
                        boss_appearance[i] = hourValue+1 + ":40";
                    }
                }
                break;
            case 2: // 마크52 알파
                // 오전:12시 45분, 2시 15분, 3시 45분, 5시 15분, 6시 45분, 8시 15분, 9시 45분, 11시 15분
                // 오후: 12시 45분, 1시 45분, 2시 45분,3시 45분, 4시 45분, 5시 45분, 6시 45분, 7시 45분, 8시 45분, 9시 45분, 11시 15분

                if(hourValue >= 0 && hourValue <= 12){
                    if(hourValue == 0){
                        if(minuteValue < 45){
                            boss_appearance[i] = hourValue + ":45";
                        } else {
                            boss_appearance[i] = hourValue+2 + ":15";
                        }
                    } else if (hourValue == 1){
                        boss_appearance[i] = hourValue + ":15";
                    } else if (hourValue == 2){
                        if(minuteValue < 15){
                            boss_appearance[i] = hourValue + ":15";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":45";
                        }
                    } else if (hourValue == 3){
                        if(minuteValue < 45){
                            boss_appearance[i] = hourValue + ":45";
                        } else {
                            boss_appearance[i] = hourValue+2 + ":15";
                        }
                    } else if (hourValue == 4){
                        boss_appearance[i] = hourValue+1 + ":15";
                    } else if (hourValue == 5){
                        if(minuteValue < 15){
                            boss_appearance[i] = hourValue + ":15";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":45";
                        }
                    } else if (hourValue == 6){
                        if(minuteValue < 45){
                            boss_appearance[i] = hourValue + ":45";
                        } else {
                            boss_appearance[i] = hourValue+2 + ":15";
                        }
                    } else if (hourValue == 7){
                        boss_appearance[i] = hourValue+1 + ":15";
                    } else if (hourValue == 8){
                        if(minuteValue < 15){
                            boss_appearance[i] = hourValue + ":15";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":45";
                        }
                    } else if (hourValue == 9){
                        if(minuteValue < 45){
                            boss_appearance[i] = hourValue + ":45";
                        } else {
                            boss_appearance[i] = hourValue+2 + ":15";
                        }
                    } else if (hourValue == 10){
                        boss_appearance[i] = hourValue+1 + ":15";
                    } else if (hourValue == 11){
                        if(minuteValue < 15){
                            boss_appearance[i] = hourValue + ":15";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":45";
                        }
                    }
                } else if (hourValue >= 12 && hourValue <= 24){
                    if(hourValue == 12){
                        if(minuteValue < 45){
                            boss_appearance[i] = hourValue + ":45";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":45";
                        }
                    } else if(hourValue == 13){
                        if(minuteValue < 45){
                            boss_appearance[i] = hourValue + ":45";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":45";
                        }
                    } else if(hourValue == 14){
                        if(minuteValue < 45){
                            boss_appearance[i] = hourValue + ":45";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":45";
                        }
                    } else if(hourValue == 15){
                        if(minuteValue < 45){
                            boss_appearance[i] = hourValue + ":45";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":45";
                        }
                    } else if(hourValue == 16){
                        if(minuteValue < 45){
                            boss_appearance[i] = hourValue + ":45";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":45";
                        }
                    } else if(hourValue == 17){
                        if(minuteValue < 45){
                            boss_appearance[i] = hourValue + ":45";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":45";
                        }
                    } else if(hourValue == 18){
                        if(minuteValue < 45){
                            boss_appearance[i] = hourValue + ":45";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":45";
                        }
                    } else if(hourValue == 19){
                        if(minuteValue < 45){
                            boss_appearance[i] = hourValue + ":45";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":45";
                        }
                    } else if(hourValue == 20){
                        if(minuteValue < 45){
                            boss_appearance[i] = hourValue + ":45";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":45";
                        }
                    } else if(hourValue == 21){
                        if(minuteValue < 45){
                            boss_appearance[i] = hourValue + ":45";
                        } else {
                            boss_appearance[i] = hourValue+2 + ":15";
                        }
                    } else if(hourValue == 22){
                        boss_appearance[i] = hourValue+1 + ":15";
                    } else if (hourValue == 23){
                        if(minuteValue < 15){
                            boss_appearance[i] = hourValue + ":15";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":45";
                        }
                    }
                }

                break;
            case 3: // 깔패 바라하
                if(hourValue >= 0 && hourValue <= 11){
                    if(minuteValue < 55){
                        boss_appearance[i] = hourValue + ":55";
                    } else {
                        boss_appearance[i] = hourValue+1 + ":55";
                    }
                } else if (hourValue >= 11 && hourValue <= 22){
                    if(hourValue == 12){
                        if(minuteValue < 55){
                            boss_appearance[i] = hourValue + ":55";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":25";
                        }
                    } else if (hourValue != 12){
                        if(minuteValue < 25){
                            boss_appearance[i] = hourValue + ":25";
                        } else if (minuteValue >= 25 && minuteValue < 55){
                            boss_appearance[i] = hourValue + ":55";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":25";
                        }
                    }
                } else if (hourValue == 23){
                    if(minuteValue < 55){
                        boss_appearance[i] = hourValue + ":55";
                    } else {
                        boss_appearance[i] = hourValue+1 + ":55";
                    }
                }
                break;
            case 4: // 데블린 워리어
                if(hourValue >= 0 &&hourValue <= 12){
                    if(hourValue == 0){
                        if(minuteValue < 55){
                            boss_appearance[i] = hourValue +":55";
                        } else {
                            boss_appearance[i] = hourValue+ 2 + ":25";
                        }
                    } else if (hourValue == 1){
                        boss_appearance[i] = hourValue+ 1 + ":25";
                    } else if (hourValue == 2){
                        if(minuteValue < 25){
                            boss_appearance[i] = hourValue + ":25";
                        } else {
                            boss_appearance[i] = hourValue+ 1 + ":55";
                        }
                    } else if (hourValue == 3){
                        if(minuteValue < 55){
                            boss_appearance[i] = hourValue+ ":55";
                        } else {
                            boss_appearance[i] = hourValue+ 2 + ":25";
                        }
                    } else if (hourValue == 4){
                        boss_appearance[i] = hourValue+ 1 + ":25";
                    } else if (hourValue == 5){
                        if(minuteValue < 25){
                            boss_appearance[i] = hourValue + ":25";
                        } else {
                            boss_appearance[i] = hourValue+ 1 + ":55";
                        }
                    } else if (hourValue == 6){
                        if(minuteValue < 55){
                            boss_appearance[i] = hourValue + ":55";
                        } else {
                            boss_appearance[i] = hourValue+2 + ":25";
                        }
                    } else if (hourValue == 7){
                        boss_appearance[i] = hourValue+1 + ":25";
                    } else if (hourValue == 8){
                        if(minuteValue < 25){
                            boss_appearance[i] = hourValue + ":25";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":55";
                        }
                    } else if (hourValue == 9){
                        if(minuteValue < 55){
                            boss_appearance[i] = hourValue + ":55";
                        } else {
                            boss_appearance[i] = hourValue+2 + ":25";
                        }
                    } else if (hourValue == 10){
                        boss_appearance[i] = hourValue+1 + ":25";
                    } else if (hourValue == 11){
                        if(minuteValue < 25){
                            boss_appearance[i] = hourValue + ":25";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":55";
                        }
                    } else if (hourValue == 12){
                        if(minuteValue < 55){
                            boss_appearance[i] = hourValue + ":55";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":55";
                        }
                    }
                } else if (hourValue >= 12 && hourValue <= 24){
                    if(hourValue == 13){
                        if(minuteValue < 55){
                            boss_appearance[i] = hourValue + ":55";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":55";
                        }
                    } else if(hourValue == 14){
                        if(minuteValue < 55){
                            boss_appearance[i] = hourValue + ":55";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":55";
                        }
                    } else if(hourValue == 15){
                        if(minuteValue < 55){
                            boss_appearance[i] = hourValue + ":55";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":55";
                        }
                    } else if(hourValue == 16){
                        if(minuteValue < 55){
                            boss_appearance[i] = hourValue + ":55";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":55";
                        }
                    } else if(hourValue == 17){
                        if(minuteValue < 55){
                            boss_appearance[i] = hourValue + ":55";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":55";
                        }
                    } else if(hourValue == 18){
                        if(minuteValue < 55){
                            boss_appearance[i] = hourValue + ":55";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":55";
                        }
                    } else if(hourValue == 19){
                        if(minuteValue < 55){
                            boss_appearance[i] = hourValue + ":55";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":55";
                        }
                    } else if(hourValue == 20){
                        if(minuteValue < 55){
                            boss_appearance[i] = hourValue + ":55";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":55";
                        }
                    } else if(hourValue == 21){
                        if(minuteValue < 55){
                            boss_appearance[i] = hourValue + ":55";
                        } else {
                            boss_appearance[i] = hourValue+2 + ":25";
                        }
                    } else if (hourValue == 22){
                        boss_appearance[i] = hourValue+1 + ":25";
                    } else if (hourValue == 23){
                        if(minuteValue < 25){
                            boss_appearance[i] = hourValue + ":55";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":55";
                        }
                    }
                }

                break;
            case 5: // 닉시
                if(hourValue >= 0 && hourValue <= 12){
                    if(hourValue == 0){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    } else if (hourValue == 1){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    } else if (hourValue == 2){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    } else if (hourValue == 3){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    } else if (hourValue == 4){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    } else if (hourValue == 5){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    } else if (hourValue == 6){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    }  else if (hourValue == 7){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    }  else if (hourValue == 8){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    }  else if (hourValue == 9){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    } else if (hourValue == 10){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    }  else if (hourValue == 11){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    }  else if (hourValue == 12){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    }

                } else if (hourValue >= 12 && hourValue <= 23){
                    if(hourValue == 13){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue +":05";
                        } else if (minuteValue >= 5 && minuteValue < 35) {
                            boss_appearance[i] = hourValue + ":35";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    } else if(hourValue == 14){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue +":05";
                        } else if (minuteValue >= 5 && minuteValue < 35) {
                            boss_appearance[i] = hourValue + ":35";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    } else if(hourValue == 15){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue +":05";
                        } else if (minuteValue >= 5 && minuteValue < 35) {
                            boss_appearance[i] = hourValue + ":35";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    } else if(hourValue == 16){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue +":05";
                        } else if (minuteValue >= 5 && minuteValue < 35) {
                            boss_appearance[i] = hourValue + ":35";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    } else if(hourValue == 17){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue +":05";
                        } else if (minuteValue >= 5 && minuteValue < 35) {
                            boss_appearance[i] = hourValue + ":35";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    } else if(hourValue == 18){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue +":05";
                        } else if (minuteValue >= 5 && minuteValue < 35) {
                            boss_appearance[i] = hourValue + ":35";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    } else if(hourValue == 19){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue +":05";
                        } else if (minuteValue >= 5 && minuteValue < 35) {
                            boss_appearance[i] = hourValue + ":35";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    } else if(hourValue == 20){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue +":05";
                        } else if (minuteValue >= 5 && minuteValue < 35) {
                            boss_appearance[i] = hourValue + ":35";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    } else if(hourValue == 21){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue +":05";
                        } else if (minuteValue >= 5 && minuteValue < 35) {
                            boss_appearance[i] = hourValue + ":35";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    } else if(hourValue == 22){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue +":05";
                        } else if (minuteValue >= 5 && minuteValue < 35) {
                            boss_appearance[i] = hourValue + ":35";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    } else if (hourValue == 23){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    }

                }



                break;
            case 6: // 에피
                if(hourValue >= 0 && hourValue <= 12){
                    if(hourValue == 0){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    } else if (hourValue == 1){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    } else if (hourValue == 2){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    } else if (hourValue == 3){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    } else if (hourValue == 4){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    } else if (hourValue == 5){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    } else if (hourValue == 6){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    }  else if (hourValue == 7){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    }  else if (hourValue == 8){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    }  else if (hourValue == 9){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    } else if (hourValue == 10){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    }  else if (hourValue == 11){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    }  else if (hourValue == 12){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    }

                } else if (hourValue >= 12 && hourValue <= 23){
                    if(hourValue == 13){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue +":05";
                        } else if (minuteValue >= 5 && minuteValue < 35) {
                            boss_appearance[i] = hourValue + ":35";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    } else if(hourValue == 14){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue +":05";
                        } else if (minuteValue >= 5 && minuteValue < 35) {
                            boss_appearance[i] = hourValue + ":35";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    } else if(hourValue == 15){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue +":05";
                        } else if (minuteValue >= 5 && minuteValue < 35) {
                            boss_appearance[i] = hourValue + ":35";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    } else if(hourValue == 16){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue +":05";
                        } else if (minuteValue >= 5 && minuteValue < 35) {
                            boss_appearance[i] = hourValue + ":35";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    } else if(hourValue == 17){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue +":05";
                        } else if (minuteValue >= 5 && minuteValue < 35) {
                            boss_appearance[i] = hourValue + ":35";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    } else if(hourValue == 18){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue +":05";
                        } else if (minuteValue >= 5 && minuteValue < 35) {
                            boss_appearance[i] = hourValue + ":35";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    } else if(hourValue == 19){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue +":05";
                        } else if (minuteValue >= 5 && minuteValue < 35) {
                            boss_appearance[i] = hourValue + ":35";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    } else if(hourValue == 20){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue +":05";
                        } else if (minuteValue >= 5 && minuteValue < 35) {
                            boss_appearance[i] = hourValue + ":35";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    } else if(hourValue == 21){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue +":05";
                        } else if (minuteValue >= 5 && minuteValue < 35) {
                            boss_appearance[i] = hourValue + ":35";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    } else if(hourValue == 22){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue +":05";
                        } else if (minuteValue >= 5 && minuteValue < 35) {
                            boss_appearance[i] = hourValue + ":35";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    } else if (hourValue == 23){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    }

                }

            case 7:
                if(hourValue >= 0 && hourValue <= 12){
                    if(hourValue == 0){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    } else if (hourValue == 1){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    } else if (hourValue == 2){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    } else if (hourValue == 3){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    } else if (hourValue == 4){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    } else if (hourValue == 5){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    } else if (hourValue == 6){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    }  else if (hourValue == 7){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    }  else if (hourValue == 8){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    }  else if (hourValue == 9){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    } else if (hourValue == 10){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    }  else if (hourValue == 11){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    }  else if (hourValue == 12){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    }

                } else if (hourValue >= 12 && hourValue <= 23){
                    if(hourValue == 13){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue +":05";
                        } else if (minuteValue >= 5 && minuteValue < 35) {
                            boss_appearance[i] = hourValue + ":35";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    } else if(hourValue == 14){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue +":05";
                        } else if (minuteValue >= 5 && minuteValue < 35) {
                            boss_appearance[i] = hourValue + ":35";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    } else if(hourValue == 15){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue +":05";
                        } else if (minuteValue >= 5 && minuteValue < 35) {
                            boss_appearance[i] = hourValue + ":35";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    } else if(hourValue == 16){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue +":05";
                        } else if (minuteValue >= 5 && minuteValue < 35) {
                            boss_appearance[i] = hourValue + ":35";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    } else if(hourValue == 17){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue +":05";
                        } else if (minuteValue >= 5 && minuteValue < 35) {
                            boss_appearance[i] = hourValue + ":35";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    } else if(hourValue == 18){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue +":05";
                        } else if (minuteValue >= 5 && minuteValue < 35) {
                            boss_appearance[i] = hourValue + ":35";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    } else if(hourValue == 19){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue +":05";
                        } else if (minuteValue >= 5 && minuteValue < 35) {
                            boss_appearance[i] = hourValue + ":35";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    } else if(hourValue == 20){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue +":05";
                        } else if (minuteValue >= 5 && minuteValue < 35) {
                            boss_appearance[i] = hourValue + ":35";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    } else if(hourValue == 21){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue +":05";
                        } else if (minuteValue >= 5 && minuteValue < 35) {
                            boss_appearance[i] = hourValue + ":35";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    } else if(hourValue == 22){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue +":05";
                        } else if (minuteValue >= 5 && minuteValue < 35) {
                            boss_appearance[i] = hourValue + ":35";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    } else if (hourValue == 23){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":05";
                        }
                    }

                }



                break;

            case 8: // 둔둔
                // 오전: 12시 5분, 1시 35분, 3시 5분, 4시 35분, 6시 5분, 7시 35분, 9시 5분, 10시 35분
                // 오후: 12시 5분, 1시 5분, 2시 5분, 3시 5분, 4시 5분, 5시 5분, 6시 5분, 7시 5분, 8시 5분, 9시 5분, 10시 35분

                if(hourValue >= 0 && hourValue <= 1){
                    if(hourValue == 0){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else if (minuteValue >= 5){
                            boss_appearance[i] = hourValue+1 + ":35";
                        }
                    } else if (hourValue == 1){
                        if (minuteValue < 35){
                            boss_appearance[i] = hourValue + ":35";
                        } else if (minuteValue >= 35){
                            boss_appearance[i] = hourValue + 2 + ":05";
                        }
                    }
                } else if (hourValue >= 1 && hourValue <= 3){
                    if(hourValue == 2){
                        boss_appearance[i] = hourValue + 1 + ":05";
                    } else if (hourValue == 3){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else if (minuteValue >= 5){
                            boss_appearance[i] = hourValue + 1 + ":35";
                        }
                    }
                } else if (hourValue >= 3 && hourValue <= 6){
                    if(hourValue == 4){
                        if(minuteValue < 35){
                            boss_appearance[i] = hourValue + ":35";
                        } else if (minuteValue >= 35){
                            boss_appearance[i] = hourValue + 2 + ":05";
                        }
                    } else if (hourValue == 5){
                        boss_appearance[i] = hourValue + 1 + ":35";
                    } else if (hourValue == 6){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue  + ":05";
                        } else if (minuteValue >= 5){
                            boss_appearance[i] = hourValue + 1 + ":35";
                        }
                    }
                } else if (hourValue >= 6 && hourValue <= 9){
                    if(hourValue == 7){
                        if(minuteValue < 35){
                            boss_appearance[i] = hourValue +  ":35";
                        } else if (minuteValue >= 35){
                            boss_appearance[i] = hourValue + 2 + ":05";
                        }
                    } else if (hourValue == 8){
                        boss_appearance[i] = hourValue + 1 + ":05";
                    } else if (hourValue == 9){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue+ ":05";
                        } else if (minuteValue >= 5){
                            boss_appearance[i] = hourValue + 1 + ":35";
                        }
                    }
                } else if (hourValue >= 9 && hourValue <= 12){
                    if(hourValue == 10){
                        if(minuteValue < 35){
                            boss_appearance[i] = hourValue + ":35";
                        } else if (minuteValue >= 35){
                            boss_appearance[i] = hourValue + 2 + ":05";
                        }
                    } else if (hourValue == 11){
                        boss_appearance[i] = hourValue + 1 + ":05";
                    } else if (hourValue == 12){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else if (minuteValue >= 5){
                            boss_appearance[i] = hourValue + ":05";
                        }
                    }
                } else if (hourValue >= 12 && hourValue <= 21){
                    if(hourValue == 13){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else {
                            boss_appearance[i] = hourValue + 1 + ":05";
                        }
                    } else if(hourValue == 14){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else {
                            boss_appearance[i] = hourValue + 1 + ":05";
                        }
                    } else if(hourValue == 15){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else {
                            boss_appearance[i] = hourValue + 1 + ":05";
                        }
                    } else if(hourValue == 16){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else {
                            boss_appearance[i] = hourValue + 1 + ":05";
                        }
                    } else if(hourValue == 17){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else {
                            boss_appearance[i] = hourValue + 1 + ":05";
                        }
                    } else if(hourValue == 18){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else {
                            boss_appearance[i] = hourValue + 1 + ":05";
                        }
                    } else if(hourValue == 19){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else {
                            boss_appearance[i] = hourValue + 1 + ":05";
                        }
                    } else if(hourValue == 20){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else {
                            boss_appearance[i] = hourValue + 1 + ":05";
                        }
                    } else if(hourValue == 21){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else {
                            boss_appearance[i] = hourValue + 1 + ":05";
                        }
                    }

                } else if (hourValue >= 21 && hourValue <= 24){
                    if(hourValue == 22){
                        if(minuteValue < 35){
                            boss_appearance[i] = hourValue + ":35";
                        } else {
                            boss_appearance[i] = hourValue +2 + ":05";
                        }
                    } else if (hourValue == 23){
                        boss_appearance[i] = hourValue +1 + ":05";
                    }
                }

                break;
            case 9: // 레버넌트 좀비
                if(hourValue >= 0 && hourValue <= 12){
                    if(minuteValue < 15){
                        boss_appearance[i] = hourValue + ":15";
                    } else {
                        boss_appearance[i] = hourValue+1 + ":15";
                    }
                } else if (hourValue >= 13 && hourValue <= 22){
                    if(minuteValue < 15){
                        boss_appearance[i] = hourValue + ":15";
                    } else if (minuteValue >= 15 && minuteValue < 45){
                        boss_appearance[i] = hourValue + ":45";
                    } else {
                        boss_appearance[i] = hourValue+1 + ":15";
                    }
                } else if (hourValue == 13){
                    if(minuteValue < 15){
                        boss_appearance[i] = hourValue + ":15";
                    } else {
                        boss_appearance[i] = hourValue+1 + ":15";
                    }
                }


                break;
            case 10: // 우르자
                if(minuteValue >= 0 && minuteValue < 5){
                    boss_appearance[i] = hourValue + ":05";
                } else if (minuteValue >= 5 && minuteValue < 15){
                    boss_appearance[i] = hourValue + ":15";
                } else if (minuteValue >= 15 && minuteValue < 25){
                    boss_appearance[i] = hourValue + ":25";
                } else if (minuteValue >= 25 && minuteValue < 35){
                    boss_appearance[i] = hourValue + ":35";
                } else if (minuteValue >= 35 && minuteValue < 45){
                    boss_appearance[i] = hourValue + ":45";
                } else if (minuteValue >= 45 && minuteValue < 55){
                    boss_appearance[i] = hourValue + ":55";
                } else if (minuteValue >= 55){
                    boss_appearance[i] = (hourValue+1) + ":05";
                }
                break;
            case 11: // 부기콜리
                if(minuteValue >= 0 && minuteValue < 5){
                    boss_appearance[i] = hourValue + ":05";
                } else if (minuteValue >= 5 && minuteValue < 15){
                    boss_appearance[i] = hourValue + ":15";
                } else if (minuteValue >= 15 && minuteValue < 25){
                    boss_appearance[i] = hourValue + ":25";
                } else if (minuteValue >= 25 && minuteValue < 35){
                    boss_appearance[i] = hourValue + ":35";
                } else if (minuteValue >= 35 && minuteValue < 45){
                    boss_appearance[i] = hourValue + ":45";
                } else if (minuteValue >= 45 && minuteValue < 55){
                    boss_appearance[i] = hourValue + ":55";
                } else if (minuteValue >= 55){
                    boss_appearance[i] = (hourValue+1) + ":05";
                }

                break;
            case 12: // 그리폰
                if(hourValue >= 0 && hourValue <= 10) {
                    if (hourValue == 0) {
                        if (minuteValue < 15) {
                            boss_appearance[i] = hourValue + ":15";
                        } else {
                            boss_appearance[i] = hourValue + ":45";
                        }
                    } else if (hourValue == 1) {
                        if (minuteValue < 45) {
                            boss_appearance[i] = hourValue + ":45";
                        } else {
                            boss_appearance[i] = hourValue + 2 + ":15";
                        }
                    } else if (hourValue == 2) {
                        boss_appearance[i] = hourValue + 1 + ":15";
                    } else if (hourValue == 3) {
                        if (minuteValue < 15) {
                            boss_appearance[i] = hourValue + ":15";
                        } else {
                            boss_appearance[i] = hourValue + 1 + ":45";
                        }
                    } else if (hourValue == 4) {
                        if (minuteValue < 45) {
                            boss_appearance[i] = hourValue + ":45";
                        } else {
                            boss_appearance[i] = hourValue + 2 + ":15";
                        }
                    } else if (hourValue == 5) {
                        boss_appearance[i] = hourValue + 1 + ":15";
                    } else if (hourValue == 6) {
                        if (minuteValue < 15) {
                            boss_appearance[i] = hourValue + ":15";
                        } else {
                            boss_appearance[i] = hourValue + 1 + ":45";
                        }
                    } else if (hourValue == 7) {
                        if (minuteValue < 45) {
                            boss_appearance[i] = hourValue + ":45";
                        } else {
                            boss_appearance[i] = hourValue + 2 + ":15";
                        }
                    } else if (hourValue == 8) {
                        boss_appearance[i] = hourValue + 1 + ":15";
                    } else if (hourValue == 9) {
                        if (minuteValue < 15) {
                            boss_appearance[i] = hourValue + ":15";
                        } else {
                            boss_appearance[i] = hourValue + 1 + ":45";
                        }
                    } else if (hourValue == 10) {
                        if (minuteValue < 45) {
                            boss_appearance[i] = hourValue + ":45";
                        } else {
                            boss_appearance[i] = hourValue + 2 + ":15";
                        }
                    }
                } else if (hourValue >= 10 && hourValue <= 12){
                    if(hourValue == 11){
                        boss_appearance[i] = hourValue+1 + ":15";
                    } else if (hourValue == 12){
                        if(minuteValue < 15){
                            boss_appearance[i] = hourValue + ":15";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":15";
                        }

                    }
                } else if (hourValue >= 12 && hourValue <= 23){
                    if(hourValue == 13){
                        if(minuteValue <15){
                            boss_appearance[i] = hourValue + ":15";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":15";
                        }
                    } else if(hourValue == 14){
                        if(minuteValue <15){
                            boss_appearance[i] = hourValue + ":15";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":15";
                        }
                    } else if(hourValue == 15){
                        if(minuteValue <15){
                            boss_appearance[i] = hourValue + ":15";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":15";
                        }
                    } else if(hourValue == 16){
                        if(minuteValue <15){
                            boss_appearance[i] = hourValue + ":15";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":15";
                        }
                    } else if(hourValue == 17){
                        if(minuteValue <15){
                            boss_appearance[i] = hourValue + ":15";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":15";
                        }
                    } else if(hourValue == 18){
                        if(minuteValue <15){
                            boss_appearance[i] = hourValue + ":15";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":15";
                        }
                    } else if(hourValue == 19){
                        if(minuteValue <15){
                            boss_appearance[i] = hourValue + ":15";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":15";
                        }
                    } else if(hourValue == 20){
                        if(minuteValue <15){
                            boss_appearance[i] = hourValue + ":15";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":15";
                        }
                    } else if(hourValue == 21){
                        if(minuteValue <15){
                            boss_appearance[i] = hourValue + ":15";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":15";
                        }
                    } else if(hourValue == 22){
                        if(minuteValue <45){
                            boss_appearance[i] = hourValue + ":45";
                        } else {
                            boss_appearance[i] = hourValue+2 + ":15";
                        }
                    } else if (hourValue == 23){
                        boss_appearance[i] = hourValue+1 + ":15";
                    }
                }
                break;
            case 13: // 프랑케네뜨
                if(hourValue >= 0 && hourValue <=12){
                    if(minuteValue < 20){
                        boss_appearance[i] = hourValue + ":20";
                    } else {
                        boss_appearance[i] = hourValue+1 + ":20";
                    }
                } else if (hourValue >= 12 && hourValue <= 22){
                    if(minuteValue < 20){
                        boss_appearance[i] = hourValue + ":20";
                    } else if (minuteValue >= 20 && minuteValue < 50){
                        boss_appearance[i] = hourValue + ":50";
                    } else {
                        boss_appearance[i] = hourValue+1 + ":20";
                    }
                } else if (hourValue == 23){
                    if(minuteValue < 20){
                        boss_appearance[i] = hourValue + ":20";
                    } else {
                        boss_appearance[i] = hourValue+1 + ":20";
                    }
                }


                break;
            case 14: // 경비대장 차우
                if(minuteValue >= 0 && minuteValue < 5){
                    boss_appearance[i] = hourValue + ":05";
                } else if (minuteValue >= 5 && minuteValue < 20){
                    boss_appearance[i] = hourValue + ":20";
                } else if (minuteValue >= 20 && minuteValue < 35){
                    boss_appearance[i] = hourValue + ":35";
                } else if (minuteValue >= 35 && minuteValue < 50){
                    boss_appearance[i] = hourValue + ":50";
                } else if (minuteValue >= 50){
                    boss_appearance[i] = hourValue + 1 + ":05";
                }
                break;
            case 15: // 그리피나

                if(hourValue >= 0 && hourValue <= 12){
                    if(hourValue == 0){
                        if(minuteValue < 25){
                            boss_appearance[i] = hourValue + ":25";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":55";
                        }
                    } else if (hourValue == 1){
                        if(minuteValue < 55){
                            boss_appearance[i] = hourValue + ":55";
                        } else {
                            boss_appearance[i] = hourValue+2 + ":25";
                        }
                    } else if (hourValue == 2){
                        boss_appearance[i] = hourValue+1 + ":45";
                    } else if (hourValue == 3){
                        if(minuteValue < 25){
                            boss_appearance[i] = hourValue + ":25";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":55";
                        }
                    } else if (hourValue == 4){
                        if(minuteValue < 55){
                            boss_appearance[i] = hourValue + ":55";
                        } else {
                            boss_appearance[i] = hourValue+2 + ":25";
                        }
                    } else if (hourValue == 5){
                        boss_appearance[i] = hourValue+1 + ":25";
                    } else if (hourValue == 6) {
                        if (minuteValue < 25) {
                            boss_appearance[i] = hourValue + ":25";
                        } else {
                            boss_appearance[i] = hourValue + 1 + ":55";
                        }
                    } else if (hourValue == 7){
                        if (minuteValue< 55){
                            boss_appearance[i] = hourValue + ":55";
                        } else {
                            boss_appearance[i] = hourValue+2 + ":25";
                        }
                    } else if (hourValue == 8){
                        boss_appearance[i] = hourValue+1 + ":45";
                    } else if (hourValue == 9) {
                        if (minuteValue < 25) {
                            boss_appearance[i] = hourValue + ":25";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":55";
                        }
                    } else if (hourValue == 10){
                        if(minuteValue < 55){
                            boss_appearance[i] = hourValue + ":55";
                        } else {
                            boss_appearance[i] = hourValue+2 + ":25";
                        }
                    } else if (hourValue == 11){
                        boss_appearance[i] = hourValue+1 + ":25";
                    } else if (hourValue == 12){
                        if(minuteValue < 25){
                            boss_appearance[i] = hourValue + ":25";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":25";
                        }
                    }
                } else if (hourValue >= 12 && hourValue <= 24){
                    if(hourValue == 13){
                        if(minuteValue < 25){
                            boss_appearance[i] = hourValue + ":25";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":25";
                        }
                    } else if(hourValue == 14){
                        if(minuteValue < 25){
                            boss_appearance[i] = hourValue + ":25";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":25";
                        }
                    } else if(hourValue == 15){
                        if(minuteValue < 25){
                            boss_appearance[i] = hourValue + ":25";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":25";
                        }
                    } else if(hourValue == 16){
                        if(minuteValue < 25){
                            boss_appearance[i] = hourValue + ":25";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":25";
                        }
                    } else if(hourValue == 17){
                        if(minuteValue < 25){
                            boss_appearance[i] = hourValue + ":25";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":25";
                        }
                    } else if(hourValue == 18){
                        if(minuteValue < 25){
                            boss_appearance[i] = hourValue + ":25";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":25";
                        }
                    } else if(hourValue == 19){
                        if(minuteValue < 25){
                            boss_appearance[i] = hourValue + ":25";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":25";
                        }
                    } else if(hourValue == 20){
                        if(minuteValue < 25){
                            boss_appearance[i] = hourValue + ":25";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":25";
                        }
                    } else if(hourValue == 21){
                        if(minuteValue < 25){
                            boss_appearance[i] = hourValue + ":25";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":55";
                        }
                    } else if(hourValue == 22){
                        if(minuteValue < 55){
                            boss_appearance[i] = hourValue + ":55";
                        } else {
                            boss_appearance[i] = hourValue+2 + ":25";
                        }
                    } else if (hourValue == 23){
                        boss_appearance[i] = hourValue+1 + ":25";
                    }
                }

                break;
            case 16: // 매드오네뜨
                if(hourValue >= 13 && hourValue <=  22){
                    if(minuteValue >= 0 && minuteValue < 30){
                        boss_appearance[i] = hourValue + ":30";
                    } else if (minuteValue >= 30 && minuteValue <= 59){
                        boss_appearance[i] = (hourValue +  1) + ":00";
                    }
                } else {
                    if(minuteValue >= 0 && minuteValue < 30){
                        boss_appearance[i] = hourValue + ":30";
                    } else {
                        boss_appearance[i] = (hourValue + 1) + ":30";
                    }
                }
                break;
            case 17: // 자이언트 터틀
                // 오전: 12시 35분, 2시 5분, 3시 35분, 5시 5분, 6시 35분, 8시 5분, 9시 35분, 11시 5분
                if(hourValue >= 0 && hourValue <= 2){
                    if(hourValue == 12){
                        if(minuteValue < 35){
                            boss_appearance[i] = hourValue + ":35";
                        } else if (minuteValue >= 35){
                            boss_appearance[i] = (hourValue+2) + ":05";
                        }
                    } else if (hourValue == 1){
                        boss_appearance[i] = (hourValue+1) + ":05";
                    } else if (hourValue == 2){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        }
                    }
                } else if (hourValue >= 2 && hourValue <= 3){
                    if(hourValue == 2){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else if (minuteValue >= 5){
                            boss_appearance[i] = (hourValue+1) + ":35";
                        }
                    } else if (hourValue == 3){
                        if(minuteValue < 35){
                            boss_appearance[i] = hourValue + ":35";
                        } else if (minuteValue >= 35){
                            boss_appearance[i] = (hourValue+2) + ":05";
                        }
                    }
                } else if (hourValue >= 3 && hourValue <= 5){
                    if(hourValue == 4) {
                        boss_appearance[i] = (hourValue+1) + ":05";
                    } else if (hourValue == 5){
                        if(minuteValue < 05){
                            boss_appearance[i] = hourValue + ":05";
                        } else if(minuteValue >= 05){
                            boss_appearance[i] = hourValue+1 + ":35";
                        }
                    }
                } else if (hourValue >= 5 && hourValue <= 6){
                    if(hourValue == 6){
                        if(minuteValue < 35){
                            boss_appearance[i] = hourValue + ":35";
                        } else if (minuteValue >= 35){
                            boss_appearance[i] = hourValue+2 + ":05";
                        }
                    }
                } else if (hourValue >= 6 && hourValue <= 8){
                    if(hourValue == 7){
                        boss_appearance[i] = hourValue+1 + ":05";
                    } else if (hourValue == 8){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else if (minuteValue >= 5){
                            boss_appearance[i] = hourValue+1 + ":35";
                        }

                    }
                } else if (hourValue >= 8 && hourValue <= 9){
                    if(hourValue == 9){
                        if(minuteValue < 35){
                            boss_appearance[i] = hourValue + ":35";
                        } else if (minuteValue >= 35){
                            boss_appearance[i] = hourValue+2 + ":05";
                        }
                    }
                } else if (hourValue >= 9 && hourValue <= 11){
                    if(hourValue == 10){
                        boss_appearance[i] = hourValue+1 + ":05";
                    } else if (hourValue == 11){
                        if(minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else if (minuteValue >= 5){
                            boss_appearance[i] = hourValue+1 + ":35";
                        }
                    }
                } else if (hourValue >= 11 && hourValue <= 12){
                    if(hourValue == 12){
                        if(minuteValue < 35){
                            boss_appearance[i] = hourValue + ":35";
                        } else if(minuteValue >= 35){
                            boss_appearance[i] = hourValue+1 + ":35";
                        }
                    }
                } else if (hourValue >= 12 && hourValue <= 21){
                    if(hourValue == 13){
                        if(minuteValue < 35){
                            boss_appearance[i] = hourValue + ":35";
                        } else{
                            boss_appearance[i] = hourValue+1 + ":35";
                        }
                    } else if(hourValue == 14){
                        if(minuteValue < 35){
                            boss_appearance[i] = hourValue + ":35";
                        } else{
                            boss_appearance[i] = hourValue+1 + ":35";
                        }
                    }
                    else if(hourValue == 15){
                        if(minuteValue < 35){
                            boss_appearance[i] = hourValue + ":35";
                        } else{
                            boss_appearance[i] = hourValue+1 + ":35";
                        }
                    }
                    else if(hourValue == 16){
                        if(minuteValue < 35){
                            boss_appearance[i] = hourValue + ":35";
                        } else{
                            boss_appearance[i] = hourValue+1 + ":35";
                        }
                    }
                    else if(hourValue == 17){
                        if(minuteValue < 35){
                            boss_appearance[i] = hourValue + ":35";
                        } else{
                            boss_appearance[i] = hourValue+1 + ":35";
                        }
                    }
                    else if(hourValue == 18){
                        if(minuteValue < 35){
                            boss_appearance[i] = hourValue + ":35";
                        } else{
                            boss_appearance[i] = hourValue+1 + ":35";
                        }
                    }
                    else if(hourValue == 19){
                        if(minuteValue < 35){
                            boss_appearance[i] = hourValue + ":35";
                        } else{
                            boss_appearance[i] = hourValue+1 + ":35";
                        }
                    }
                    else if(hourValue == 20){
                        if(minuteValue < 35){
                            boss_appearance[i] = hourValue + ":35";
                        } else{
                            boss_appearance[i] = hourValue+1 + ":35";
                        }
                    }
                    else if(hourValue == 21){
                        if(minuteValue < 35){
                            boss_appearance[i] = hourValue + ":35";
                        } else{
                            boss_appearance[i] = hourValue+2 + ":05";
                        }
                    }
                } else if (hourValue >= 21 && hourValue <= 23){
                    if(hourValue == 22){
                        boss_appearance[i] = hourValue+1 + ":05";
                    } else if (hourValue == 23){
                        if (minuteValue < 5){
                            boss_appearance[i] = hourValue + ":05";
                        } else {
                            boss_appearance[i] = hourValue+1 + ":35";
                        }
                    }

                }
                break;
            default:
        }
        return boss_appearance;
    }
}

