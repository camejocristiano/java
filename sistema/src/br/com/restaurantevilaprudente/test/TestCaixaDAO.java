package br.com.restaurantevilaprudente.test;

import java.util.Calendar;
import java.util.List;

import br.com.restaurantevilaprudente.dao.CaixaDAO;
import br.com.restaurantevilaprudente.model.Caixa;

public class TestCaixaDAO {

	public static void main(String [] args) {
		testCadastrar();
		testSomarCaixa();
		testListarTodos();
		testListarDias();
		testbuscarByLastId();
	}
	
	private static void testCadastrar() {
		Caixa caixa = new Caixa();
		caixa.setUsuario(1);

		Calendar c = Calendar.getInstance();
		
		caixa.setDia(c.get(Calendar.DAY_OF_MONTH));
		int mes = c.get(Calendar.MONTH);
		caixa.setMes(mes + 1);
		caixa.setAno(c.get(Calendar.YEAR));
		
		CaixaDAO caixaDao = new CaixaDAO();
		caixaDao.cadastrar(caixa);
	}//cadastrar
	
	private static void testSomarCaixa() {
		CaixaDAO caixaDao = new CaixaDAO();
		caixaDao.somarCaixa();
	}//cadastrar	
	
	private static void testListarTodos(){
		CaixaDAO caixaDao = new CaixaDAO();
		List<Caixa> listarResultados = caixaDao.listarTodos();
		for(Caixa c: listarResultados){
			int dia = c.getAno();
			int mes = c.getMes();
			int ano = c.getDia();
			System.out.println(dia +"/"+ mes +"/"+ ano);
		}
	}
	
	private static void testListarDias(){
		CaixaDAO caixaDao = new CaixaDAO();
		List<Caixa> listarResultados = caixaDao.listarTodos();
		for(Caixa c: listarResultados){
			int dia = c.getDia();
			System.out.println(dia);
		}
	}
	
	private static void testbuscarByLastId(){
		CaixaDAO caixaDao = new CaixaDAO();
		Caixa caixa = caixaDao.buscarByLastId();
		System.out.println("Caixa: " + caixa.getValor() + " \n" + "Pedidos = " + caixa.getPedidos());
	}
	
}
