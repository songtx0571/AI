package com.howei.mapper;

import com.howei.pojo.AiEquipment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface AiEquipmentMapper {

    List<AiEquipment> getAiEquipemntList(Map map);

    int addAiEquipemnt(AiEquipment aiEquipment);

    int updAiEquipemnt(AiEquipment aiEquipment);

    AiEquipment getAiEquipemnt(Map map);

    List<AiEquipment> getAiEquipmentMap(String departmentId);

    AiEquipment getById(Integer id);
}
