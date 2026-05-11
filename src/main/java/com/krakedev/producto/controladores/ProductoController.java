package com.krakedev.producto.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.krakedev.producto.entidades.Producto;
import com.krakedev.producto.servicios.ProductoServicio;

/*
 * Clase controlador REST.
 * Maneja las peticiones HTTP.
 */
@RestController

/*
 * Ruta principal del controlador.
 */
@RequestMapping("/productos")
public class ProductoController {

	/*
	 * Inyección automática del servicio.
	 */
	@Autowired
	private ProductoServicio servicio;

	/*
	 * Endpoint para crear productos.
	 * Método HTTP: POST
	 */
	@PostMapping
	public Producto crear(@RequestBody Producto producto) {

		/*
		 * @RequestBody convierte el JSON
		 * en un objeto Producto.
		 */

		return servicio.guardar(producto);
	}

	/*
	 * Endpoint para listar productos.
	 * Método HTTP: GET
	 */
	@GetMapping
	public List<Producto> listar() {

		return servicio.listar();
	}

	/*
	 * Endpoint para buscar producto por código.
	 * Método HTTP: GET
	 */
	@GetMapping("/{codigo}")
	public Producto buscar(@PathVariable String codigo) {

		/*
		 * @PathVariable obtiene el valor
		 * enviado en la URL.
		 */

		return servicio.buscarPorCodigo(codigo);
	}

	/*
	 * Endpoint para actualizar un producto.
	 * Método HTTP: PUT
	 */
	@PutMapping("/{codigo}")
	public Producto actualizar(
			@PathVariable String codigo,
			@RequestBody Producto producto) {

		return servicio.actualizar(codigo, producto);
	}

	/*
	 * Endpoint para eliminar un producto.
	 * Método HTTP: DELETE
	 */
	@DeleteMapping("/{codigo}")
	public String eliminar(@PathVariable String codigo) {

		// Ejecuta eliminación
		boolean eliminado = servicio.eliminar(codigo);

		// Verifica resultado
		if (eliminado) {

			return "Producto eliminado";
		}

		// Mensaje cuando no existe
		return "Producto no encontrado";
	}
}