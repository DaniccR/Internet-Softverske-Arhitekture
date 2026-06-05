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
import jakarta.persistence.Column; 
import jakarta.persistence.Entity; 
import jakarta.persistence.GeneratedValue; 
import jakarta.persistence.GenerationType; 
import jakarta.persistence.Id; 
import jakarta.persistence.Table; 
  
@Entity 
@Table(name = "veterinar") 
public class Veterinar {

	 @Id 
	 @GeneratedValue(strategy = GenerationType.IDENTITY) 
	 private Long id; 
	  
	 @Column(nullable = false, length = 100) 
	 private String ime; 
	  
	 @Column(nullable = false, length = 100) 
	 private String prezime; 
	  
	 @Column(length = 150) 
	 private String specijalizacija;

	 public Veterinar() {
	 }

	 public Long getId() {
		 return id;
	 }

	 public void setId(Long id) {
		 this.id = id;
	 }

	 public String getIme() {
		 return ime;
	 }

	 public void setIme(String ime) {
		 this.ime = ime;
	 }

	 public String getPrezime() {
		 return prezime;
	 }

	 public void setPrezime(String prezime) {
		 this.prezime = prezime;
	 }

	 public String getSpecijalizacija() {
		 return specijalizacija;
	 }

	 public void setSpecijalizacija(String specijalizacija) {
		 this.specijalizacija = specijalizacija;
	 } 
	 
	 
}
