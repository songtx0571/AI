package com.howei.controller;

import com.alibaba.fastjson.JSON;
import com.howei.pojo.*;
import com.howei.service.*;
import com.howei.util.DateFormat;
import com.howei.util.LayuiResult;
import com.howei.util.Page;
import com.howei.util.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 数据配置
 */
@Controller
@RequestMapping("/ai/dataCon")
public class DataConfigurationController {

    @Autowired
    private AiEquipmentService aiEquipmentService;

    @Autowired
    DataConfigurationService dataConfigurationService;

    @Autowired
    EquipmentService equipmentService;

    @Autowired
    UnitService unitService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    AiConfigurationDataService aiConfigurationDataService;

    @RequestMapping("/toAiConfigurationData")
    public String toAiConfigurationData(){
        return "aiConfigurationData";
    }

    /**
     * 部门下拉框列表
     * @return
     */
    @RequestMapping("/getCompanyMap")
    @ResponseBody
    public String getCompanyMap(){
        List<Map<String,String>> list=companyService.getDepartmentMap(1);
        return JSON.toJSONString(list);
    }

    /**
     * 获取设备列表
     * @return
     */
    @RequestMapping("/getAddressList")
    @ResponseBody
    public String getAddressList(HttpServletRequest request){
        String departmentId=request.getParameter("departmentId");
        if(departmentId!=null&&!departmentId.equals("")){
            List<Map<String,String>> list=aiEquipmentService.getAiEquipmentMap(departmentId);
            return JSON.toJSONString(list);
        }
        return JSON.toJSONString(null);
    }

    /**
     * 获取数据配置列表
     * @param request
     * @return
     */
    @RequestMapping("/getDataConfigurationList")
    @ResponseBody
    public String getDataConfigurationList(HttpServletRequest request){
        String address=request.getParameter("address");
        String addressName="";
        Map map=new HashMap();
        if(address!=null&&!address.equals("")){
            AiEquipment aiEquipment=aiEquipmentService.getById(Integer.parseInt(address));
            if(aiEquipment!=null){
                addressName=aiEquipment.getName();
            }
            map.put("address",address);
        }
        List<DataConfiguration> list=dataConfigurationService.getDataConfigurationList(map);
        List<DataConfiguration> list1=new ArrayList<>();
        for(int i=1;i<=64;i++){
            String measuringPoint1="temp"+i;
            DataConfiguration dataConfiguration=new DataConfiguration();
            dataConfiguration.setEquipment(null);
            dataConfiguration.setMeasuringType(null);
            dataConfiguration.setTime(null);
            dataConfiguration.setUnit(null);
            dataConfiguration.setId(null);
            dataConfiguration.setMeasuringPoint(measuringPoint1);
            dataConfiguration.setAddress(address);
            dataConfiguration.setAddressName(addressName);
            list1.add(dataConfiguration);
        }
        for(int i=0;i<list1.size();i++){
            DataConfiguration dataConfiguration=list1.get(i);
            String measuringPoint=dataConfiguration.getMeasuringPoint();
            boolean bool=true;
            DataConfiguration dataConfiguration1=new DataConfiguration();
            for(int k=0;k<list.size();k++){
                dataConfiguration1=list.get(k);
                String measuringPoint1=dataConfiguration1.getMeasuringPoint();
                if(measuringPoint1.equals(measuringPoint)){
                    bool=false;
                }
            }
            if(bool){
                list.add(dataConfiguration);
            }
        }
        LayuiResult result=new LayuiResult();
        result.setCode(0);
        result.setCount(list.size());
        result.setData(list);
        return JSON.toJSONString(result);
    }

    /**
     * 修改数据配置
     * @return
     */
    @RequestMapping("/updateDataConfiguration")
    @ResponseBody
    public String updateDataConfiguration(HttpServletRequest request){
        String id=request.getParameter("id");
        String address=request.getParameter("address");
        String measuringPoint=request.getParameter("measuringPoint");
        String equipment=request.getParameter("equipment");
        String unit=request.getParameter("unit");
        String measuringType=request.getParameter("measuringType");
        String departmentId=request.getParameter("departmentId");
        //id为空，则创建记录
        if(id==null||id.equals("")){
            DataConfiguration dataConfiguration=new DataConfiguration();
            dataConfiguration.setUnit(unit);
            dataConfiguration.setTime(DateFormat.getYMDHMS(new Date()));
            dataConfiguration.setMeasuringType(measuringType);
            dataConfiguration.setMeasuringPoint(measuringPoint);
            dataConfiguration.setEquipment(equipment);
            dataConfiguration.setAddress(address);
            dataConfiguration.setDepartmentId(Integer.parseInt(departmentId));
            int result=dataConfigurationService.addDataConfiguration(dataConfiguration);
            return JSON.toJSONString(Type.SUCCESS);
        }
        //存在记录，直接修改记录
        else{
            DataConfiguration dataConfiguration=dataConfigurationService.getDataConfigurationById(id);
            if(dataConfiguration!=null){
                if(equipment!=null&&!equipment.equals("")){
                    dataConfiguration.setEquipment(equipment);
                }
                if(unit!=null&&!unit.equals("")){
                    dataConfiguration.setUnit(unit);
                }
                if(measuringType!=null&&!measuringType.equals("")){
                    dataConfiguration.setMeasuringType(measuringType);
                }
                dataConfiguration.setTime(DateFormat.getYMDHMS(new Date()));
                int result=dataConfigurationService.updateDataConfiguration(dataConfiguration);
                return JSON.toJSONString(Type.SUCCESS);
            }else{
                return JSON.toJSONString(Type.CANCEL);//不存在
            }
        }
    }

    /**
     * 下拉框属性
     * @return
     */
    @RequestMapping("/getEquMap")
    @ResponseBody
    public List<Map<String,Object>> getEquMap(HttpServletRequest request){
        List<Map<String,Object>> list=new ArrayList<>();
        String type=request.getParameter("type");
        String departmentId=request.getParameter("departmentId");
        Map souMap=new HashMap();
        souMap.put("type",type);
        souMap.put("departmentId",departmentId);
        List<Equipment> equ=equipmentService.getEquMap(souMap);
        if(equ!=null){
            for(int i=0;i<equ.size();i++){
                Equipment equipment=equ.get(i);
                Map<String,Object> map= new HashMap<>();
                map.put("text",equipment.getName());
                map.put("id",i);
                list.add(map);
            }
        }
        return list;
    }

    /**
     * 下拉框属性
     * 获取测点类型
     * @param request
     * @return
     */
    @RequestMapping("/getUnityMap")
    @ResponseBody
    public List<Map<String,Object>> getUnityMap(HttpServletRequest request){
        String mold=request.getParameter("mold");
        String departmentId=request.getParameter("departmentId");
        String bothType=request.getParameter("bothType");//1:人工；2:ai
        List<Map<String,Object>> result=new ArrayList<>();
        Map map1=new HashMap();
        map1.put("type",mold);
        map1.put("departmentId",departmentId);
        map1.put("bothType",bothType);
        List<Unit> list=unitService.getUnityMap(map1);
        for(int i=0;i<list.size();i++){
            Map map=new HashMap();
            Unit unit=list.get(i);
            String unitName=unit.getNuit();
            map.put("id",i);
            map.put("text",unitName);
            result.add(map);
        }
        return result;
    }

    @RequestMapping("/getAiConfigureDataList")
    @ResponseBody
    public String getAiConfigureDataList(HttpServletRequest request){
        String addressId=request.getParameter("addressId");
        String departmentId=request.getParameter("departmentId");
        String measuringPoint=request.getParameter("measuringPoint");
        String startTime=request.getParameter("startTime");
        String endTime=request.getParameter("endTime");
        String page=request.getParameter("page");
        String rows=request.getParameter("limit");
        int offset=Page.getOffSet(page,rows);
        Map map=new HashMap();
        if(departmentId!=null&&!departmentId.equals("")&&addressId!=null&&!addressId.equals("")){
            map.put("addressId",addressId);
            map.put("departmentId",departmentId);
        }
        if(measuringPoint!=null&&!measuringPoint.equals("")){
            map.put("measuringPoint",measuringPoint);
        }
        if(startTime!=null&&!startTime.equals("")&&endTime!=null&&!endTime.equals("")){
            map.put("startTime",startTime);
            map.put("endTime",endTime);
        }else if(startTime!=null&&!startTime.equals("")&&(endTime==null||endTime.equals(""))){
            map.put("startTime",startTime);
        }else if((startTime==null||startTime.equals(""))&&endTime!=null&&!endTime.equals("")){
            map.put("endTime",endTime);
        }

        List<AiConfigurationData> total=aiConfigurationDataService.getAiConfigureDataList(map);
        map.put("page",offset);
        map.put("pageSize",rows);
        List<AiConfigurationData> list=aiConfigurationDataService.getAiConfigureDataList(map);
        LayuiResult result=new LayuiResult();
        result.setCode(0);
        result.setData(list);
        result.setCount(total.size());
        return JSON.toJSONString(result);
    }
}
