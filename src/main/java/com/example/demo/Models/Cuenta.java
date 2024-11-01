package com.example.demo.Models;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.Exceptions.SaldoInsuficienteException;

@Data
@Entity
@Table(name = "cuenta")
public class Cuenta implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private String numeroCuenta; // Clave única

	private String tipoCuenta;
	private double saldoInicial;
	private boolean estado;

	@OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL)
	private List<Movimientos> movimientos = new ArrayList<>();

	public Cuenta() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cuenta(Long id, String numeroCuenta, String tipoCuenta, double saldoInicial, boolean estado) {
		super();
		this.id = id;
		this.numeroCuenta = numeroCuenta;
		this.tipoCuenta = tipoCuenta;
		this.saldoInicial = saldoInicial;
		this.estado = estado;
	}

	public List<Movimientos> getMovimientos() {
		return movimientos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public double getSaldoInicial() {
		return saldoInicial;
	}

	public void setSaldoInicial(double saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	// Método para registrar un movimiento
	public void registrarMovimiento(String tipoMovimiento, double valor) {
		// Verificar si el movimiento es un débito y si hay saldo suficiente
		if (tipoMovimiento.equalsIgnoreCase("débito") && this.saldoInicial + valor < 0) {
			throw new SaldoInsuficienteException("Saldo no disponible");
		}

		// Actualizar saldo y registrar movimiento
		double nuevoSaldo = this.saldoInicial + valor;
		Movimientos movimiento = new Movimientos(tipoMovimiento, valor, nuevoSaldo, this);
		this.movimientos.add(movimiento);
		this.saldoInicial = nuevoSaldo;
	}

}
