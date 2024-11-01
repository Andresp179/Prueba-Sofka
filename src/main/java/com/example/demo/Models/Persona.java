package com.example.demo.Models;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "persona")
public class Persona implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombre;
	private String genero;
	private int edad;
	private String identificacion;
	private String direccion;
	private String telefono;

	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + ", genero=" + genero + ", edad=" + edad
				+ ", identificacion=" + identificacion + ", direccion=" + direccion + ", telefono=" + telefono + "]";
	}

	public Persona(Long id, String nombre, String genero, int edad, String identificacion, String direccion,
			String telefono) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.genero = genero;
		this.edad = edad;
		this.identificacion = identificacion;
		this.direccion = direccion;
		this.telefono = telefono;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	// Constructores
	public Persona() {
	}

}
