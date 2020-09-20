import java.time.*;

public class ManagerTest
{
	public static void main(String[] args)
	{
		Manager boss = new Manager("Carl Cracker", 80000, 1987, 12, 15);
		boss.setBonus(5000);
		
		Employee[] staff = new Employee[3];
		
		staff[0] = boss;
		staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
		staff[2] = new Employee("Tommy Tester", 40000, 1990, 3, 15);
		
		for(Employee e : staff)
		{
			System.out.println("name="+e.getName() + "salary="+e.getSalary());
		}
	}
}

class Employee
{
	private String name;
	private double salary;
	private LocalDate hireDay;
	
	public Employee(String aName, double aSalary, int year, int month, int day)
	{
		name = aName;
		salary = aSalary;
		hireDay = LocalDate.of(year, month, day);
		System.out.println(name + "is hired in " + hireDay.toString());
		System.out.println(", with the salary " + Double.toString(salary));
	}
	
	public String getName()
	{
		return name;
	}
	
	public double getSalary()
	{
		return salary;
	}
	
	public LocalDate getHireDay()
	{
		return hireDay;
	}
	
	public void raiseSalary(int percent)
	{
		salary *= (1 + percent/100.0);
		System.out.println("New salary is " + Double.toString(salary));
	}
}

class Manager extends Employee
{
	private double bonus;
	
	public Manager(String aName, double aSalary, int year, int month, int day)
	{
		super(aName, aSalary, year, month, day);
		bonus = 0;
	}
	
	public double getSalaxy()
	{
		double baseSalary = super.getSalary();
		return baseSalary = bonus;
	}
	
	public void setBonus(double b)
	{
		bonus = b;
	}
}