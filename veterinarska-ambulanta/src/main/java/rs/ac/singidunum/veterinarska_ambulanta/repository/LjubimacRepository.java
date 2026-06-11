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
import rs.ac.singidunum.veterinarska_ambulanta.model.Ljubimac; 

public interface LjubimacRepository extends JpaRepository<Ljubimac, Long>{
	
	boolean existsByBrojMikrocipa(String brojMikrocipa);
	boolean existsByVlasnikId(Long vlasnikId);
    java.util.Optional<Ljubimac> findByBrojMikrocipa(String brojMikrocipa);
    @org.springframework.data.jpa.repository.Query(""" 
            SELECT lj 
            FROM Ljubimac lj 
            WHERE lj.id NOT IN ( 
                SELECT p.ljubimac.id 
                FROM Pregled p 
            ) 
        """) 
        java.util.List<Ljubimac> findLjubimceBezPregleda();
}
