package wu.com.weather.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import wu.com.weather.model.City;
import wu.com.weather.model.Country;
import wu.com.weather.model.Province;

public class DBUtils {

    private static final String DB_NAME = "weather";     //数据库名
    private static final int Vision = 1;   //版本

    private static WeatherOpenHelper weatherOpenHelper;
    private static DBUtils dbUtils ;
    private SQLiteDatabase  db;

    private DBUtils(Context context){                             //构造方法私有化
        weatherOpenHelper = new WeatherOpenHelper(context,DB_NAME,null,Vision);
        db = weatherOpenHelper.getReadableDatabase();
    }

    public synchronized static DBUtils getInstance(Context context){              //获取DButils的实例
        if(dbUtils == null){
            dbUtils = new DBUtils(context);
        }
        return dbUtils;
    }

    public void saveProvince(Province province){                           //储存进省份表
        if(province != null){
            ContentValues values = new ContentValues();
            values.put("province_name",province.getProvinceName());
            values.put("province_code",province.getProvinceCode());
            db.insert("province",null,values);
        }
    }

    /**
     * 从数据库读取全国所有的省份信息。
     */
    public List<Province> loadProvinces() {
         List<Province> provinceList = new ArrayList<>();
        Cursor cursor = db.query("province" ,null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do {
             Province province = new Province();
             province.setId(cursor.getInt(cursor.getColumnIndex("id")));
             province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
             province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
             provinceList.add(province);
            }while (cursor.moveToNext());
        }
        return provinceList;
    }

    /**
     * 将City实例存储到数据库。
     */
    public void saveCity(City city) {
        if (city != null) {
            ContentValues values = new ContentValues();
            values.put("city_name", city.getCityName());
            values.put("city_code", city.getCityCode());
            values.put("province_id", city.getProvinceId());
            db.insert("City", null, values);
        }
    }
    /**
     * 从数据库读取某省下所有的城市信息。
     */
    public List<City> loadCities(int provinceId) {
        List<City> list = new ArrayList<City>();
        Cursor cursor = db.query("City", null, "province_id = ?",
                new String[] { String.valueOf(provinceId) }, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                City city = new City();
                city.setCityId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityName(cursor.getString(cursor
                        .getColumnIndex("city_name")));
                city.setCityCode(cursor.getString(cursor
                        .getColumnIndex("city_code")));
                city.setProvinceId(provinceId);
                list.add(city);
            } while (cursor.moveToNext());
        }
        return list;
    }
    /**
     * 将Country实例存储到数据库。
     */
    public void saveCounty(Country country) {
        if (country != null) {
            ContentValues values = new ContentValues();
            values.put("country_name", country.getCountryName());
            values.put("weather_id", country.getweatherId());
            values.put("city_id", country.getCityId());
            db.insert("Country", null, values);
        }
    }
    /**
     * 从数据库读取某城市下所有的县信息。
     */
    public List<Country> loadCounties(int cityId) {
        List<Country> list = new ArrayList<Country>();
        Cursor cursor = db.query("Country", null, "city_id = ?",
                new String[] { String.valueOf(cityId) }, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Country country = new Country();
                country.setCountryId(cursor.getInt(cursor.getColumnIndex("id")));
                country.setCountryName(cursor.getString(cursor
                        .getColumnIndex("country_name")));
                country.setweatherId(cursor.getString(cursor
                        .getColumnIndex("weather_id")));
                country.setCityId(cityId);
                list.add(country);
            } while (cursor.moveToNext());
        }
        return list;
    }
}

