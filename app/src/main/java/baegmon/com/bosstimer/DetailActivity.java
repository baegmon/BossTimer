package baegmon.com.bosstimer;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends Activity {

    int[] boss_icon = {R.drawable.boss_1, R.drawable.boss_2, R.drawable.boss_3,
            R.drawable.boss_4, R.drawable.boss_5, R.drawable.boss_6, R.drawable.boss_7,
            R.drawable.boss_8, R.drawable.boss_9, R.drawable.boss_10, R.drawable.boss_11,
            R.drawable.boss_12, R.drawable.boss_13, R.drawable.boss_14, R.drawable.boss_15,
            R.drawable.boss_16, R.drawable.boss_17, R.drawable.boss_18, R.drawable.boss_19,
            R.drawable.boss_20, R.drawable.boss_21, R.drawable.boss_22, R.drawable.boss_23, R.drawable.boss_24,
            R.drawable.boss_25, R.drawable.boss_26, R.drawable.boss_27, R.drawable.boss_28, R.drawable.boss_29,
            R.drawable.boss_30};


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

    private void createDetailedView(){
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/cl.ttf");
        ImageView bossIcon = (ImageView) findViewById(R.id.boss_detail_icon);
        TextView name = (TextView) findViewById(R.id.boss_detail_name);
        TextView type = (TextView) findViewById(R.id.boss_detail_type);
        TextView location = (TextView) findViewById(R.id.boss_detail_location);
        TextView level = (TextView) findViewById(R.id.boss_detail_level);
        TextView hp = (TextView) findViewById(R.id.boss_detail_hp);
        name.setTypeface(custom_font);
        location.setTypeface(custom_font);
        type.setTypeface(custom_font);
        level.setTypeface(custom_font);
        hp.setTypeface(custom_font);


        // set fonts
        TextView text = (TextView) findViewById(R.id.textView);
        TextView text2 = (TextView) findViewById(R.id.textView2);
        TextView text3 = (TextView) findViewById(R.id.textView3);
        TextView text4 = (TextView) findViewById(R.id.textView4);
        TextView text5 = (TextView) findViewById(R.id.textView5);
        text.setTypeface(custom_font);
        text2.setTypeface(custom_font);
        text3.setTypeface(custom_font);
        text4.setTypeface(custom_font);
        text5.setTypeface(custom_font);





        Intent intent = this.getIntent();


        String bossName = intent.getStringExtra("bossName");
        String bossLocation = getBossLocation(bossName);
        String bossType = getBossType(bossName);
        String bossLevel = getBossLevel(bossName);
        String bossHP = getBossHP(bossName);


        int bossID = getID(bossName);
        hp.setText(bossHP);
        name.setText(bossName);
        type.setText(bossType);
        level.setText(bossLevel);
        location.setText(bossLocation);
        bossIcon.setImageResource(boss_icon[bossID]);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        createDetailedView();;

    }



    private String getBossHP(String bossName){
        String hp = "";

        if(bossName.equals("에피")){
            hp = "69,520";
        }

        if(bossName.equals("닉시")){
            hp = "94,915";
        }

        if(bossName.equals("다크 지란트")){
            hp = "477,133";
        }

        if (bossName.equals("마크52 알파")) {
            hp = "5,452,133";
        }

        if(bossName.equals("자이언트 라바아이")){
            hp = "477,133";
        }

        if(bossName.equals("부기콜리")){
            hp = "3,626";
        }

        if(bossName.equals("자이언트 터틀")){
            hp = "2,078,673";
        }

        if(bossName.equals("경비대장 차우")){
            hp = "58,566";
        }

        if(bossName.equals("깡패 바라하")){
            hp = "282,164";
        }

        if(bossName.equals("우르자")){
            hp = "8,226";
        }

        if(bossName.equals("머쉬맘")){
            hp = "212,682";
        }

        if(bossName.equals("데블린 워리어")){
            hp = "3,100,377";
        }

        if(bossName.equals("둔둔")){
            hp = "1,417,485";
        }

        if(bossName.equals("그리폰")){
            hp = "3,759,015";
        }

        if(bossName.equals("그리피나")){
            hp = "6,955,418";
        }

        if (bossName.equals("매드오네뜨")){
            hp = "125,287";

        }

        if (bossName.equals("프랑케네뜨")){
            hp = "339,790";
        }

        if(bossName.equals("레버넌트 좀비")){
            hp = "234,670";
        }

        if(bossName.equals("분노의 바포메트")){
            hp = "14,352,800";
        }

        if(bossName.equals("레르노스")){
            hp = "14,352,800";
        }

        if(bossName.equals("우르판다")){
            hp = "984,585";
        }

        if(bossName.equals("소환술사 라툰")){
            hp = "787,668";
        }

        if(bossName.equals("바람술사 라팽")){
            hp = "858,326";
        }

        if(bossName.equals("알파 터틀")){
            hp = "11,764,618";
        }


        if(bossName.equals("마노")){
            hp = "807,039";
        }

        if(bossName.equals("바야르 수문장")){
            hp = "10,217,570";
        }

        if(bossName.equals("킹슬라임")){
            hp = "700,913";
        }

        if(bossName.equals("스텀피")){
            hp = "647,676";
        }

        if(bossName.equals("로로와 무무스")){
            hp = "8,133,308";
        }

        if(bossName.equals("좀비머쉬맘")){
            hp = "557,935";
        }


        return hp;
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


    private String getBossLocation(String bossName){
        String location = "";

        if(bossName.equals("에피") || bossName.equals("닉시")){
            location =  "요정 나무 호수";
        }

        if(bossName.equals("레버넌트 좀비")){
            location = "나무꾼의 언덕 -> 버려진 납골당";
        }

        if(bossName.equals("다크 지란트")){
            location = "토르하라의 샘물";
        }

        if (bossName.equals("마크52 알파")) {
            location = "뉴런 DNA 연구 센터";
        }

        if(bossName.equals("자이언트 라바아이")){
            location = "핫뜨뜨 강가 -> 라바아이의 둥지";
        }

        if(bossName.equals("부기콜리")){
            location = "로얄로드 진입광장 -> 부기콜리 동굴";
        }

        if(bossName.equals("자이언트 터틀")){
            location = "비치웨이 111";
        }

        if(bossName.equals("경비대장 차우")){
            location = "커닝 인터체인지";
        }

        if(bossName.equals("깡패 바라하")){
            location = "앙드레아 가문의 영지";
        }

        if(bossName.equals("우르자")){
            location = "굽이치는 협곡";
        }

        if(bossName.equals("머쉬맘")){
            location = "헤네시스 -> 남의 집";
        }

        if(bossName.equals("데블린 워리어")){
            location = "로얄로드 남부";
        }

        if(bossName.equals("둔둔")){
            location = "커닝 폐기물 처리장";
        }

        if(bossName.equals("그리폰")){
            location = "차가운 심장";
        }

        if(bossName.equals("그리피나")){
            location = "트리니안 가도";
        }

        if (bossName.equals("매드오네뜨")){
            location = "플로라 애비뉴";

        }

        if (bossName.equals("프랑케네뜨")){
            location = "뉴런 DNA 연구 센터 -> 연구센터 지하실";
        }

        if(bossName.equals("킹슬라임")){
            location = "신의 샘물방울";
        }

        if(bossName.equals("스텀피")){
            location = "스위트 밸리";
        }

        if(bossName.equals("로로와 무무스")){
            location = "바움나무";
        }

        if(bossName.equals("좀비머쉬맘")){
            location = "개미굴 입구";
        }

        if(bossName.equals("바야르 수문장")){
            location = "깎은 절벽 요새";
        }

        if (bossName.equals("마노")) {
            location = "그리니아 폭포";
        }

        if(bossName.equals("알파 터틀")){
            location = "엘루아 강가";
        }

        if(bossName.equals("바람술사 라팽")){
            location = "녹아내린 정원";
        }

        if(bossName.equals("소환술사 라툰")){
            location = "장미의 성";
        }

        if(bossName.equals("우르판다")){
            location = "무지개 상";
        }

        if(bossName.equals("레르노스")){
            location = "퍼플 문 캐슬";
        }

        if(bossName.equals("분노의 바포메트")){
            location = "캐슬 리버스";
        }


        return location;
    }

    private String getBossType(String bossName){
        String type = "";

        if(bossName.equals("우르자") || bossName.equals("부기콜리") || bossName.equals("매드오네뜨") || bossName.equals("닉시") || bossName.equals("에피")
                || bossName.equals("경비대장 차우") || bossName.equals("머쉬맘") || bossName.equals("레버넌트 좀비") ||
                bossName.equals("깡패 바라하") || bossName.equals("프랑케네뜨") || bossName.equals("다크 지란트") ||
                bossName.equals("자이언트 라바아이") || bossName.equals("우르판다") || bossName.equals("소환술사 라툰") ||
                bossName.equals("바람술사 라팽") || bossName.equals("마노") || bossName.equals("스텀피") ||
                bossName.equals("좀비머쉬맘")){
            type = "엘리트";
        } else {
            type = "보스";
        }


        return type;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
