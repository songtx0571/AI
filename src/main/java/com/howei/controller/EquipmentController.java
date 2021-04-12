package com.howei.controller;

import com.alibaba.fastjson.JSON;
import com.howei.pojo.AiEquipment;
import com.howei.service.AiEquipmentService;
import com.howei.service.CompanyService;
import com.howei.util.LayuiResult;
import com.howei.util.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/ai/equipment")
public class EquipmentController {

    @Autowired
    private AiEquipmentService aiEquipmentService;

    @Autowired
    private CompanyService companyService;

    /**
     * 跳转ai设备页面
     * @return
     */
    @RequestMapping("/toEquipment")
    public String toEquipment(){
        return "equipment";
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
     * 获取Ai设备列表
     * @param request
     * @return
     */
    @RequestMapping("/getAiEquipemntList")
    @ResponseBody
    public String getAiEquipemntList(HttpServletRequest request){
        String departmentId=request.getParameter("departmentId");
        Map map=new HashMap();
        if(departmentId!=null){
            map.put("departmentId",departmentId);
        }
        List<AiEquipment> list=aiEquipmentService.getAiEquipemntList(map);
        LayuiResult result=new LayuiResult();
        result.setCode(0);
        result.setData(list);
        result.setCount(list.size());
        return JSON.toJSONString(result);
    }

    /**
     * 添加ai设备
     * @param aiEquipment
     * @return
     */
    @RequestMapping(value = "/addAiEquipemnt",method = {RequestMethod.POST})
    @ResponseBody
    public String addAiEquipemnt(@RequestBody AiEquipment aiEquipment){
        if(aiEquipment.getName().trim().equals("")){
            return JSON.toJSONString(Type.CANCEL);
        }
        String name=aiEquipment.getName();
        Integer departmentId=aiEquipment.getDepartmentId();
        Map map=new HashMap();
        map.put("name",name);
        map.put("departmentId",departmentId);
        AiEquipment aiEquipment1=aiEquipmentService.getAiEquipemnt(map);
        if(aiEquipment1!=null){
            return JSON.toJSONString(Type.CANCEL);//同名
        }
        int result=aiEquipmentService.addAiEquipemnt(aiEquipment);
        if(result>0){
            return JSON.toJSONString(Type.SUCCESS);
        }
        return JSON.toJSONString(Type.SUCCESS);
    }

    /**
     * 修改ai设备
     * @param aiEquipment
     * @return
     */
    @RequestMapping(value = "/updAiEquipemnt",method = {RequestMethod.POST})
    @ResponseBody
    public String updAiEquipemnt(@RequestBody AiEquipment aiEquipment){
        if(aiEquipment.getId()==null){
            return JSON.toJSONString(Type.ERROR);//无id
        }
        //同名检测
        Integer departmentId=aiEquipment.getDepartmentId();
        String name=aiEquipment.getName();
        Map map=new HashMap();
        map.put("name",name);
        map.put("departmentId",departmentId);
        AiEquipment aiEquipment1=aiEquipmentService.getAiEquipemnt(map);
        if(aiEquipment1!=null){
            return JSON.toJSONString(Type.CANCEL);//同名
        }
        int result=aiEquipmentService.updAiEquipemnt(aiEquipment);
        if(result>0){
            return JSON.toJSONString(Type.SUCCESS);
        }
        return JSON.toJSONString(Type.CANCEL);
    }
}
