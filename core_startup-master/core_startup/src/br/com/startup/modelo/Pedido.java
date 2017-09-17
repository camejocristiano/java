package br.com.startup.modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer mesa;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar data_hora;
	
	@ManyToMany
    @JoinTable(name="itens", joinColumns=
    {@JoinColumn(name="pedido_id")}, inverseJoinColumns=
      {@JoinColumn(name="cardapio_id")})
    private List<Cardapio> cardapios;
	
	@ManyToOne
	private Usuario usuario;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMesa() {
		return mesa;
	}

	public void setMesa(Integer mesa) {
		this.mesa = mesa;
	}
	public Calendar getData_hora() {
		return data_hora;
	}

	public void setData_hora(Calendar data_hora) {
		this.data_hora = data_hora;
	}
	
	public List<Cardapio> getCardapios() {
		return cardapios;
	}

	public void setCardapios(List<Cardapio> cardapios) {
		this.cardapios = cardapios;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	@Override
	public String toString(){
		List<Cardapio> cardapios = new ArrayList<Cardapio>();
		for (Cardapio cardapio : cardapios) {
			cardapios.add(cardapio);
		}
		return "id="+id+", mesa="+mesa+", cardapios="+cardapios+", usuario="+usuario;
	}
	
}
