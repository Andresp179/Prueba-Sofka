package com.example.demo.Models;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long clienteId;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "persona_id", referencedColumnName = "id")
	private Persona persona;

	private String contraseña;
	private boolean estado;

	@Override
	public String toString() {
		return "Cliente [clienteId=" + clienteId + ", persona=" + persona + ", contraseña=" + contraseña + ", estado="
				+ estado + "]";
	}

	public Cliente(Long clienteId, Persona persona, String contraseña, boolean estado) {
		super();
		this.clienteId = clienteId;
		this.persona = persona;
		this.contraseña = contraseña;
		this.estado = estado;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	// Constructores
	public Cliente() {
	}

}
