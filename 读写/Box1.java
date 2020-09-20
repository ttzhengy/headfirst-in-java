import java.io.*;

public class Box1 implements Serializable
{
	private int width;
	private int height;
	
	public void setWidth(int w)
	{
		width = w;
	}
	
	public void setHeight(int h)
	{
		height = h;
	}
	
	public static void main(String[] args)
	{
		Box box = new Box();
		box.setHeight(30);
		box.setWidth(50);
		
		try(FileOutputStream fos = new FileOutputStream("foo.ser");)
		{
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(box);
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}