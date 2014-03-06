
public class Main {
	
	public static void main(String[] args) {
		TP5 tp = new TP5(1,2);
		
		
		try {
		tp.openClass(args[0]);
		} catch (Exception e) {
			System.out.println("Trop peu d'aguments");
			return;
		}
		
		
		tp.openFields();
		tp.openMethods();
		tp.openConstructors();
		
		tp.createFile();
		
		tp.writeMain();
		tp.writeHeaderHFile();
		tp.writeCPPFile();
		
	}

}
