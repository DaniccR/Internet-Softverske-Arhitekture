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
import rs.ac.singidunum.veterinarska_ambulanta.model.Veterinar; 
import rs.ac.singidunum.veterinarska_ambulanta.service.VeterinarService; 
 
@Controller 
@RequestMapping("/web/veterinari") 
public class VeterinarWebController {
	 
    private final VeterinarService veterinarService; 
 
    public VeterinarWebController(VeterinarService veterinarService) { 
        this.veterinarService = veterinarService; 
    } 
 
    @GetMapping 
    public String prikaziVeterinare(Model model) { 
        model.addAttribute("veterinari", veterinarService.findAll()); 
        model.addAttribute("veterinar", new Veterinar()); 
        return "veterinari"; 
    } 
 
    @PostMapping 
    public String sacuvajVeterinara(@ModelAttribute Veterinar veterinar) { 
        veterinarService.save(veterinar); 
        return "redirect:/web/veterinari"; 
    } 
 
    @GetMapping("/edit/{id}") 
    public String izmeniVeterinara(@PathVariable Long id, Model model) { 
        model.addAttribute("veterinari", veterinarService.findAll()); 
        model.addAttribute("veterinar", veterinarService.findById(id)); 
        return "veterinari"; 
    } 
 
    @PostMapping("/update/{id}") 
    public String updateVeterinar(@PathVariable Long id, @ModelAttribute Veterinar veterinar) { 
        veterinarService.update(id, veterinar); 
        return "redirect:/web/veterinari"; 
    } 
 
    @PostMapping("/delete/{id}") 
    public String deleteVeterinar(@PathVariable Long id) { 
        veterinarService.deleteById(id); 
        return "redirect:/web/veterinari"; 
    } 
}
