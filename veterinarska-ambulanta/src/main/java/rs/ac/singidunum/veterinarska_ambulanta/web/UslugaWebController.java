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
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.*; 
import rs.ac.singidunum.veterinarska_ambulanta.model.Usluga; 
import rs.ac.singidunum.veterinarska_ambulanta.service.UslugaService; 
 
@Controller 
@RequestMapping("/web/usluge") 
public class UslugaWebController {
	 
    private final UslugaService uslugaService; 
 
    public UslugaWebController(UslugaService uslugaService) { 
        this.uslugaService = uslugaService; 
    } 
 
    @GetMapping 
    public String prikaziUsluge(Model model) { 
        model.addAttribute("usluge", uslugaService.findAll()); 
        model.addAttribute("usluga", new Usluga()); 
        return "usluge"; 
    } 
 
    @PostMapping 
    public String sacuvajUslugu(@ModelAttribute Usluga usluga) { 
        uslugaService.save(usluga); 
        return "redirect:/web/usluge"; 
    } 
 
    @GetMapping("/edit/{id}") 
    public String izmeniUslugu(@PathVariable Long id, Model model) { 
        model.addAttribute("usluge", uslugaService.findAll()); 
        model.addAttribute("usluga", uslugaService.findById(id)); 
        return "usluge"; 
    } 
 
    @PostMapping("/update/{id}") 
    public String updateUsluga(@PathVariable Long id, @ModelAttribute Usluga usluga) { 
        uslugaService.update(id, usluga); 
        return "redirect:/web/usluge"; 
    } 
 
    @PostMapping("/delete/{id}") 
    public String deleteUsluga(@PathVariable Long id) { 
        uslugaService.deleteById(id); 
        return "redirect:/web/usluge"; 
    } 
    
    @GetMapping("/najcesca") 
    public String najcesca(Model model) { 
        model.addAttribute("najcesca", uslugaService.findNajcescaUsluga()); 
        model.addAttribute("usluge", uslugaService.findAll()); 
        model.addAttribute("usluga", new Usluga()); 
        return "usluge"; 
    } 
}
