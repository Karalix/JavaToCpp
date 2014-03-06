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
			if (f.toGenericString().split(" ")[0].equals("public")){
				publicField.add(f);
			}
			if (f.toGenericString().split(" ")[0].equals("private")){
				privateField.add(f);
			}
			if (f.toGenericString().split(" ")[0].equals("protected")){
				protectedField.add(f);
			}
		}
	}
	
	public void openMethods(){
		methodTab = classe.getDeclaredMethods();
		
		for(Method m : methodTab) {
			if(m.toGenericString().split(" ")[0].equals("public")) {
				publicMethod.add(m);
			}
			if(m.toGenericString().split(" ")[0].equals("private")){
				privateMethod.add(m);
			}
			if(m.toGenericString().split(" ")[0].equals("protected")){
				protectedMethod.add(m);
			}
			
		}
		
	}
	
	public void openConstructors(){
		constructorTab = classe.getDeclaredConstructors();
		
		for(Constructor c : constructorTab) {
			if(c.toGenericString().split(" ")[0].equals("public")) {
				publicConstructor.add(c);
			}
			if(c.toGenericString().split(" ")[0].equals("private")){
				privateConstructor.add(c);
			}
			if(c.toGenericString().split(" ")[0].equals("protected")){
				protectedConstructor.add(c);
			}
			
		}
		
	}
	
	public void createFile() {
		
		String className = classe.getSimpleName();
		
		
	    cpp = new File(className+".cpp");
	    try {
	    	if (cpp.exists())
	    		cpp.delete();
			cpp.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    h = new File(className+".h");
	    try {
	    	if(h.exists())
	    		h.delete();
			h.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writeHeaderHFile() {
		FileWriter fw;
		try {
			fw = new FileWriter(h,true);
			

			fw.write("#ifndef "+classe.getSimpleName().toUpperCase()+"_H\n");
			fw.write("#define "+classe.getSimpleName().toUpperCase()+"_H\n");
			fw.write("\n");
			fw.write("class "+classe.getSimpleName()+"\n");
			fw.write("{\n");
			fw.close();
			
			writePrivateAtribute();
			writeProtectedAtribute();
			writePublicAtribute();
			
			writePrivateMethods();
			writeProtectedMethods();
			writePublicMethods();
			
			
			
			fw = new FileWriter(h,true);
			fw.write("}\n");
			fw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writePublicAtribute() {
        FileWriter fw;
              try {
                      fw = new FileWriter(h,true);
                      

                      fw.write("public:\n");
                      fw.close();

                      for(Field f : publicField) {
                    	  writeGenericAtribute(f);
                      }
                      
              			} catch (IOException e) {
                      // TODO Auto-generated catch block
                      e.printStackTrace();
              		}
	}

public void writePrivateAtribute() {
        FileWriter fw;
              try {
                      fw = new FileWriter(h,true);
                      

					fw.write("private:\n");
                    fw.close();
					
					for(Field f : privateField) {
						writeGenericAtribute(f);
					}
                      
		              } catch (IOException e) {
		                      // TODO Auto-generated catch block
		                      e.printStackTrace();
		              }
	}

public void writeProtectedAtribute() {
        FileWriter fw;
              try {
                      fw = new FileWriter(h,true);
                      

						fw.write("protected:\n");
	                     fw.close();
						
						for(Field f : protectedField) {
							writeGenericAtribute(f);
						}
                      
		              } catch (IOException e) {
		                      // TODO Auto-generated catch block
		                      e.printStackTrace();
		              }
	}

public void writePublicMethods() {
    FileWriter fw;
          try {
                  fw = new FileWriter(h,true);
                  

				fw.write("public:\n");
                fw.close();
				
				for(Method m : publicMethod) {
					writeGenericMethod(m);
				}
                  
	              } catch (IOException e) {
	                      // TODO Auto-generated catch block
	                      e.printStackTrace();
	              }
}

public void writeProtectedMethods() {
    FileWriter fw;
          try {
                  fw = new FileWriter(h,true);
                  

				fw.write("protected:\n");
                fw.close();
				
				for(Method m : protectedMethod) {
					writeGenericMethod(m);
				}
                  
	              } catch (IOException e) {
	                      // TODO Auto-generated catch block
	                      e.printStackTrace();
	              }
}
	

public void writePrivateMethods() {
    FileWriter fw;
          try {
                  fw = new FileWriter(h,true);
                  

				fw.write("private:\n");
                fw.close();
				
				for(Method m : privateMethod) {
					writeGenericMethod(m);
				}
                  
	              } catch (IOException e) {
	                      // TODO Auto-generated catch block
	                      e.printStackTrace();
	              }
}

public void writeGenericAtribute(Field f){
	FileWriter fw;
	try {
		fw = new FileWriter(h,true);
		String type = f.toString().split(" ")[1].split("\\.")[f.toString().split(" ")[1].split("\\.").length-1];
		System.out.println(f.toGenericString());
		fw.write("\t"+type);
		if (f.toGenericString().contains("<"))
			fw.write("<"+f.toGenericString().split("<")[1].split(">")[0].split("\\.")[f.toGenericString().split("<")[1].split(">")[0].split("\\.").length-1]+">");
		fw.write(" "+f.getName());
		fw.write(";\n");
        fw.close();
	} catch (IOException e) {
		// TODO Bloc catch généré automatiquement
		e.printStackTrace();
	}
}

public void writeGenericMethod(Method m){
	FileWriter fw;
	try {
		fw = new FileWriter(h,true);

		String[] args = m.toGenericString().split("\\(")[1].split(",");
		String type = m.toString().split(" ")[1].split("\\.")[m.toString().split(" ")[1].split("\\.").length-1];
		fw.write("\t"+type+" "+m.getName()+"(");
		for (int i =0 ; i< args.length ; ++i) {
			fw.write(args[i].split("\\.")[args[i].split("\\.").length-1]);
			if (i+1 != args.length)
				fw.write(",");
		}
		fw.write(";\n");
        fw.close();
	} catch (IOException e) {
		// TODO Bloc catch généré automatiquement
		e.printStackTrace();
	}
}

public void test(String i, String v)
{
}


}
