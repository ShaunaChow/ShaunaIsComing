package top.shauna.shaunaiscoming.authoritymanage.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.shauna.shaunaiscoming.bean.User;
import top.shauna.shaunaiscoming.repository.UsersRepository;

/**
 * @Author Shauna.Chow
 * @Date 2020/11/11 20:48
 * @E-Mail z1023778132@icloud.com
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UsersRepository usersRepository;

    @PostMapping("/loginServlet")
    @ResponseBody
    public String login(String phone, String psw){
        User user = usersRepository.getByPhone(phone);
        if (psw.equals(user.getPassword())){
            return "success";
        }else {
            return "password error";
        }
    }
}
