package com.howei.service.impl;

import com.howei.mapper.WeatherMapper;
import com.howei.pojo.Weather;
import com.howei.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    WeatherMapper weatherMapper;

    @Override
    public List<Weather> getWeatherList(Map<String, Object> map) {
        return weatherMapper.getWeatherList(map);
    }
}
