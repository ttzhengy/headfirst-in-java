class Duck
{
	int size;
	
	public Duck(int ducksize)
	{
		if (ducksize == 0)
		{
			size = 12;
		}else
		{
			size = ducksize;
		}
		System.out.println("DucK");
		size = ducksize;
		System.out.println("size is " + size);
	}
}

public class UseADuck
{
	public static void main(String[] args)
	{
		Duck aduck = new Duck();
	}
}
