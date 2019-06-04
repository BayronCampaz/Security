package model;



public class Tests {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Controller controller = new Controller();
		try {
			
			controller.createKeys("Lakey1", "c mamo");
			controller.signFile("data/Ejemplo", "data/EjemploSigned", "c mamo");
			System.out.println(controller.verifyFile("data/Ejemplo", "data/EjemploSigned", "c mamo"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
