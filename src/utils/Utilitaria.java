package utils;

import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

public class Utilitaria {
	
	public Utilitaria () {
		
	}
	
	//******************************************
	//Funcion que retorna como String cuantos dias se va hospedar el cliente 
		public String valorRes(Date fechaEntrada, Date fechaSalida) {
		return valorReserva(fechaEntrada, fechaSalida)+"";
	}
		//esta lo retorna como double
	private double valorReserva(Date fechaEntrada, Date fechaSalida) {
		double precio;
		// Convierte los objetos Date a objetos Calendar
	    Calendar calendarInicial = Calendar.getInstance();
	    calendarInicial.setTime(fechaEntrada);

	    Calendar calendarFinal = Calendar.getInstance();
	    calendarFinal.setTime(fechaSalida);
		
		// Calcula la diferencia entre las fechas en milisegundos
	    long diferenciaEnMillis = calendarFinal.getTimeInMillis() - calendarInicial.getTimeInMillis();

	    // Convierte la diferencia de milisegundos a días
	    long diasDiferencia = diferenciaEnMillis / (24 * 60 * 60 * 1000);

		precio = diasDiferencia * 100;
		
		return precio;
		
	}
	
	//funcion que valida que se haya selecionado una fecha de entrada antes que la de salida
	
	public boolean validaFechas(Date fechaEntrada, Date fechaSalida) {
		if (fechaEntrada.after(fechaSalida)) {
            JOptionPane.showMessageDialog(null, "La fecha de entrada debe ser anterior a la fecha de salida.");
            return false;
        }
		return true;
	}
	
	public Date obtenerFechaActual() {
		// Obtener la fecha actual
		Calendar calendario = Calendar.getInstance();
		Date fechaActual = calendario.getTime();
		return fechaActual;
	}
	
	public boolean validaFechaEntrada(Date fechaEntrada) {
		if (fechaEntrada.after(this.obtenerFechaActual())) {
            return false;
        }
		return true;
	}

}
