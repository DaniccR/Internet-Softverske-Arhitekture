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
import rs.ac.singidunum.veterinarska_ambulanta.model.Vlasnik; 
import rs.ac.singidunum.veterinarska_ambulanta.service.VlasnikService; 
 
@Controller 
@RequestMapping("/web/vlasnici") 
public class VlasnikWebController {
	private final VlasnikService vlasnikService; 
	 
	public VlasnikWebController(VlasnikService vlasnikService) { 
	    this.vlasnikService = vlasnikService; 
	} 
	 
	@GetMapping 
	public String prikaziVlasnike(Model model) { 
	    model.addAttribute("vlasnici", vlasnikService.findAll()); 
	    model.addAttribute("vlasnik", new Vlasnik()); 
	    return "vlasnici"; 
	} 
	 
	@PostMapping 
	public String sacuvajVlasnika(@ModelAttribute Vlasnik vlasnik) { 
		vlasnikService.save(vlasnik); 
	    return "redirect:/web/vlasnici"; 
	} 
	 
	@GetMapping("/edit/{id}") 
	public String izmeniVlasnika(@PathVariable Long id, Model model) { 
	    model.addAttribute("vlasnici", vlasnikService.findAll()); 
	    model.addAttribute("vlasnik", vlasnikService.findById(id)); 
	    return "vlasnici"; 
	} 
	 
	@PostMapping("/update/{id}") 
	public String updateVlasnik(@PathVariable Long id, @ModelAttribute Vlasnik vlasnik) { 
	    vlasnikService.update(id, vlasnik); 
	    return "redirect:/web/vlasnici"; 
	} 
	 
	@PostMapping("/delete/{id}") 
	public String deleteVlasnik(@PathVariable Long id) { 
	    vlasnikService.deleteById(id); 
	    return "redirect:/web/vlasnici"; 
	} 
}
