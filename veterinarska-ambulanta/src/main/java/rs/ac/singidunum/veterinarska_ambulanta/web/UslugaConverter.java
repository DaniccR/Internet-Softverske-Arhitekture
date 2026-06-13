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
import org.springframework.core.convert.converter.Converter; 
import org.springframework.stereotype.Component; 
import rs.ac.singidunum.veterinarska_ambulanta.model.Usluga; 
import rs.ac.singidunum.veterinarska_ambulanta.service.UslugaService; 
 
@Component 
public class UslugaConverter {
	 
    private final UslugaService uslugaService; 
 
    public UslugaConverter(UslugaService uslugaService) { 
        this.uslugaService = uslugaService; 
    } 
 
    public Usluga convert(String source) { 
        if (source == null || source.isBlank()) { 
            return null; 
        } 
        Long id = Long.valueOf(source); 
        return uslugaService.findById(id); 
    } 
}
