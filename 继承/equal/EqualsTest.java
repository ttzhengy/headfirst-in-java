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

public class EqualsTest
{
	public static void main(String[] args)
	{
		Employee alice1 = new Employee("Alice Adams", 75000, 1987, 12, 15);
		Employee alice2 = alice1;
		Employee alice3 = new Employee("Alice Adams", 75000, 1987, 12, 15);
		Employee bob = new Employee("Bob Brandson", 50000, 1989, 10, 1);
		
		System.out.println("alice1 == alice2:" + (alice1 == alice2));	//true
		System.out.println("alice1 == alice3:" + (alice1 == alice3));	//false
		System.out.println("alice1.equals(alice3):" + alice1.equals(alice3));	//true
		System.out.println("alice3.equals(bob):" + alice3.equals(bob));	//false
		System.out.println("bob.toString():" + bob);
		
		Manager carl = new Manager("Carl Cracker", 80000, 1987, 12, 15);
		Manager boss = new Manager("Carl Cracker", 80000, 1987, 12, 15);
		boss.setBonus(5000);
		
		System.out.println("boss.toString():" + boss);
		System.out.println("carl.equals(boss):" + carl.equals(boss));	//false
		System.out.println("alice1.hashCode():" + alice1.hashCode());
		System.out.println("alice3.hashCode():" + alice3.hashCode());
		System.out.println("boss.hashCode():" + boss.hashCode());
		System.out.println("carl.hashCode():" + carl.hashCode());
	}	
}