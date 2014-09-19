import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.epam.hnyp.task2.subtask3.model.parser.FieldParser;
import com.epam.hnyp.task2.subtask3.model.parser.StringFieldParser;

public class Test2 {
	public static void main(String[] args) {
		MyClassB b = new MyClassB();
		Class clazz = MyClassB.class;
		List<Field> annotatedFields = readFieldsWithAnn(clazz, MyAnn.class);
		for (Field f : annotatedFields) {
			MyAnn myAn = f.getAnnotation(MyAnn.class);
			System.out.println(f.getName() + " | annotated message ->> "
					+ myAn.message());
			if (f.getName().equals("name")) {
				f.setAccessible(true);
				try {
					f.set(b, "arrrrrrrrrr");
				} catch (Exception e) {
					System.out.println(e);
				}
			}
			System.out.println(b.getName());
		}
	}

	public static List<Field> readFieldsWithAnn(Class c,
			Class<? extends Annotation> a) {
		List<Field> fieldsList = new ArrayList<>();
		Field[] fields = c.getDeclaredFields();
		if (fields != null) {
			for (Field f : fields) {
				if (f.isAnnotationPresent(a)) {
					fieldsList.add(f);
				}
			}
		}
		Class sclazz = c.getSuperclass();
		if (sclazz != null) {
			fieldsList.addAll(readFieldsWithAnn(sclazz, a));
		}
		return fieldsList;
	}
}

class MyClassA {
	//@MyAnn(message = "field a")
	private int a;
	@MyAnn(message = "field name", parser=StringFieldParser.class)
	private String name;

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

class MyClassB extends MyClassA {
	@MyAnn(message = "field b", parser=StringFieldParser.class)
	private int b;

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MyAnn {
	String message();
	Class<? extends FieldParser> parser();
}
