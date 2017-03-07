package schedPack;

import java.util.Date;

public class Horaire {
	

	public String heureDebut; 
	public String heureFin;
	public String jour;
	
	public Horaire(){}
	
	public Horaire(String hDeb, String hFin, String j){
		heureDebut = hDeb;
		heureFin = hFin;
		jour = j;
	}
	
	/**
	 * @return the dureeMinute
	 */


	/**
	 * @param dureeMinute the dureeMinute to set
	 */


	@Override
	public String toString() {
		return "Horaire [heureDebut=" + heureDebut + ", heureFin=" + heureFin + ", jour=" + jour + "]";
	}

	/**
	 * @return the heureDebut
	 */
	public String getHeureDebut() {
		return heureDebut;
	}

	/**
	 * @param heureDebut the heureDebut to set
	 */
	public void setHeureDebut(String heureDebut) {
		this.heureDebut = heureDebut;
	}

	/**
	 * @return the heureFin
	 */
	public String getHeureFin() {
		return heureFin;
	}

	/**
	 * @param heureFin the heureFin to set
	 */
	public void setHeureFin(String heureFin) {
		this.heureFin = heureFin;
	}

	/**
	 * @return the jour
	 */
	public String getJour() {
		return jour;
	}

	/**
	 * @param jour the jour to set
	 */
	public void setJour(String jour) {
		this.jour = jour;
	}
}
