
/*
Programa de un semaforo normal y corriente pero nosotros elegimos el color
*/

public class Semafor {
    public static void main(String[] args) {
    	
    	System.out.println("Color?");
        String color = Entrada.readLine();
    	
    	if(color.equals("verd")){
		System.out.println("passa");
		
	}
		
		else if(color.equals("groc")){
			System.out.println("corre!");
		
		}
	
		else if(color.equals("vermell")){
			System.out.println("espera");
		
		}
		
		
		else{
			System.out.println("ves a l'oculista");
		}
	
		
	
}
}
