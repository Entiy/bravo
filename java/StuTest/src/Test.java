class SingleTon {
	private static SingleTon singleTon = new SingleTon();
	public static int count1;
	public static int count2=0;

//	static{
//		System.out.println("===");
//		count1++;
//		count2++;
//	}
	static{
		System.out.println("======");
	}
	
	private SingleTon() {
		count1++;
		count2++;
	}

	public static SingleTon getInstance() {
		return singleTon;
	}
}

public class Test {
	public static void main(String[] args) {
		SingleTon singleTon=SingleTon.getInstance();
		System.out.println("count1=" + SingleTon.count1);
		System.out.println("count2=" + SingleTon.count2);
	}
}

//class Hallo{
//	public static int count1;
//	public static int count2=0;
//	static{
//		System.out.println("Hallo");
//	}
//	public Hallo() {
//		count1++;
//		count2++;
//	}
	
//}