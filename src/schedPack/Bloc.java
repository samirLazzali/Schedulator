package schedPack;

import java.util.List;

public class Bloc {
    public String nom;
    public double duree;
    public List<Horaire> horaires;

    //liste d'horaire : heureDeb1, heureFin1, jour1, ....
    public Bloc (){

    }

    public Bloc(String n, double duree, List<Horaire> listH){
        nom = n;
        horaires = listH;
        this.duree = duree;
    }

    @Override
    public String toString() {
        return "Bloc [nom=" + nom + ", duree=" + duree + ", horaires=" + horaires + "]";
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the horaires
     */
    public List<Horaire> getHoraires() {
        return horaires;
    }

    /**
     * @param horaires the horaires to set
     */
    public void setHoraires(List<Horaire> horaires) {
        this.horaires = horaires;
    }
    public double getDuree() {
        return duree;
    }

    public void setDuree(double duree) {
        this.duree = duree;
    }

    public boolean isVariable(){
        if (horaires.size() == 1 ){
            return false;
        }
        return  true ;
    }


}
