
public class Test3 {

	public static void main(String[] args) {
		
		int a[]=new int[3];
		System.out.println(a.getClass().getName());
		Car cars[]=new Car[2];
		System.out.println(cars.getClass().getName());
	}
}

class Car{
	String name;
	public Car(String name){
		this.name=name;
	}
}