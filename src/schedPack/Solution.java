package schedPack;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by laplace_jordan on 19/03/17.
 */
public class Solution {
    private  List<String> nom ;
    private  List<int[]> calendrier ;
    private int   evaluationUn ;
    private int evaluationdeux ;
    private int evaluationtrois ;
    private int increment ;

    public Solution(){
        evaluationUn = -1 ;
        evaluationdeux = -1 ;
        evaluationtrois = -1 ;
        calendrier = new ArrayList<int[]>();
        nom = new ArrayList<String>();
    }

    public void setCalendier(int [] horraire , String nomMatiere){
        calendrier.add(horraire);
        nom.add(nomMatiere);
    }

    public  List<int[]> getCalendrier() {
        return calendrier;
    }

    /**
     * LES METHODES D'EVALUATION SONT A UTILISER APRES AVOIR SET TOUS LE CALENDRIER
     */

    //moins de troue possible
    public  void setEvaluationUn(){
        // on trie le tableau
        sort();

        /**
         * troue entre 8h et 20h
         */

        int débutEval = 8 * 12  -1;
        int finEval = 20 * 12 + 1  ;

        //valeur entre 20h et 8h que l'on ne va pas traitée
        int[] valeurInterval = new int[14];


        int incrementTab = 0 ;

        for (int i = 0; i < 7 ; i++) {
            valeurInterval[incrementTab] = débutEval + (288 * i);
            incrementTab++ ;

            valeurInterval[incrementTab] = finEval + (288 * i);
            incrementTab++ ;
        }

        //petmet de savoir dans quelle tranche horraire nous somme
        int incrementZoneHorraire = 0 ;

        // on va prendre le dernier terme du 1er element puis le dernier terme du second et calculer la difference
        //  on ajoutera cette difference a evalUN , plus evalUN sera grand , plus l'emploie du temps aurra de troue

        evaluationUn = 0 ;

        for (int j = 0; j <calendrier.size()-1 ; j++) {
            if (calendrier.get(j+1)[0] > valeurInterval[incrementZoneHorraire +1]){
                incrementZoneHorraire += 2;
                j--;
            }else{
                if (calendrier.get(j+1)[calendrier.get(j+1).length - 1] <= valeurInterval[incrementZoneHorraire +1] && calendrier.get(j+1)[calendrier.get(j+1).length - 1] >= valeurInterval[incrementZoneHorraire ] ){
                    if (calendrier.get(j)[0] <= valeurInterval[incrementZoneHorraire +1] && calendrier.get(j)[0] >= valeurInterval[incrementZoneHorraire ] ){
                        evaluationUn +=   calendrier.get(j+1)[0] -  calendrier.get(j)[calendrier.get(j).length - 1];
                        //System.out.println(j);
                    }
                }

            }
        }

        System.out.println(evaluationUn);
    }

    public  void setEvaluationdeux(){
        // on trie le tableau
        sort();

        /**
         * pas cour le lundi matin
         */

        int débutEval = 0 ;
        int finEval = 12 * 12  ;

        int increment  = 0  ;
        evaluationdeux = 0 ;

        for (int i = 0; i <calendrier.size()  ; i++) {
            if (calendrier.get(i)[0]> finEval){
                break;
            }
            while (calendrier.get(i)[increment] < finEval && calendrier.get(i)[increment] > débutEval ){
                evaluationdeux++ ;
                increment ++  ;
                if (calendrier.get(i).length - 1 < increment ) break ;
            }

            increment = 0;
        }
        System.out.println(evaluationdeux);

    }

    public  void setEvaluationTrois(){
        // on trie le tableau
        sort();

        /**
         * pas cour le lundi matin
         */

        int débutEval = 12 * 12 + (4 * 288) - 1 ;
        int finEval = 23 * 12 + (4 * 288) + 1 ;

        int increment  = 0  ;
        evaluationtrois = 0 ;

        for (int i = 0; i <calendrier.size()  ; i++) {
            if (calendrier.get(i)[0]> finEval){
                break;
            }
            while (calendrier.get(i)[increment] < finEval && calendrier.get(i)[increment] > débutEval ){
                evaluationtrois++ ;
                increment ++  ;
                if (calendrier.get(i).length - 1 < increment ) break ;
            }

            increment = 0;
        }
        System.out.println(evaluationtrois);

    }



    /**
     * on va trier par rapport au dernier element des listes
     */

    public  void sort() {
        int[] stockChangement  ;
        String nomStock ;
        for (int i = 0; i < calendrier.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (calendrier.get(j+1)[0] < calendrier.get(j)[0] ){

                    stockChangement = calendrier.get(j+1)  ;
                    calendrier.set(j+1 ,calendrier.get(j) )   ;
                    calendrier.set(j ,stockChangement )   ;

                    nomStock = nom.get(j+1)  ;
                    nom.set(j+1 ,nom.get(j) )   ;
                    nom.set(j ,nomStock )   ;
                }
            }
        }
    }

    public String toString(){
        String StringHorraire = "";

        for (int i = 0; i <nom.size() ; i++) {
            StringHorraire += nom.get(i) + ";" ;
            StringHorraire += converteHorraireDeb(calendrier.get(i)[0]) + ";" ;
            StringHorraire += converteHorraireFin(calendrier.get(i)[calendrier.get(i).length-1]) + ";" ;
            StringHorraire += String.valueOf(calendrier.get(i)[0]/288) + ";" ;
        }

        return StringHorraire ;
    }

    public String converteHorraireDeb(int horraire){
        String stringHorraire = "" ;

        int moduloDuJour = (horraire )  % 288 ;

        //nombre de tranche de 5 min dans une heure
        int heure =  moduloDuJour / 12 ;


        //TODO SA TENTE DES TRUCKS
        int minute =  moduloDuJour % 12 * 5   ;

        stringHorraire = String.valueOf(heure) + ':' +  String.valueOf(minute)  ;

        return stringHorraire ;


    }

    public String converteHorraireFin(int horraire){
        String stringHorraire = "" ;

        int moduloDuJour = (horraire +1  )  % 288 ;

        //nombre de tranche de 5 min dans une heure
        int heure =  moduloDuJour / 12 ;


        //TODO SA TENTE DES TRUCKS
        int minute =  moduloDuJour % 12 * 5   ;

        stringHorraire = String.valueOf(heure) + ':' +  String.valueOf(minute)  ;

        return stringHorraire ;


    }
    public int getEvaluationUn() {
        return evaluationUn;
    }



    public int getEvaluationdeux() {
        return evaluationdeux;
    }



    public int getEvaluationtrois() {
        return evaluationtrois;
    }



    /*
    public static void main(String []args) {
        Solution test = new Solution();

        test.setCalendier(new int[]{97,98,99} , "1");
        test.setCalendier(new int[]{99,100},"2");
        test.setCalendier(new int[]{1827},"3");
        System.out.println(test);


    }*/
}