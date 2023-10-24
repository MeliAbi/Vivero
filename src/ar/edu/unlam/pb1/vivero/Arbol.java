package ar.edu.unlam.pb1.vivero;

public class Arbol extends Planta implements Florales{

	private final double GANANCIA_ARBOL = 2.3;
	private Double estadoFloracion=0.0;
	private int madurezFrutos=0;

	public Arbol(int codigo, String nombre, double precio, int stock) {
		super(codigo,nombre, precio, stock);
	}

	@Override
	public Double obtenerPrecio() {
		/*	 * El precio del arbol se incrementa de acuerdo al avance de la floracion:
	 * - Menos de 33% de floracion incrementa un 5% su precio. 
	 * - Entre 34% y 66% incrementa un 7.5% su precio.
	 * - Mas del 66% y menos de 100% incrementa un 8.5% su precio.
	 * - Cuando el estado de floracion llega al 100%, se comienza la produccion de frutos
	 * - Cuando un arbol produce frutos, su precio aumenta 10% inicialmente (por tener el estado de floracion al 100%) y
	 * - agrega al precio, el porcentaje de madurez. Ejemplo: precioBase = 100 + 10% (por floracion) + 3% (madurez actual de los frutos)
	 * */
		Double precioFinal=precioBase;
		
		if(estadoFloracion<=0.33) {
			precioFinal=precioFinal+(precioFinal*0.05);
		}else if(estadoFloracion>0.33 && estadoFloracion<=0.66) {
			precioFinal=precioFinal+(precioFinal*0.075);
		}else if(estadoFloracion>0.66 && estadoFloracion<1) {
			precioFinal=precioFinal+(precioFinal*0.085);
		}else if(estadoFloracion==1&&madurezFrutos!=0) {
			precioFinal=precioFinal+(precioFinal*0.1)+(precioFinal*(madurezFrutos/10));
		}
		return precioFinal*GANANCIA_ARBOL;
	}

	@Override
	public void florar() {
		/**
		 * Los arboles pueden dar flores, las que posteriormente se convertiran en frutos. 
		 * Las flores tienen un rango de crecimiento, siendo 0 (el valor inicial) cuando no tiene flores aun y 100 cuando ya estan aptas para dar frutos.
		 */
		if(estadoFloracion<1) {
			estadoFloracion=estadoFloracion+0.2;
		}

		if(estadoFloracion==1 && madurezFrutos==0) {
			producirFrutos();
		}
				
	}

	@Override
	public void producirFrutos() {
		/**
		 * Para poder producir frutos, el estado de floracion debe ser 100%.
		 * La produccion de frutos se mide entre 1 y 5 siendo 5 el mejor escenario.
		
		 * */
		if(estadoFloracion==1 && madurezFrutos<=5) {
			madurezFrutos=madurezFrutos+1;
		}
		
	}



}
