package com.jk.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jk.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CarController {

    @Reference(version = "1.0")
    private StudentService studentService;

    @RequestMapping("bingzhuang")
    public String bingzhuang(){
        return "bingzhuang";
    }

    @RequestMapping("zhexian")
    public String zhexian(){
        return "zhexian";
    }

    @RequestMapping("mianji")
    public String mianji(){
        return "mianji";
    }

    @RequestMapping("zhuzhuang")
    public String zhuzhuang(){
        return "zhuzhuang";
    }
    //饼状
    @RequestMapping("queryCar")
    @ResponseBody
    public List<Map<String, Object>> queryCar() {
        List<Map<String, Object>> map1 = studentService.queryCar();

        List<Map<String, Object>> map2 = new ArrayList<Map<String, Object>>();

        for (Map<String, Object> map : map1) {
            Map<String, Object> map3 = new HashMap<>();
            map3.put("y", map.get("y"));
            map3.put("name",map.get("name"));
            map2.add(map3);
        }
        return map2;

    }

    //折线
   @RequestMapping("queryCarAll")
    @ResponseBody
    public List<Map<String,Object>> queryCarAll(){

        List<Map<String,Object>> map =studentService.queryCarAll();

        List<Map<String,Object>> map2 =new ArrayList<Map<String,Object>>();

        for (Map<String,Object> map1:map) {
            Map<String,Object> map3=new HashMap<>();
            map3.put("name",map1.get("time"));
            map3.put("data",map1.get("counts"));
            map2.add(map3);

        }

        return map;
    }

    //面积
    @RequestMapping("querymianji")
    @ResponseBody
    public List<Map<String,Object>> querymianji(){

        List<Map<String,Object>> map =studentService.querymianji();

        List<Map<String,Object>> map2 =new ArrayList<Map<String,Object>>();

        for (Map<String,Object> map1:map) {
            Map<String,Object> map3=new HashMap<>();
            map3.put("name",map1.get("cname"));
            map3.put("data",map1.get("counts"));
            map2.add(map3);

        }

        return map;
    }

    //柱状
    @RequestMapping("queryzhuzhuang")
    @ResponseBody
    public List<Map<String,Object>> queryzhuzhuang(){

        List<Map<String,Object>> map =studentService.queryzhuzhuang();

        List<Map<String,Object>> map2 =new ArrayList<Map<String,Object>>();

        for (Map<String,Object> map1:map) {
            Map<String,Object> map3=new HashMap<>();
            map3.put("name",map1.get("time"));
            map3.put("data",map1.get("counts"));
            map2.add(map3);

        }

        return map;
    }

}
