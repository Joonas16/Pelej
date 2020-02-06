package model;

public class Peli {
	private int Id;
	private String Nimi;
	private String Kuvaus;
	private String Kehittaja;
	private String Julkaisija;
	private int Julkaisuvuosi;
	private double Arvostelu;
	private double Hinta;
	
	public Peli(int id,String nimi, String kuvaus, String kehittaja, String julkaisija, int julkaisuvuosi, double arvostelu, double hinta) {
		super();
		Id = id;
		Nimi = nimi;
		Kuvaus = kuvaus;
		Kehittaja = kehittaja;
		Julkaisija = julkaisija;
		Julkaisuvuosi = julkaisuvuosi;
		Arvostelu = arvostelu;
		Hinta = hinta;
	}

	public double getArvostelu() {
		return Arvostelu;
	}
	public void setArvostelu(double arvostelu) {
		Arvostelu = arvostelu;
	}

	public int getId() {
		return Id;
	}
	public void setInt(int id) {
		Id = id;
	}
	
	public String getNimi() {
		return Nimi;
	}
	public void setNimi(String nimi) {
		if(nimi.length() > 0) {
			Nimi = nimi;
		} else {
			nimi = "Ei tietoja";
		}
		
	}
	public String getKuvaus() {
		return Kuvaus;
	}
	public void setKuvaus(String kuvaus) {
		if(kuvaus.length() > 0) {
			Kuvaus = kuvaus;
		} else {
			kuvaus = "Ei tietoja";
		}
		
	}
	public String getKehittaja() {
		return Kehittaja;
	}
	public void setKehittaja(String kehittaja) {
		if(kehittaja.length() > 0) {
			Kehittaja = kehittaja;
		} else {
			kehittaja = "Ei tietoja";
		}
	}
	public String getJulkaisija() {
		return Julkaisija;
	}
	public void setJulkaisija(String julkaisija) {
		if(julkaisija.length() > 0) {
			Julkaisija = julkaisija;
		} else {
			julkaisija = "Ei tietoja";
		}
	}
	public int getJulkaisuvuosi() {
		return Julkaisuvuosi;
	}
	public void setJulkaisuvuosi(int julkaisuvuosi) {
		Julkaisuvuosi = julkaisuvuosi;
	}
	public double getHinta() {
		return Hinta;
	}
	public void setHinta(double hinta) {
		Hinta = hinta;
	}
	@Override
	public String toString() {
		return "Peli [Nimi=" + Nimi + ", Kuvaus=" + Kuvaus + ", Kehittaja=" + Kehittaja + ", Julkaisija=" + Julkaisija
				+ ", Julkaisuvuosi=" + Julkaisuvuosi + ", Hinta=" + Hinta + "]";
	}

	
	
}
