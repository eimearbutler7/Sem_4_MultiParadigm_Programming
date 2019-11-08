public class Student extends Person{
	//name and age already handled by Person class
	
	private String[] classes;
	
	public Student(String n, int a, String[] c){
		super(n,a);
		this.classes = c;
		
	}

	public String toString(){
		String repr = super.toString + "\n ---CLASSES--- \n";
		for(int i = 0; i<classes.length;i++){
		repr += classes[i] + "\n";	
		}
	}
	
	public static void main(String[] args){
		String[] classes = new String[]{"Introduction to Maths", "Management for Computing", "Programming 1"};
		Student s = new Student("Mister", 50, classes);
		System.out.println(s); //automatically calls toString method from Person file
		a.setAge(443);
	}
}
