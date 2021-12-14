package es.studium.hibernate;


import java.time.LocalDateTime;
import es.studium.hibernate.dao.PedidoDao;
public class GestorPedidos {
	public static void main(String[] args) {
		/*Creamos un objeto de tipo PedidoDao*/
		PedidoDao pedidoDao = new PedidoDao();
		/*Creamos tres objetos de tipo Producto*/
		Producto libro = new Producto("libJava", "Manual Imprescindible Java");
				Producto cuaderno = new Producto("cuaRojo",
						"Cuaderno rojo");
		Producto lapiz = new Producto("lapHB", "Lápiz HB");
		/*Creamos un obeto de tipo Pedido y le añadimos los
Productos*/
		Pedido vueltaAlCole = new Pedido("153947",
				LocalDateTime.now());
		vueltaAlCole.addProducto(libro);
		vueltaAlCole.addProducto(cuaderno);
		vueltaAlCole.addProducto(lapiz);
		/*Guardamos el Pedido*/
		pedidoDao.save(vueltaAlCole);
		/*Generamos un Albaran y una Factura*/
		Albaran albaran = vueltaAlCole.generaAlbaran();
		Factura factura = vueltaAlCole.generaFactura();
		/*Mostramos los Pedidos*/
		System.out.println("Pedido:\n" + vueltaAlCole);
		
	}
}
