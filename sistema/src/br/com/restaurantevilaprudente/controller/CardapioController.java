package br.com.restaurantevilaprudente.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

import br.com.restaurantevilaprudente.dao.CardapioDAO;
import br.com.restaurantevilaprudente.model.Cardapio;

/**
 * Servlet implementation class CardapioController
 */
@WebServlet("/cardapiocontroller.do")
public class CardapioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CardapioController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");
		CardapioDAO cardapioDao = new CardapioDAO();

		/**
		 * Excluir cardápio
		 */
		if (acao != null && acao.equals("excluir")) {
			String id = request.getParameter("id");
			Cardapio cardapio = new Cardapio();
			cardapio.setId(Integer.parseInt(id));
			cardapioDao.excluir(cardapio);
			response.sendRedirect("cardapiocontroller.do?acao=listar");
		}

		/**
		 * Alterar cardápio
		 */
		if (acao != null && acao.equals("alterar")) {
			String id = request.getParameter("id");
			Cardapio cardapio = cardapioDao.buscarById(Integer.parseInt(id));
			request.setAttribute("cardapio", cardapio);
			List<Cardapio> lista = cardapioDao.listarTodos();
			request.setAttribute("lista", lista);
			RequestDispatcher saida = request.getRequestDispatcher("cardapios.jsp");
			saida.forward(request, response);
		}

		/**
		 * Cadastrar cardápio
		 */
		if (acao != null && acao.equals("cadastrar")) {
			Cardapio cardapio = new Cardapio();
			cardapio.setId(0);
			cardapio.setTitulo("nome");
			cardapio.setDescricao("endereco");
			cardapio.setValor(new BigDecimal("valor"));
			request.setAttribute("cardapio", cardapio);
			RequestDispatcher saida = request.getRequestDispatcher("editarproduto.jsp");
			saida.forward(request, response);
		}

		/**
		 * Listar todos os cardápios
		 */
		if (acao != null && acao.equals("listar")) {
			List<Cardapio> lista = cardapioDao.listarTodos();
			request.setAttribute("lista", lista);
			CardapioDAO cardapioJson = new CardapioDAO();
			cardapioJson.gerarJeson();
			RequestDispatcher saida = request.getRequestDispatcher("cardapios.jsp");
			saida.forward(request, response);
		}

		/**
		 * Novo Cardápio
		 */
		if (acao != null && acao.equals("novo")) {
			RequestDispatcher saida = request.getRequestDispatcher("cardapio.jsp");
			saida.forward(request, response);
		}
		
		/**
		 * Listar Json
		 */
		if (acao != null && acao.equals("listarjson")) {
			JSONArray listaCardapios = new JSONArray(); //cria o array de objetos no padrao JSON

			for (Cardapio c: new CardapioDAO().listarTodos()){ //pegando os dados do DAO
				listaCardapios.put(new JSONObject(c)); //convertendo para JSONObject e adicionando ao JSONArray
			}

			response.setContentType("application/json");
			response.getWriter().write(listaCardapios.toString());
			}
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/**
		 * Recebe os dados do formulário
		 */
		String id = request.getParameter("id");
		String titulo = request.getParameter("titulo");
		String descricao = request.getParameter("descricao");
		String valor = request.getParameter("valor");

		/**
		 * Cria o objeto cardápio.
		 */
		Cardapio cardapio = new Cardapio();
		
		/**
		 * Confere se o Id do objeto cardápio está incluso,
		 * caso sim seta ao objeto,
		 * caso não seta o objeto sem id.
		 */
		if (id != null && id != "" && id != "0") {
			cardapio.setId(Integer.parseInt(id));
		}
		
		/**
		 * Seta os demais atributos do objeto cardápio.
		 */
		cardapio.setTitulo(titulo);
		cardapio.setDescricao(descricao);
		cardapio.setValor(new BigDecimal(valor));
		cardapio.setData(Calendar.getInstance());
		
		/**
		 * Salva o objeto cardápio.
		 */
		CardapioDAO cardapioDao = new CardapioDAO();
		cardapioDao.salvar(cardapio);
		
		
		
		CardapioDAO cardapioJson = new CardapioDAO();
		List<Cardapio> listarResultados = cardapioJson.listarTodos();
		int contador = 1;
		String json ="[";
		try { 
		for(Cardapio c: listarResultados){
			//System.out.println(c.getId() + " " + c.getTitulo() + " " +  c.getDescricao() + " " + c.getValor());
			Gson gson = new Gson();  
			 if(contador == 1){
			  json = json + gson.toJson(c); 
			  contador++;
			 } else {
			  json = json + ", " + gson.toJson(c);  
			 }
		}//for
		json = json + "]";
		String path = getServletContext().getRealPath("cardapios.json");
		//BufferedWriter out = new BufferedWriter(new FileWriter(path));
		  //write converted json data to a file named "CountryGSON.json"  
		   FileWriter writer = new FileWriter(path);  
		   writer.write(json);  
		   writer.close();  
		    
		  } catch (IOException e) {  
		   e.printStackTrace();  
		  }  
		     
		  System.out.println(json); 
		
		
		
		
		
		/**
		 * Retorna para a homepage.
		 */
		response.sendRedirect("cardapiocontroller.do?acao=listar");
	}

}
