package com.aistar.controller;

import com.aistar.service.ProductService;
import com.aistar.util.SeverResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {
    @Autowired
    private ProductService service;

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/products")
    @ResponseBody
    public SeverResponse products(){
        System.out.println("products.....................");
        SeverResponse severResponse = service.getAll();
        System.out.println(severResponse);
        return severResponse;
    }

    @GetMapping("/product/{id}")
    @ResponseBody
    public SeverResponse product(@PathVariable("id") Integer id){
        return service.getById(id);
    }


    @GetMapping("/addToCart/{id}")
    public ModelAndView addToCart(@PathVariable("id") Integer id){
        ModelAndView mav = new ModelAndView();
        SeverResponse severResponse = service.getById(id);
        mav.addObject("product",severResponse.getData());
        mav.setViewName("cart");
        return mav;
    }

}
