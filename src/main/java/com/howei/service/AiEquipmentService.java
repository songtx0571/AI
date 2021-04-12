package com.howei.service;

import com.howei.pojo.AiEquipment;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AiEquipmentService {
    List<AiEquipment> getAiEquipemntList(Map map);

    int addAiEquipemnt(AiEquipment aiEquipment);

    int updAiEquipemnt(AiEquipment aiEquipment);

    AiEquipment getAiEquipemnt(Map map);

    List<Map<String,String>> getAiEquipmentMap(String departmentId);

    AiEquipment getById(@Param("id") Integer id);
}
