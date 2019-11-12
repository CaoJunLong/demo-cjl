package com.jk.controller;

import com.jk.model.Car;
import com.jk.service.CarService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CarController {

    @Autowired
    private SolrClient client;

    @Autowired
    private CarService carService;

    @RequestMapping("toindex")
    public String toindex(){
        return "index";
    }

    @RequestMapping("queryCar")
    @ResponseBody
    public Map<String, Object> search(Car car, Integer page, Integer rows){


        //返回到前台
        Map<String, Object> map1=new HashMap<>();

        try {
            //存放所有的查询条件
            SolrQuery params = new SolrQuery();

            //查询条件, 这里的 q 对应 下面图片标红的地方
            if(car.getCname()!=null && !"".equals(car.getCname())){
                params.set("q", car.getCname());
            }else {
                params.set("q", "*:*");
            }


            //过滤条件
            // params.set("fq", "carPrice:["+car.get+" TO "++"]");

            //排序
            //params.addSort("carPrice", SolrQuery.ORDER.desc);

            //分页
            if(page==null){
                params.setStart(0);
            }else {
                params.setStart((page-1)*rows);
            }
            if(rows==null){
                params.setRows(5);
            }else {
                params.setRows(rows);
            }


            //默认域
            params.set("df", "cname");

            //只查询指定域
            //params.set("fl", "id,product_title,product_price");

            //高亮
            //打开开关
            params.setHighlight(true);
            //指定高亮域
            params.addHighlightField("cname");
            //设置前缀
            params.setHighlightSimplePre("<span style='color:red'>");
            //设置后缀
            params.setHighlightSimplePost("</span>");

            //查询后返回的对象
            QueryResponse queryResponse = client.query("core1",params);
            //查询后返回的对象 获得真正的查询结果
            SolrDocumentList results = queryResponse.getResults();
            //查询的总条数
            long numFound = results.getNumFound();

            System.out.println(numFound);

            //获取高亮显示的结果, 高亮显示的结果和查询结果是分开放的
            Map<String, Map<String, List<String>>> highlight = queryResponse.getHighlighting();

            //创建list集合接收我们循环的参数
            List<Car> list1 =new ArrayList<>();
            for (SolrDocument result : results) {

                Car car1=new Car();
                String highFile="";

                Map<String, List<String>> map = highlight.get(result.get("id"));
                List<String> list = map.get("cname");
                if(list==null){
                    highFile= result.get("cname").toString();
                }else {
                    highFile=list.get(0);
                }

                car1.setCid(Integer.parseInt(result.get("id").toString()));
                car1.setCname((String)result.get("cname"));
                car1.setCounts((Integer) result.get("counts"));
                car1.setCartime((String) result.get("cartime"));
                car1.setCname(highFile);

                list1.add(car1);
            }
            map1.put("total",numFound);
            map1.put("rows",list1);
            return map1;

        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }


    @RequestMapping("addcar")
    @ResponseBody
    public void addcar(Car car){

        carService.addcar(car);
        try {
            SolrInputDocument doc = new SolrInputDocument();
            doc.setField("id", car.getCid());
            doc.setField("cname", car.getCname());
            doc.setField("counts", car.getCounts());
            doc.setField("cartime", car.getCartime());


            client.add("core1", doc);
            //client.commit();
            client.commit("core1");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("del")
    @ResponseBody
        public String del(String id)  {
        carService.del(id);
            try {
                client.deleteById("core1",id);
                client.commit("core1");

                return id;
            } catch (Exception e) {
                e.printStackTrace();
            }


            return "index";
        }


}
