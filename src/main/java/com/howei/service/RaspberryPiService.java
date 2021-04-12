package com.howei.service;

import com.howei.pojo.AiConfigure;

import java.util.List;
import java.util.Map;

public interface RaspberryPiService {
    List<AiConfigure> getAddressList();

    List<AiConfigure> getRaspberryPiList(Map map);

    int updAiConfigure(Map map);
}
