package top.shauna.shaunaiscoming.authoritymanage.regist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.shauna.shaunaiscoming.bean.User;
import top.shauna.shaunaiscoming.repository.UsersRepository;

import java.util.Date;

/**
 * @Author Shauna.Chow
 * @Date 2020/11/9 15:27
 * @E-Mail z1023778132@icloud.com
 */
@RequestMapping("/regist")
@Controller
public class RegistController {
    @Autowired
    private UsersRepository usersRepository;

    @PostMapping("/registServlet")
    @ResponseBody
    public String regist(String phone, String psw){
        User user = new User(
                null,
                phone,
                psw,
                null,
                "/",
                new Date(),
                0);
        try {
            usersRepository.addUser(phone,psw,"system","/",new Date(),0);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "failed";
        }
    }
}
