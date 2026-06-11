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
import rs.ac.singidunum.veterinarska_ambulanta.model.Usluga; 
import rs.ac.singidunum.veterinarska_ambulanta.service.UslugaService; 
 
@RestController 
@RequestMapping("/usluge") 
public class UslugaController {
	private final UslugaService uslugaService; 
	 
    public UslugaController(UslugaService uslugaService) {
        this.uslugaService = uslugaService; 
    } 
 
    @GetMapping 
    public List<Usluga> getAllUsluge() {
    	return uslugaService.findAll();
    } 
 
    @GetMapping("/{id}") 
    public Usluga getUslugaById(@PathVariable Long id) {
    	return uslugaService.findById(id);
    } 
 
    @PostMapping 
    public Usluga createUsluga(@RequestBody Usluga usluga) {
    	return uslugaService.save(usluga);
    } 
 
    @PutMapping("/{id}") 
    public Usluga updateUsluga(@PathVariable Long id, @RequestBody Usluga usluga) { 
        return uslugaService.update(id, usluga); 
    } 
 
    @DeleteMapping("/{id}") 
    public void deleteUsluga(@PathVariable Long id) {
    	uslugaService.deleteById(id);
    }
    
    @GetMapping("/najcesca") 
    public Usluga getNajcescaUsluga() { 
        return uslugaService.findNajcescaUsluga(); 
    }
}
