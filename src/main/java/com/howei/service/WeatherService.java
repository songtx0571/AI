package com.howei.service;

import com.howei.pojo.Weather;

import java.util.List;
import java.util.Map;

public interface WeatherService {
    List<Weather> getWeatherList(Map<String,Object> map);
}
