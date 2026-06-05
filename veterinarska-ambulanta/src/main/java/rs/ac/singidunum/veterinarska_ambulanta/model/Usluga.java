/**
 * @author Radomir Danic
 * @date 3. 6. 2026.
 */
package rs.ac.singidunum.veterinarska_ambulanta.model;

/**
 * TODO
 * 
 * @author Radomir
 */
import java.math.BigDecimal; 

import jakarta.persistence.Column; 
import jakarta.persistence.Entity; 
import jakarta.persistence.GeneratedValue; 
import jakarta.persistence.GenerationType; 
import jakarta.persistence.Id; 
import jakarta.persistence.Table; 
  
@Entity 
@Table(name = "usluga") 
public class Usluga {
	
	 @Id 
	 @GeneratedValue(strategy = GenerationType.IDENTITY) 
	 private Long id; 
	  
	 @Column(nullable = false, length = 150) 
	 private String naziv; 
	  
	 @Column(columnDefinition = "TEXT") 
	 private String opis; 
	  
	 @Column(nullable = false, precision = 10, scale = 2) 
	 private BigDecimal cena;

	 public Usluga() {
	 }

	 public Long getId() {
		 return id;
	 }

	 public void setId(Long id) {
		 this.id = id;
	 }

	 public String getNaziv() {
		 return naziv;
	 }

	 public void setNaziv(String naziv) {
		 this.naziv = naziv;
	 }

	 public String getOpis() {
		 return opis;
	 }

	 public void setOpis(String opis) {
		 this.opis = opis;
	 }

	 public BigDecimal getCena() {
		 return cena;
	 }

	 public void setCena(BigDecimal cena) {
		 this.cena = cena;
	 } 
	 
	 
}
