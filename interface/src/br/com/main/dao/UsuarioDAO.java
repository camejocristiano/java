package br.com.main.dao;

import java.util.List;

import br.com.main.model.Usuario;

public interface UsuarioDAO {
	
	public void addUsuario(Usuario usuario);
	public Usuario getUsuarioById(int id);
	public void updateUsuario(Usuario usuario);
	public List<Usuario> listUsuarios();
	public void removeUsuario(int id);
	
}
