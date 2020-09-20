import java.time.*;
import java.util.*;

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
		// System.out.println(name + "is hired in " + hireDay.toString());
		// System.out.println(", with the salary " + Double.toString(salary));
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
		// System.out.println("New salary is " + Double.toString(salary));
	}
	
	public boolean equals(Object otherObject)
	{
		//根据引用对象快速判断
		if(this == otherObject) return true;
		
		//空指针
		if(otherObject == null) return false;
		
		//类名
		if(getClass() != otherObject.getClass()) return false;
		
		//强制类型转换
		Employee other = (Employee)otherObject;
		
		//判断域的值是否相等
		return Objects.equals(name, other.name)
			&& salary == other.salary
			&& Objects.equals(hireDay, other.hireDay);
	}
	
	public int hashCode()
	{
		return Objects.hash(name, salary, hireDay);
	}
	
	public String toString()
	{
		return getClass().getName()+"[name="+name+",salary="+salary+",hireDay="
			+hireDay+"]";
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
	
	public boolean equals(Object otherObject)
	{
		if(!super.equals(otherObject)) return false;
		Manager other = (Manager) otherObject;
		return bonus == other.bonus;
	}
	
	public int hashCode()
	{
		return super.hashCode() + 17* new Double(bonus).hashCode();
	}
	
	public String toString()
	{
		return super.toString() + "[bonus="+bonus+"]";
	}
}

public class ArrayListTest
{
	public static void main(String[] args)
	{
		//使用ArrayList存放Employee
		ArrayList<Employee> staff = new ArrayList<>();
		
		staff.add(new Employee("Carl Cracker", 75000, 1987, 12, 15));
		staff.add(new Employee("Harry Hacker", 50000, 1989, 10, 1));
		staff.add(new Employee("Tony Tester", 40000, 1990, 3, 15));
		
		for(Employee e : staff)
			e.raiseSalary(5);
		
		for(Employee e : staff)
			System.out.println(e);
	}	
}