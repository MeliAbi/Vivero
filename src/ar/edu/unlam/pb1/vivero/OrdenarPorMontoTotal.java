package ar.edu.unlam.pb1.vivero;

import java.util.Comparator;

public class OrdenarPorMontoTotal implements Comparator<Venta> {

	@Override
	public int compare(Venta o1, Venta o2) {
		return o2.obtenerMontoTotal().compareTo(o1.obtenerMontoTotal());
	}

}
