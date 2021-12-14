package es.studium.hibernate;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name = "pedido")
public class Pedido {
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "referencia")
	private String referencia;
	@Column(name = "fecha")
	private LocalDateTime fecha;
	@OneToMany(mappedBy = "pedido", cascade =
			CascadeType.ALL)
	private List<Albaran> albaranes = new
	ArrayList<Albaran>();
	@OneToOne(mappedBy = "pedido")
	private Factura factura;
	@ManyToMany(mappedBy = "pedidos", cascade =
			CascadeType.ALL)
	private Set<Producto> productos = new
	HashSet<Producto>();
	public Pedido() {
		id = 0;
		referencia = "";

		fecha = LocalDateTime.now();
	}
	public Pedido(String referencia, LocalDateTime fecha) {
		this.referencia = referencia;
		this.fecha = fecha;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	public List<Albaran> getAlbaranes() {
		return albaranes;
	}
	public void setAlbaranes(List<Albaran> albaranes) {
		this.albaranes = albaranes;
	}
	public Factura getFactura() {
		return factura;
	}
	public void setFactura(Factura factura) {
		this.factura = factura;
	}
	public Set<Producto> getProductos() {
		return productos;
	}
	public void setProductos(Set<Producto> productos) {
		this.productos = productos;

	}
	public void addProducto(Producto producto) {
		productos.add(producto);
		if (!producto.getPedidos().contains(this)) {
			producto.addPedido(this);
		}
	}

	public Albaran generaAlbaran() {
		Albaran albaran = new Albaran();
		albaranes.add(albaran);
		return albaran;
	}
	public Factura generaFactura() {
		factura = new Factura(this);
		return factura;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", referencia=" + referencia 
				+ ", fecha=" + fecha + ", albaranes=" + albaranes + ",	factura=" + factura + ", productos=" + productos + "]";
	}

}

