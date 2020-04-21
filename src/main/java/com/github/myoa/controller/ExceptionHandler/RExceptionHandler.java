package com.github.myoa.controller.ExceptionHandler;

import com.github.myoa.util.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class RExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R allExp(Exception exception){
        R r = new R();
        r.put("code",500);
        r.put("msg","系统内部异常，请联系管理员");
        return r;
    }

}
