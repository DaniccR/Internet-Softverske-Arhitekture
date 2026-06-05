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
import rs.ac.singidunum.veterinarska_ambulanta.model.Pregled; 
import rs.ac.singidunum.veterinarska_ambulanta.model.Veterinar; 
import rs.ac.singidunum.veterinarska_ambulanta.model.Usluga; 
import rs.ac.singidunum.veterinarska_ambulanta.model.Ljubimac; 
import rs.ac.singidunum.veterinarska_ambulanta.repository.PregledRepository; 
import rs.ac.singidunum.veterinarska_ambulanta.repository.VeterinarRepository; 
import rs.ac.singidunum.veterinarska_ambulanta.repository.UslugaRepository; 
import rs.ac.singidunum.veterinarska_ambulanta.repository.LjubimacRepository; 

@Service
public class PregledService {
	private final PregledRepository pregledRepository; 
    private final LjubimacRepository ljubimacRepository; 
    private final VeterinarRepository veterinarRepository; 
    private final UslugaRepository uslugaRepository; 
 
    public PregledService(PregledRepository pregledRepository, LjubimacRepository ljubimacRepository, VeterinarRepository veterinarRepository, 
        UslugaRepository uslugaRepository) { 
        this.pregledRepository = pregledRepository; 
        this.ljubimacRepository = ljubimacRepository; 
        this.veterinarRepository = veterinarRepository; 
        this.uslugaRepository = uslugaRepository; 
    } 
 
    public List<Pregled> findAll() { 
        return pregledRepository.findAll(); 
    } 
 
    public Pregled findById(Long id) {
        return pregledRepository.findById(id) 
            .orElseThrow(() -> new RuntimeException("Pregled nije nadjen.")); 
    } 
 
    public Pregled save(Pregled pregled) { 
        pripremiRelacije(pregled); 
        return pregledRepository.save(pregled); 
    } 
 
    public Pregled update(Long id, Pregled izmenjeniPregled) { 
        Pregled postojeciPregled = findById(id); 
        postojeciPregled.setDatumPrijema(izmenjeniPregled.getDatumPrijema()); 
        postojeciPregled.setDatumZavrsetka(izmenjeniPregled.getDatumZavrsetka()); 
        postojeciPregled.setOpisDijagnoze(izmenjeniPregled.getOpisDijagnoze()); 
        postojeciPregled.setStatus(izmenjeniPregled.getStatus()); 
        postojeciPregled.setLjubimac(izmenjeniPregled.getLjubimac()); 
        postojeciPregled.setVeterinar(izmenjeniPregled.getVeterinar()); 
        postojeciPregled.setUsluge(izmenjeniPregled.getUsluge()); 
        
        pripremiRelacije(postojeciPregled); 
        return pregledRepository.save(postojeciPregled); 
    } 
 
    public void deleteById(Long id) { 
        Pregled pregled = findById(id); 
        pregledRepository.delete(pregled); 
    } 
 
    private void pripremiRelacije(Pregled pregled) { 
        Long ljubimacId = pregled.getLjubimac().getId(); 
        Long veterinarId = pregled.getVeterinar().getId(); 
 
        Ljubimac ljubimac = ljubimacRepository.findById(ljubimacId) 
            .orElseThrow(() -> new RuntimeException("Ljubimac nije nadjen.")); 
        Veterinar veterinar = veterinarRepository.findById(veterinarId) 
            .orElseThrow(() -> new RuntimeException("Veterinar nije nadjen.")); 
 
        List<Long> uslugaIds = pregled.getUsluge() 
            .stream() 
            .map(Usluga::getId) 
            .toList(); 
        List<Usluga> usluge = uslugaRepository.findAllById(uslugaIds); 
 
        pregled.setLjubimac(ljubimac); 
        pregled.setVeterinar(veterinar); 
        pregled.setUsluge(usluge); 
    }
}
