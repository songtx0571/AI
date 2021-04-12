package com.howei.controller;

import com.alibaba.fastjson.JSON;
import com.howei.pojo.AiConfigure;
import com.howei.pojo.CirculatingPump;
import com.howei.service.AiEquipmentService;
import com.howei.service.RaspberryPiService;
import com.howei.util.LayuiResult;
import com.howei.util.Page;
import com.howei.util.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/ai/aiConfigure")
public class AiConfigureController {

    @Autowired
    RaspberryPiService raspberryPiService;

    @Autowired
    private AiEquipmentService aiEquipmentService;

    /**
     * 跳转配置页面
     * @return
     */
    @RequestMapping("/toDispose")
    public String toDispose(){
        return "dispose";
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
     * 获取页面数据
     * @param request
     * @return
     */
    @RequestMapping("/getAiConfigureList")
    @ResponseBody
    public String getAiConfigureList(HttpServletRequest request){
        String address=request.getParameter("address");
        String page=request.getParameter("page");
        String rows=request.getParameter("limit");
        int offset=Page.getOffSet(page,rows);
        Map map=new HashMap();
        if(address!=null&&!address.equals("")){
            map.put("address",address);
        }
        List<AiConfigure> total=raspberryPiService.getRaspberryPiList(map);
        map.put("page",offset);
        map.put("pageSize",rows);
        List<AiConfigure> list=raspberryPiService.getRaspberryPiList(map);
        LayuiResult layuiResult=new LayuiResult();
        layuiResult.setCode(0);
        layuiResult.setCount(total.size());
        layuiResult.setData(list);
        return JSON.toJSONString(layuiResult);
    }

    @RequestMapping("/updAiConfigure")
    @ResponseBody
    public String updAiConfigure(HttpServletRequest request){
        String address=request.getParameter("address");
        String inquiry=request.getParameter("inquiry");
        String worked=request.getParameter("worked");
        String type=request.getParameter("type");
        Map map=new HashMap();
        if(address!=null&&!address.equals("")){
            map.put("address",address);
        }
        if(inquiry!=null&&!inquiry.equals("")){
            map.put("inquiry",inquiry);
        }
        if(worked!=null&&!worked.equals("")){
            map.put("worked",worked);
        }
        if(type!=null&&!type.equals("")){
            map.put("type",type);
        }
        try {
            int result=raspberryPiService.updAiConfigure(map);
            return JSON.toJSONString(Type.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(Type.ERROR);
    }
}
