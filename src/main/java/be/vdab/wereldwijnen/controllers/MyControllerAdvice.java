package be.vdab.wereldwijnen.controllers;

import be.vdab.wereldwijnen.services.LandService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
class MyControllerAdvice {
    private final LandService landService;

    public MyControllerAdvice(LandService landService) {
        this.landService = landService;
    }

    @ModelAttribute
    void extraDataToevoegenAanModel(Model model) {
        model.addAttribute("listLand", landService.findAll());
    }
}
