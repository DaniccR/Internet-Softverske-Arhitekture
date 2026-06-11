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
import rs.ac.singidunum.veterinarska_ambulanta.model.Pregled; 
import rs.ac.singidunum.veterinarska_ambulanta.service.PregledService; 
 
@RestController 
@RequestMapping("/pregledi") 
public class PregledController {
	private final PregledService pregledService; 
	 
    public PregledController(PregledService pregledService) {
        this.pregledService = pregledService; 
    } 
 
    @GetMapping 
    public List<Pregled> getAllPregledi() {
    	return pregledService.findAll();
    } 
 
    @GetMapping("/{id}") 
    public Pregled getPregledById(@PathVariable Long id) {
    	return pregledService.findById(id);
    } 
 
    @PostMapping 
    public Pregled createPregled(@RequestBody Pregled pregled) {
    	return pregledService.save(pregled);
    } 
 
    @PutMapping("/{id}") 
    public Pregled updatePregled(@PathVariable Long id, @RequestBody Pregled pregled) {
        return pregledService.update(id, pregled); 
    } 
 
    @DeleteMapping("/{id}") 
    public void deletePregled(@PathVariable Long id) {
    	pregledService.deleteById(id);
    }
    
    @PutMapping("/{id}/zavrsi") 
    public Pregled zavrsiPregled(@PathVariable Long id) { 
        return pregledService.zavrsiPregled(id); 
    } 

    @PutMapping("/{id}/otkazi") 
    public Pregled otkaziPregled(@PathVariable Long id) { 
        return pregledService.otkaziPregled(id); 
    } 

    @GetMapping("/{id}/ukupna-cena") 
    public java.math.BigDecimal getUkupnaCena(@PathVariable Long id) { 
        return pregledService.izracunajUkupnuCenu(id); 
    }
    
    @GetMapping("/prihod") 
    public java.math.BigDecimal getUkupanPrihod( 
            @RequestParam("od") java.time.LocalDate odDatuma, 
            @RequestParam("do") java.time.LocalDate doDatuma) { 
        return pregledService.izracunajUkupanPrihod(odDatuma, doDatuma); 
    }
}
