public class Test1 {
	/*
	 * 这就是+和+=的区别。
	 */
	public static void main(String[] args) {
		// 12:39:10 PM Nov 14, 2016
		byte a=127;
		byte b=126;
//		a=a+1;//1是int类型,a是byte类型。类型不符。不能编译。
		a+=1; //+=不但可以相加，还可以进行类型强制转换
		System.out.println(a);
	}
	
}
