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

import java.util.List; 
import com.fasterxml.jackson.annotation.JsonManagedReference; 
import jakarta.persistence.Column; 
import jakarta.persistence.Entity; 
import jakarta.persistence.GeneratedValue; 
import jakarta.persistence.GenerationType; 
import jakarta.persistence.Id; 
import jakarta.persistence.OneToMany; 
import jakarta.persistence.Table; 

@Entity 
@Table(name = "vlasnik") 
public class Vlasnik {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id; 
	  
	@Column(nullable = false, length = 100) 
	private String ime; 
	  
	@Column(nullable = false, length = 100) 
	private String prezime; 
	  
	@Column(length = 30) 
	private String telefon; 
	  
	@Column(length = 150) 
	private String email; 
	  
	@JsonManagedReference 
	@OneToMany(mappedBy = "vlasnik") 
	private List<Ljubimac> ljubimci; 
	  
	public Vlasnik() {}

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

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Ljubimac> getLjubimci() {
		return ljubimci;
	}

	public void setLjubimci(List<Ljubimac> ljubimci) {
		this.ljubimci = ljubimci;
	}
	
	
} 

