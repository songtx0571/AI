package com.howei.service.impl;

import com.howei.mapper.CirculatingPumpMapper;
import com.howei.pojo.CirculatingPump;
import com.howei.service.CirculatingPumpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CirculatingPumpServiceImpl implements CirculatingPumpService {

    @Autowired
    private CirculatingPumpMapper circulatingPumpMapper;

    @Override
    public List<CirculatingPump> getCirculationPumpList(Map map) {
        return circulatingPumpMapper.getCirculationPumpList(map);
    }
}
