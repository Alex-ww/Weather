package wu.com.weather.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.widget.Toast;

import wu.com.weather.WeatherActivity;
import wu.com.weather.gson.AQI;
import wu.com.weather.gson.Weather;
import wu.com.weather.utils.HttpUtils;
import wu.com.weather.utils.ResultUtils;

public class AutoUpdateService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        updateWeather();
        updateBingPic();
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int anHour = 8 * 60 * 60 * 1000; // 这是8小时的毫秒数
        long triggerAtTime = SystemClock.elapsedRealtime() + anHour;
        Intent i = new Intent(this, AutoUpdateService.class);
        PendingIntent pi = PendingIntent.getService(this, 0, i, 0);
        manager.cancel(pi);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 更新天气信息。
     */
    private void updateWeather(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String weatherString = prefs.getString("weather", null);
        String aqiString = prefs.getString("aqi",null);
        if (weatherString != null) {
            // 有缓存时直接解析天气数据
            Weather weather = ResultUtils.handleWeatherResponse(weatherString);
            AQI aqi = ResultUtils.handleAQIResponse(aqiString);
            String weatherId = weather.getBasic().getCid();
            String weatherUrl = "https://free-api.heweather.net/s6/weather?location=" + weatherId + "&key=1d7c82721f8345ca88b1092ccaa75ec0";
            String AQIUrl ="https://free-api.heweather.net/s6/air/now?location=" + weatherId + "&key=1d7c82721f8345ca88b1092ccaa75ec0";
            HttpUtils.sendHttpRequest(weatherUrl, new HttpUtils.HttpCallBackListener() {
                @Override
                public void OnFinish(String result) {
                    String responseText = result;
                    Weather weather = ResultUtils.handleWeatherResponse(responseText);
                    if (weather != null && "ok".equals(weather.getStatus())) {
                        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(AutoUpdateService.this).edit();
                        editor.putString("weather", responseText);
                        editor.apply();
                    }
                }

                @Override
                public void OnError(Exception e) {
                    e.printStackTrace();
                }
            });
            HttpUtils.sendHttpRequest(AQIUrl , new HttpUtils.HttpCallBackListener() {

                @Override
                public void OnFinish(String result) {
                    final String responseText = result;
                    final AQI aqi = ResultUtils.handleAQIResponse(responseText);

                            if (aqi != null && "ok".equals(aqi.getStatus())) {
                                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(AutoUpdateService.this).edit();
                                editor.putString("aqi", responseText);
                                editor.apply();
                            }
                        }
                @Override
                public void OnError(Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    /**
     * 更新必应每日一图
     */
    private void updateBingPic() {
        String requestBingPic = "http://guolin.tech/api/bing_pic";
        HttpUtils.sendHttpRequest(requestBingPic, new HttpUtils.HttpCallBackListener() {
            @Override
            public void OnFinish(String result) {
                String bingPic = result;
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(AutoUpdateService.this).edit();
                editor.putString("bing_pic", bingPic);
                editor.apply();
            }

            @Override
            public void OnError(Exception e) {
                e.printStackTrace();
            }

        });
    }

}
