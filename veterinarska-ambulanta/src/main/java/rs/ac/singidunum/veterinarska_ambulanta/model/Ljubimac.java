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
import com.fasterxml.jackson.annotation.JsonBackReference; 

import jakarta.persistence.Column; 
import jakarta.persistence.Entity; 
import jakarta.persistence.GeneratedValue; 
import jakarta.persistence.GenerationType; 
import jakarta.persistence.Id; 
import jakarta.persistence.JoinColumn; 
import jakarta.persistence.ManyToOne; 
import jakarta.persistence.Table; 
  
@Entity 
@Table(name = "ljubimac") 
public class Ljubimac {
	@Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id; 
  
    @Column(nullable = false, unique = true, length = 50) 
    private String brojMikrocipa; 
  
    @Column(nullable = false, length = 50) 
    private String vrsta; 
  
    @Column(nullable = false, length = 100) 
    private String ime; 
  
    private Integer godinaRodjenja; 
  
    @JsonBackReference 
    @ManyToOne 
    @JoinColumn(name = "vlasnik_id", nullable = false) 
    private Vlasnik vlasnik;

	public Ljubimac() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBrojMikrocipa() {
		return brojMikrocipa;
	}
	
	public void setBrojMikrocipa(String brojMikrocipa) {
		this.brojMikrocipa = brojMikrocipa;
	}

	public String getVrsta() {
		return vrsta;
	}

	public void setVrsta(String vrsta) {
		this.vrsta = vrsta;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public Integer getGodinaRodjenja() {
		return godinaRodjenja;
	}

	public void setGodinaRodjenja(Integer godinaRodjenja) {
		this.godinaRodjenja = godinaRodjenja;
	}

	public Vlasnik getVlasnik() {
		return vlasnik;
	}

	public void setVlasnik(Vlasnik vlasnik) {
		this.vlasnik = vlasnik;
	}
    
    
}
