package be.vdab.wereldwijnen.controllers;

import be.vdab.wereldwijnen.services.LandService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("soort")
public class SoortController {
    private final LandService landService;

    public SoortController(LandService landService) {
        this.landService = landService;
    }

    @GetMapping("{id}")
    public ModelAndView toonSoorten(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("soorten");
        landService.findById(id).ifPresent(land -> modelAndView.addObject("land", land));
        return modelAndView;
    }
}
