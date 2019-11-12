package com.jk.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jk.model.Student;
import com.jk.service.StudentService;
import com.jk.util.ExportExcel;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class StudentController {

    @Reference(version = "1.0")
    private StudentService studentService;

    @RequestMapping("toshow")
    public String toshow() {
        return "show";
    }

    @RequestMapping("toshow2")
    public String toshow2() {
        return "show2";
    }


    @RequestMapping("queryStudentList")
    @ResponseBody
    public HashMap<String, Object> queryStudentList(Integer page, Integer rows) {
        return studentService.queryStudentList(page, rows);
    }

    @RequestMapping("queryStudent")
    @ResponseBody
    public List<Map<String, Object>> queryStudent() {
        List<Map<String, Object>> map1 = studentService.queryStudent();

        List<Map<String, Object>> map2 = new ArrayList<Map<String, Object>>();

        for (Map<String, Object> map : map1) {
            Map<String, Object> map3 = new HashMap<>();
            map3.put("y", map.get("y"));
            if (map.get("性别").toString().equals("1")) {
                map3.put("name", "男");
                map3.put("sliced", true);
                map3.put("selected", true);
            } else if (map.get("性别").toString().equals("2")) {
                map3.put("name", "女");
            }
            map2.add(map3);
        }
        return map2;

    }

    @RequestMapping("del")
    @ResponseBody
    public void del (Integer id){
        studentService.del(id);
    }

    @RequestMapping("addstudent")
    @ResponseBody
    public void addstudent(Student student){
        studentService.addstudent(student);
    }

    @RequestMapping("updatebyid")
    @ResponseBody
    public Student updatebyid(Integer id){
        return studentService.updatebyid(id);
    }


    @RequestMapping("export")
    public void export(HttpServletResponse response,String[] id) throws Exception {

        if(id.length<0){


      List<Student> list=  new ArrayList<Student>();
       list= studentService.queryStudentsList();
        //定义表格的标题
        String title ="1905全体学生信息";
        //定义列名
        String[] rowName={"编号","姓名","性别"};
        //定义工具类要的数据
        List<Object[]>  dataList = new ArrayList<Object[]>();



        //循环数据把数据存放到 List<Object[]>
        for (Student student:list) {
            Object[] obj=new Object[rowName.length];
            obj[0]=student.getSid();
            obj[1]= student.getStudentName();
            if(student.getSex()==1){
                obj[2]="男" ;
            }else{
                obj[2]="女" ;
            }
            dataList.add(obj);
        }
        ExportExcel exportExcel=new ExportExcel(title,rowName,dataList,response);
        exportExcel.export();
        }else{


            List<Student> list=  new ArrayList<Student>();
            list= studentService.queryStudentsLists(id);
            //定义表格的标题
            String title ="1905全体学生信息";
            //定义列名
            String[] rowName={"编号","姓名","性别"};
            //定义工具类要的数据
            List<Object[]>  dataList = new ArrayList<Object[]>();

            //循环数据把数据存放到 List<Object[]>
            for (Student student:list) {
                Object[] obj=new Object[rowName.length];
                obj[0]=student.getSid();
                obj[1]= student.getStudentName();
                if(student.getSex()==1){
                    obj[2]="男" ;
                }else{
                    obj[2]="女" ;
                }
                dataList.add(obj);
            }
            ExportExcel exportExcel=new ExportExcel(title,rowName,dataList,response);
            exportExcel.export();

        }



    }

    @RequestMapping("importExcel")
    public String importExcel(MultipartFile file, HttpServletResponse response){
        //上传文件的名称
        String fileName = file.getOriginalFilename();
        //生成新的文件名称
        String filePath = "./src/main/resources/templates/fileupload/";

        //创建list集合接收传递的参数
        List<Student> list= new ArrayList<Student>();

        //上传文件
        try {
            uploadFile(file.getBytes(), filePath, fileName);

            SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
            //通过文件创建流
            FileInputStream fileInputStream = new FileInputStream(filePath+fileName);
            //创建操作excel的对象   因为xls的文件需要HSSFWorkbook  xlsx需要的XSSFWorkbook 因此直接使用workBook对象就行了
            Workbook workbook= WorkbookFactory.create(fileInputStream) ;
            //通过workbook获得sheet页  sheet有可能有多个
            for(int i=0;i<workbook.getNumberOfSheets();i++){
                //创建sheet对象
                Sheet sheetAt = workbook.getSheetAt(i);
                //根绝sheet创建行  row
                for(int j=3;j<sheetAt.getPhysicalNumberOfRows();j++){
                    //创建row对象
                    Row row = sheetAt.getRow(j);

                    //创建对象接收从文件读取的内容
                    Student student=new Student();
                    if( !"".equals(row.getCell(1)) && row.getCell(1)!=null){
                        student.setStudentName(row.getCell(1).toString());
                    }

                    if( !"".equals(row.getCell(2)) && row.getCell(2)!=null){
                        if(row.getCell(2).toString().equals("男")){
                            student.setSex(1);
                        }else {
                            student.setSex(2);
                        }
                    }

                    list.add(student);
                }

            }
            studentService.addStudent(list);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "show";
    }



    //上传文件的方法
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }
}
