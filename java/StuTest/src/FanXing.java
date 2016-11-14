import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/*
 * 定义泛型类
 * 
 * 1.此处的A，B既是泛型参数，紧跟类名后面，用<>围住。
 * 2.A,B代表所有可能的类型，而A，B的实际类型在GenericClass实例化时指定。
 * 3.由于泛型类型只有在类实例化之后才能确定，类中的泛型成员只能使用Object类型中的方法。
 */
class GenericClass<A,B> {

	public A a;  //在泛型类中，泛型是不可以实例化的，只能通过其他方式赋值（比如在构造方法中赋值）。 A a=new A() 这种写法是不正确的
	public B b;

	public GenericClass(A a,B b) {
		 this.a=a;  //比如这种形式的实例化，或其他形式的赋值，如set
		 this.b=b;
	}
	public GenericClass() {
	}
	public void printAB() {
		System.out.println("A：\t"+a+"\nB：\t"+b);
	}
}

/*
 * 泛型类继承的关系
 */
class GenericClassSon<A,B,C> extends GenericClass<A, B>{
	public C c;

	public GenericClassSon(A a, B b, C c) {
		super(a, b);
		this.c = c;
	}
	public void printABC() {
		System.out.println("A：\t"+a+"\nB：\t"+b+"\nC：\t"+c);
	}
}

class A {
	public String name = "A";
}

class B{
	public String name="B";
}

class C{
	public String name="C";
}


/*
 * 泛型接口，以工厂模式为例。
 */

interface FactoryI<T>{
	T create();
}

class DFactory implements FactoryI<D>{

	@Override
	public D create() {
		return new D();
	}
}

class DF implements FactoryI{

	@Override
	public Object create() {
		return null;
	}
	
}

class DF1<T> implements FactoryI<T>{

	@Override
	public T create() {
		// TODO Auto-generated method stub
		return null;
	}
	
}

class D{
	public String name="D";
}

class E<T extends List>{
	
	private T list;

	public T getList() {
		return list;
	}

	public void setList(T list) {
		this.list = list;
	}
	
	public void doSome() {
		list.add("hello,word");
		System.out.println(list.get(0));
	}
}

class F<T>{
	private T t;
	public F(T t){
		this.t=t;
	}
	public F(){
	}
	void printT(){
		System.out.println(t);
	}
	public T getT() {
		return t;
	}
	public void setT(T t) {
		this.t = t;
	}
}

class G{
	public void printInfo(F<?> f){
		f.printT();
	}
}

class Util{
	static void printCollection(Collection<?> c){
		for (Object o : c) {
			System.out.println(o);
		}
	}
}

class H{
	public <T> void printInfo(T t){
		System.out.println(t.getClass().getName());
	}
}
public class FanXing {

	public static void main(String[] args) {
		/*
		 * 泛型类实例化时，并不一定要指明泛型对应的实际类型，默认使用Object作为实际类型。
		 * GenericClass gc=new GenericClass();
		 */
		GenericClass<String, Integer> gc=new GenericClass<String, Integer>("a", 11);
		gc.printAB();
		/*
		 * GenericClass<String, Integer> gcs[]=new GenericClass<String, Integer>("a", 11)[5]; 在创建泛型类的数组的时候，这种建立方法是错误的。
		 * 下面的写法才是正确的。
		 */
		GenericClass<String, Integer> gcs[]=new GenericClass[5]; 
		gcs[0]=new  GenericClass<String, Integer>("aa", 111);
		gcs[0].printAB();
		/*
		 * 泛型类的继续关系
		 */
		GenericClassSon<String, String, Integer> gcson=new GenericClassSon<String, String, Integer>("a", "b", 111);
		gcson.printABC();
		
		/*
		 * 泛型接口
		 */
		D d=new DFactory().create();
		System.out.println(d.name);
		
		/**
		 * 泛型限制
		 */
		E<ArrayList> e=new E<ArrayList>();
		//E<Map> e1=new E<Map>();
		e.setList(new ArrayList<>());
		e.doSome();
		
		/*
		 * 通配符的作用
		 */
//		F<String> f1=new F<String>("hello");
//		f1.printT();
//		F<Integer> f2=new F<Integer>(110);
//		f2.printT();
//		
//		F<?> f=f1;
//		f.printT();
//		f=f2;
//		f2.printT();
//		
//		new G().printInfo(f1);
//		new G().printInfo(f2);
		
		/*
		 * 通配符对泛型成员的影响
		 */
//		F<String> f1=new F<String>("hello,word!!!");
//		F<?> f=f1;
//		f.printT();
//		f.setT(null);
		//f.setT("666");
		
		F<String> f1=new F<String>("hello");
		F<Integer> f2=new F<Integer>(110);
		F f=new F();
		f=f1;
		f.printT();
		
		H h=new H();
		h.printInfo(1);
		h.printInfo(false);
		h.printInfo(new String("11"));
	}
}
