package br.com.restaurantevilaprudente.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.restaurantevilaprudente.model.Cardapio;

public class ItensDAO {

	private Connection conn = ConectaMySQL.getConnection();

	// Método para buscar cardapio por id
	public List<Cardapio> buscarByIdPedido(Integer id) {
		String sql = "select * from cardapio RIGHT JOIN itens ON cardapio.id = itens.cardapio_id WHERE itens.pedido_id = ?";
		List<Cardapio> listaItens = new ArrayList<Cardapio>();

		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setInt(1, id);
			ResultSet resultado = preparador.executeQuery();
			while (resultado.next()) {
				Cardapio cardapio = new Cardapio();
				cardapio.setId(resultado.getInt("id"));
				cardapio.setTitulo(resultado.getString("titulo"));
				cardapio.setDescricao(resultado.getString("descricao"));
				cardapio.setValor(resultado.getBigDecimal("valor"));
				listaItens.add(cardapio);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar os cardapios. Mensagem: " + e.getMessage());
		}

		return listaItens;

	}// buscarById

}
