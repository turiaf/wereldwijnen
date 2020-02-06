package be.vdab.wereldwijnen.controllers;

import be.vdab.wereldwijnen.services.SoortService;
import be.vdab.wereldwijnen.services.WijnService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("wijn")
public class WijnController {
    private final SoortService soortService;
    private final WijnService wijnService;

    public WijnController(SoortService soortService, WijnService wijnService) {
        this.soortService = soortService;
        this.wijnService = wijnService;
    }

    @GetMapping("{id}")
    public ModelAndView toonWijnen(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("wijn");
        soortService.findById(id).ifPresent(soort ->
                modelAndView.addObject("soort", soort));
        return modelAndView;
    }

    @GetMapping("toevoegen/{id}")
    public ModelAndView toevoegen(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("toevoegen");
        wijnService.findById(id).ifPresent(wijn ->
                modelAndView.addObject("wijn", wijn));
        return modelAndView;
    }

}
