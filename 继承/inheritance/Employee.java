package inheritance;

import java.time.*;

public class Employee
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