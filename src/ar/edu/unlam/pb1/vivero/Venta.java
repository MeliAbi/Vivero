package ar.edu.unlam.pb1.vivero;

public class Venta implements Comparable<Venta>{

	private Integer id;
	private Integer unidades;
	private Planta planta;
	public Double precioUnitario; // Precio final de la planta al momento de realizar la venta
	
	public Venta(Integer id, Integer unidades, Planta planta, Double precioUnitario) {
		this.id=id;
		this.unidades=unidades;
		this.planta=planta;
		this.precioUnitario=precioUnitario;
	}

	public Integer getId() {
		return id;
	}

	public Integer getUnidades() {
		return unidades;
	}

	public Planta getPlanta() {
		return planta;
	}

	public Double getPrecioUnitario() {
		return precioUnitario;
	}
	
	public Double obtenerMontoTotal() {
		return precioUnitario*unidades;
	}
	
	public String toString() {
		return "ID: " + id + "\n" +
				"unidades: " + unidades + "\n" +
				"planta: " + planta + "\n" +
				"precioUnitario: " + precioUnitario + "\n";
	}

	public int compareTo(Venta o) {
		return o.obtenerMontoTotal().compareTo(obtenerMontoTotal());
	}
	
	
	
}
