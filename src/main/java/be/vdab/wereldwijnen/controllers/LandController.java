package be.vdab.wereldwijnen.controllers;

import be.vdab.wereldwijnen.services.LandService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("land")
public class LandController {
    private final LandService landService;

    public LandController(LandService landService) {
        this.landService = landService;
    }

    @GetMapping("{id}")
    public ModelAndView toonSoorten(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("land");
        landService.findById(id).ifPresent(land -> modelAndView.addObject("land", land));
        return modelAndView;
    }
}
