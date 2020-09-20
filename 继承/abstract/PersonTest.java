import java.time.*;

abstract class Person
{
	private String name;
	public abstract String getDescription();

	public Person(String aName)
	{
		name = aName;
	}
	
	public String getName()
	{
		return name;
	}
}

class Employee extends Person
{
	private double salary;
	private LocalDate hireDay;
	
	public Employee(String aName, double aSalary, int year, int month, int day)
	{
		super(aName);
		salary = aSalary;
		hireDay = LocalDate.of(year, month, day);
		// System.out.println(name + "is hired in " + hireDay.toString());
		// System.out.println(", with the salary " + Double.toString(salary));
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
	
	public String getDescription()
	{
		return String.format("this employee with a salary of $%.2f", salary);
	}
}

class Student extends Person
{
	private String major;
	
	public Student(String aName, String aMajor)
	{
		super(aName);
		major = aMajor;
	}
	
	public String getDescription()
	{
		return "this student majoring in " + major;
	}
}

public class PersonTest
{
	public static void main(String[] args)
	{
		Person[] people = new Person[2];
		
		//在数组中分别定义两个类的实例
		people[0] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
		people[1] = new Student("Maria Morris", "Computer Science");
		
		//调用超类People中的抽象方法getDescription()
		for(Person p:people)
		{
			System.out.println(p.getName()+", "+p.getDescription());
		}
	}
}