package schedPack;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateEdt
 */
@WebServlet("/CreateEdt")
public class CreateEdt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VUE = "/WEB-INF/CreateEdt.jsp";
	public ArrayList<Bloc> listBloc = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateEdt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String stringBlocs = request.getParameter("stringBlocs");
		String[] blocs = stringBlocs.split(";");
		String[] strBlocTmp = null;
		Bloc blocTmp;
		ArrayList<Horaire> horaireTmp = new ArrayList<Horaire>();
		listBloc = new ArrayList<Bloc>();
		System.out.println("liste general : "+stringBlocs);

		for ( int i = 0 ; i < blocs.length ; i++){
			strBlocTmp = blocs[i].split("-");
			blocTmp = new Bloc();
			blocTmp.setNom(strBlocTmp[0]);
			blocTmp.setDuree(Double.parseDouble(strBlocTmp[1]));
			System.out.println("liste parsé "+i+" : "+blocs[i]);

			for (int j = 2 ; j < strBlocTmp.length ; j += 3){
				System.out.println(j+" strbloc : "+strBlocTmp[j]+" "+ strBlocTmp[j+1]+" "+strBlocTmp[j+2]);
				horaireTmp.add(new Horaire(strBlocTmp[j], strBlocTmp[j+1], strBlocTmp[j+2]));
			}
			blocTmp.setHoraires(horaireTmp);
			listBloc.add(blocTmp);
		}
		for (Bloc b : listBloc){
			//je recupere l'heure de je split dans hdeb avec hdeb[0] = heure et hdeb[1] = minutes 
			String [] hdeb = b.getHoraires().get(0).getHeureDebut().split("H"); 
			//je multiplie les heure par 60 pour les avoir en minutes et j'additionne les minutes => le totale en minutes !!
			int debutMinutes = Integer.parseInt(hdeb[0])*60 + Integer.parseInt(hdeb[1]);
			//de meme pour l'heur de fin 
			String [] hfin = b.getHoraires().get(0).getHeureDebut().split("H");
			int finMinutes = Integer.parseInt(hfin[0])*60 + Integer.parseInt(hfin[1]);
			Double duree = b.getDuree();
			if(debutMinutes + duree != finMinutes){
				//l'heure de debut + la durée et differente de l'heure de fin => bloc libre 
				// il fait faire la suite ici pour ajouter plusieur craineaux horraire
			}			
		}
		
		//1 = compacte  lunid
		//2 min cours
		//3 min cours vendredi
		
		System.out.println("apl ");

        SolverCalendrier solve = new SolverCalendrier(listBloc,1);
        solve.initContrainteChoco();
        solve.solveProbleme();
		System.out.println("ok ? ");

		

        
        //######################################################

	}
	/*
	public static void main(String[] args) {
		
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
    */

}
