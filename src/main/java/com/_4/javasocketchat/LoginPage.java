package com._4.javasocketchat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

@Controller
public class LoginPage {
    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("userInfo", new UserInfo());
        return "LoginPage";
    }

    @PostMapping("/submit")
    public String submit(@ModelAttribute("userInfo") UserInfo userInfo, Model model){
        //此处处理提交内容
        model.addAttribute("userid", userInfo.getUserid());
        model.addAttribute("password", userInfo.getPassword());
        System.out.println(userInfo.getUserid());
        System.out.println(userInfo.getPassword());
        return "Loginsubmit";
    }
}
