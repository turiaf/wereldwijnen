package be.vdab.wereldwijnen.controllers;

import be.vdab.wereldwijnen.forms.WijnForm;
import be.vdab.wereldwijnen.services.WijnService;
import be.vdab.wereldwijnen.sessions.Mandje;
import be.vdab.wereldwijnen.sessions.StateMandje;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("wijn")
public class WijnController {
    private final WijnService wijnService;
    private final Mandje mandje;
    private final StateMandje stateMandje;

    public WijnController(WijnService wijnService, Mandje mandje, StateMandje stateMandje) {
        this.wijnService = wijnService;
        this.mandje = mandje;
        this.stateMandje = stateMandje;
    }

    @GetMapping("toevoegen/{id}")
    public ModelAndView toonFormToevoegen(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("toevoegen");
        wijnService.findById(id).ifPresent(wijn ->
                modelAndView.addObject("wijn", wijn));
        modelAndView.addObject("wijnForm", new WijnForm(null, null));
        return modelAndView;
    }

    @PostMapping("toevoegen")
    public ModelAndView toevoegen(@Valid WijnForm wijnForm, Errors errors, @RequestParam("id") String idN) {
        if(errors.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("toevoegen");
            try {
                long id = Long.parseLong(idN);
                wijnService.findById(id).ifPresent(wijn -> {
                    modelAndView.addObject("wijn", wijn);
                });
            } catch (NumberFormatException ex) {
                return new ModelAndView("fout");
            }
            return modelAndView;
        }
        wijnService.findById(wijnForm.getId()).ifPresent(wijn -> {
            mandje.addWijn(wijnForm.getId(), wijnForm.getAantal());
            mandje.verhoogTotaal(wijn.teBetalen(wijnForm.getAantal()));
            if(mandje.isGevuld()) {
                stateMandje.setGevuld(true);
            }
        });
        return new ModelAndView("redirect:/");
    }

}
