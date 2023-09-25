package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import models.Huesped;
import models.Reserva;

public class Huesped_DAO {
	
private Connection connection;
	
	public Huesped_DAO(Connection connection) {
		this.connection = connection;
	}
	private void transformarResultSetEnReserva(List<Huesped> listHuespedes, PreparedStatement pstm) throws SQLException {
		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				Huesped huesped = new Huesped(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getDate(4), rst.getString(5), rst.getString(6), rst.getInt(7));
				
				listHuespedes.add(huesped);
			}
		}
	}
	
	public void guardar(Huesped huesped) {
		try {
			String sql = "INSERT INTO huespedes (nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva) VALUES (?, ?, ?, ?, ?, ?)";

			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				java.util.Date utilDate = huesped.getFechaNacimiento();
				java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
				
				pstm.setString(1, huesped.getNombre());
				pstm.setString(2, huesped.getApellido());
				pstm.setDate(3,  sqlDate);
				pstm.setString(4, huesped.getNacionalidad());
				pstm.setString(5, huesped.getTelefono());
				pstm.setInt(6, huesped.getIdReserva());

				pstm.executeUpdate();

				try (ResultSet rst = pstm.getGeneratedKeys()) {
					while (rst.next()) {
						huesped.setId(rst.getInt(1));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public List<Huesped> listar() {
		List<Huesped> listHuespedes = new ArrayList<Huesped>();
		try {
			String sql = "SELECT id, nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva FROM huespedes";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();

				transformarResultSetEnReserva(listHuespedes, pstm);
			}
			return listHuespedes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public Huesped buscarId(int id) {
		Huesped resultado = new Huesped();

        try {
            String sql = "SELECT id, nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva FROM huespedes WHERE id = ?";
            
            final PreparedStatement statement = connection.prepareStatement(sql);
    
            try (statement) {
                statement.setInt(1, id);
                statement.execute();
    
                final ResultSet resultSet = statement.getResultSet();
    
                try (resultSet) {
                    while (resultSet.next()) {
                        resultado = (new Huesped(
                                resultSet.getInt("ID"),
                                resultSet.getString("nombre"),
                                resultSet.getString("apellido"),
                                resultSet.getDate("fechaNacimiento"),
                                resultSet.getString("nacionalidad"),
                                resultSet.getString("telefono"),
                                resultSet.getInt("idReserva")
                        		));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultado;
	}
	
	public int Eliminar(int id) {
		try {
            final PreparedStatement statement = connection.prepareStatement("DELETE FROM huespedes WHERE ID = ?");

            try (statement) {
                statement.setInt(1, id);
                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
		
	}
	public int actualizar(Huesped huespedEditado) {

		try {
            final PreparedStatement statement = connection.prepareStatement(
            		"UPDATE huespedes SET nombre = ?, apellido = ?, fechaNacimiento = ?, nacionalidad = ?, telefono = ?, idReserva = ? WHERE id = ?");

            try (statement) {
            	// Convertir las fechas de java.util.Date a java.sql.Date
                java.sql.Date fechaNacSql = new java.sql.Date(huespedEditado.getFechaNacimiento().getTime());
                
                statement.setString(1, huespedEditado.getNombre());
                statement.setString(2, huespedEditado.getApellido());
                statement.setDate(3, fechaNacSql);
                statement.setString(4, huespedEditado.getNacionalidad());
                statement.setString(5, huespedEditado.getTelefono());
                statement.setInt(6, huespedEditado.getIdReserva());
                statement.setInt(7, huespedEditado.getId());
                statement.execute();

                int updateCount = statement.getUpdateCount();
                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
		
	}

	

}
