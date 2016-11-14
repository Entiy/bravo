/*
 * volatile禁止指令重排序
 */
public class Test2 {

	public static void main(String[] args) {
		// 3:25:32 PM Nov 14, 2016
		Integer a=127,b=127;
		Integer c=128,d=128;
		a=111;
		String string="6666";
		string="7777";
		String s1= "ab" + "cd"; 
		String s2= "abc" + "d"; 
		System.out.println(a==b);
		System.out.println(c==d);
	}
}
