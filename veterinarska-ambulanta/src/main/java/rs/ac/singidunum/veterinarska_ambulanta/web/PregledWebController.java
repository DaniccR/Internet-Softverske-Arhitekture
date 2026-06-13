/**
 * @author Radomir Danic
 * @date 13. 6. 2026.
 */
package rs.ac.singidunum.veterinarska_ambulanta.web;

/**
 * TODO
 * 
 * @author Radomir
 */
import java.time.LocalDate; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.*;

import rs.ac.singidunum.veterinarska_ambulanta.model.Ljubimac;
import rs.ac.singidunum.veterinarska_ambulanta.model.Pregled; 
import rs.ac.singidunum.veterinarska_ambulanta.model.StatusPregleda;
import rs.ac.singidunum.veterinarska_ambulanta.model.Veterinar;
import rs.ac.singidunum.veterinarska_ambulanta.service.PregledService; 
import rs.ac.singidunum.veterinarska_ambulanta.service.LjubimacService; 
import rs.ac.singidunum.veterinarska_ambulanta.service.VeterinarService; 
import rs.ac.singidunum.veterinarska_ambulanta.service.UslugaService; 
 
@Controller 
@RequestMapping("/web/pregledi")
public class PregledWebController {
	 
    private final PregledService pregledService; 
    private final LjubimacService ljubimacService; 
    private final VeterinarService veterinarService; 
    private final UslugaService uslugaService; 
 
    public PregledWebController(PregledService pregledService, LjubimacService ljubimacService, VeterinarService veterinarService, 
    		UslugaService uslugaService) { 
        this.pregledService = pregledService; 
        this.ljubimacService = ljubimacService; 
        this.veterinarService = veterinarService; 
        this.uslugaService = uslugaService; 
    } 
 
    @GetMapping 
    public String prikaziPreglede(Model model) { 
        Pregled prazanPregled = new Pregled();
        prazanPregled.setLjubimac(new Ljubimac());
        prazanPregled.setVeterinar(new Veterinar());

        pripremiModel(model, prazanPregled); 
        model.addAttribute("pregledi", pregledService.findAll()); 
        return "pregledi"; 
    } 
 
    @PostMapping 
    public String sacuvajPregled(@ModelAttribute Pregled pregled) { 
        pregledService.save(pregled); 
        return "redirect:/web/pregledi"; 
    } 
 
    @PostMapping("/{id}/zavrsi") 
    public String zavrsi(@PathVariable Long id) { 
        pregledService.zavrsiPregled(id); 
        return "redirect:/web/pregledi"; 
    } 
 
    @PostMapping("/{id}/otkazi") 
    public String otkazi(@PathVariable Long id) { 
        pregledService.otkaziPregled(id); 
        return "redirect:/web/pregledi"; 
    } 
 
    @GetMapping("/prihod") 
    public String prihod(@RequestParam("od") LocalDate odDatuma, @RequestParam("do") LocalDate doDatuma, Model model) { 
                         
        Pregled prazanPregled = new Pregled();
        prazanPregled.setLjubimac(new Ljubimac());
        prazanPregled.setVeterinar(new Veterinar());

        pripremiModel(model, prazanPregled); 
        model.addAttribute("pregledi", pregledService.findAll()); 
        model.addAttribute("prihod", pregledService.izracunajUkupanPrihod(odDatuma, doDatuma)); 
        return "pregledi"; 
    }
 
    private void pripremiModel(Model model, Pregled pregled) { 
        model.addAttribute("pregled", pregled); 
        model.addAttribute("ljubimci", ljubimacService.findAll()); 
        model.addAttribute("veterinari", veterinarService.findAll()); 
        model.addAttribute("usluge", uslugaService.findAll()); 
        model.addAttribute("statusi", StatusPregleda.values()); 
    }
}
