package com.krakedev.producto.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.krakedev.producto.entidades.Producto;
import com.krakedev.producto.servicios.ProductoServicio;

/*
 * Clase de pruebas unitarias.
 * Verifica funcionamiento del CRUD.
 * 
 	guardar correctamente
	evitar duplicados
	buscar existente
	buscar inexistente
	actualizar existente
	actualizar inexistente
	eliminar existente
	eliminar inexistente
	listar productos
	validar stock
 */
public class ProductoServicioTestV2 {

	/*
	 * Prueba para guardar productos.
	 */
	@Test
	public void testGuardar() {

		ProductoServicio servicio = new ProductoServicio();

		Producto producto = new Producto("001", "Laptop", 2500, 10);

		Producto resultado = servicio.guardar(producto);

		assertNotNull(resultado);

		assertEquals("001", resultado.getCodigo());

		assertEquals(10, resultado.getStock());
	}

	/*
	 * Prueba para evitar productos duplicados.
	 */
	@Test
	public void testGuardarDuplicado() {

		ProductoServicio servicio = new ProductoServicio();

		Producto p1 = new Producto("001", "Laptop", 2500, 10);

		Producto p2 = new Producto("001", "Laptop Pro", 3000, 20);

		servicio.guardar(p1);

		Producto resultado = servicio.guardar(p2);

		assertNull(resultado);
	}

	/*
	 * Prueba para buscar productos.
	 */
	@Test
	public void testBuscarPorCodigo() {

		ProductoServicio servicio = new ProductoServicio();

		Producto producto = new Producto("002", "Mouse", 50, 15);

		servicio.guardar(producto);

		Producto encontrado = servicio.buscarPorCodigo("002");

		assertNotNull(encontrado);

		assertEquals("Mouse", encontrado.getNombre());

		assertEquals(15, encontrado.getStock());
	}

	/*
	 * Prueba búsqueda inexistente.
	 */
	@Test
	public void testBuscarNoExistente() {

		ProductoServicio servicio = new ProductoServicio();

		Producto encontrado = servicio.buscarPorCodigo("999");

		assertNull(encontrado);
	}

	/*
	 * Prueba para actualizar productos.
	 */
	@Test
	public void testActualizar() {

		ProductoServicio servicio = new ProductoServicio();

		Producto producto = new Producto("003", "Teclado", 100, 5);

		servicio.guardar(producto);

		Producto nuevo = new Producto("003", "Teclado Gamer", 150, 20);

		Producto actualizado = servicio.actualizar("003", nuevo);

		assertNotNull(actualizado);

		assertEquals("Teclado Gamer", actualizado.getNombre());

		assertEquals(150, actualizado.getPrecio());

		assertEquals(20, actualizado.getStock());
	}

	/*
	 * Prueba actualización inexistente.
	 */
	@Test
	public void testActualizarNoExistente() {

		ProductoServicio servicio = new ProductoServicio();

		Producto nuevo = new Producto("010", "Tablet", 500, 8);

		Producto actualizado = servicio.actualizar("010", nuevo);

		assertNull(actualizado);
	}

	/*
	 * Prueba para eliminar productos.
	 */
	@Test
	public void testEliminar() {

		ProductoServicio servicio = new ProductoServicio();

		Producto producto = new Producto("004", "Monitor", 800, 3);

		servicio.guardar(producto);

		boolean eliminado = servicio.eliminar("004");

		assertTrue(eliminado);
	}

	/*
	 * Prueba eliminar inexistente.
	 */
	@Test
	public void testEliminarNoExistente() {

		ProductoServicio servicio = new ProductoServicio();

		boolean eliminado = servicio.eliminar("999");

		assertFalse(eliminado);
	}

	/*
	 * Prueba listar productos.
	 */
	@Test
	public void testListar() {

		ProductoServicio servicio = new ProductoServicio();

		servicio.guardar(new Producto("001", "Laptop", 2500, 10));

		servicio.guardar(new Producto("002", "Mouse", 50, 20));

		List<Producto> lista = servicio.listar();

		assertEquals(2, lista.size());
	}
}