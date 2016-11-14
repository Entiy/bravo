import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import sun.misc.ProxyGenerator;  

/*
 * 真实类要实现的接口
 */
interface Subject {
	public void echo();

	public void sayWord(String str);
}

/*
 * 真实类，被代理的类
 */
class RealSubject implements Subject {

	@Override
	public void echo() {
		System.out.println("I'm a echo method");
	}

	@Override
	public void sayWord(String str) {
		System.out.println("I'm a sayWord method,I can say " + str);
	}

}

/*
 * 中间类,必须实现InvocationHandler接口。 这个类很多人认为是，动态代理类。真是这样的么？
 * 个人感觉动态生成的才是动态代理类，这个类个人感觉是一个中间传值的一个类。
 * 在动态生成字节码的类中，这个中间类的实例被传入，调用其中的invoke方法，在下面实现的invoke方法中，才真正调用了真实对象的方法
 * 这也解释了为什么会在中间类中会持有真实对象的引用。就是因为需要调用真实对象的真实方法。
 * 同时在下面实现的invoke方法中，在调用真实方法的前后，我们可以对真实方法进行增强，这也就是AOP使用动态代理的原因。
 * 那么在下面实现的invoke方法的参数中:
 * method:在动态生成的代理类中，通过发射机制得到的。
 * args：调用动态代理类方法时传入的。
 * proxy：真正动态代理类的实例。（不知道作用是什么）
 * 
 */
class InvocationHandlerImpl implements InvocationHandler {

	private Object realSubject;// 这个对象就是真实对象

	public InvocationHandlerImpl(Object realSubject) {
		this.realSubject = realSubject;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object,
	 * java.lang.reflect.Method, java.lang.Object[])
	 * 实现invoke方法，可以在真实方法的调用前后，增加一些功能。这也是AOP使用动态代理的的原因。另一方面也是和静态代理的区别。
	 */
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("Before.......");
		method.invoke(realSubject, args);// method参数，是通过发射机制生成的。
		System.out.println("After.........");
		return null;
	}

}

public class DProxy {

	public static void main(String[] args) {
		// 4:01:35 PM Nov 10, 2016
		// 实例化真实对象
		RealSubject realSubject = new RealSubject();
		// 传入要代理的对象
		InvocationHandler handler = new InvocationHandlerImpl(realSubject);
		/*
		 * 这个才是真正的动态代理类，通过动态生成字节码的形式实现的 。
		 * 第一个参数：动态生成的字节码代表的动态代理类，需要用类加载器加载之后才能使用。 第二个参数：指定代理类需要实现的接口
		 * 第三个参数：也就是上面提到的，最重要的中间类实例，通过它完成最终方法的调用。
		 * 动态生成的字节码是通过sun.misc.ProxyGenerator.generateProxyClass(String proxyName,class[] interfaces)方法实现的。
		 * 
		 */
		Subject proxy = (Subject) Proxy.newProxyInstance(handler.getClass().getClassLoader(), realSubject.getClass().getInterfaces(),handler);
		proxy.echo();
		proxy.sayWord("eat");

		DProxy.generateClassFile(realSubject.getClass(), "RealProxy");
	}
	
	/*
	 * 通过源码我们知道了内部是如何动态生成字节码的。
	 * 我们通过源码的方法自定义一个方法，进行动态生成字节码。
	 * 通过查看动态生成的字节码去具体了解动态代理类的实现细节。
	 */

	public static void generateClassFile(Class clazz, String proxyName) {
		// 根据类信息和提供的代理类名称，生成字节码
		byte[] classFile = ProxyGenerator.generateProxyClass(proxyName,clazz.getInterfaces());
		String paths = clazz.getResource(".").getPath();
		System.out.println(paths);
		FileOutputStream out = null;

		try {
			// 保留到本地磁盤中
			out = new FileOutputStream(paths + proxyName + ".class");
			out.write(classFile);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
