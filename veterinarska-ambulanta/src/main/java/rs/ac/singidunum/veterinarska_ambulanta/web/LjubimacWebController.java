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
import rs.ac.singidunum.veterinarska_ambulanta.model.Ljubimac; 
import rs.ac.singidunum.veterinarska_ambulanta.service.VlasnikService; 
import rs.ac.singidunum.veterinarska_ambulanta.service.LjubimacService; 
 
@Controller 
@RequestMapping("/web/ljubimci")
public class LjubimacWebController {
	 
    private final LjubimacService ljubimacService; 
    private final VlasnikService vlasnikService; 
 
    public LjubimacWebController(LjubimacService ljubimacService, VlasnikService vlasnikService) { 
        this.ljubimacService = ljubimacService; 
        this.vlasnikService = vlasnikService; 
    } 
 
    @GetMapping 
    public String prikaziLjubimce(Model model) { 
        model.addAttribute("ljubimci", ljubimacService.findAll()); 
        model.addAttribute("ljubimac", new Ljubimac());
        model.addAttribute("vlasnici", vlasnikService.findAll()); 
        return "ljubimci"; 
    } 
 
    @PostMapping 
    public String sacuvajLjubimca(@ModelAttribute Ljubimac ljubimac) { 
        ljubimacService.save(ljubimac); 
        return "redirect:/web/ljubimci"; 
    } 
 
    @GetMapping("/edit/{id}") 
    public String izmeniLjubimca(@PathVariable Long id, Model model) { 
        model.addAttribute("ljubimci", ljubimacService.findAll()); 
        model.addAttribute("ljubimac", ljubimacService.findById(id)); 
        model.addAttribute("vlasnici", vlasnikService.findAll()); 
        return "ljubimci"; 
    } 
 
    @PostMapping("/update/{id}") 
    public String updateLjubimac(@PathVariable Long id, @ModelAttribute Ljubimac ljubimac) { 
        ljubimacService.update(id, ljubimac); 
        return "redirect:/web/ljubimci"; 
    } 
 
    @PostMapping("/delete/{id}") 
    public String deleteLjubimac(@PathVariable Long id) { 
        ljubimacService.deleteById(id); 
        return "redirect:/web/ljubimci"; 
    } 
}
