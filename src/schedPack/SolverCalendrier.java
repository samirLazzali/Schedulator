package schedPack;



import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.variables.SetVar;

/**
 * Created by laplace_jordan on 12/03/17.
 */
public class SolverCalendrier {
    private static List<Bloc> bloc;
    private Model model;
    private static SetVar[][] contrainteVariable ;
    private SetVar[] contrainteStatic ;
    private int idPreference ;
    private final static String cheminFichier = "/home/mcgregor/git/Schedulator/WebContent/test.txt";
    /*
    idPreference = 0 == random pour les hommes
    idPreference = 1 == moins de troue possible
    idPreference = 2 == pas cours le lundi matin
    idPreference = 3 == pas cours le vendredi apres midi

     */



    public SolverCalendrier(List<Bloc> bloc , int idPreference){
        this.bloc = bloc ;
        this.idPreference = idPreference ;
        this.model= new Model();
    }

    public SolverCalendrier(List<Bloc> bloc){
        this.bloc = bloc ;
        this.idPreference = 0 ;
        this.model= new Model("A first model");

    }

    /**
     * toujours initilialisée les contrainte avant de demander les reponse a choco ;
     */
    public void initContrainteChoco(){
        int nbVariable = 0 ;


        for (int i = 0; i <bloc.size() ; i++) {
            if (bloc.get(i).isVariable())
                nbVariable++ ;
        }
        contrainteStatic = new SetVar[bloc.size() - nbVariable] ;
        contrainteVariable = new SetVar[nbVariable][] ;

        int incrementVar = 0 ;
        int incrementStatic = 0 ;
        for (int j = 0; j  <bloc.size() ; j++) {
            if (bloc.get(j).isVariable()){
                addContrainteVariable(bloc.get(j) , incrementVar);
                incrementVar++;
            }
            else {
                contrainteStatic[incrementStatic] = model.setVar(bloc.get(j).nom , bloc.get(j).getHoraires().get(0).getChocoTime());
                incrementStatic++;
            }
        }

        //ajout des contraintes disjoint pour les static
        for (int k = 0; k <contrainteStatic.length ; k++) {
            for (int l = 0; l <contrainteVariable.length ; l++) {
                for (int m = 0; m < contrainteVariable[l].length; m++) {
                    model.allDisjoint(contrainteStatic[k] ,contrainteVariable[l][m]).post();
                }
            }
        }

        //ajout des contraites disjoint entre les variables
        for (int n = 0; n <contrainteVariable.length ; n++) {
            for (int o = 0; o < contrainteVariable[n].length; o++) {
                for (int p = 0; p <contrainteVariable.length ; p++) {
                    for (int q = 0; q < contrainteVariable[p].length; q++) {
                        if (n == p && q == o){

                        }else{
                            model.allDisjoint(contrainteVariable[n][o] ,contrainteVariable[p][q]).post();
                        }
                    }
                }
            }
        }
    }

    /***
     * toujours solver apres avoir init les contrainstes
     */
    public void solveProbleme(){
        int i = 0 ;
        Solver solver =   model.getSolver();

        List<Solution> listeSolution = new ArrayList<Solution>();

        while(solver.solve()){
            i++ ;
            Solution solutionStock = new Solution() ;

            for (int r = 0; r < contrainteStatic.length ; r++) {
                if (  contrainteStatic[r].getValue().toArray().length > 1){
                   solutionStock.setCalendier( contrainteStatic[r].getValue().toArray() , contrainteStatic[r].getName() );
                    //System.out.println(contrainteStatic[r].getValue() );
                }
            }
            for (int ii = 0; ii < contrainteVariable.length ; ii++) {
                for (int j = 0; j <contrainteVariable[ii].length ; j++) {
                    if (  contrainteVariable[ii][j].getValue().toArray().length >1 ){
                        solutionStock.setCalendier(contrainteVariable[ii][j].getValue().toArray() , contrainteVariable[ii][j].getName() );
                        //System.out.println(contrainteVariable[ii][j].getValue().toArray());
                    }
                }

            }
            listeSolution.add(solutionStock);
            System.out.println(solutionStock.toString());
        }

        //NB pour chaque indice plus il est petit plus il est bon

        switch (idPreference){
            case 1 : selectionIdUn(listeSolution);
                break ;
            case 2 : selectionIdDeux(listeSolution);
                break ;
            case 3 : selectionIdTrois(listeSolution);
                break ;
            default: break ;
        }
        ecrireLesReponses(listeSolution);

        System.out.println("total des solution : " + i);
    }

    public void ecrireLesReponses(List<Solution> maList){
        String strFinal = "" ;
        int nbReponseVoulu = 1 ;
        //add -1 ?
        System.out.println("total des solution : " + nbReponseVoulu);

        for (int i = 0; i < nbReponseVoulu; i++) {
            strFinal += maList.get(i).toString() + "###";
        }

        File f = new File (cheminFichier);

        try
        {
            FileWriter fw = new FileWriter (f);

            fw.write (String.valueOf (strFinal));

            fw.close();
        }
        catch (IOException exception)
        {
            System.out.println ("Erreur lors de la lecture : " + exception.getMessage());
        }
    }

    public  void selectionIdUn(List<Solution> maListe ) {
        for (Solution s : maListe){
            s.setEvaluationUn();
        }
        Solution stockChangement  ;
        for (int i = 0; i < maListe.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (maListe.get(j+1).getEvaluationUn() < maListe.get(j).getEvaluationUn() ){

                    stockChangement = maListe.get(j+1)  ;
                    maListe.set(j+1 ,maListe.get(j) )   ;
                    maListe.set(j ,stockChangement )   ;

                }
            }
        }
    }

    public  void selectionIdDeux(List<Solution> maListe ) {
        for (Solution s : maListe){
            s.setEvaluationdeux();
        }
        Solution stockChangement  ;
        for (int i = 0; i < maListe.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (maListe.get(j+1).getEvaluationdeux() < maListe.get(j).getEvaluationdeux() ){

                    stockChangement = maListe.get(j+1)  ;
                    maListe.set(j+1 ,maListe.get(j) )   ;
                    maListe.set(j ,stockChangement )   ;

                }
            }
        }
    }


    public  void selectionIdTrois(List<Solution> maListe ) {
        for (Solution s : maListe){
            s.setEvaluationTrois();
        }
        Solution stockChangement  ;
        for (int i = 0; i < maListe.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (maListe.get(j+1).getEvaluationtrois() < maListe.get(j).getEvaluationtrois() ){

                    stockChangement = maListe.get(j+1)  ;
                    maListe.set(j+1 ,maListe.get(j) )   ;
                    maListe.set(j ,stockChangement )   ;

                }
            }
        }
    }


/*
    public int[] setToTabInt(ISet monIset){

        String taleauIntString =monIset.


                String arr = "[1,2]";
        String[] items = arr.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s", "").split(",");

        int[] results = new int[items.length];

        for (int i = 0; i < items.length; i++) {
            try {
                results[i] = Integer.parseInt(items[i]);
            } catch (NumberFormatException nfe) {
                //NOTE: write something here if you need to recover from formatting errors
            };
        }


    }*/


/*
    public static void main(String []args) {
        bloc = new ArrayList<Bloc>();

        //initBloc();



        int nbVariable = 0 ;

        //
        for (int i = 0; i <bloc.size() ; i++) {
            if (bloc.get(i).isVariable())
            nbVariable++ ;
        }
        contrainteStatic = new SetVar[bloc.size() - nbVariable] ;
        contrainteVariable = new SetVar[nbVariable][] ;

        int incrementVar = 0 ;
        int incrementStatic = 0 ;
        for (int j = 0; j  <bloc.size() ; j++) {
            if (bloc.get(j).isVariable()){
                addContrainteVariable(bloc.get(j) , incrementVar);
                incrementVar++;
            }
            else {
                contrainteStatic[incrementStatic] = model.setVar(bloc.get(j).nom , bloc.get(j).getHoraires().get(0).getChocoTime());
                incrementStatic++;
            }
        }

        //ajout des contraintes disjoint pour les static
        for (int k = 0; k <contrainteStatic.length ; k++) {
            for (int l = 0; l <contrainteVariable.length ; l++) {
                for (int m = 0; m < contrainteVariable[l].length; m++) {
                    model.allDisjoint(contrainteStatic[k] ,contrainteVariable[l][m]).post();
                }
            }
        }

        //ajout des contraites disjoint entre les variables
        for (int n = 0; n <contrainteVariable.length ; n++) {
            for (int o = 0; o < contrainteVariable[n].length; o++) {
                for (int p = 0; p <contrainteVariable.length ; p++) {
                    for (int q = 0; q < contrainteVariable[p].length; q++) {
                        if (n == p && q == o){

                        }else{
                            model.allDisjoint(contrainteVariable[n][o] ,contrainteVariable[p][q]).post();
                        }
                    }
                }
            }
        }


        int i = 0 ;
        Solver solver =   model.getSolver();

        while(solver.solve()){
            i++ ;
            System.out.println("solution numéro : " + i);
            System.out.println("solution static : " );
            System.out.println(contrainteVariable[0][0].getValue());

            for (int r = 0; r < contrainteStatic.length ; r++) {
                if ( ! contrainteStatic[r].getValue().equals(new int[] {})){
                    System.out.println(contrainteStatic[r].getName() + "  est egale a : " +contrainteStatic[r].getValue() );
                }
            }


        }
        System.out.println("total des solution : " + i);




    }*/

    /**
     * ici on ajoute les contraites des horraire variable
     *
     * @param bloc
     *  bloc d'activité
     */
    public  void addContrainteVariable(Bloc bloc , int index){


        int nbHoraire = bloc.getHoraires().size() ;
        contrainteVariable[index] = new SetVar[nbHoraire];

        SetVar[][] horraireLimite = new SetVar[nbHoraire][2] ;

        //on initialise les valeur possible d'une activité
        for (int i = 0; i <nbHoraire; i++) {
            contrainteVariable[index][i] = model.setVar(bloc.getNom() ,new int[]{} ,bloc.getHoraires().get(i).getChocoTime()  );

            //cela va servir a dire a choco que l'activité peut soit être l'horraire de l'activité soit être null
            horraireLimite[i][0] = model.setVar("limite valeur du bloc"  ,bloc.getHoraires().get(i).getChocoTime());
            horraireLimite[i][1] = model.setVar("limite valeur null"  ,new int[]{});

        }
        SetVar[] valHoraireNull = new SetVar[1];
        valHoraireNull[0] = model.setVar("mathId4", new int[]{});

        // cela sert dit a choco  que l'activité peut soit être l'horraire de l'activité soit être null
        for (int j = 0; j <nbHoraire ; j++) {
            model.member(horraireLimite[j],contrainteVariable[index][j]).post();
        }

        model.nbEmpty(contrainteVariable[index] , nbHoraire-1).post();

    }

    /*
    //sert a tester les methodes
    public  void initBloc(){
         List<Horaire> toto = new ArrayList<Horaire>() ;
         toto.add(new Horaire("4H55" ,"18H5","lundi"));
         bloc.add(new Bloc("test1" , 14.5 ,  toto) );

        List<Horaire> toto2 = new ArrayList<Horaire>() ;
        toto2.add(new Horaire("4H5" ,"12H00","vendredi"));
        bloc.add(new Bloc("test2" , 14.5 ,  toto2) );

        List<Horaire> toto3 = new ArrayList<Horaire>() ;
        toto3.add(new Horaire("4H55" ,"23H10","lundi"));
        toto3.add(new Horaire("23H05" ,"23H10","mardi"));
        bloc.add(new Bloc("test3" , 14.5 ,  toto3) );

        List<Horaire> toto4 = new ArrayList<Horaire>() ;
        toto4.add(new Horaire("20H25" ,"22H10","dimanche"));
        bloc.add(new Bloc("test4" , 14.5 ,  toto4) );


    }*/

}