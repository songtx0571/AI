package com.howei.service;

import com.howei.pojo.AiConfigurationData;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface AiConfigurationDataService {


    int insList(Set<AiConfigurationData> aList);

    List<AiConfigurationData> getAiConfigureDataList(Map map);
}
