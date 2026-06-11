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
        Long ljubimacId = pregled.getLjubimac().getId(); 
        
        // Proveravamo da li ljubimac već ima pregled U_TOKU
        boolean imaAktivanPregled = pregledRepository.existsByLjubimacIdAndStatus( 
            ljubimacId, rs.ac.singidunum.veterinarska_ambulanta.model.StatusPregleda.U_TOKU); 
        
        if (imaAktivanPregled) { 
            throw new RuntimeException("Nije moguće otvoriti novi pregled jer ljubimac već ima pregled koji je u toku."); 
        } 
        
        // Automatski postavljamo status i datum prijema
        pregled.setStatus(rs.ac.singidunum.veterinarska_ambulanta.model.StatusPregleda.U_TOKU); 
        pregled.setDatumPrijema(java.time.LocalDate.now()); 
        pregled.setDatumZavrsetka(null); 
        
        pripremiRelacije(pregled); 
        return pregledRepository.save(pregled); 
    }
 
    public Pregled update(Long id, Pregled izmenjeniPregled) { 
        Pregled postojeciPregled = findById(id); 
        
        // Sprečavanje izmene završenog ili otkazanog pregleda
        if (postojeciPregled.getStatus() == rs.ac.singidunum.veterinarska_ambulanta.model.StatusPregleda.ZAVRSENO || 
            postojeciPregled.getStatus() == rs.ac.singidunum.veterinarska_ambulanta.model.StatusPregleda.OTKAZAN) { 
            throw new RuntimeException("Završen ili otkazan pregled se ne može menjati."); 
        } 

        // TVOJE PRAVILO: Zabrana promene veterinara dok je pregled U_TOKU
        if (postojeciPregled.getStatus() == rs.ac.singidunum.veterinarska_ambulanta.model.StatusPregleda.U_TOKU) {
            Long postojeciVeterinarId = postojeciPregled.getVeterinar().getId();
            Long noviVeterinarId = izmenjeniPregled.getVeterinar().getId();
            
            if (!postojeciVeterinarId.equals(noviVeterinarId)) {
                throw new RuntimeException("Nije dozvoljena promena dodeljenog veterinara jer je pregled u toku.");
            }
        }
        
        // Provera da datum završetka ne bude pre datuma prijema
        if (izmenjeniPregled.getDatumZavrsetka() != null && 
            izmenjeniPregled.getDatumZavrsetka().isBefore(postojeciPregled.getDatumPrijema())) { 
            throw new RuntimeException("Datum završetka ne može biti pre datuma prijema."); 
        } 
        
        postojeciPregled.setDatumPrijema(izmenjeniPregled.getDatumPrijema()); 
        postojeciPregled.setDatumZavrsetka(izmenjeniPregled.getDatumZavrsetka()); 
        postojeciPregled.setOpisDijagnoze(izmenjeniPregled.getOpisDijagnoze()); 
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
    
    public Pregled zavrsiPregled(Long id) { 
        Pregled pregled = findById(id); 
        
        if (pregled.getStatus() != rs.ac.singidunum.veterinarska_ambulanta.model.StatusPregleda.U_TOKU) { 
            throw new RuntimeException("Samo pregled koji je u toku može biti završen."); 
        } 
        
        if (pregled.getUsluge() == null || pregled.getUsluge().isEmpty()) { 
            throw new RuntimeException("Pregled ne može biti završen jer nema dodatu nijednu uslugu."); 
        } 

        if (pregled.getOpisDijagnoze() == null || pregled.getOpisDijagnoze().isBlank()) {
            throw new RuntimeException("Pregled ne može biti završen bez opisa dijagnoze.");
        }
        
        pregled.setStatus(rs.ac.singidunum.veterinarska_ambulanta.model.StatusPregleda.ZAVRSENO); 
        pregled.setDatumZavrsetka(java.time.LocalDate.now()); 
        
        return pregledRepository.save(pregled); 
    } 

    public Pregled otkaziPregled(Long id) { 
        Pregled pregled = findById(id); 
        
        if (pregled.getStatus() != rs.ac.singidunum.veterinarska_ambulanta.model.StatusPregleda.U_TOKU) { 
            throw new RuntimeException("Samo pregled koji je u toku može biti otkazan."); 
        } 
        
        pregled.setStatus(rs.ac.singidunum.veterinarska_ambulanta.model.StatusPregleda.OTKAZAN); 
        pregled.setDatumZavrsetka(null); 
        
        return pregledRepository.save(pregled); 
    }
    
    public java.math.BigDecimal izracunajUkupnuCenu(Long id) { 
        Pregled pregled = findById(id); 
        
        if (pregled.getUsluge() == null || pregled.getUsluge().isEmpty()) { 
            return java.math.BigDecimal.ZERO; 
        } 
        
        return pregled.getUsluge() 
            .stream() 
            .map(Usluga::getCena) 
            .reduce(java.math.BigDecimal.ZERO, java.math.BigDecimal::add); 
    }
    
    public java.math.BigDecimal izracunajUkupanPrihod(java.time.LocalDate odDatuma, java.time.LocalDate doDatuma) { 
        if (odDatuma.isAfter(doDatuma)) { 
            throw new RuntimeException("Početni datum ne može biti posle krajnjeg datuma."); 
        } 
        
        List<Pregled> pregledi = pregledRepository.findByStatusAndDatumPrijemaBetween( 
            rs.ac.singidunum.veterinarska_ambulanta.model.StatusPregleda.ZAVRSENO, odDatuma, doDatuma); 
        
        return pregledi.stream() 
            .flatMap(p -> p.getUsluge().stream()) 
            .map(Usluga::getCena) 
            .reduce(java.math.BigDecimal.ZERO, java.math.BigDecimal::add); 
    }
}
