package top.shauna.shaunaiscoming.authoritymanage.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import top.shauna.shaunaiscoming.bean.User;
import top.shauna.shaunaiscoming.repository.UsersRepository;

import javax.servlet.http.HttpSession;

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
    public String login(String phone, String psw, HttpSession session){
        User user;
        try {
            user = usersRepository.findByPhonenum(phone);
            if(user==null||!psw.equals(user.getPassword())){
                session.setAttribute("msg","账号或密码错误！");
                return "login/login";
            }else {
                session.setAttribute("user",user);
                return "success";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "login/login";
    }
}
