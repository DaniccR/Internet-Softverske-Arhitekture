/**
 * @author Radomir Danic
 * @date 5. 6. 2026.
 */
package rs.ac.singidunum.veterinarska_ambulanta.service;

/**
 * TODO
 * 
 * @author Radomir
 */

import java.util.List; 
import org.springframework.stereotype.Service;

import rs.ac.singidunum.veterinarska_ambulanta.exception.BusinessException;
import rs.ac.singidunum.veterinarska_ambulanta.exception.ResourceNotFoundException;
import rs.ac.singidunum.veterinarska_ambulanta.model.Vlasnik;
import rs.ac.singidunum.veterinarska_ambulanta.repository.LjubimacRepository;
import rs.ac.singidunum.veterinarska_ambulanta.repository.VlasnikRepository; 

@Service
public class VlasnikService {
	
	private final VlasnikRepository vlasnikRepository; 
    private final LjubimacRepository ljubimacRepository;

    public VlasnikService(VlasnikRepository vlasnikRepository, LjubimacRepository ljubimacRepository) { 
        this.vlasnikRepository = vlasnikRepository; 
        this.ljubimacRepository = ljubimacRepository;
    } 

    public void deleteById(Long id) { 
        Vlasnik vlasnik = findById(id); 
        
        if (ljubimacRepository.existsByVlasnikId(id)) { 
            throw new BusinessException("Vlasnik ne moze biti obrisan jer ima registrovane ljubimce u ambulanti."); 
        } 
        
        vlasnikRepository.delete(vlasnik); 
    }
 
    public List<Vlasnik> findAll() { 
        return vlasnikRepository.findAll(); 
    } 
 
    public Vlasnik findById(Long id) { 
        return vlasnikRepository.findById(id) 
            .orElseThrow(() -> new ResourceNotFoundException("Vlasnik nije nadjen.")); 
    } 
 
    public Vlasnik save(Vlasnik vlasnik) { 
        return vlasnikRepository.save(vlasnik); 
    } 
 
    public Vlasnik update(Long id, Vlasnik izmenjeniVlasnik) { 
        Vlasnik postojeciVlasnik = findById(id); 
        postojeciVlasnik.setIme(izmenjeniVlasnik.getIme()); 
        postojeciVlasnik.setPrezime(izmenjeniVlasnik.getPrezime()); 
        postojeciVlasnik.setTelefon(izmenjeniVlasnik.getTelefon()); 
        postojeciVlasnik.setEmail(izmenjeniVlasnik.getEmail()); 
        return vlasnikRepository.save(postojeciVlasnik); 
    } 
 
}
