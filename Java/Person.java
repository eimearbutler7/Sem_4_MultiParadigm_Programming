public class Person{
	private String name;
	private int age;
	
	public Person(String n, int a){
		this.name = n;
		this.age = a;}

	public String toString(){
		return "NAME: " + this.name + " AGE: " + this.age; 
	}
	
	public void setAge(int n){
		if (n<0){
			//this line is a comment, we want to finish the method and not modify the age
		return;
		}
		this.age = n;
	}
	
	public static void main(String[] args){
		Person a = new Person("John", 23);
		Person b = new Person("Paul", 51);
		System.out.println(a);
		a.setAge(33);
		System.out.println(a);
		System.out.print(b);
	}
		
	
}