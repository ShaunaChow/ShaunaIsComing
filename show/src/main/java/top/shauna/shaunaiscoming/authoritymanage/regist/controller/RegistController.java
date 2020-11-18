package top.shauna.shaunaiscoming.authoritymanage.regist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import top.shauna.shaunaiscoming.bean.User;
import top.shauna.shaunaiscoming.repository.UsersRepository;
import top.shauna.shaunaiscoming.service.ShaunaDfsService;

import java.util.Date;
import java.util.Map;

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
    @Autowired
    private ShaunaDfsService shaunaDfsService;

    @PostMapping("/registServlet")
    public String regist(String phone, String psw, Map<String,String> map){
        try {
            if (phone==null||phone.length()!=11||psw.length()<6) {
                map.put("msg","账号或密码不符合规范！");
                return "register/regist";
            }
            User exits = usersRepository.getByPhone(phone);
            if (exits!=null){
                map.put("msg","用户已存在！");
                return "register/regist";
            }
            usersRepository.addUser(phone,psw,"system","/"+phone+"/",new Date(),1);
            shaunaDfsService.mkdir("/"+phone+"/");
            map.put("msg","注册成功!");
            return "login/login";
        }catch (Exception e){
            e.printStackTrace();
            return "failed";
        }
    }
}
