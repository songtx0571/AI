package com.howei.util;

import com.howei.pojo.AiConfigurationData;
import com.howei.pojo.AiEquipment;
import com.howei.pojo.CirculatingPump;
import com.howei.pojo.DataConfiguration;
import com.howei.service.AiConfigurationDataService;
import com.howei.service.AiEquipmentService;
import com.howei.service.CirculatingPumpService;
import com.howei.service.DataConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SchedulerTask {

    @Autowired
    private AiConfigurationDataService aiConfigurationDataService;

    @Autowired
    private CirculatingPumpService circulatingPumpService;

    @Autowired
    DataConfigurationService dataConfigurationService;

    @Autowired
    private AiEquipmentService aiEquipmentService;

    /**
     * ai数据转发
     * 每5分钟一次
     */
    @Scheduled(fixedRate = 300000)
    private void processFixedRate(){
        List<DataConfiguration> list=dataConfigurationService.getDataConfigurationList(new HashMap());
        if(list!=null&&list.size()>0){
            for(DataConfiguration dataConfiguration:list){
                Integer id=dataConfiguration.getId();
                String measuringPoint=dataConfiguration.getMeasuringPoint();//测点
                String address=dataConfiguration.getAddress();//机器
                Integer departmentId=dataConfiguration.getDepartmentId();//部门
                String equipmentName="";
                if(address!=null){
                    AiEquipment aiEquipment=aiEquipmentService.getById(Integer.parseInt(address));
                    equipmentName=aiEquipment.getName();
                }
                Map map=new HashMap();
                map.put(measuringPoint,measuringPoint);
                map.put("address",equipmentName);
                List<CirculatingPump> cList=circulatingPumpService.getCirculationPumpList(map);
                Set<AiConfigurationData> aList=new HashSet<>();
                if(cList!=null&&cList.size()>0){
                    for (CirculatingPump circulatingPump:cList) {
                        String tempData=circulatingPump.getTempData();
                        String time=circulatingPump.getTime();
                        AiConfigurationData aiConfigurationData=new AiConfigurationData();
                        aiConfigurationData.setAiConfigurationId(id);
                        aiConfigurationData.setData(tempData);
                        aiConfigurationData.setAddressId(Integer.parseInt(address));
                        aiConfigurationData.setDepartmentId(departmentId);
                        aiConfigurationData.setMeasuringPoint(measuringPoint);
                        aiConfigurationData.setTime(time);
                        aList.add(aiConfigurationData);
                    }
                }
                /*for(AiConfigurationData aiConfigurationData:aList){
                    System.out.println(aiConfigurationData.toString());
                }*/
                if(aList.size()>0){
                    try {
                        //aiConfigurationDataService.insList(aList);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {

                    }
                }
            }
        }
    }
}
