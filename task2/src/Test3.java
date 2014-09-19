import java.lang.reflect.Field;

public class Test3 {
	public static void main(String[] args) {
		printType(new Integer(10));
		printType(new Double(0.5));
		
		System.out.println("-------------");
		
		printAllFields(new AImpl());
	}

	public static void printType(Number num) {
		System.out.println(num.getClass().getName());
	}

	public static void printAllFields(Object a) {
		Class clazz = a.getClass();
		Field[] fields = clazz.getDeclaredFields();
		if (fields != null) {
			for (Field f : fields) {
				System.out.println(f.getName() + " "  + f.getType());
			}
		}
	}

}

interface A {
	void a();
}

class AImpl implements A {

	private int val;
	private double x;

	@Override
	public void a() {
		// TODO Auto-generated method stub

	}

}
