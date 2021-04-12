package com.howei.mapper;

import com.howei.pojo.Weather;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface WeatherMapper {

    List<Weather> getWeatherList(Map<String,Object> map);
}
