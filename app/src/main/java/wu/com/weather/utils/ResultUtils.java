package wu.com.weather.utils;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import wu.com.weather.db.DBUtils;
import wu.com.weather.gson.AQI;
import wu.com.weather.gson.Weather;
import wu.com.weather.model.City;
import wu.com.weather.model.Country;
import wu.com.weather.model.Province;

/**
 * 解析从服务器得到的数据，并将其存入数据表
 */
public class ResultUtils {

    /**
     * 解析和处理服务器返回的省级数据
     */
    public synchronized static boolean getProvincesResult(DBUtils dbUtils, String result) {
        if (!TextUtils.isEmpty(result)) {
            try {
                JSONArray allProvinces = new JSONArray(result);
                for (int i = 0; i < allProvinces.length(); i++) {
                    JSONObject provinceObject = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceCode(provinceObject.getString("id"));
                    province.setProvinceName(provinceObject.getString("name"));
                    // 将解析出来的数据存储到Province表
                    dbUtils.saveProvince(province);
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的市级数据
     */
    public static boolean getCityResult(DBUtils dbUtils,
                                               String result, int provinceId) {
        if (!TextUtils.isEmpty(result)) {
            try {
                JSONArray allCities = new JSONArray(result);
                for (int i = 0; i < allCities.length(); i++) {
                    JSONObject cityObject = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityCode(cityObject.getString("id"));
                    city.setCityName(cityObject.getString("name"));
                    city.setProvinceId(provinceId);
                    // 将解析出来的数据存储到Province表
                    dbUtils.saveCity(city);
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    /**
     * 解析和处理服务器返回的县级数据
     */
    public static boolean getCountryResult(DBUtils dbUtils,
                                                 String result, int cityId) {
        if (!TextUtils.isEmpty(result)) {
            try {
                JSONArray allCounties = new JSONArray(result);
                for(int i = 0; i<allCounties.length() ; i++){
                    JSONObject countryObject = allCounties.getJSONObject(i);
                        Country country = new Country();
                        country.setweatherId(countryObject.getString("weather_id"));
                        country.setCountryName(countryObject.getString("name"));
                        country.setCityId(cityId);
    // 将解析出来的数据存储到County表
                        dbUtils.saveCounty(country);
                    }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 将返回的JSON数据解析成Weather实体类
     */
    public static Weather handleWeatherResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather6");
            String weatherContent = jsonArray.getJSONObject(0).toString();
            Log.i("结果数据：",weatherContent);
            return new Gson().fromJson(weatherContent, Weather.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static AQI handleAQIResponse(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather6");
            String aqiContent = jsonArray.getJSONObject(0).toString();
            Log.i("AQI结果数据：",aqiContent);
            return new Gson().fromJson(aqiContent, AQI.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
