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
import rs.ac.singidunum.veterinarska_ambulanta.model.Veterinar; 
import rs.ac.singidunum.veterinarska_ambulanta.service.VeterinarService; 
 
@RestController 
@RequestMapping("/veterinari")
public class VeterinarController {
	private final VeterinarService veterinarService; 
	 
    public VeterinarController(VeterinarService veterinarService) { 
    	this.veterinarService = veterinarService; 
    } 
 
    @GetMapping 
    public List<Veterinar> getAllVeterinari() {
    	return veterinarService.findAll();
    } 
 
    @GetMapping("/{id}") 
    public Veterinar getVeterinarById(@PathVariable Long id) {
    	return veterinarService.findById(id);
    } 
 
    @PostMapping 
    public Veterinar createVeterinar(@RequestBody Veterinar veterinar) {
    	return veterinarService.save(veterinar);
    } 
 
    @PutMapping("/{id}") 
    public Veterinar updateVeterinar(@PathVariable Long id, @RequestBody Veterinar veterinar) { 
        return veterinarService.update(id, veterinar); 
    } 
 
    @DeleteMapping("/{id}") 
    public void deleteVeterinar(@PathVariable Long id) {
    	veterinarService.deleteById(id);
    }
    
    @GetMapping("/najaktivniji") 
    public Veterinar getNajaktivnijiVeterinar() { 
        return veterinarService.findNajaktivnijiVeteriser(); 
    }

}
