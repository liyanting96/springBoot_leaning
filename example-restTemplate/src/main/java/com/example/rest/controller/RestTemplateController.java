package com.example.rest.controller;

import com.example.rest.common.RespEntity;
import com.example.rest.common.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/rest")
public class RestTemplateController {
    @Autowired
    Util util;
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public RespEntity getStudent(){
        util.create_aggregator(1, "lyt");
        return RespEntity.success();

    }
}
