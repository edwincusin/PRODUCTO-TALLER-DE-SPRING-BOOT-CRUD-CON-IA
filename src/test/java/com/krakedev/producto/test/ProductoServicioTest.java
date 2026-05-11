package com.krakedev.producto.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.krakedev.producto.entidades.Producto;
import com.krakedev.producto.servicios.ProductoServicio;

/*
 * Clase de pruebas unitarias.
 * Verifica funcionamiento del CRUD.
 */
public class ProductoServicioTest {

	/*
	 * Prueba para guardar productos.
	 */
	@Test
	public void testGuardar() {

		// Crea instancia del servicio
		ProductoServicio servicio = new ProductoServicio();

		// Crea producto
		Producto producto = new Producto("001", "Laptop", 2500);

		// Guarda producto
		Producto resultado = servicio.guardar(producto);

		// Verifica resultado
		assertNotNull(resultado);

		// Verifica código
		assertEquals("001", resultado.getCodigo());
	}


	/*
	 * Prueba para buscar productos.
	 */
	@Test
	public void testBuscarPorCodigo() {

		ProductoServicio servicio = new ProductoServicio();

		Producto producto = new Producto("002", "Mouse", 50);

		servicio.guardar(producto);

		// Busca producto
		Producto encontrado = servicio.buscarPorCodigo("002");

		// Verifica existencia
		assertNotNull(encontrado);

		// Verifica nombre
		assertEquals("Mouse", encontrado.getNombre());
	}

	/*
	 * Prueba para actualizar productos.
	 */
	@Test
	public void testActualizar() {

		ProductoServicio servicio = new ProductoServicio();

		Producto producto = new Producto("003", "Teclado", 100);

		servicio.guardar(producto);

		// Datos actualizados
		Producto nuevo = new Producto("003", "Teclado Gamer", 150);

		// Actualiza producto
		Producto actualizado = servicio.actualizar("003", nuevo);

		// Verifica cambios
		assertEquals("Teclado Gamer", actualizado.getNombre());

		assertEquals(150, actualizado.getPrecio());
	}

	/*
	 * Prueba para eliminar productos.
	 */
	@Test
	public void testEliminar() {

		ProductoServicio servicio = new ProductoServicio();

		Producto producto = new Producto("004", "Monitor", 800);

		servicio.guardar(producto);

		// Ejecuta eliminación
		boolean eliminado = servicio.eliminar("004");

		// Verifica eliminación
		assertTrue(eliminado);
	}
}