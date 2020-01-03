package com.table.TableManager.controller;

import com.table.TableManager.modul.Product;
import com.table.TableManager.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

//acts as a Spring MCV controller to handle requests from the clients

@Controller
@RequestMapping("/product")
public class BookController
{
    private static final String ADMIN_ROLE="ROLE_ADMIN";
    @Autowired
    private ProductService service;
    //handler methods
    //we will create the methods when implementing each CRUD operation
    @RequestMapping(value = "/username",method = RequestMethod.GET)
    @ResponseBody
    public String currentUser(Principal principal)
    {
        return principal.getName();
    }

    @RequestMapping("/")
    public String viewHomePage(Model model)
    {
        List<Product> listProducts=service.listAll();
        model.addAttribute("listProducts",listProducts);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getAuthorities();
        model.addAttribute("isAdmin",authentication.getAuthorities().toArray()[0].toString().equals(ADMIN_ROLE));
        return "index_product";
    }
    @RequestMapping("/new")
    public String showNewProductPage(Model model)
    {
        Product product=new Product();
        model.addAttribute("product",product);
        return "new_product";
    }
    //handler method to save the product information into the database
    //after the product is inserted into the database, it redirects to
    // the homepage to refresh the product list
    @RequestMapping(value="/save", method= RequestMethod.POST)
    public String saveProduct(@ModelAttribute("product") Product product)
    {
        service.save(product);
        return "redirect:/product/";
    }
    @RequestMapping("/edit/{id}")
    public ModelAndView showEditProductPage(@PathVariable(name="id") int id)
    {
        ModelAndView mav=new ModelAndView("edit_product");
        Optional<Product> product=service.get(id);
        mav.addObject("product",product);
        return mav;
    }
    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name="id") Long id)
    {
        service.delete(id);
        return "redirect:/product/";
    }
}
