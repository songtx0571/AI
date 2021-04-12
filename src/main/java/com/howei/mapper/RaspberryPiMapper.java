package com.howei.mapper;

import com.howei.pojo.AiConfigure;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface RaspberryPiMapper {
    List<AiConfigure> getAddressList();

    List<AiConfigure> getRaspberryPiList(Map map);

    int updAiConfigure(Map map);
}
