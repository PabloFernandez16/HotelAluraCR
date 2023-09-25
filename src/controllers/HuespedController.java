package controllers;

import java.sql.Connection;
import java.util.List;

import javax.swing.JOptionPane;

import dao.Huesped_DAO;
import factory.ConnectionFactory;
import models.Huesped;
import models.Reserva;

public class HuespedController {
	
	private Huesped_DAO huespedDAO;
	 
	 public HuespedController() {
			Connection connection = new ConnectionFactory().recuperarConexion();
			this.huespedDAO = new Huesped_DAO(connection);
		}

		 public void guardar(Huesped huesped) {
			this.huespedDAO.guardar(huesped);
		}
		 
		public List<Huesped> listar() {
	        return huespedDAO.listar();
	    }
		
		public Huesped buscarId(int id) {
			return this.huespedDAO.buscarId(id);
		}

		public void Eliminar(int id) {
		this.huespedDAO.Eliminar(id);
		}


		public void actualizar(Huesped huespedEditado) {
			JOptionPane.showMessageDialog(null, huespedEditado.toString());
			this.huespedDAO.actualizar(huespedEditado);
		}
		
}
