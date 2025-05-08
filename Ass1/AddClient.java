import java.rmi.*;

public class AddClient{
	public static void main(String args[]){
		try{
			String serverURL = "rmi://" + args[0] + "/AddServer";
			AddServerIntf addServerIntf = (AddServerIntf) Naming.lookup(serverURL);
			System.out.println("F" + args[1]);
			double a = Double.parseDouble(args[1]);
			double b = Double.parseDouble(args[2]);
			System.out.println("S" + args[2]);
			
			System.out.println("Add:" + addServerIntf.add(a,b));
		}
		catch (Exception e){
			System.out.println("Error" + e);
		}
	}
}
