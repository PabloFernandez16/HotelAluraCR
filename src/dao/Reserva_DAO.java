package dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JOptionPane;

import models.Reserva;

public class Reserva_DAO {
	
private Connection connection;
	
	public Reserva_DAO(Connection connection) {
		this.connection = connection;
	}
	
	private void transformarResultSetEnReserva(List<Reserva> reservas, PreparedStatement pstm) throws SQLException {
		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				Reserva res = new Reserva(rst.getInt(1), rst.getDate(2), rst.getDate(3), rst.getString(4), rst.getString(5));
				
				reservas.add(res);
			}
		}
	}
	
	public void guardar(Reserva reserva) {
		try {
			String sql = "INSERT INTO reservas (fechaEntrada, fechaSalida, valor, formaPago) VALUES (?, ?, ?, ?)";

			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				pstm.setDate(1, (java.sql.Date) reserva.getfechaE());
				pstm.setDate(2, (java.sql.Date) reserva.getfechaS());
				pstm.setString(3, reserva.getvalor());
				pstm.setString(4, reserva.getformaPago());

				pstm.executeUpdate();

				try (ResultSet rst = pstm.getGeneratedKeys()) {
					while (rst.next()) {
						reserva.setId(rst.getInt(1));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	//SELECT MAX(id) AS ultimo_id FROM reservas;
	//Listar ultimo id resrvas
	public int listarIdUltimaReserva() {
		int ultimoId=0;
		ResultSet resultado = null;

		try {

            // Prepara la consulta SQL
            String consulta = "SELECT MAX(id) AS ultimo_id FROM reservas;";
            PreparedStatement statement = connection.prepareStatement(consulta);

            // Ejecuta la consulta
            resultado = statement.executeQuery();

            // Procesa el resultado
            if (resultado.next()) {
                ultimoId = resultado.getInt("ultimo_id");
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ultimoId;
    }

	
	//Listar reservas
	public List<Reserva> listar() {
		List<Reserva> reservas = new ArrayList<Reserva>();
		try {
			String sql = "SELECT id, fechaEntrada, fechaSalida, valor, formaPago FROM reservas";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();

				transformarResultSetEnReserva(reservas, pstm);
			}
			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Reserva buscarId(int id) {
		Reserva resultado = new Reserva();

        try {
            String sql = "SELECT id, fechaEntrada, fechaSalida, valor, formaPago"
            + " FROM reservas WHERE id = ?";
            
            final PreparedStatement statement = connection.prepareStatement(sql);
    
            try (statement) {
                statement.setInt(1, id);
                statement.execute();
    
                final ResultSet resultSet = statement.getResultSet();
    
                try (resultSet) {
                    while (resultSet.next()) {
                        resultado = (new Reserva(
                                resultSet.getInt("ID"),
                                resultSet.getDate("fechaEntrada"),
                                resultSet.getDate("fechaSalida"),
                                resultSet.getString("valor"),
                                resultSet.getString("formaPago")
                        		));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultado;
	}

	public int Actualizar(Reserva reserva) {
		
		try {
            final PreparedStatement statement = connection.prepareStatement(
            		"UPDATE reservas SET fechaEntrada = ?, fechaSalida = ?, valor = ?, formaPago = ? WHERE id = ?");

            try (statement) {
            	// Convertir las fechas de java.util.Date a java.sql.Date
                java.sql.Date fechaEntradaSql = new java.sql.Date(reserva.getfechaE().getTime());
                java.sql.Date fechaSalidaSql = new java.sql.Date(reserva.getfechaS().getTime());
                
                statement.setDate(1, fechaEntradaSql);
                statement.setDate(2, fechaSalidaSql);
                statement.setString(3, reserva.getvalor());
                statement.setString(4, reserva.getformaPago());
                statement.setInt(5, reserva.getId());
                statement.execute();

                int updateCount = statement.getUpdateCount();
                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
		
	}

	public int Eliminar(int id) {
		try {
            final PreparedStatement statement = connection.prepareStatement("DELETE FROM reservas WHERE ID = ?");
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


	
	
	

}
