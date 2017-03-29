package schedPack;
import java.util.ArrayList;
import java.util.List;
public class test {

	/**
	 * Created by laplace_jordan on 12/03/17.
	 */
public static void main(String []args) {
	
	
        List<Bloc> bloc = new ArrayList<Bloc>() ;


        List<Horaire> toto = new ArrayList<Horaire>() ;
        toto.add(new Horaire("8H00" ,"8H10","vendredi"));
        bloc.add(new Bloc("test1" , 14.5 ,  toto) );

        List<Horaire> toto25 = new ArrayList<Horaire>() ;
        toto25.add(new Horaire("12H00" ,"18H50","vendredi"));
        bloc.add(new Bloc("test125" , 14.5 ,  toto25) );



        List<Horaire> toto2 = new ArrayList<Horaire>() ;
        toto2.add(new Horaire("4H5" ,"12H00","vendredi"));
        bloc.add(new Bloc("test2" , 14.5 ,  toto2) );

        List<Horaire> toto3 = new ArrayList<Horaire>() ;
        toto3.add(new Horaire("4H55" ,"20H00","lundi"));
        toto3.add(new Horaire("4H55" ,"20H00","mardi"));
        toto3.add(new Horaire("4H55" ,"20H00","mercredi"));
        toto3.add(new Horaire("4H55" ,"20H00","jeudi"));
        bloc.add(new Bloc("test666" , 14.5 ,  toto3) );

        List<Horaire> toto4 = new ArrayList<Horaire>() ;
        toto4.add(new Horaire("12H00" ,"15H00","dimanche"));
        bloc.add(new Bloc("test4" , 14.5 ,  toto4) );


        List<Horaire> toto5 = new ArrayList<Horaire>() ;
        toto5.add(new Horaire("4H55" ,"20H00","lundi"));
        toto5.add(new Horaire("4H55" ,"20H00","mardi"));
        toto5.add(new Horaire("4H55" ,"20H00","mercredi"));
        toto5.add(new Horaire("4H55" ,"20H00","jeudi"));
        bloc.add(new Bloc("test777" , 14.5 ,  toto5) );


        SolverCalendrier solve = new SolverCalendrier(bloc);

        solve.initContrainteChoco();
        solve.solveProbleme();


    }

}
