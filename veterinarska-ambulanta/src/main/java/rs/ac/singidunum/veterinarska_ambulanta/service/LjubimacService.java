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
    private final rs.ac.singidunum.veterinarska_ambulanta.repository.PregledRepository pregledRepository; // DODATO

    public LjubimacService(LjubimacRepository ljubimacRepository, VlasnikRepository vlasnikRepository, 
                           rs.ac.singidunum.veterinarska_ambulanta.repository.PregledRepository pregledRepository) { 
        this.ljubimacRepository = ljubimacRepository; 
        this.vlasnikRepository = vlasnikRepository; 
        this.pregledRepository = pregledRepository; // DODATO
    } 
 
    public List<Ljubimac> findAll() { 
        return ljubimacRepository.findAll(); 
    } 
 
    public Ljubimac findById(Long id) { 
        return ljubimacRepository.findById(id) 
            .orElseThrow(() -> new RuntimeException("Ljubimac nije pronađen.")); 
    } 
 
    public Ljubimac save(Ljubimac ljubimac) { 
        if (ljubimacRepository.existsByBrojMikrocipa(ljubimac.getBrojMikrocipa())) { 
            throw new RuntimeException("Ljubimac sa ovim brojem mikročipa već postoji u sistemu."); 
        } 
        
        Long vlasnikId = ljubimac.getVlasnik().getId(); 
        Vlasnik vlasnik = vlasnikRepository.findById(vlasnikId) 
            .orElseThrow(() -> new RuntimeException("Vlasnik nije pronađen.")); 
        ljubimac.setVlasnik(vlasnik); 
        
        return ljubimacRepository.save(ljubimac); 
    }
 
    public Ljubimac update(Long id, Ljubimac izmenjeniLjubimac) { 
        Ljubimac postojeciLjubimac = findById(id); 
        
        ljubimacRepository.findByBrojMikrocipa(izmenjeniLjubimac.getBrojMikrocipa()) 
            .ifPresent(lj -> { 
                if (!lj.getId().equals(id)) { 
                    throw new RuntimeException("Ljubimac sa ovim brojem mikročipa već postoji u sistemu."); 
                } 
            }); 
            
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
        
        boolean imaAktivanPregled = pregledRepository.existsByLjubimacIdAndStatus(
            id, rs.ac.singidunum.veterinarska_ambulanta.model.StatusPregleda.U_TOKU);
            
        if (imaAktivanPregled) { 
            throw new RuntimeException("Ljubimac ne može biti obrisan jer je trenutno na aktivnom pregledu."); 
        } 
        
        ljubimacRepository.delete(ljubimac); 
    }
    
    public List<Ljubimac> findLjubimciBezPregleda() { 
        return ljubimacRepository.findLjubimceBezPregleda(); 
    }
}
