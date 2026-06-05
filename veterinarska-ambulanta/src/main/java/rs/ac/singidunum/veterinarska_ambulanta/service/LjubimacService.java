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
import rs.ac.singidunum.veterinarska_ambulanta.model.Vlasnik; 
import rs.ac.singidunum.veterinarska_ambulanta.model.Ljubimac; 
import rs.ac.singidunum.veterinarska_ambulanta.repository.VlasnikRepository; 
import rs.ac.singidunum.veterinarska_ambulanta.repository.LjubimacRepository;

@Service
public class LjubimacService {
	private final LjubimacRepository ljubimacRepository; 
    private final VlasnikRepository vlasnikRepository; 
 
    public LjubimacService(LjubimacRepository ljubimacRepository, VlasnikRepository vlasnikRepository) { 
        this.ljubimacRepository = ljubimacRepository; 
        this.vlasnikRepository = vlasnikRepository; 
    } 
 
    public List<Ljubimac> findAll() { 
        return ljubimacRepository.findAll(); 
    } 
 
    public Ljubimac findById(Long id) { 
        return ljubimacRepository.findById(id) 
            .orElseThrow(() -> new RuntimeException("Ljubimac nije pronađen.")); 
    } 
 
    public Ljubimac save(Ljubimac ljubimac) { 
        Long vlasnikId = ljubimac.getVlasnik().getId(); 
        Vlasnik vlasnik = vlasnikRepository.findById(vlasnikId) 
            .orElseThrow(() -> new RuntimeException("Vlasnik nije pronađen.")); 
        ljubimac.setVlasnik(vlasnik); 
        return ljubimacRepository.save(ljubimac); 
    } 
 
    public Ljubimac update(Long id, Ljubimac izmenjeniLjubimac) { 
        Ljubimac postojeciLjubimac = findById(id); 
        Long vlasnikId = izmenjeniLjubimac.getVlasnik().getId(); 
        Vlasnik vlasnik = vlasnikRepository.findById(vlasnikId) 
            .orElseThrow(() -> new RuntimeException("Vlasnik nije pronađen.")); 
            
        postojeciLjubimac.setBrojMikrocipa(izmenjeniLjubimac.getBrojMikrocipa()); 
        postojeciLjubimac.setVrsta(izmenjeniLjubimac.getVrsta()); 
        postojeciLjubimac.setIme(izmenjeniLjubimac.getIme()); 
        postojeciLjubimac.setGodinaRodjenja(izmenjeniLjubimac.getGodinaRodjenja()); 
        postojeciLjubimac.setVlasnik(vlasnik); 
        
        return ljubimacRepository.save(postojeciLjubimac); 
    } 
 
    public void deleteById(Long id) { 
        Ljubimac ljubimac = findById(id); 
        ljubimacRepository.delete(ljubimac); 
    }
}
