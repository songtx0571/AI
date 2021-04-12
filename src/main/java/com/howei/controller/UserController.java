package com.howei.controller;


import com.howei.pojo.Menu;
import com.howei.pojo.Users;
import com.howei.service.MenuService;
import com.howei.service.UserService;
import com.howei.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/ai")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    MenuService menuService;


    @RequestMapping("/")
    public String login(HttpSession session, HttpServletRequest request){
        if(session.getAttribute("userId")!=null){

        }
        return "redirect:/login";
    }

    @RequestMapping("/login")
    public String index(){
        return "login";
    }

    @RequestMapping("/home")
    public String home(){
        return "home";
    }

    @RequestMapping(value = "/loginPage", method = {RequestMethod.POST, RequestMethod.GET})
    public String loginadmin(HttpServletRequest request, HttpSession session) {
        String userNumber = request.getParameter("userNumber");
        String password = request.getParameter("password");
        String userId = request.getParameter("userId");
        MD5 md5=new MD5();
        if(userId!=null&&!userId.equals("")){
            Users users = userService.findById(userId);
            userNumber=users.getUserNumber();
            password=users.getPassword();
        }else {
            try {
                password = md5.EncoderByMd5(password);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        Users users = userService.findUser(userNumber, password);

        if (users!=null) {
            session.setAttribute("userId", users.getId());//编号
            return "home";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping("/getMenu")
    @ResponseBody
    public List<Menu> getMenuTree(HttpSession session, HttpServletRequest request){
        String parentId=request.getParameter("parent");
        Map map=new HashMap();
        map.put("parentId",parentId);
        List<Menu> result=menuService.getMenuTree(map);
        return result;
    }
}
