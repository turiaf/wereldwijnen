package be.vdab.wereldwijnen.controllers;

import be.vdab.wereldwijnen.services.LandService;
import be.vdab.wereldwijnen.sessions.StateMandje;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
class MyControllerAdvice {
    private final LandService landService;
    private final StateMandje stateMandje;


    public MyControllerAdvice(LandService landService, StateMandje stateMandje) {
        this.landService = landService;
        this.stateMandje = stateMandje;
    }

    @ModelAttribute
    void extraDataToevoegenAanModel(Model model) {
        model.addAttribute("listLand", landService.findAllLandsId())
        .addAttribute(stateMandje);
    }
}
