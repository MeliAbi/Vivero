package ar.edu.unlam.pb1.vivero;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Vivero {

	private String nombre;
	private Set<Planta> plantas;
	private List<Venta> ventas;
	private Integer contadorTicket = 2000;

	public Vivero(String nombre) {
		this.nombre=nombre;
		ventas=new ArrayList<Venta>();
		plantas=new HashSet<Planta>();
	}
	
	
	public String getNombre() {
		return nombre;
	}


	public Set<Planta> getPlantas() {
		return plantas;
	}


	public List<Venta> getVentas() {
		return ventas;
	}


	public Boolean agregarPlanta(Planta planta) throws PlantaDuplicadaException {
		// No puede haber 2 plantas con el mismo Id , Si se duplica lanza una Exception
		// Planta Duplicada Exception
		Boolean resultado = plantas.add(planta);
		if(resultado) {
			return true;
		}else {
			throw new PlantaDuplicadaException("Ya existe una planta con este ID");
		}
	}
	
	public Planta buscarPlanta(Integer codigoPlanta) {
		
		for(Planta actual : plantas) {
			if(actual.getCodigo()==codigoPlanta) {
				return actual;
			}
		}
		
		return null;
	}
	
	public void modificarStock(Integer codigoPlanta, Integer cantidadAVender) {
		Integer cant = buscarPlanta(codigoPlanta).getStock();
		cant = cant-cantidadAVender;
		buscarPlanta(codigoPlanta).setStock(cant);
	}
	
	public void venderPlanta(Integer codigoPlanta, Integer cantidadAVender) throws VentaException {
		Planta local = buscarPlanta(codigoPlanta);
		
		if(local!=null) {
			if(local.getStock()>= cantidadAVender) {
				Venta nueva = new Venta(contadorTicket, cantidadAVender, local,local.obtenerPrecio());
				ventas.add(nueva);
				modificarStock(codigoPlanta,cantidadAVender);
				contadorTicket=contadorTicket+1;
			}else {
				throw new VentaException("No hay suficiente stock");
			}
			
		}else {
			;
		}
	}

	public Set<Venta> obtenerTodasLasVentasDeArbolesOrdenadosPorElValorTotalDeLaVenta() {
		/*
		 * Obtener un listado de todos los arboles vendidos ordenados por el total de
		 * venta (Cantidad * precioDeLaPlanta)
		 * 
		 */
		
		Set<Venta> ventasArboles = new TreeSet <Venta>(new OrdenarPorMontoTotal());
		
		for(Venta actual : ventas) {
			Planta local = actual.getPlanta();
			if(local instanceof Arbol) {
				ventasArboles.add(actual);
			}
		}
	
		return ventasArboles;
	}


	public Map<String, List<Planta>> obtenerReporteDePlantasAgrupadasPorTipo() {
		/*
		 * Obtener Un reporte de las plantas vendidas agrupados por tipo Plantas
		 * Ejemplo: para una key "arbol", debemos completar las plantas de este tipo
		 * 
		 */
		Map<String, List<Planta>> reporte = new TreeMap<String, List<Planta>>();
		List<Planta> arboles = new ArrayList<Planta>();
		List<Planta> arbustos = new ArrayList<Planta>();
		List<Planta> hierbas = new ArrayList<Planta>();
		
		
		for(Planta actual : plantas) {
			if(actual instanceof Arbol) {
				arboles.add(actual);
			}else if(actual instanceof Arbusto) {
				arbustos.add(actual);
			}else if(actual instanceof Hierba) {
				hierbas.add(actual);
			}
		}
		
		reporte.put("Arbol", arboles);
		reporte.put("Arbusto", arbustos);
		reporte.put("Hierba", hierbas);
		
		return reporte;
	}


	public List<Florales> obtenerTodasLasPlantasFlorales() {
		/**
		 * Obtener una lista de plantas que implementen la interfaz correspondiente
		 * */
		List<Florales> florales = new ArrayList<Florales>();
		
		for(Planta actual : plantas) {
			if(actual instanceof Florales) {
				florales.add(((Florales)actual));
			}
		}
		return florales;
	}
}
