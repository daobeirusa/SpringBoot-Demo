package com.example.demo.Controller;

import com.example.demo.bean.MyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestErrorController {
    @RequestMapping("NumberFormatException")
    public String numberFormat()throws Exception{
        throw new NumberFormatException("数值转换异常");
    }

    @RequestMapping("jsonError")
    public String jsonException()throws MyException{
        throw new MyException("IO异常");
    }
}
