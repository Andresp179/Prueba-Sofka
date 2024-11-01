package com.example.demo.Service;

import java.time.LocalDateTime;
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

	        Movimientos movimiento = new Movimientos();
	        movimiento.setCuenta(cuenta); // Asignar el objeto Cuenta
	        movimiento.setFecha(LocalDateTime.now());
	        movimiento.setTipoMovimiento(tipoMovimiento);
	        movimiento.setValor(valor);
	        
	        // Aquí puedes calcular y asignar el saldo si es necesario
	        movimiento.setSaldo(cuenta.getSaldoInicial() + valor); // Por ejemplo

	        return movimientoRepository.save(movimiento); // Guardar el movimiento
	    }
}
