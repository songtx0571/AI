package com.howei.service.impl;


import com.howei.mapper.AiEquipmentMapper;
import com.howei.pojo.AiEquipment;
import com.howei.service.AiEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AiEquipmentServiceImpl implements AiEquipmentService {

    @Autowired
    private AiEquipmentMapper aiEquipmentMapper;

    @Override
    public List<AiEquipment> getAiEquipemntList(Map map) {
        return aiEquipmentMapper.getAiEquipemntList(map);
    }

    @Override
    public int addAiEquipemnt(AiEquipment aiEquipment) {
        return aiEquipmentMapper.addAiEquipemnt(aiEquipment);
    }

    @Override
    public int updAiEquipemnt(AiEquipment aiEquipment) {
        return aiEquipmentMapper.updAiEquipemnt(aiEquipment);
    }

    @Override
    public AiEquipment getAiEquipemnt(Map map) {
        return aiEquipmentMapper.getAiEquipemnt(map);
    }

    @Override
    public List<Map<String, String>> getAiEquipmentMap(String departmentId) {
        List<AiEquipment> list=aiEquipmentMapper.getAiEquipmentMap(departmentId);
        List<Map<String, String>> result=new ArrayList<>();
        for(AiEquipment aiEquipment:list){
            Map<String,String> map=new HashMap<>();
            Integer id=aiEquipment.getId();
            String name=aiEquipment.getName();
            map.put("id",id+"");
            map.put("name",name);
            result.add(map);
        }
        return result;
    }

    @Override
    public AiEquipment getById(Integer id) {
        return aiEquipmentMapper.getById(id);
    }
}
