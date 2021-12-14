package es.studium.hibernate;

import java.time.LocalDateTime;
import java.util.List;
import es.studium.hibernate.dao.PedidoDao;

public class Principal {
	public static void main(String[] args) {
		PedidoDao pedidoDao = new PedidoDao();
		
		Pedido pedido = new Pedido();
		pedido.setFecha(LocalDateTime.now());
		pedido.setReferencia("001");
		pedidoDao.save(pedido);
		
		List<Pedido> pedidos = pedidoDao.getAll();
		System.out.println("Lista de pedidos: " +
				pedidos);
	}
}



