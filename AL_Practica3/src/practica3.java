
public class practica3 {
	
	private static boolean comprobarRelaciones(int[][] m, Coordenada c){
		boolean valido = true;
		
		if(m[c.getFila()][c.getColumna()]<m[c.getFila()-1][c.getColumna()])valido = false;
		else if(m[c.getFila()][c.getColumna()]<m[c.getFila()][c.getColumna()-1])valido = false;
		else if(m[c.getFila()-1][c.getColumna()]<m[c.getFila()-1][c.getColumna()-1])valido = false;
		else if(m[c.getFila()][c.getColumna()-1]<m[c.getFila()-1][c.getColumna()-1])valido = false;
		
		return valido;
	}

	public static int maxMatOrdenada(int m[][]){
		
		Coordenada n = new Coordenada();
		int f0 = 0, c0=0;
		int ordenMax = 0;
		int adyt[][] = new int[m.length][m[0].length]; 
		
		// CASOS BASES - MATRICES RANGO 1
		
		for (int c = 0; c < adyt[0].length; c++) 
			adyt[0][c]= 1;
		
		for (int f = 0; f < adyt.length; f++) 
			adyt[f][0]= 1;
		
		
		// RESTO DE LA MATRIZ 
		
		for (int fi = 1; fi < adyt.length; fi++) {
			for (int co = 1; co < adyt[0].length; co++) {
				
				// COMPRUEBA SI EL ELEM. DE POS. ACT. ES EL MAYOR DE SU SUBMATRIZ
				
			if(!comprobarRelaciones(m,new Coordenada(fi,co))) adyt[fi][co]=1;
				else{
					//BUSCAR EL TAMAÑO CORRECTO DE LA SUBMATRIZ
					int max = adyt[fi-1][co-1];// System.out.println(max);
					if(max>adyt[fi][co-1])max = adyt[fi][co-1]; //System.out.println(max);
					if(max>adyt[fi-1][co])max = adyt[fi-1][co];// System.out.println(max);
					
					adyt[fi][co] =  max+1;
				}
			
				
				if(adyt[fi][co]>ordenMax) {
					
					ordenMax = adyt[fi][co];
					n = new Coordenada(fi, co); //GUARDA LA POS. DEL ULTIMO ELEM. DE LA MAX_SUBMATRIZ_ORDENADA
				}
			}	
		}
		
		//POS. INICIO DE LA MAX_SUBMATRIZ_ORDENADA
		
		f0 = n.getFila()-(ordenMax-1);
		c0 = n.getColumna()-(ordenMax-1);
		
		//IMPRIME LA MAX_SUBMATRIZ_ORDENADA
		/*
		imprimirMaxMat(adyt,0,0,adyt.length-1,adyt[0].length-1);
		System.out.println();
		imprimirMaxMat(m, f0, c0, n.getFila(), n.getColumna());*/
		return ordenMax;
		
	}
	
	public static int maxMatOrdenadaOptimizado(int m[][], Coordenada n){
		
		int f0 = 0, c0=0;
		int ordenMax = 0;
		//Coordenada n=new Coordenada();
		int adyt[][] = new int[2][m[0].length];
		int[] aux;
		
		// CASO BASE - MATRICES RANGO 1
		
		adyt[1][0]= 1;
		for (int c = 0; c < adyt[0].length; c++) 
			adyt[0][c]= 1;
	
	
		//CASO RECURSIVO -  RESTOS DE LA MATRIZ
		
		for (int fi = 1; fi < m.length; fi++) {
	
			//int aux = fi%2; //FILAS DE LA NUEVA MATRIZ DE ADY. 
			//System.out.println((fi%2));
			
			for (int co = 1; co < adyt[0].length; co++) {
				
				/*System.out.println("fi: " + fi+ "     c: " + co);
				  System.out.println("fAux: " + aux+ "     c: " + co);*/
				
				// COMPRUEBA SI EL ELEM. DE POS. ACT. ES EL MAYOR DE SU SUBMATRIZ - COMPRUEBA SU DIAGONAL
				
				if(m[fi][co] <= m[fi-1][co-1]) adyt[1][co] = 1;
				else {
					
					//COMPRUEBA SI EL ELEM. DE POS. ACT. ES MAYOR QUE ELEM DE ENCIMA Y EL DE SU IZQ.
					
					if(!comprobarRelaciones(m,new Coordenada(fi,co))) adyt[1][co]=1;
					else{
						//BUSCAR EL TAMAÑO CORRECTO DE LA SUBMATRIZ
						int max = adyt[0][co-1];// System.out.println(max);
						if(max>adyt[1][co-1])max = adyt[1][co-1]; //System.out.println(max);
						if(max>adyt[0][co])max = adyt[0][co];// System.out.println(max);
						
						adyt[1][co] =  max+1;
					}
					
					/*if(m[fi-1][co] > m[fi][co] || m[fi][co-1] > m[fi][co]) adyt[aux][co] = 1;
					else {
						
						if(aux == 0) adyt[aux][co] = adyt[aux+1][co-1] + 1;
						else adyt[aux][co] = adyt[aux-1][co-1]+1;
					}	*/	
				}
				
				if(adyt[1][co]>ordenMax) {
					
					ordenMax = adyt[1][co];
					n.setColumna(co);
					n.setFila(fi);
				}
			}
			
			//MOVER EL VECTOR MODIFICADO A LA PRIMERA POSICIÓN Y EL OTRO A LA SEGUNDA PARA TRABAJAR EN LA SIGUIENTE ITERACIÓN
			aux = adyt[1];
			adyt[1] = adyt[0];
			adyt[0] =aux;
		}
		
		
		//f0 = n.getFila()-(ordenMax-1);
		//c0 = n.getColumna()-(ordenMax-1);
		
		//imprimirMaxMat(m, f0, c0, n.getFila(), n.getColumna());
		
		return ordenMax;
		
	}
	
	public static void imprimirMaxMat(int m[][], int f0, int c0, int f, int c){
		for ( int i = f0; i <= f; i++) {
			for (int j = c0; j <= c; j++) {
				System.out.print("\t" + m[i][j]);
			}
			System.out.print("\n");
		}
	}
	
	public static void mostrarSubmatriz(int [][]m, int rango, Coordenada c){
		imprimirMaxMat(m,c.getFila()-(rango-1),c.getColumna()-(rango-1),c.getFila(),c.getColumna());
	}
	
	public static void main(String ag[]){
		
		Coordenada n = new Coordenada(0,0);
		
		//int ma[][]={{10,1,4,1,4,0},{1,2,10,6,2,1},{6,7,20,10,1,2},{9,10,23,0,3,5}};
		int ma[][] = {{1,2,1},{2,3,4},{1,4,5}};
		imprimirMaxMat(ma, 0, 0, ma.length-1, ma[0].length-1);
		System.out.println("\n");
		//int ma[][]={{10,1,1,2,10,11},{1,2,6,7,20,21},{1,3,9,10,23,24},{9,5,10,12,29,30}};
		//int ma[][]={{10,1,1,2,10,11},{1,8,6,7,1,0},{10,1,4,1,4,0},{1,2,10,6,2,1},{6,7,20,10,1,2},{9,10,23,0,3,5}};
		
		
		//int resul = maxMatOrdenada(ma,n);
		int resul = maxMatOrdenadaOptimizado(ma,n);
		mostrarSubmatriz(ma,resul,n);
		System.out.println("Max Orden: " + resul);
	}
	

}
