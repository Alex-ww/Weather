package wu.com.weather.gson;

public class AQI {

    /**
     * basic : {"cid":"CN101010100","location":"北京","parent_city":"北京","admin_area":"北京","cnty":"中国","lat":"39.90498734","lon":"116.4052887","tz":"+8.00"}
     * update : {"loc":"2019-02-13 10:55","utc":"2019-02-13 02:55"}
     * status : ok
     * air_now_city : {"aqi":"20","qlty":"优","main":"-","pm25":"10","pm10":"8","no2":"9","so2":"3","co":"0.4","o3":"63","pub_time":"2019-02-13 11:00"}
     * air_now_station : [{"air_sta":"万寿西宫","aqi":"18","asid":"CNA1001","co":"0.3","lat":"39.8673","lon":"116.366","main":"-","no2":"10","o3":"55","pm10":"0","pm25":"12","pub_time":"2019-02-13 11:00","qlty":"优","so2":"2"},{"air_sta":"定陵","aqi":"20","asid":"CNA1002","co":"0.2","lat":"40.2865","lon":"116.17","main":"-","no2":"1","o3":"64","pm10":"3","pm25":"0","pub_time":"2019-02-13 11:00","qlty":"优","so2":"2"},{"air_sta":"东四","aqi":"22","asid":"CNA1003","co":"0.3","lat":"39.9522","lon":"116.434","main":"-","no2":"11","o3":"69","pm10":"4","pm25":"4","pub_time":"2019-02-13 11:00","qlty":"优","so2":"4"},{"air_sta":"天坛","aqi":"18","asid":"CNA1004","co":"0.5","lat":"39.8745","lon":"116.434","main":"-","no2":"17","o3":"57","pm10":"10","pm25":"8","pub_time":"2019-02-13 11:00","qlty":"优","so2":"2"},{"air_sta":"农展馆","aqi":"19","asid":"CNA1005","co":"0.3","lat":"39.9716","lon":"116.473","main":"-","no2":"6","o3":"60","pm10":"7","pm25":"7","pub_time":"2019-02-13 11:00","qlty":"优","so2":"2"},{"air_sta":"官园","aqi":"26","asid":"CNA1006","co":"0.5","lat":"39.9425","lon":"116.361","main":"-","no2":"15","o3":"51","pm10":"0","pm25":"18","pub_time":"2019-02-13 11:00","qlty":"优","so2":"5"},{"air_sta":"海淀区万柳","aqi":"21","asid":"CNA1007","co":"0.2","lat":"39.9934","lon":"116.315","main":"-","no2":"8","o3":"67","pm10":"15","pm25":"6","pub_time":"2019-02-13 11:00","qlty":"优","so2":"3"},{"air_sta":"顺义新城","aqi":"24","asid":"CNA1008","co":"0.3","lat":"40.1438","lon":"116.72","main":"-","no2":"2","o3":"74","pm10":"0","pm25":"6","pub_time":"2019-02-13 11:00","qlty":"优","so2":"4"},{"air_sta":"怀柔镇","aqi":"23","asid":"CNA1009","co":"0.2","lat":"40.3937","lon":"116.644","main":"-","no2":"9","o3":"71","pm10":"0","pm25":"9","pub_time":"2019-02-13 11:00","qlty":"优","so2":"3"},{"air_sta":"昌平镇","aqi":"23","asid":"CNA1010","co":"0.3","lat":"40.1952","lon":"116.23","main":"-","no2":"2","o3":"72","pm10":"5","pm25":"2","pub_time":"2019-02-13 11:00","qlty":"优","so2":"2"},{"air_sta":"奥体中心","aqi":"22","asid":"CNA1011","co":"0.3","lat":"40.0031","lon":"116.407","main":"-","no2":"11","o3":"56","pm10":"0","pm25":"15","pub_time":"2019-02-13 11:00","qlty":"优","so2":"2"},{"air_sta":"古城","aqi":"22","asid":"CNA1012","co":"0.4","lat":"39.9279","lon":"116.225","main":"-","no2":"9","o3":"54","pm10":"0","pm25":"15","pub_time":"2019-02-13 11:00","qlty":"优","so2":"5"}]
     */
    private BasicBean basic;
    private String status;
    private AirNowCityBean air_now_city;

    public AirNowCityBean getAir_now_city() {
        return air_now_city;
    }

    public void setAir_now_city(AirNowCityBean air_now_city) {
        this.air_now_city = air_now_city;
    }

    public static class AirNowCityBean{
        private String aqi;
        private String pm25;

        public String getAqi() {
            return aqi;
        }

        public void setAqi(String aqi) {
            this.aqi = aqi;
        }

        public String getPm25() {
            return pm25;
        }

        public void setPm25(String pm25) {
            this.pm25 = pm25;
        }
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

        private String cid;
        private String location;

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }
    }

    public BasicBean getBasic() {
        return basic;
    }

    public void setBasic(BasicBean basic) {
        this.basic = basic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
