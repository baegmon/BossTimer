package baegmon.com.bosstimer;

public class Boss {


    private int boss_icon_resource;
    private String boss_name;
    private String boss_time_resource;
    private String boss_appearance;
    private String boss_level;


    public String getBoss_level() {
        return boss_level;
    }

    public void setBoss_level(String boss_level) {
        this.boss_level = boss_level;
    }

    public Boss(int boss_icon, String boss_name, String boss_time, String boss_appearance, String boss_level ){
        this.setBoss_icon(boss_icon);
        this.setBoss_name(boss_name);
        this.setBoss_time(boss_time);
        this.setBoss_appearance(boss_appearance);
        this.setBoss_level(boss_level);


    }

    public int getBoss_icon() {
        return boss_icon_resource;
    }


    public void setBoss_icon(int boss_icon) {
        this.boss_icon_resource = boss_icon;
    }

    public String getBoss_time() {
        return boss_time_resource;
    }

    public void setBoss_time(String boss_time){
        this.boss_time_resource = boss_time;
    }

    public String getBoss_name() {
        return boss_name;
    }

    public void setBoss_name(String boss_name) {
        this.boss_name = boss_name;
    }

    public String getBoss_appearance() {
        return boss_appearance;
    }

    public void setBoss_appearance(String boss_appearance) {
        this.boss_appearance = boss_appearance;
    }



}
