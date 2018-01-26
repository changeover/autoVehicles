package grid.impl;
/**
 * Small class which defines a Source
 * @author Kevin Streiter, Cesar De Carmo
 *
 */

class LightSource {
	private int[] coordinates;
	private int value;
	public LightSource(int[] coordinates,int value){
		this.coordinates=coordinates;
		this.value=value;

	}
	public int getX(){
		return coordinates[0];
	}
	public int getY(){
		return coordinates[1];
	}
	
	public int[] getKoordinates() {
		return coordinates;
	}
	public void setKoordinates(int[] koordinates) {
		this.coordinates = koordinates;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
}
