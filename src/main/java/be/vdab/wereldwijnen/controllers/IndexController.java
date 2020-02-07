package be.vdab.wereldwijnen.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
class IndexController /*implements ErrorController*/ {
    @GetMapping
    ModelAndView index() {
        return new ModelAndView("index");
    }

//    @Override
//    @RequestMapping("/error")
//    public String getErrorPath() {
//        return "fout";
//    }
}
