import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


public class TP5 {
	
	public Class classe;
	
	private File cpp;
	private File h;
	
	public Field[] fieldTab;

	
	public List<Field> publicField;
	public List<Field> privateField;
	public List<Field> protectedField;
	
	public Method[] methodTab;
	
	public List<Method> publicMethod;
	public List<Method> protectedMethod;
	public List<Method> privateMethod;
	
	public Constructor[] constructorTab;
	
	public List<Constructor> publicConstructor;
	public List<Constructor> protectedConstructor;
	public List<Constructor> privateConstructor;
	
	
	public TP5(){
		publicField = new ArrayList<Field>();
		privateField = new ArrayList<Field>();
		protectedField = new ArrayList<Field>();
		
		publicMethod = new ArrayList<Method>();
		privateMethod = new ArrayList<Method>();
		protectedMethod = new ArrayList<Method>();
		
		publicConstructor = new ArrayList<Constructor>();
		privateConstructor = new ArrayList<Constructor>();
		protectedConstructor = new ArrayList<Constructor>();

	}

	public void openClass(String classPath) {		
		try {
			classe = Class.forName(classPath);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void openFields(){

		fieldTab = classe.getDeclaredFields();

		for(Field f : fieldTab) {
			if (f.toGenericString() == "public"){
				publicField.add(f);
			}
			if (f.toGenericString() == "private"){
				privateField.add(f);
			}
			if (f.toGenericString() == "protected"){
				protectedField.add(f);
			}
		}
	}
	
	public void openMethods(){
		methodTab = classe.getDeclaredMethods();
		
		for(Method m : methodTab) {
			if(m.toGenericString() == "public") {
				publicMethod.add(m);
			}
			if(m.toGenericString()=="private"){
				privateMethod.add(m);
			}
			if(m.toGenericString()=="protected"){
				protectedMethod.add(m);
			}
			
		}
		
	}
	
	public void openConstructors(){
		constructorTab = classe.getDeclaredConstructors();
		
		for(Constructor c : constructorTab) {
			if(c.toGenericString() == "public") {
				publicConstructor.add(c);
			}
			if(c.toGenericString()=="private"){
				privateConstructor.add(c);
			}
			if(c.toGenericString()=="protected"){
				protectedConstructor.add(c);
			}
			
		}
		
	}
	
	public void createFile() {
		
		String className = classe.getSimpleName();
		
		
	    cpp = new File(className+".cpp");
	    try {
			cpp.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    h = new File(className+".h");
	    try {
			h.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writeHeaderHFile() {
		FileWriter fw;
		try {
			fw = new FileWriter(h);
			

			fw.write("#ifndef "+classe.getSimpleName().toUpperCase()+"_H\n");
			fw.write("#define "+classe.getSimpleName().toUpperCase()+"_H\n");
			fw.write("\n");
			fw.write("class "+classe.getSimpleName()+"\n");
			fw.write("{\n");
			
			writePrivateAtribute();
			writeProtectedAtribute();
			writePublicAtribute();
			
			
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writePublicAtribute() {
        FileWriter fw;
              try {
                      fw = new FileWriter(h);
                      

fw.write("public:\n");

for(Field f : publicField) {
fw.write("\t"+f.toGenericString()+" "+f.toString()+"\n");
}
                      
                      fw.close();
              } catch (IOException e) {
                      // TODO Auto-generated catch block
                      e.printStackTrace();
              }
}

public void writePrivateAtribute() {
        FileWriter fw;
              try {
                      fw = new FileWriter(h);
                      

fw.write("private:\n");

for(Field f : privateField) {
fw.write("\t"+f.toGenericString()+" "+f.toString()+"\n");
}
                      
                      fw.close();
              } catch (IOException e) {
                      // TODO Auto-generated catch block
                      e.printStackTrace();
              }
}

public void writeProtectedAtribute() {
        FileWriter fw;
              try {
                      fw = new FileWriter(h);
                      

fw.write("protected:\n");

for(Field f : protectedField) {
fw.write("\t"+f.toGenericString()+" "+f.toString()+"\n");
}
                      
                      fw.close();
              } catch (IOException e) {
                      // TODO Auto-generated catch block
                      e.printStackTrace();
              }
}
	

}
