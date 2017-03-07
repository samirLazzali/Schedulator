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
		for ( int i = 0 ; i < blocs.length ; i++){
			strBlocTmp = blocs[i].split("-");
			blocTmp = new Bloc();
			blocTmp.setNom(strBlocTmp[0]);
			blocTmp.setDuree(Double.parseDouble(strBlocTmp[1]));

			for (int j = 2 ; j < strBlocTmp.length ; j += 3){
				horaireTmp.add(new Horaire(strBlocTmp[j], strBlocTmp[j+1], strBlocTmp[j+2]));
			}
			blocTmp.setHoraires(horaireTmp);
			listBloc.add(blocTmp);
		}
		
	}

}
