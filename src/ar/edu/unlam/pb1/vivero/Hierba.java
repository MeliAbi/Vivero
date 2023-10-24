package ar.edu.unlam.pb1.vivero;

public class Hierba extends Planta{

	private final double gananciaHierbaMata = 1.2;

	public Hierba(int codigo, String nombre, double precio, int stock) {
		super(codigo,nombre, precio, stock);
	}
	
	public double getGananciaHierbaMata() {
		return gananciaHierbaMata;
	}

	@Override
	public Double obtenerPrecio() {
		return precioBase*gananciaHierbaMata;
	}
}
