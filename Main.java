import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;


public class Main
{
	public static void main(String[] args)	throws Exception
	{
	 
		Start s = new Start();
	
		// 프로그램을 시작하면(Main을 실행하면) Start클래스의 startScreen을 무한반복. 
		while (true)
		{
			s.startScreen();	
		}
	}
}