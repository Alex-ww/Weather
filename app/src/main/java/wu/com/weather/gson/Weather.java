package wu.com.weather.gson;

import java.util.List;

public class Weather {
    /**
     * basic : {"cid":"CN101010100","location":"北京","parent_city":"北京","admin_area":"北京","cnty":"中国","lat":"39.90498734","lon":"116.4052887","tz":"+8.00"}
     * update : {"loc":"2019-02-13 09:55","utc":"2019-02-13 01:55"}
     * status : ok
     * now : {"cloud":"91","cond_code":"101","cond_txt":"多云","fl":"-9","hum":"27","pcpn":"0.0","pres":"1041","tmp":"-3","vis":"23","wind_deg":"13","wind_dir":"东北风","wind_sc":"3","wind_spd":"18"}
     * daily_forecast : [{"cond_code_d":"101","cond_code_n":"101","cond_txt_d":"多云","cond_txt_n":"多云","date":"2019-02-13","hum":"56","mr":"11:33","ms":"00:48","pcpn":"0.0","pop":"0","pres":"1041","sr":"07:08","ss":"17:48","tmp_max":"1","tmp_min":"-6","uv_index":"2","vis":"20","wind_deg":"16","wind_dir":"东北风","wind_sc":"3-4","wind_spd":"19"},{"cond_code_d":"400","cond_code_n":"101","cond_txt_d":"小雪","cond_txt_n":"多云","date":"2019-02-14","hum":"81","mr":"12:12","ms":"01:52","pcpn":"0.0","pop":"15","pres":"1035","sr":"07:07","ss":"17:50","tmp_max":"-1","tmp_min":"-6","uv_index":"0","vis":"17","wind_deg":"122","wind_dir":"东南风","wind_sc":"1-2","wind_spd":"5"},{"cond_code_d":"100","cond_code_n":"100","cond_txt_d":"晴","cond_txt_n":"晴","date":"2019-02-15","hum":"59","mr":"12:59","ms":"02:58","pcpn":"0.0","pop":"0","pres":"1031","sr":"07:06","ss":"17:51","tmp_max":"2","tmp_min":"-7","uv_index":"3","vis":"20","wind_deg":"288","wind_dir":"西北风","wind_sc":"4-5","wind_spd":"29"}]
     * lifestyle : [{"type":"comf","brf":"较不舒适","txt":"白天天气阴沉，您会感觉偏冷，不很舒适，请注意添加衣物，以防感冒。"},{"type":"drsg","brf":"冷","txt":"天气冷，建议着棉服、羽绒服、皮夹克加羊毛衫等冬季服装。年老体弱者宜着厚棉衣、冬大衣或厚羽绒服。"},{"type":"flu","brf":"较易发","txt":"天气较凉，较易发生感冒，请适当增加衣服。体质较弱的朋友尤其应该注意防护。"},{"type":"sport","brf":"较不宜","txt":"天气较好，但考虑天气寒冷，风力较强，推荐您进行室内运动，若户外运动请注意保暖并做好准备活动。"},{"type":"trav","brf":"一般","txt":"天空状况还是比较好的，但温度比较低，且风稍大，会让人感觉有点冷。外出请备上防风保暖衣物。"},{"type":"uv","brf":"最弱","txt":"属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"},{"type":"cw","brf":"较适宜","txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"},{"type":"air","brf":"良","txt":"气象条件有利于空气污染物稀释、扩散和清除，可在室外正常活动。"}]
     */

    private BasicBean basic;
    private UpdateBean update;
    private String status;
    private NowBean now;
    private List<DailyForecastBean> daily_forecast;
    private List<LifestyleBean> lifestyle;

    public BasicBean getBasic() {
        return basic;
    }

    public void setBasic(BasicBean basic) {
        this.basic = basic;
    }

    public UpdateBean getUpdate() {
        return update;
    }

    public void setUpdate(UpdateBean update) {
        this.update = update;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public NowBean getNow() {
        return now;
    }

    public void setNow(NowBean now) {
        this.now = now;
    }

    public List<DailyForecastBean> getDaily_forecast() {
        return daily_forecast;
    }

    public void setDaily_forecast(List<DailyForecastBean> daily_forecast) {
        this.daily_forecast = daily_forecast;
    }

    public List<LifestyleBean> getLifestyle() {
        return lifestyle;
    }

    public void setLifestyle(List<LifestyleBean> lifestyle) {
        this.lifestyle = lifestyle;
    }

    public static class BasicBean {
        /**
         * cid : CN101010100
         * location : 北京
         * parent_city : 北京
         * admin_area : 北京
         * cnty : 中国
         * lat : 39.90498734
         * lon : 116.4052887
         * tz : +8.00
         */

        private String location;
        private String cid;

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }
    }

    public static class UpdateBean {
        /**
         * loc : 2019-02-13 09:55
         * utc : 2019-02-13 01:55
         */

        private String loc;

        public String getLoc() {
            return loc;
        }

        public void setLoc(String loc) {
            this.loc = loc;
        }
    }

    public static class NowBean {
        /**
         * cloud : 91
         * cond_code : 101
         * cond_txt : 多云
         * fl : -9
         * hum : 27
         * pcpn : 0.0
         * pres : 1041
         * tmp : -3
         * vis : 23
         * wind_deg : 13
         * wind_dir : 东北风
         * wind_sc : 3
         * wind_spd : 18
         */

        private String cond_txt;
        private String tmp;
        private String wind_deg;

        public String getCond_txt() {
            return cond_txt;
        }

        public void setCond_txt(String cond_txt) {
            this.cond_txt = cond_txt;
        }

        public String getTmp() {
            return tmp;
        }

        public void setTmp(String tmp) {
            this.tmp = tmp;
        }

        public String getWind_deg() {
            return wind_deg;
        }

        public void setWind_deg(String wind_deg) {
            this.wind_deg = wind_deg;
        }
    }

    public static class DailyForecastBean {
        /**
         * cond_code_d : 101
         * cond_code_n : 101
         * cond_txt_d : 多云
         * cond_txt_n : 多云
         * date : 2019-02-13
         * hum : 56
         * mr : 11:33
         * ms : 00:48
         * pcpn : 0.0
         * pop : 0
         * pres : 1041
         * sr : 07:08
         * ss : 17:48
         * tmp_max : 1
         * tmp_min : -6
         * uv_index : 2
         * vis : 20
         * wind_deg : 16
         * wind_dir : 东北风
         * wind_sc : 3-4
         * wind_spd : 19
         */

        private String cond_txt_d;
        private String date;
        private String tmp_max;
        private String tmp_min;

        public String getCond_txt_d() {
            return cond_txt_d;
        }

        public void setCond_txt_d(String cond_txt_d) {
            this.cond_txt_d = cond_txt_d;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTmp_max() {
            return tmp_max;
        }

        public void setTmp_max(String tmp_max) {
            this.tmp_max = tmp_max;
        }

        public String getTmp_min() {
            return tmp_min;
        }

        public void setTmp_min(String tmp_min) {
            this.tmp_min = tmp_min;
        }
    }

    public static class LifestyleBean {
        /**
         * type : comf
         * brf : 较不舒适
         * txt : 白天天气阴沉，您会感觉偏冷，不很舒适，请注意添加衣物，以防感冒。
         */

        private String type;
        private String brf;
        private String txt;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getBrf() {
            return brf;
        }

        public void setBrf(String brf) {
            this.brf = brf;
        }

        public String getTxt() {
            return txt;
        }

        public void setTxt(String txt) {
            this.txt = txt;
        }
    }

}
