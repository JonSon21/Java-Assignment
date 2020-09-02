import java.io.*;
import java.util.*;

public class Test {

 public static void main(String [] args) {
 	
 	  UserDetails f = new UserDetails("Jon Son","Lee",'M',"0123226545","ljs@email.com","000123141331");
      Manager e = new Manager(f,"abcxyz","Branch Manager");

      try {
         FileOutputStream fileOut = new FileOutputStream("../obj/manager.bin");
         ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
         
         objectOut.writeObject(e);
         objectOut.close();
         fileOut.close();
         
         System.out.printf("Serialized data is saved in /tmp/employee.ser");
      } catch (Exception i) {
      }
   }
    
    
}