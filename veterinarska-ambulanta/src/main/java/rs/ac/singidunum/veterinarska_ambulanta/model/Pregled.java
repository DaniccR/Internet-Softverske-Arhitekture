/**
 * @author Radomir Danic
 * @date 5. 6. 2026.
 */
package rs.ac.singidunum.veterinarska_ambulanta.model;

/**
 * TODO
 * 
 * @author Radomir
 */

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
	
@Entity 
@Table(name = "pregled") 
public class Pregled {
	 
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id; 
	 
	@ManyToOne 
	@JoinColumn(name = "ljubimac_id", nullable = false) 
	private Ljubimac ljubimac; 
	 
	@ManyToOne 
	@JoinColumn(name = "veterinar_id", nullable = false) 
	private Veterinar veterinar; 
	 
	@Column(nullable = false) 
	private LocalDate datumPrijema; 
	 
	private LocalDate datumZavrsetka; 
	 
	@Column(nullable = false, columnDefinition = "TEXT") 
	private String opisDijagnoze; 
	 
	@Enumerated(EnumType.STRING) 
	@Column(nullable = false) 
	private StatusPregleda status; 
	 
	@ManyToMany 
	@JoinTable( 
		name = "pregled_usluga", 
	    joinColumns = @JoinColumn(name = "pregled_id"), 
	    inverseJoinColumns = @JoinColumn(name = "usluga_id") 
	)
	
	private List<Usluga> usluge; 
	 
	public Pregled() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Ljubimac getLjubimac() {
		return ljubimac;
	}

	public void setLjubimac(Ljubimac ljubimac) {
		this.ljubimac = ljubimac;
	}

	public Veterinar getVeterinar() {
		return veterinar;
	}

	public void setVeterinar(Veterinar veterinar) {
		this.veterinar = veterinar;
	}

	public LocalDate getDatumPrijema() {
		return datumPrijema;
	}

	public void setDatumPrijema(LocalDate datumPrijema) {
		this.datumPrijema = datumPrijema;
	}

	public LocalDate getDatumZavrsetka() {
		return datumZavrsetka;
	}

	public void setDatumZavrsetka(LocalDate datumZavrsetka) {
		this.datumZavrsetka = datumZavrsetka;
	}

	public String getOpisDijagnoze() {
		return opisDijagnoze;
	}

	public void setOpisDijagnoze(String opisDijagnoze) {
		this.opisDijagnoze = opisDijagnoze;
	}

	public StatusPregleda getStatus() {
		return status;
	}

	public void setStatus(StatusPregleda status) {
		this.status = status;
	}

	public List<Usluga> getUsluge() {
		return usluge;
	}

	public void setUsluge(List<Usluga> usluge) {
		this.usluge = usluge;
	} 
	 
	
}	    
