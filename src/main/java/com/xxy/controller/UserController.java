package com.xxy.controller;


import com.xxy.model.Goods;
import com.xxy.model.RootUser;
import com.xxy.model.User;
import com.xxy.service.GoodsService;
import com.xxy.service.RootUserService;
import com.xxy.service.UserService;
import com.xxy.util.JsonMsg;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private RootUserService rootUserService;
    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login()
    {
        return "login";
    }
    @RequestMapping(value = "do_login",method = RequestMethod.POST)
    public String dologin(HttpSession session,User user, Model model)
    {
        User u=userService.getuser(user.getUsername());
        RootUser ru=rootUserService.getrootuser(user.getUsername());
        if(u==null&&ru==null)
        {
            model.addAttribute("msg","用户不存在");
            return "login";
        }
        else if(u!=null)
        {
            if(u.getPassword().equals(user.getPassword()))
            {
                model.addAttribute("User",user);
                List<Goods> list=goodsService.getAllGoods(0,goodsService.getcount());
                model.addAttribute("productList",list);
                session.setAttribute("user",u);
                return "goods_list";
            }
            else{
                model.addAttribute("msg","用户名与密码不匹配");
                return "login";
            }
        }
        else
        {
            if(ru.getPassword().equals(user.getPassword()))
            {
                model.addAttribute("User",user);
                List<Goods> list=goodsService.getAllGoods(0,goodsService.getcount());
                model.addAttribute("productList",list);
                session.setAttribute("user",ru);
                return "main";
            }
            else{
                model.addAttribute("msg","用户名与密码不匹配");
                return "login";
            }
        }
    }
    @RequestMapping(value = "do_register",method = RequestMethod.POST)
    public String register(User user, Model model)
    {
        try {
            User u =userService.getuser(user.getUsername());
            if(u!=null)
                model.addAttribute("msg","注册失败");
            else{
                userService.insertuser(user);
                model.addAttribute("msg","注册成功");
            }
        }
        catch (Exception e)
        {
            model.addAttribute("msg","注册失败");
            e.printStackTrace();
        }
        return "login";
    }
    @RequestMapping(value = "getAllUser",method = RequestMethod.GET)
    public ModelAndView getAllUser(@RequestParam(value = "pageNo",defaultValue = "1")Integer pageNo)
    {
        ModelAndView mv=new ModelAndView("userPage");
        int limit=5;
        int offset=(pageNo-1)*limit;
        List<User> users=userService.getalluser();
        int totalItems = userService.getcount();
        //获取总的页数
        int temp = totalItems / limit;
        int totalPages = (totalItems % limit == 0) ? temp : temp+1;
        //当前页数
        int curPage = pageNo;
        mv.addObject("employees", users)
                .addObject("totalItems", totalItems)
                .addObject("totalPages", totalPages)
                .addObject("curPage", curPage);
        return mv;
    }
    /*删除用户*/
    @RequestMapping("deleteUser")
    public JsonMsg deleteUser(@Param("Userid") Integer userid)
    {
        int res=0;
        if(userid>0)
        {
            res=userService.deleteUser(userid);
        }
        if(res!=1)
            return JsonMsg.fail().addInfo("user_delete_error", "用户删除异常");
        return JsonMsg.success();
    }
    @RequestMapping(value = "/getTotalPage", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg getTotalPage(){
        int totalItems = userService.getcount();
        //获取总的页数
        int temp = totalItems / 5;
        int totalPages = (totalItems % 5 == 0) ? temp : temp+1;
        return JsonMsg.success().addInfo("totalPages", totalPages);
    }

}
