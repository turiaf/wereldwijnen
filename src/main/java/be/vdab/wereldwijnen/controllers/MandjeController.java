package be.vdab.wereldwijnen.controllers;

import be.vdab.wereldwijnen.domain.Bestelbon;
import be.vdab.wereldwijnen.domain.Bestelwijze;
import be.vdab.wereldwijnen.domain.Wijn;
import be.vdab.wereldwijnen.exceptions.RecordAangepastException;
import be.vdab.wereldwijnen.exceptions.WijnNietGevondenException;
import be.vdab.wereldwijnen.forms.BestelLijn;
import be.vdab.wereldwijnen.forms.BestelbonForm;
import be.vdab.wereldwijnen.services.BestelbonService;
import be.vdab.wereldwijnen.services.WijnService;
import be.vdab.wereldwijnen.sessions.Mandje;
import be.vdab.wereldwijnen.sessions.StateMandje;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("mandje")
public class MandjeController {
    private final Mandje mandje;
    private final WijnService wijnService;
    private final BestelbonService bestelbonService;
    private final StateMandje stateMandje;

    public MandjeController(Mandje mandje, WijnService wijnService, BestelbonService bestelbonService, StateMandje stateMandje) {
        this.mandje = mandje;
        this.wijnService = wijnService;
        this.bestelbonService = bestelbonService;
        this.stateMandje = stateMandje;
    }

    @GetMapping
    public ModelAndView toonMandje() {
        ModelAndView modelAndView = maakMandje();
        modelAndView.addObject(
                new BestelbonForm(null, null, null, null, null, null));
        return modelAndView;
    }

    @PostMapping("bevestigen")
    public ModelAndView bevestigen(@Valid BestelbonForm bestelbonForm, Errors errors, RedirectAttributes redirect) {
        if(errors.hasErrors()) {
            ModelAndView modelAndView = maakMandje();
            return modelAndView;
        }
        if(mandje.isGevuld()) {
            try {
                long idBon = bestelbonService.bestelbonBevestigen(mandje.getWijnen(),
                        new Bestelbon(bestelbonForm.getNaam(), bestelbonForm.getStraat(),
                                bestelbonForm.getHuisNr(), bestelbonForm.getPostCode(), bestelbonForm.getGemeente(),
                                bestelbonForm.getBestelwijze() == 0 ? Bestelwijze.AFHALEN : Bestelwijze.LEVEREN
                        ));
                mandje.delete();
                stateMandje.setGevuld(false);
                redirect.addAttribute("toegevoegd", idBon);
            } catch (WijnNietGevondenException | RecordAangepastException ex) {
                return new ModelAndView("redirect:/mandje/bestelBon");
            }
        } else {
            return maakMandje();
        }
        return new ModelAndView("redirect:/mandje/bestelBon");
    }

    @GetMapping("bestelBon")
    public ModelAndView toonBevestigen() {
        return new ModelAndView("bestelBon");
    }

    private ModelAndView maakMandje() {
        ModelAndView modelAndView = new ModelAndView("mandje");
        if(mandje.isGevuld()) {
            List<BestelLijn> mandjeList = new ArrayList<>();
            List<Long> idList = new ArrayList<Long>(mandje.getWijnen().keySet());
            List<Wijn> wijnList = wijnService.findAllInList(idList);
            mandje.getWijnen().entrySet().stream().forEach(entry -> {
                wijnList.stream().forEach(wijn -> {
                    if(entry.getKey() == wijn.getId()) {
                        BigDecimal tebetalen = wijn.teBetalen(entry.getValue());
                        mandjeList.add(new BestelLijn(wijn, entry.getValue(), tebetalen));
                    }
                });
            });
            modelAndView.addObject("mandje", mandjeList);
        }
        modelAndView.addObject("totaal", mandje.getTotaal());
        return modelAndView;
    }

}
