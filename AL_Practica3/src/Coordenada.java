
public class Coordenada {
	
	private int i;
	private int j;
	
	public Coordenada(){
		i = 0;
		j = 0;
	}
	
	public Coordenada(int n, int m){
		i=n;
		j=m;
	}
	
	public int getFila(){
		return i;
	}
	
	public int getColumna(){
		return j;
	}

	public void setFila(int f){
		this.i = f;
	}
	public void setColumna(int c){
		this.j = c;
	}
}
