package com.example.demo.Exception;

import com.example.demo.bean.ErrorInfo;
import com.example.demo.bean.MyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    public final static String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req , Exception e) throws Exception{
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception",e);
        System.out.println(e.getMessage());
        mav.addObject("url",req.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }

    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public ErrorInfo<String> jsonErrorHandler(HttpServletRequest req,MyException e)throws MyException{
        ErrorInfo<String> r = new ErrorInfo<>();
        r.setUrl(req.getRequestURL().toString());
        r.setData("Json Data");
        r.setMessage(e.getMessage());
        r.setCode(ErrorInfo.ERROR);
        return r;
    }
}
