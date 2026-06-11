/**
 * @author Radomir Danic
 * @date 11. 6. 2026.
 */
package rs.ac.singidunum.veterinarska_ambulanta.controller;

/**
 * TODO
 * 
 * @author Radomir
 */
import java.util.List; 
import org.springframework.web.bind.annotation.*; 
import rs.ac.singidunum.veterinarska_ambulanta.model.Vlasnik; 
import rs.ac.singidunum.veterinarska_ambulanta.service.VlasnikService; 
 
@RestController 
@RequestMapping("/vlasnici") 
public class VlasnikController {
	private final VlasnikService vlasnikService; 
	 
    public VlasnikController(VlasnikService vlasnikService) { 
        this.vlasnikService = vlasnikService; 
    } 
 
    @GetMapping 
    public List<Vlasnik> getAllVlasnici() { 
    	return vlasnikService.findAll(); 
    } 
 
    @GetMapping("/{id}") 
    public Vlasnik getVlasnikById(@PathVariable Long id) { 
    	return vlasnikService.findById(id);
    } 
 
    @PostMapping 
    public Vlasnik createVlasnik(@RequestBody Vlasnik vlasnik) {
    	return vlasnikService.save(vlasnik);
    } 
 
    @PutMapping("/{id}") 
    public Vlasnik updateVlasnik(@PathVariable Long id, @RequestBody Vlasnik vlasnik) { 
        return vlasnikService.update(id, vlasnik); 
    } 
 
    @DeleteMapping("/{id}") 
    public void deleteVlasnik(@PathVariable Long id) {
    	vlasnikService.deleteById(id);
    } 
}
