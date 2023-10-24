package ar.edu.unlam.pb1.vivero;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;


public class TestCase {

	@Test
	public void queSeAgreguePlanta() throws PlantaDuplicadaException {
		Vivero vivero = new Vivero ("Nombre del Vivero");
		Planta plantita = new Hierba(001, "Pepe" , 500.0, 5);
		//-//
		try {
			vivero.agregarPlanta(plantita);
		} catch (PlantaDuplicadaException e) {
			throw new PlantaDuplicadaException(e.getMessage());
		}
		//-//
		assertFalse(vivero.getPlantas().isEmpty());
	}
	
	@Test  (expected = PlantaDuplicadaException.class)
	public void queNoSeAgreguePlanta() throws PlantaDuplicadaException {
		Vivero vivero = new Vivero ("Nombre del Vivero");
		Planta plantita = new Hierba(001, "Pepe" , 500.0, 5);
		Planta plantita2 = new Hierba(001, "Laura" , 500.0, 5);
		//-//
		try {
			vivero.agregarPlanta(plantita);
			vivero.agregarPlanta(plantita2);
		} catch (PlantaDuplicadaException e) {
			throw new PlantaDuplicadaException(e.getMessage());
		}
	}
	
	@Test
	public void queSePuedaVenderUnaPlanta() throws PlantaDuplicadaException, VentaException {
		Vivero vivero = new Vivero ("Nombre del Vivero");
		Planta plantita = new Hierba(001, "Pepe" , 500.0, 5);
		//-//
		try {
			vivero.agregarPlanta(plantita);
		} catch (PlantaDuplicadaException e) {
			throw new PlantaDuplicadaException(e.getMessage());
		}
		
		try {
			vivero.venderPlanta(001, 1);
		} catch (VentaException e) {
			throw new VentaException(e.getMessage());
		}
		
		//-//
		assertFalse(vivero.getVentas().isEmpty());
		assertEquals(4,vivero.buscarPlanta(001).getStock());
	}
	
	@Test (expected = VentaException.class)
	public void queNoSePuedaVenderUnaPlanta() throws PlantaDuplicadaException, VentaException {
		Vivero vivero = new Vivero ("Nombre del Vivero");
		Planta plantita = new Hierba(001, "Pepe" , 500.0, 5);
		//-//
		try {
			vivero.agregarPlanta(plantita);
		} catch (PlantaDuplicadaException e) {
			throw new PlantaDuplicadaException(e.getMessage());
		}
		
		try {
			vivero.venderPlanta(001, 6);
		} catch (VentaException e) {
			throw new VentaException(e.getMessage());
		}
		
		//-//
		assertFalse(vivero.getVentas().isEmpty());
		assertEquals(4,vivero.buscarPlanta(001).getStock());
	}
	
	@Test
	public void queSeObtengaReporteDePlantasAgrupadasPorTipo() throws PlantaDuplicadaException {
		Vivero vivero = new Vivero ("Nombre del Vivero");
		Planta plantita = new Hierba(001, "Pepe" , 500.0, 5);
		Planta plantita1 = new Arbol(002, "Lola" , 300.0, 2);
		Planta plantita11 = new Arbol(004, "Lola" , 600.0, 2);
		Planta plantita2 = new Arbusto(003, "Mili" , 200.0, 7);
		Planta plantita22 = new Arbusto(005, "Mili" , 100.0, 7);
		//-//
		try {
			vivero.agregarPlanta(plantita);
			vivero.agregarPlanta(plantita1);
			vivero.agregarPlanta(plantita11);
			vivero.agregarPlanta(plantita2);
			vivero.agregarPlanta(plantita22);
		} catch (PlantaDuplicadaException e) {
			throw new PlantaDuplicadaException(e.getMessage());
		}
		
		
		Map<String, List<Planta>> reporte = vivero.obtenerReporteDePlantasAgrupadasPorTipo();
		
		//-//
		
		assertEquals(3,reporte.size());
	}
	
	@Test
	public void queSeobtenganTodasLasVentasDeArbolesOrdenadosPorElValorTotalDeLaVenta() throws PlantaDuplicadaException, VentaException {
		Vivero vivero = new Vivero ("Nombre del Vivero");
		Planta plantita1 = new Arbol(002, "Lola" , 400.0, 2);
		Planta plantita2 = new Arbol(003, "Lola" , 1000.0, 2);
		Planta plantita3 = new Arbol(004, "Lola" , 2000.0, 2);
		//-//
		try {
			vivero.agregarPlanta(plantita3);
			vivero.agregarPlanta(plantita1);
			vivero.agregarPlanta(plantita2);
		} catch (PlantaDuplicadaException e) {
			throw new PlantaDuplicadaException(e.getMessage());
		}
		
		try {
			vivero.venderPlanta(002, 2);
			vivero.venderPlanta(003, 1);
			vivero.venderPlanta(004, 2);
		} catch (VentaException e) {
			throw new VentaException(e.getMessage());
		}
		
		Set<Venta> ventasArboles = vivero.obtenerTodasLasVentasDeArbolesOrdenadosPorElValorTotalDeLaVenta();
		//-//
		assertFalse(ventasArboles.isEmpty());
		assertEquals(3,ventasArboles.size());
	}
	
	@Test
	public void obtenerPrecioPlanta() throws PlantaDuplicadaException {
		Vivero vivero = new Vivero ("Nombre del Vivero");
		Planta plantita = new Hierba(001, "Pepe" , 500.0, 1);
		Planta plantita1 = new Arbusto (002, "Pepe" , 100.0, 1);
		Planta plantita2 = new Hierba(003, "Pepe" , 1000.0, 1);
		Planta plantita3 = new Arbusto (004, "Pepe" , 200.0, 1);
		
		//-//
		try {
			vivero.agregarPlanta(plantita);
			vivero.agregarPlanta(plantita1);
			vivero.agregarPlanta(plantita2);
			vivero.agregarPlanta(plantita3);
		} catch (PlantaDuplicadaException e) {
			throw new PlantaDuplicadaException(e.getMessage());
		}

		//-//
		assertEquals(((Double)600.0),plantita.obtenerPrecio());
		assertEquals(((Double)160.0),plantita1.obtenerPrecio());
		assertEquals(((Double)1200.0),plantita2.obtenerPrecio());
		assertEquals(((Double)320.0),plantita3.obtenerPrecio());
	}
	
	@Test
	public void obtenerPrecioFloralEnFloracion() throws PlantaDuplicadaException {
		Vivero vivero = new Vivero ("Nombre del Vivero");
		Planta plantita = new Arbol(001, "Pepe" , 500.0, 1);
		Planta plantita1 = new Arbol (002, "Pepe" , 100.0, 1);
		
		//-//
		try {
			vivero.agregarPlanta(plantita);
			vivero.agregarPlanta(plantita1);
		} catch (PlantaDuplicadaException e) {
			throw new PlantaDuplicadaException(e.getMessage());
		}
		
		((Arbol)plantita).florar();
		((Arbol)plantita).florar();
		((Arbol)plantita).florar();
		((Arbol)plantita).florar();
		
		((Arbol)plantita1).florar();
		((Arbol)plantita1).florar();

		//-//
		assertEquals(((Double)1247.75),plantita.obtenerPrecio(),0.02);
		assertEquals(((Double)247.25),plantita1.obtenerPrecio(),0.02);
	}
	
	@Test
	public void obtenerPrecioFloralEnProduccion() throws PlantaDuplicadaException {
		Vivero vivero = new Vivero ("Nombre del Vivero");
		Planta plantita = new Arbol(001, "Pepe" , 500.0, 1);
		Planta plantita1 = new Arbol (002, "Pepe" , 100.0, 1);
		
		//-//
		try {
			vivero.agregarPlanta(plantita);
			vivero.agregarPlanta(plantita1);
		} catch (PlantaDuplicadaException e) {
			throw new PlantaDuplicadaException(e.getMessage());
		}
		
		((Arbol)plantita).florar();
		((Arbol)plantita).florar();
		((Arbol)plantita).florar();
		((Arbol)plantita).florar();
		((Arbol)plantita).florar();
		
		((Arbol)plantita1).florar();
		((Arbol)plantita1).florar();
		((Arbol)plantita1).florar();
		((Arbol)plantita1).florar();
		((Arbol)plantita1).florar();
		
		((Arbol)plantita1).producirFrutos();
		((Arbol)plantita1).producirFrutos();

		//-//
		assertEquals(((Double)1265.0),plantita.obtenerPrecio(),0.1);
		assertEquals(((Double)252.9),plantita1.obtenerPrecio(),0.1);
	}
	
	@Test
	public void queSeObtenganTodasLasPlantasFlorales() throws PlantaDuplicadaException {
		Vivero vivero = new Vivero ("Nombre del Vivero");
		Planta plantita1 = new Arbol(002, "Lola" , 400.0, 2);
		Planta plantita2 = new Arbol(003, "Lola" , 1000.0, 2);
		Planta plantita = new Hierba(001, "Pepe" , 500.0, 5);
		//-//
		try {
			vivero.agregarPlanta(plantita);
			vivero.agregarPlanta(plantita1);
			vivero.agregarPlanta(plantita2);
		} catch (PlantaDuplicadaException e) {
			throw new PlantaDuplicadaException(e.getMessage());
		}
		
		List<Florales> florales = vivero.obtenerTodasLasPlantasFlorales();
		//-//
		assertFalse(florales.isEmpty());
		assertEquals(2,florales.size());
		assertTrue(florales.get(0)instanceof Florales);
	}
	
}
