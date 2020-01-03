package com.table.TableManager.controller;

import com.table.TableManager.service.UserService;
import com.table.TableManager.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;
    //handler methods
    //we will create the methods when implementing each CRUD operation
  /*  @RequestMapping("/")
    public String viewHomePage(Model model)
    {
        List<User> listUser=service.listAll();
        model.addAttribute("listUser",listUser);
        return "index_user";
    }*/
    @RequestMapping(value={"/","/welcome"},method = RequestMethod.GET)
    public String welcomePage(Model model)
    {
        model.addAttribute("title","Welcome");
        model.addAttribute("message","This is welcome page!");
        return "index_user";
    }
    @RequestMapping(value="/admin",method = RequestMethod.GET)
    public String adminPage(Model model, Principal principal)
    {
        User loginedUser=(User)((Authentication) principal).getPrincipal();
        String userInfo= WebUtils.toString(loginedUser);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getAuthorities();
        model.addAttribute("role1",authentication.getAuthorities().toArray()[0]);
        return "adminPage";
    }


    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model)
    {
        model.addAttribute("title", "Logout");
        return "logoutSuccessful";
    }
    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal)
    {
        String userName = principal.getName();
        System.out.println("User Name: " + userName);
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        String userInfo = WebUtils.toString(loginedUser);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getAuthorities();
        model.addAttribute("role1",authentication.getAuthorities().toArray()[0]);
        return "userInfo";
    }
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal)
    {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            String userInfo = WebUtils.toString(loginedUser);
            model.addAttribute("userInfo", userInfo);
            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);
        }
        return "403Page";
    }
    @RequestMapping("/new")
    public String showNewUserPage(Model model)
    {
        com.table.TableManager.modul.User user=new com.table.TableManager.modul.User();
        model.addAttribute("user",user);
        return "new_user";
    }
    //handler method to save the product information into the database
    //after the product is inserted into the database, it redirects to
    // the homepage to refresh the product list
    @RequestMapping(value="/save", method= RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") com.table.TableManager.modul.User user)
    {
        service.save(user);
        return "redirect:/user/";
    }
    @RequestMapping("/edit/{id}")
    public ModelAndView showEditUserPage(@PathVariable(name="id") int id)
    {
        ModelAndView mav=new ModelAndView("edit_user");
        Optional<com.table.TableManager.modul.User> user=service.get(id);
        mav.addObject("user",user);
        return mav;
    }
    @RequestMapping("/delete/{id}")
    public String deleteUser(@PathVariable(name="id") Long id)
    {
        service.delete(id);
        return "redirect:/user/";
    }
//    @GetMapping("/login")
//    public String controlView(Model model)
//    {
//        return "loginPage";
//    }
}
