package schedPack;


import java.util.Date;

public class Horaire {

    final static int TRANCHE_CALENDRIER = 5  ;
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
     *
     * @return
     *      retourne combiens de tranche de TRANCHE_CALENDRIER ce sont écouler de puis 00h00 a heureDebut
     */
    public int getChocoHeureDebut(){
        String[] parts = heureDebut.split("H");

        return ( Integer.parseInt(parts[0] ) * (60 / TRANCHE_CALENDRIER))  + (Integer.parseInt(parts[1]) / TRANCHE_CALENDRIER)  + (288 * getIntJour() );

    }

    /**
     *
     * @return
     *  retourne combiens de tranche de TRANCHE_CALENDRIER ce sont écouler de puis 00h00 a heureFin
     */
    public int getChocoHeureFin(){
        String[] parts = heureFin.split("H");

        return ( Integer.parseInt(parts[0] ) * (60 / TRANCHE_CALENDRIER))  + (Integer.parseInt(parts[1]) / TRANCHE_CALENDRIER) + (288 * getIntJour()) ;
    }


    /**
     *
     * @return liste de int qui represente une duree de TRANCHE_CALENDRIER minute dans une semaine
     */
    public int[] getChocoTime(){
        int jour  = getIntJour() ;
        int chocoTimeDebut  = getChocoHeureDebut()   ;
        int chocoTimeFin  = getChocoHeureFin() ;
        int [] chocoTime   = new int[(chocoTimeFin-chocoTimeDebut) ] ;
        int increment = 0 ;
        for (int i = chocoTimeDebut  ; i <chocoTimeFin ; i++) {
            chocoTime[increment] = i;
            increment++;
        }

        return chocoTime ;
    }

    /**
     *
     * @return
     *  valeur du jour
     */
    public int getIntJour(){
        switch (jour){
            case "lundi" : return 0 ;


            case "mardi" : return 1 ;


            case "mercredi" : return 2 ;


            case "jeudi" : return 3 ;


            case "vendredi" : return 4 ;


            case "samedi" : return 5 ;


            case "dimanche" : return 6 ;


        }
        return  -1 ;
    }

    /**
     * @param jour the jour to set
     */
    public void setJour(String jour) {
        this.jour = jour;
    }
}
