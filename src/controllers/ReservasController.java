package controllers;


import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import dao.Reserva_DAO;
import factory.ConnectionFactory;
import models.Reserva;

public class ReservasController {
 private Reserva_DAO reservaDAO;
 
 public ReservasController() {
		Connection connection = new ConnectionFactory().recuperarConexion();
		this.reservaDAO = new Reserva_DAO(connection);
	}
 
	 public int buscarIdUltimaReserva() {
		return this.reservaDAO.listarIdUltimaReserva();
	}

	 public void guardar(Reserva reserva) {
			this.reservaDAO.guardar(reserva);
		}
	 
	 public List<Reserva> listar() {
	        return reservaDAO.listar();
	    }
	 
	 public Reserva buscarId(int id) {
			return this.reservaDAO.buscarId(id);
		}
	 
	 public void actualizar( Reserva reserva) {
			this.reservaDAO.Actualizar(reserva);
		}
	 
	public void Eliminar(int id) {
		this.reservaDAO.Eliminar(id);
	}
}
