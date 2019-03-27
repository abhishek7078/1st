import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class client {
public static void main(String args[]) throws UnknownHostException, IOException
{
Scanner sc= new Scanner(System.in);	
Socket s=new Socket("103.51.233.180",8080);
Scanner sc1=new Scanner(s.getInputStream());
System.out.println("enter a number");
int num = sc.nextInt();
PrintStream p=new PrintStream(s.getOutputStream());
p.println(num);
int tip=sc1.nextInt();
System.out.println(tip);

	
	
	
	
}
}

