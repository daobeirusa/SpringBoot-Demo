package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NumberFormatErrorController {
    @RequestMapping("NumberFormatException")
    public String numberFormat()throws Exception{
        throw new NumberFormatException("数值转换异常");
    }
}
