package wu.com.weather.model;

public class Country {
    private int countryId;
    private String countryName;
    private String weatherId;
    private int cityId;

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setweatherId(String countryCode) {
        this.weatherId = countryCode;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getCountryId() {
        return countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getweatherId() {
        return weatherId;
    }

    public int getCityId() {
        return cityId;
    }
}
