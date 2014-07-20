package trial1;

import java.io.IOException;

public class finalDriver{
	public static int inputk;
	public static void main(String args[]) throws IOException
	{
		//k = Integer.parseInt(args[1]);
		inputk=Integer.parseInt(args[1]);
		Driver1 d1 = new Driver1(args[0]);
		//Driver1 d1 = new Driver1("/home/mansi/input/");
		driver2 d2 = new driver2();
	}
}
