public class HashCodeTest
{
	public static void main(String[] args)
	{
		String s = "OK";
		StringBuilder sb = new StringBuilder(s);
		System.out.println(s.hashCode() + " " + sb.hashCode());
		String t = new String("OK");
		StringBuilder tb = new StringBuilder(t);
		System.out.println(t.hashCode() + " " + tb.hashCode());
	}
}