package top.shauna.shaunaiscoming.authoritymanage.regist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author Shauna.Chow
 * @Date 2020/11/9 15:27
 * @E-Mail z1023778132@icloud.com
 */
@RequestMapping("/regist")
@Controller
public class RegistController {

    @PostMapping("/registServlet")
    @ResponseBody
    public String regist(String phone, String psw){
        System.out.println(phone+"  "+psw);
        return "okkk";
    }
}
