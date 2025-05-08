import java.rmi.*;

public class AddServer {
	public static void main (String args[]){
		try{
			AddServerImpl addServerImpl = new AddServerImpl();
			Naming.rebind("AddServer",addServerImpl);
		}
		catch (Exception e){
			System.out.println("Error" + e);
		}
	}
}

/*
Run Commands###

Create a folder and make all 4 files in that folder only

javac *.java
rmiregistry

------New Terminal

java Server

------New Terminal

java Client

*/
