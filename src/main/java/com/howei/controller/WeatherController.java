package com.howei.controller;

import com.alibaba.fastjson.JSON;
import com.howei.pojo.CirculatingPump;
import com.howei.pojo.Weather;
import com.howei.service.CirculatingPumpService;
import com.howei.service.WeatherService;
import com.howei.util.EasyuiResult;
import com.howei.util.LayuiResult;
import com.howei.util.LayuiTree;
import com.howei.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 气象
 */
@Controller
@RequestMapping("/ai/weather")
public class WeatherController {

    @Autowired
    WeatherService weatherService;

    @Autowired
    CirculatingPumpService cPumpService;

    /**
     * 跳转气象查看页面
     * @return
     */
    @RequestMapping("/toWeather")
    public String weather(){

        return "weather";
    }

    /**
     * 跳转循环泵页面
     * @return
     */
    @RequestMapping("/toCirculationPump")
    public String toCirculationPump(){

        return "circulatingPump";
    }

    /**
     * 跳转边缘计算配置页面
     * @return
     */
    @RequestMapping("/toEdgeCalc")
    public String edgeCalc(){
        return "edgeCalc";
    }

    @RequestMapping("/getWeatherList")
    @ResponseBody
    public EasyuiResult getWeatherList(HttpServletRequest request){
        EasyuiResult easyuiResult=new EasyuiResult();
        String page=request.getParameter("page");
        String rows=request.getParameter("rows");
        int offset=Page.getOffSet(page,rows);
        Map<String,Object> map=new HashMap<String, Object>();
        List<Weather> total=weatherService.getWeatherList(map);
        map.put("page",offset);
        map.put("pageSize",rows);
        List<Weather> list=weatherService.getWeatherList(map);
        Weather weather=list.get(0);
        System.out.println(weather.getAtmospheric());
        easyuiResult.setTotal(total.size());
        easyuiResult.setRows(list);
        return easyuiResult;
    }

    @RequestMapping("/getCirculationPumpList")
    @ResponseBody
    public String getCirculationPumpList(HttpServletRequest request){
        String page=request.getParameter("page");
        String rows=request.getParameter("limit");
        int offset=Page.getOffSet(page,rows);
        Map map=new HashMap();
        List<CirculatingPump> total=cPumpService.getCirculationPumpList(map);
        map.put("page",offset);
        map.put("pageSize",rows);
        List<CirculatingPump> list=cPumpService.getCirculationPumpList(map);
        LayuiResult layuiResult=new LayuiResult();
        layuiResult.setCode(0);
        layuiResult.setCount(total.size());
        layuiResult.setData(list);
        return JSON.toJSONString(layuiResult);
    }
}
