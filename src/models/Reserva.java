package models;


import java.util.Date;


public class Reserva {
	
	private int id;
	private Date fechaEntrada;
	private Date fechaSalida;
	private String valor;
	private String formaPago;
	
	
	public Reserva(Date fechaE, Date fechaS, String valor, String formaPago) {		
		this.fechaEntrada = fechaE;
		this.fechaSalida = fechaS;
		this.valor = valor;
		this.formaPago = formaPago;
	}
		
	public Reserva(int id, Date fechaE, Date fechaS, String valor, String formaPago) {
		this.id = id;
		this.fechaEntrada = fechaE;
		this.fechaSalida = fechaS;
		this.valor = valor;
		this.formaPago = formaPago;
	}
	
	public Reserva() {
		
	}
	
	public Reserva(Date date, Date date2) {
		this.fechaEntrada = date;
		this.fechaSalida = date2;
	}

	
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Date getfechaE() {
		return fechaEntrada;
	}

	public Date getfechaS() {
		return fechaSalida;
	}

	public String getvalor() {
		return valor;
	}

	public String getformaPago() {
		return formaPago;
	}


}
