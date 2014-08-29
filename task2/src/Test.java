import java.lang.reflect.Array;
import java.util.Arrays;

import com.epam.hnyp.task2.subtask3.GoodsContainer;


public class Test {
	public static void main(String[] args) {
//		GoodsContainer<Integer> gc = new GoodsContainer<>();
//		gc.add(10);
//		gc.add(12);
		Integer[] mas = a1(new Integer[0], 23);
		System.out.println(mas[0]);
		
	}
	
	public static <T> T[] a1(T[] mas, T value) {
		T[] ret = (T[]) Array.newInstance(mas.getClass().getComponentType(), 10);
		ret[0] = value;
		return ret;
	}
}
