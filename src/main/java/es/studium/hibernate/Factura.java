package es.studium.hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name = "factura")
public class Factura {
	private static final String PREFIJO = "FAC-";
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String numero;
	@OneToOne
	@JoinColumn
	private Pedido pedido;
	public Factura() {
	}
	public Factura(Pedido pedido) {
		this.numero = PREFIJO + pedido.getReferencia();
		this.pedido = pedido;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	@Override
	public String toString() {
		return "Factura [id=" + id + ", numero=" + numero + "]";
		}

}