/**
 * 
 * @author hadoop
 * 对于线程安全的理解。
 * 1.synchronized修饰的实例方法，只是锁定当前共享的同一实例。
 *		所有线程在同一时间内只能访问所有同步方法中的一个。
 *		但是，对于非同步方法，另一个线程是可以访问的 。
 *		个人理解：所有的同步方法可能会造成数据不一致性问题，所以，所有线程在同一时间内只能访问其中的一个同步方法，其他线程阻塞。对于非同步方法，不涉及数据不一致问题，所以，另一个线程可以访问。
 * 2.synchronized修改的同步块和同步方法是一样的， 只是锁定当前共享的同一实例。只是同步的范围不一样。
 * 		同步块修饰的范围更小。
 * 		能使用同步块，尽量不使用同步方法。（同步方法的同步范围广，效率低。）
 * 3.synchronized修饰静态方法，锁定的是类的所有实例对象。
 * 		所有线程（拥有不同实例）同一时间只能有一个线程访问所有同步静态方法中的其中一个。
 * 		但是，对于非同步静态方法，另一个线程是可以访问的 。
 * 		个人理解：因为静态方法是对所有的实例是共有的，对其进行同步，持有不同实例的所有线程同一时间也只能有一个线程可以访问该方法。
 * 总结：同步实例方法和同步静态方法性质是一样的。只是同步的实例范围不同。
 * 	         同步块和同步实例方法的作用是一样的，只是同步的范围不同。
 */
public class SynchromizedTest {

	public static void main(String[] args) throws Exception {

		/**
		 * 多个线程之间的共享变量
		 */
		final Account account = new Account("Sam", 1000);
		final Account account1 = new Account("Tom", 2000);

		/**
		 * 线程1访问共享变量的add方法,操作共享变量的sum属性。
		 */
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					//account.add(100);
					//account.sayHello();
					account.method();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		/**
		 * 线程2访问共享变量的add方法,操作共享变量的sum属性。
		 */
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					//account1.echoHello();
					//account.add(100);
					//account.printInfo();//调用非同步方法
					//account1.method();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		t1.setName("t1");
		t2.setName("t2");
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		
		System.out.println(account.sum);
	}
}

class Account {
	String name;
	float sum;

	public Account(String name, float sum) {
		this.name = name;
		this.sum = sum;
	}

	public  void add(float num) throws Exception {
		//同步块的使用		
		synchronized(this){
			for (int i = 0; i < 10; i++) {
				System.out.println(Thread.currentThread().getName()+" is using add method !!!");
				float tmp = sum;
				tmp += num;
				Thread.sleep(1);
				sum = tmp;	
			}
		}
		
//		float tmp = sum;
//		tmp += num;
//		Thread.sleep(1);
//		sum = tmp;	
				
	}

	public   void withdraw(float num) throws Exception {
//		同步块的使用
//		synchronized(this){
//			for (int i = 0; i < 10; i++) {
//				System.out.println(Thread.currentThread().getName()+" is using withdraw method !!!");	
//				float tmp = sum;
//				tmp -= num;
//				Thread.sleep(1);
//				sum = tmp;	
//			}
//		}
		
//		float tmp = sum;
//		tmp -= num;
//		Thread.sleep(1);
//		sum = tmp;	
		

	}

	public void printInfo() {
		for(int i=0;i<10;i++){
			System.out.println("I'm " + name + ",I hava " + sum + "money "+Thread.currentThread().getName());
		}
	}
	
	
	public synchronized static void sayHello(){
		for(int i=0;i<10;i++){
			System.out.println("Say Hello to "+Thread.currentThread().getName());
		}
	}
	
	public synchronized static void echoHello(){
		for(int i=0;i<10;i++){
			System.out.println("Echo Hello to "+Thread.currentThread().getName());
		}
	}
	
	private byte[] lock = new byte[0];  // 特殊的instance变量
	   public void method()
	   {
	      synchronized(lock) {
	    	  for(int i=0;i<10;i++){
	  			System.out.println(Thread.currentThread().getName());
	  		}
	      }
	   }
}