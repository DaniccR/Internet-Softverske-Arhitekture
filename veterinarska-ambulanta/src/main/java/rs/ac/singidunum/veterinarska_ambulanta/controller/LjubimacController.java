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
import rs.ac.singidunum.veterinarska_ambulanta.model.Ljubimac; 
import rs.ac.singidunum.veterinarska_ambulanta.service.LjubimacService; 
 
@RestController 
@RequestMapping("/ljubimci")
public class LjubimacController {
	private final LjubimacService ljubimacService; 
	 
    public LjubimacController(LjubimacService ljubimacService) {
        this.ljubimacService = ljubimacService; 
    } 
 
    @GetMapping 
    public List<Ljubimac> getAllLjubimci() {
    	return ljubimacService.findAll();
    } 
 
    @GetMapping("/{id}") 
    public Ljubimac getLjubimacById(@PathVariable Long id) {
    	return ljubimacService.findById(id);
    } 
 
    @PostMapping 
    public Ljubimac createLjubimac(@RequestBody Ljubimac ljubimac) {
    	return ljubimacService.save(ljubimac);
    } 
 
    @PutMapping("/{id}") 
    public Ljubimac updateLjubimac(@PathVariable Long id, @RequestBody Ljubimac ljubimac) {
        return ljubimacService.update(id, ljubimac); 
    } 
 
    @DeleteMapping("/{id}") 
    public void deleteLjubimac(@PathVariable Long id) {
    	ljubimacService.deleteById(id);
    }
    
    @GetMapping("/bez-pregleda") 
    public List<Ljubimac> getLjubimciBezPregleda() { 
        return ljubimacService.findLjubimciBezPregleda(); 
    }
}
