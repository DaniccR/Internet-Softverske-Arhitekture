/**
 * @author Radomir Danic
 * @date 5. 6. 2026.
 */
package rs.ac.singidunum.veterinarska_ambulanta.service;

/**
 * 
 * 
 * @author Radomir
 */

import java.util.List; 
import org.springframework.stereotype.Service; 
import rs.ac.singidunum.veterinarska_ambulanta.model.Vlasnik;
import rs.ac.singidunum.veterinarska_ambulanta.exception.BusinessException;
import rs.ac.singidunum.veterinarska_ambulanta.exception.ResourceNotFoundException;
import rs.ac.singidunum.veterinarska_ambulanta.model.Ljubimac; 
import rs.ac.singidunum.veterinarska_ambulanta.repository.VlasnikRepository; 
import rs.ac.singidunum.veterinarska_ambulanta.repository.LjubimacRepository;

@Service
public class LjubimacService {
	private final LjubimacRepository ljubimacRepository; 
    private final VlasnikRepository vlasnikRepository; 
    private final rs.ac.singidunum.veterinarska_ambulanta.repository.PregledRepository pregledRepository;

    public LjubimacService(LjubimacRepository ljubimacRepository, VlasnikRepository vlasnikRepository, 
                           rs.ac.singidunum.veterinarska_ambulanta.repository.PregledRepository pregledRepository) { 
        this.ljubimacRepository = ljubimacRepository; 
        this.vlasnikRepository = vlasnikRepository; 
        this.pregledRepository = pregledRepository;
    } 
 
    public List<Ljubimac> findAll() { 
        return ljubimacRepository.findAll(); 
    } 
 
    public Ljubimac findById(Long id) { 
        return ljubimacRepository.findById(id) 
            .orElseThrow(() -> new ResourceNotFoundException("Ljubimac nije nadjen.")); 
    } 
 
    public Ljubimac save(Ljubimac ljubimac) { 
        if (ljubimacRepository.existsByBrojMikrocipa(ljubimac.getBrojMikrocipa())) { 
            throw new BusinessException("Ljubimac sa ovim brojem mikrocipa vec postoji u sistemu."); 
        } 
        
        Long vlasnikId = ljubimac.getVlasnik().getId(); 
        Vlasnik vlasnik = vlasnikRepository.findById(vlasnikId) 
            .orElseThrow(() -> new ResourceNotFoundException("Vlasnik nije nadjen.")); 
        ljubimac.setVlasnik(vlasnik); 
        
        return ljubimacRepository.save(ljubimac); 
    }
 
    public Ljubimac update(Long id, Ljubimac izmenjeniLjubimac) { 
        Ljubimac postojeciLjubimac = findById(id); 
        
        ljubimacRepository.findByBrojMikrocipa(izmenjeniLjubimac.getBrojMikrocipa()) 
            .ifPresent(lj -> { 
                if (!lj.getId().equals(id)) { 
                    throw new BusinessException("Ljubimac sa ovim brojem mikrocipa vec postoji u sistemu."); 
                } 
            }); 
            
        Long vlasnikId = izmenjeniLjubimac.getVlasnik().getId(); 
        Vlasnik vlasnik = vlasnikRepository.findById(vlasnikId) 
            .orElseThrow(() -> new ResourceNotFoundException("Vlasnik nije nadjen.")); 
            
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
            throw new BusinessException("Ljubimac ne moze biti obrisan jer je trenutno na aktivnom pregledu."); 
        } 
        
        ljubimacRepository.delete(ljubimac); 
    }
    
    public List<Ljubimac> findLjubimciBezPregleda() { 
        return ljubimacRepository.findLjubimceBezPregleda(); 
    }
}
