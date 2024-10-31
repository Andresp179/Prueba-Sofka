package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Models.Movimientos;
import com.example.demo.Repository.MovimientosRepository;

@Service
public class MovimientoService {
	
	@Autowired
	private final MovimientosRepository movimientoRepository;
	
	public MovimientoService(MovimientosRepository movimientoRepository) {
		this.movimientoRepository=movimientoRepository;
	}
	
	public List<Movimientos> findAll(){return movimientoRepository.findAll();}
	public Movimientos findById(Long id) {return movimientoRepository.findById(id).orElse(null);}
	public Movimientos save(Movimientos movimientos) {return movimientoRepository.save(movimientos);}
	public void deleteById(Long id) {movimientoRepository.deleteById(id);}
}