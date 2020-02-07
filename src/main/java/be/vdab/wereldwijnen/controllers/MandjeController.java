package be.vdab.wereldwijnen.controllers;

import be.vdab.wereldwijnen.domain.Bestelbon;
import be.vdab.wereldwijnen.forms.BestelLijn;
import be.vdab.wereldwijnen.services.WijnService;
import be.vdab.wereldwijnen.sessions.Mandje;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("mandje")
public class MandjeController {
    private final Mandje mandje;
    private final WijnService wijnService;

    public MandjeController(Mandje mandje, WijnService wijnService) {
        this.mandje = mandje;
        this.wijnService = wijnService;
    }

    @GetMapping
    public ModelAndView toonMandje() {
        ModelAndView modelAndView = new ModelAndView("mandje");
        if(mandje.isGevuld()) {
            List<BestelLijn> mandjeList = new ArrayList<>();
            mandje.getWijnen().entrySet().stream().forEach(entry -> {
                wijnService.findById(entry.getKey()).ifPresent(wijn -> {
                    BigDecimal tebetalen = wijn.teBetalen(entry.getValue());
                    mandjeList.add(new BestelLijn(wijn, entry.getValue(), tebetalen));
                });
            });
            modelAndView.addObject("mandje", mandjeList);
        }
        modelAndView.addObject("totaal", mandje.getTotaal());
//        modelAndView.addObject(new Bestelbon())
        return modelAndView;
    }
}
