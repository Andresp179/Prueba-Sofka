package com.example.demo.Models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "movimientos")
public class Movimientos implements Serializable {

	private static final long serialVersionUID = 1L;

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long movimientoId; // Clave única (PK)

	private LocalDateTime fecha;

	@Column(length = 20)
	private String tipoMovimiento;

	private BigDecimal valor;
	private BigDecimal saldo;

	@Override
	public String toString() {
		return "Movimientos [movimientoId=" + movimientoId + ", fecha=" + fecha + ", tipoMovimiento=" + tipoMovimiento
				+ ", valor=" + valor + ", saldo=" + saldo + "]";
	}

	public Movimientos(Long movimientoId, LocalDateTime fecha, String tipoMovimiento, BigDecimal valor,
			BigDecimal saldo) {
		super();
		this.movimientoId = movimientoId;
		this.fecha = fecha;
		this.tipoMovimiento = tipoMovimiento;
		this.valor = valor;
		this.saldo = saldo;
	}

	public Long getMovimientoId() {
		return movimientoId;
	}

	public void setMovimientoId(Long movimientoId) {
		this.movimientoId = movimientoId;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	// Constructores
	public Movimientos() {
	}

}
