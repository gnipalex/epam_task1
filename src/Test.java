import java.util.Arrays;

public class Test {
	public static void main(String[] args) {
		int[] mas = new int[10];
		Arrays.fill(mas, 2);
		System.out.println(mas);
		for (int i : mas) {
			System.out.print(i + " ");
		}
		System.out.println();
		System.out.println("-------");
		int[] clone = mas.clone();
		clone[1] = 9;
		System.out.println("mas ");
		for (int i : mas) {
			System.out.print(i + " ");
		}
		System.out.println();
		System.out.println("clone ");
		for (int i : clone) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
}
