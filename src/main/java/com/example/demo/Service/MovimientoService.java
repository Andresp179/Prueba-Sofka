package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Models.Cuenta;
import com.example.demo.Models.Movimientos;
import com.example.demo.Repository.CuentaRepository;
import com.example.demo.Repository.MovimientosRepository;

@Service
public class MovimientoService {
	
	@Autowired
	private final MovimientosRepository movimientoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;
	
	public MovimientoService(MovimientosRepository movimientoRepository) {
		this.movimientoRepository=movimientoRepository;
	}
	
	public List<Movimientos> findAll(){return movimientoRepository.findAll();}
	public Movimientos findById(Long id) {return movimientoRepository.findById(id).orElse(null);}
	public Movimientos save(Movimientos movimientos) {return movimientoRepository.save(movimientos);}
	public void deleteById(Long id) {movimientoRepository.deleteById(id);}
	
	 public Movimientos registrarMovimiento(Long cuentaId, String tipoMovimiento, double valor) {
	        Cuenta cuenta = cuentaRepository.findById(cuentaId)
	                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

	        // Registrar el movimiento y actualizar el saldo
	        cuenta.registrarMovimiento(tipoMovimiento, valor);
	        cuentaRepository.save(cuenta);

	        // Obtener el último movimiento registrado para retornar
	        return cuenta.getMovimientos().get(cuenta.getMovimientos().size() - 1);
	    }
}
