package com.github.myoa.controller;

import com.github.myoa.dto.UserLoginDto;
import com.github.myoa.dto.UserRegistDto;
import com.github.myoa.service.UserService;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private DefaultKaptcha kaptcha;

    @Resource
    private UserService userService;
    @GetMapping("/code")
    public void getImage(HttpServletResponse response, HttpSession session) throws Exception{
        response.setHeader("Cache-Control","no-store, no-cache");
        // 设置响应内容
        response.setContentType("image/jpg");
        //生成验证码
        String text = kaptcha.createText();//文本
        BufferedImage image = kaptcha.createImage(text);
        session.setAttribute("code",text);
        ServletOutputStream outputStream = null;
       outputStream = response.getOutputStream();
       ImageIO.write(image,"jpg",outputStream);
    }

    @PostMapping("/regist")
    public ModelAndView regist( UserRegistDto userRegist, HttpSession session){
        ModelAndView mav = new ModelAndView();
        Object code = session.getAttribute("code");
        if(!userRegist.getCode().equals((String)code)){
            mav.addObject("error","验证码错误");
            return mav;
        }
        userService.regist(userRegist);
        mav.setViewName("redirect:/");
        return mav;
    }
    @PostMapping("/login")
    public ModelAndView login(UserLoginDto userLogin){
        ModelAndView mav = new ModelAndView();
        UserLoginDto login = userService.login(userLogin);
        if(login!=null){
            mav.addObject("user",login);
            mav.setViewName("redirect:/emp/findAll");
        }else {
            mav.addObject("error","登录失败");
        }
        return mav;
    }

}
