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
import rs.ac.singidunum.veterinarska_ambulanta.model.Veterinar; 
import rs.ac.singidunum.veterinarska_ambulanta.repository.VeterinarRepository; 

@Service
public class VeterinarService {
	private final VeterinarRepository veterinarRepository; 
	 
    public VeterinarService(VeterinarRepository veterinarRepository) { 
        this.veterinarRepository = veterinarRepository; 
    } 
 
    public List<Veterinar> findAll() { 
    	return veterinarRepository.findAll();
    } 
 
    public Veterinar findById(Long id) { 
        return veterinarRepository.findById(id) 
            .orElseThrow(() -> new RuntimeException("Veterinar nije nadjen.")); 
    } 
 
    public Veterinar save(Veterinar veterinar) { 
    	return veterinarRepository.save(veterinar); 
    } 
 
    public Veterinar update(Long id, Veterinar izmenjeniVeterinar) { 
        Veterinar postojeciVeterinar = findById(id); 
        postojeciVeterinar.setIme(izmenjeniVeterinar.getIme()); 
        postojeciVeterinar.setPrezime(izmenjeniVeterinar.getPrezime()); 
        postojeciVeterinar.setSpecijalizacija(izmenjeniVeterinar.getSpecijalizacija()); 
        return veterinarRepository.save(postojeciVeterinar); 
    } 
 
    public void deleteById(Long id) { 
        Veterinar veterinar = findById(id); 
        veterinarRepository.delete(veterinar); 
    } 
}
