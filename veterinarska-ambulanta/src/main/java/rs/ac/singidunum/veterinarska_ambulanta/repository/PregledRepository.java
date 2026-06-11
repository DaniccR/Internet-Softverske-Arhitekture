/**
 * @author Radomir Danic
 * @date 5. 6. 2026.
 */
package rs.ac.singidunum.veterinarska_ambulanta.repository;

/**
 * TODO
 * 
 * @author Radomir
 */

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.singidunum.veterinarska_ambulanta.model.Pregled;
import rs.ac.singidunum.veterinarska_ambulanta.model.Veterinar;

public interface PregledRepository extends JpaRepository<Pregled, Long>{
	boolean existsByLjubimacIdAndStatus(Long ljubimacId, rs.ac.singidunum.veterinarska_ambulanta.model.StatusPregleda status);
	
	java.util.List<Pregled> findByStatusAndDatumPrijemaBetween( 
	        rs.ac.singidunum.veterinarska_ambulanta.model.StatusPregleda status, 
	        java.time.LocalDate odDatuma, 
	        java.time.LocalDate doDatuma 
			);
	
	@org.springframework.data.jpa.repository.Query(""" 
	        SELECT p.veterinar 
	        FROM Pregled p 
	        WHERE p.status = rs.ac.singidunum.veterinarska_ambulanta.model.StatusPregleda.ZAVRSENO
	        GROUP BY p.veterinar 
	        ORDER BY COUNT(p) DESC 
	    """) 
	    java.util.List<Veterinar> findNajaktivnijiVeterinari();
}
