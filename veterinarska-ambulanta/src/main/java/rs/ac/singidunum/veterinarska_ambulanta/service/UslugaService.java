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
import rs.ac.singidunum.veterinarska_ambulanta.model.Usluga; 
import rs.ac.singidunum.veterinarska_ambulanta.repository.UslugaRepository; 

@Service
public class UslugaService {
	private final UslugaRepository uslugaRepository; 
	 
    public UslugaService(UslugaRepository uslugaRepository) { 
        this.uslugaRepository = uslugaRepository; 
    } 
 
    public List<Usluga> findAll() { 
    	return uslugaRepository.findAll(); 
    } 
 
    public Usluga findById(Long id) { 
        return uslugaRepository.findById(id) 
            .orElseThrow(() -> new RuntimeException("Usluga nije nadjen.")); 
    } 
 
    public Usluga save(Usluga usluga) { 
    	return uslugaRepository.save(usluga); 
    } 
 
    public Usluga update(Long id, Usluga izmenjenaUsluga) { 
        Usluga postojecaUsluga = findById(id); 
        postojecaUsluga.setNaziv(izmenjenaUsluga.getNaziv()); 
        postojecaUsluga.setOpis(izmenjenaUsluga.getOpis()); 
        postojecaUsluga.setCena(izmenjenaUsluga.getCena()); 
        return uslugaRepository.save(postojecaUsluga); 
    } 
 
    public void deleteById(Long id) { 
        Usluga usluga = findById(id); 
        uslugaRepository.delete(usluga); 
    } 
    
    public Usluga findNajcescaUsluga() { 
        List<Usluga> usluge = uslugaRepository.findNajcesceKorisceneUsluge(); 
        
        if (usluge.isEmpty()) { 
            throw new RuntimeException("Ne postoji nijedna korišćena usluga."); 
        } 
        
        return usluge.get(0); 
    }
}
