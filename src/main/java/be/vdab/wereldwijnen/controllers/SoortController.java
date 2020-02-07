package be.vdab.wereldwijnen.controllers;

import be.vdab.wereldwijnen.services.SoortService;
import be.vdab.wereldwijnen.services.WijnService;
import be.vdab.wereldwijnen.sessions.Mandje;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("soort")
public class SoortController {
    private final SoortService soortService;
    private final WijnService wijnService;

    public SoortController(SoortService soortService, WijnService wijnService) {
        this.soortService = soortService;
        this.wijnService = wijnService;
    }

    @GetMapping("{id}")
    public ModelAndView toonWijnen(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("soort");
        soortService.findById(id).ifPresent(soort ->
                modelAndView.addObject("soort", soort));
        return modelAndView;
    }



}
