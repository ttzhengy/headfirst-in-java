package inheritance;

public class Manager extends Employee
{
	private double bonus;
	
	public Manager(String aName, double aSalary, int year, int month, int day)
	{
		super(aName, aSalary, year, month, day);
		bonus = 0;
	}
	
	public double getSalaxy()
	{
		double baseSalary = super.getSalaxy();
		return baseSalary = bonus;
	}
	
	public void setBonus(double b)
	{
		bonus = b;
	}
}