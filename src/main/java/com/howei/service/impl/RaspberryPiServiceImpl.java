package com.howei.service.impl;

import com.howei.mapper.RaspberryPiMapper;
import com.howei.pojo.AiConfigure;
import com.howei.service.RaspberryPiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RaspberryPiServiceImpl implements RaspberryPiService {

    @Autowired
    RaspberryPiMapper raspberryPiMapper;

    @Override
    public List<AiConfigure> getAddressList() {
        return raspberryPiMapper.getAddressList();
    }

    @Override
    public List<AiConfigure> getRaspberryPiList(Map map) {
        return raspberryPiMapper.getRaspberryPiList(map);
    }

    @Override
    public int updAiConfigure(Map map) {
        return raspberryPiMapper.updAiConfigure(map);
    }
}
