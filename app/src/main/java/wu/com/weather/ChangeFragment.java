package wu.com.weather;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import wu.com.location.R;
import wu.com.weather.db.DBUtils;
import wu.com.weather.model.City;
import wu.com.weather.model.Country;
import wu.com.weather.model.Province;
import wu.com.weather.utils.HttpUtils;
import wu.com.weather.utils.ResultUtils;

/**
 *改变省份的fragment
 */
public class ChangeFragment extends Fragment {
    private static final String TAG = "ChooseAreaFragment";
    public static final int LEVEL_PROVINCE = 0;
    public static final int LEVEL_CITY = 1;
    public static final int LEVEL_COUNTY = 2;
    private ProgressDialog progressDialog;
    private Button backButton;
    private TextView titleText;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private DBUtils dbUtils ;
    private List<String> dataList = new ArrayList<>();
    /**
     * 省列表
     */
    private List<Province> provinceList ;
    /**
     * 市列表
     */
    private List<City> cityList  ;
    /**
     * 县列表
     */
    private List<Country> countryList ;
    /**
     * 选中的省份
     */
    private Province selectedProvince;
    /**
     * 选中的城市
     */
    private City selectedCity;
    /**
     * 当前选中的级别
     */
    private int currentLevel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.choose_area, container, false);
        titleText = (TextView) view.findViewById(R.id.title_text);
        backButton = (Button) view.findViewById(R.id.back_button);
        listView = (ListView) view.findViewById(R.id.list_view);
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dbUtils = DBUtils.getInstance(getContext());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (currentLevel == LEVEL_PROVINCE) {
                    selectedProvince = provinceList.get(position);
                    queryCities();
                } else if (currentLevel == LEVEL_CITY) {
                    selectedCity = cityList.get(position);
                    queryCounties();
                } else if (currentLevel == LEVEL_COUNTY) {
                    String weatherId = countryList.get(position).getweatherId();
                    if (getActivity() instanceof MainActivity) {
                        Intent intent = new Intent(getActivity(), WeatherActivity.class);
                        intent.putExtra("weather_id", weatherId);
                        startActivity(intent);
                        getActivity().finish();
                    } else if (getActivity() instanceof WeatherActivity) {
                        WeatherActivity activity = (WeatherActivity) getActivity();
                        activity.drawerLayout.closeDrawers();
                        activity.swipeRefresh.setRefreshing(true);
                        activity.requestWeather(weatherId);
                    }
                }
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentLevel == LEVEL_COUNTY) {
                    queryCities();
                } else if (currentLevel == LEVEL_CITY) {
                    queryProvinces();
                }
            }
        });
        queryProvinces();
    }
/**
 * 查询全国所有的省，优先从数据库查询，如果没有查询到再去服务器上查询。
 */
        private void queryProvinces() {
            provinceList = dbUtils.loadProvinces();
            if (provinceList.size() > 0) {
                dataList.clear();
                for (Province province : provinceList) {
                    dataList.add(province.getProvinceName());
                }
                adapter.notifyDataSetChanged();
                listView.setSelection(0);
                titleText.setText("中国");
                currentLevel = LEVEL_PROVINCE;
            } else {
                String address = "http://guolin.tech/api/china";
                queryFromServer(address, "province");
            }
        }
/**
 * 查询选中省内所有的市，优先从数据库查询，如果没有查询到再去服务器上查询。
 */
        private void queryCities() {
            cityList = dbUtils.loadCities(selectedProvince.getId());
            if (cityList.size() > 0) {
                dataList.clear();
                for (City city : cityList) {
                    dataList.add(city.getCityName());
                }
                adapter.notifyDataSetChanged();
                listView.setSelection(0);
                titleText.setText(selectedProvince.getProvinceName());
                currentLevel = LEVEL_CITY;
            } else {
                String address = "http://guolin.tech/api/china/"+selectedProvince.getProvinceCode();
                queryFromServer(address, "city");
            }
        }
/**
 * 查询选中市内所有的县，优先从数据库查询，如果没有查询到再去服务器上查询。
 */
        private void queryCounties() {
            countryList = dbUtils.loadCounties(selectedCity.getCityId());
            if (countryList.size() > 0) {
                dataList.clear();
                for (Country county : countryList) {
                    dataList.add(county.getCountryName());
                }
                adapter.notifyDataSetChanged();
                listView.setSelection(0);
                titleText.setText(selectedCity.getCityName());
                currentLevel = LEVEL_COUNTY;
            } else {
                String address = "http://guolin.tech/api/china/"+selectedProvince.getProvinceCode()+"/"+selectedCity.getCityCode();
                queryFromServer(address, "county");
            }
        }
/**
 * 根据传入的代号和类型从服务器上查询省市县数据。
 */
        private void queryFromServer(String address,final String type  ) {
//            String address;
//            if (type.equals("city") ) {
//                //address = "http://www.weather.com.cn/data/list3/city" + code +".xml";
//               address = "http://guolin.tech/api/china/"+provinceCode;
//            } else if(type.equals("country")) {
//                address = "http://guolin.tech/api/china/"+provinceCode+"/"+cityCode;
//            }else
//                {
//                    //address = "http://www.weather.com.cn/data/list3/city.xml";
//                    address = "http://guolin.tech/api/china";
//                }
            showProgressDialog();
            HttpUtils.sendHttpRequest(address, new HttpUtils.HttpCallBackListener() {
                @Override
                public void OnFinish(String result) {
                    boolean flag = false;
                    if ("province".equals(type)) {
                        flag = ResultUtils.getProvincesResult(dbUtils,result);

                    } else if ("city".equals(type)) {
                        flag = ResultUtils.getCityResult(dbUtils,
                                result, selectedProvince.getId());
                    } else if ("county".equals(type)) {
                        flag = ResultUtils.getCountryResult(dbUtils,
                                result, selectedCity.getCityId());
                    }
                    if (flag) {
// 通过runOnUiThread()方法回到主线程处理逻辑
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                closeProgressDialog();
                                if ("province".equals(type)) {
                                    queryProvinces();
                                } else if ("city".equals(type)) {
                                    queryCities();
                                } else if ("county".equals(type)) {
                                    queryCounties();
                                }
                            }
                        });
                    }

                }
                @Override
                public void OnError(Exception e) {
// 通过runOnUiThread()方法回到主线程处理逻辑
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            closeProgressDialog();
                            Toast.makeText(getContext(),
                                    "加载失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
/**
 * 显示进度对话框
 */
        private void showProgressDialog() {
            if (progressDialog == null) {
                progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage("正在加载...");
                progressDialog.setCanceledOnTouchOutside(false);
            }
            progressDialog.show();
        }
/**
 * 关闭进度对话框
 */
        private void closeProgressDialog() {
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
        }
}
