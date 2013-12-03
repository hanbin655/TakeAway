package com.foodie.controller;

import com.foodie.model.request.RegisterRequest;
import com.foodie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author jim.wu
 * @date 11/25/13
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void register(HttpServletResponse response,RegisterRequest registerRequest) throws IOException {
        boolean success = userService.register(registerRequest.getUserName(), registerRequest.getPassword());
        System.out.println("success:" + success);
        if (!success) response.sendRedirect("/resources/view/register_fail.html");
        else response.sendRedirect("/resources/view/register_success.html");
    }
}
