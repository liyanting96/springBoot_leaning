package com.example.mqops.controller;

import com.example.mqops.config.MqConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class configController {
    @Autowired
    private MqConfig mqConfig;
    @RequestMapping(value = "/config",method = RequestMethod.GET)
    @ResponseBody
    public String findAll(){
//        List<Student> studentList = this.studentService.findAll();
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("total", studentList.size());
//        param.put("rows", studentList);
//        for(Object m : param.keySet()){
//            System.out.print("total: " + m + " rows: " + param.get(m) );
//        }
//
//        return param;
        // Sort sort = new Sort(Sort.Direction.DESC, "name");

        String HOST_GROUP_ID = mqConfig.gethostGroupId();
        System.out.println("HOST_GROUP_ID" + HOST_GROUP_ID);

        //System.out.println("API_ADDR: " + API_ADDR);
        String OWNER = mqConfig.getOWNER();
        System.out.println("OWNER: " + OWNER);

        //util util1 = new util();
        String topicName = "DEV_TID_UOC_TERMINATE";
        //int code = topicService.checkTopic(topicName);
        //System.out.println("code: " + code);



        return "";

//        model.addAttribute("studentList",studentList);
//        return "index1";
        //return studentService.findAll();
    }

}
