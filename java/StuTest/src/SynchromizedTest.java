/**
 * 
 * @author hadoop
 * 对于线程安全的理解。
 * 1.synchronized修饰的实例方法，只是锁定
 */
public class SynchromizedTest {

	public static void main(String[] args) throws Exception {

		/**
		 * 多个线程之间的共享变量
		 */
		final Account account = new Account("sam", 1000);

		/**
		 * 线程1访问共享变量的add方法,操作共享变量的sum属性。
		 */
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					account.add(100);
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
					account.add(100);
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

	public synchronized void add(float num) throws Exception {
		for (int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName()+" is using add method !!!");	
		}
		
		float tmp = sum;
		tmp += num;
		Thread.sleep(1);
		sum = tmp;	
//		
//		synchronized(this){
//			float tmp = sum;
//			tmp += num;
//			Thread.sleep(1);
//			sum = tmp;	
//		}
//		
	}

	public  synchronized void withdraw(float num) throws Exception {
		for (int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName()+" is using withdraw method !!!");	
		}
		
		float tmp = sum;
		tmp -= num;
		Thread.sleep(1);
		sum = tmp;	
		
//		synchronized(this){
//			float tmp = sum;
//			tmp -= num;
//			Thread.sleep(1);
//			sum = tmp;	
//		}
	}

	public void printInfo() {
		System.out.println("I'm " + name + ",I hava " + sum + "money");
	}
}