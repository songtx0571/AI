package com.howei.mapper;

import com.howei.pojo.CirculatingPump;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface CirculatingPumpMapper {
    List<CirculatingPump> getCirculationPumpList(Map map);
}
