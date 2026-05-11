package com.krakedev.producto.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.krakedev.producto.entidades.Producto;

/*
 * Clase de servicio.
 * Contiene toda la lógica del CRUD.
 */
@Service
public class ProductoServicio {

	/*
	 * Lista en memoria donde se almacenan los productos.
	 */
	private List<Producto> productos = new ArrayList<>();

	/*
	 * Método para guardar un producto.
	 * Valida que no exista un código repetido.
	 * Si existe retorna null.
	 */
	public Producto guardar(Producto producto) {

		/*
		 * Busca si ya existe un producto
		 * con el mismo código.
		 */
		Producto encontrado = buscarPorCodigo(producto.getCodigo());

		/*
		 * Si el producto existe
		 * no realiza el registro.
		 */
		if (encontrado != null) {

			return null;
		}

		/*
		 * Si no existe,
		 * guarda el producto.
		 */
		productos.add(producto);

		// Retorna el producto guardado
		return producto;
	}

	/*
	 * Método para listar todos los productos.
	 */
	public List<Producto> listar() {

		return productos;
	}

	/*
	 * Método para buscar un producto por código.
	 */
	public Producto buscarPorCodigo(String codigo) {

		// Recorre todos los productos
		for (Producto p : productos) {

			// Verifica coincidencia del código
			if (p.getCodigo().equals(codigo)) {

				// Retorna el producto encontrado
				return p;
			}
		}

		// Si no existe retorna null
		return null;
	}

	/*
	 * Método para actualizar un producto.
	 */
	public Producto actualizar(String codigo, Producto productoActualizado) {

		// Busca el producto existente
		Producto encontrado = buscarPorCodigo(codigo);

		/*
		 * Si el producto no existe
		 * retorna null.
		 */
		if (encontrado == null) {

			return null;
		}

		// Actualiza nombre
		encontrado.setNombre(productoActualizado.getNombre());

		// Actualiza precio
		encontrado.setPrecio(productoActualizado.getPrecio());

		// Retorna producto actualizado
		return encontrado;
	}

	/*
	 * Método para eliminar un producto.
	 */
	public boolean eliminar(String codigo) {

		// Busca el producto
		Producto encontrado = buscarPorCodigo(codigo);

		/*
		 * Si no existe
		 * retorna false.
		 */
		if (encontrado == null) {

			return false;
		}

		// Elimina el producto
		productos.remove(encontrado);

		// Retorna true si eliminó correctamente
		return true;
	}
}