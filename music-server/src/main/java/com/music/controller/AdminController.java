package com.music.controller;

import com.music.common.ErrorMessage;
import com.music.common.SuccessMessage;
import com.music.service.impl.AdminServiceImpl;

import org.apache.commons.lang3.ObjectUtils.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class AdminController {
    @Autowired
    private AdminServiceImpl adminService;

    // 判断是否登录成功
    @ResponseBody
    @RequestMapping(value = "/admin/login/status", method = RequestMethod.POST)
    public Object loginStatus(HttpServletRequest req, HttpSession session) {
        String name = req.getParameter("username");
        String password = req.getParameter("password");

        boolean res = adminService.veritypasswd(name, password);
        if (res) {
            session.setAttribute("username", name);
            return new SuccessMessage<Null>("登录成功").getMessage();
        } else {
            return new ErrorMessage("用户名或密码错误").getMessage();
        }
    }
}
