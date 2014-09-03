import java.util.List;

import com.epam.hnyp.task1.subtask1.Guitar;
import com.epam.hnyp.task1.subtask1.MusicInstrument;
import com.epam.hnyp.task1.subtask1.Trumpet;
import com.epam.hnyp.task1.subtask1.Violin;
import com.epam.hnyp.task1.subtask2.Condition;
import com.epam.hnyp.task1.subtask2.GoodsList;


public class Test {
	public static void main(String[] args) {
		GoodsList<MusicInstrument> goods = new GoodsList<>();
		goods.add(new Guitar());
		goods.add(new Violin("v1", 2005, 4)); 
		MusicInstrument m = new Trumpet("v1", 1997, "bronze");
		goods.add(m);
		goods.add(new MusicInstrument("v2", 1995));
		goods.setIteratorCondition(new Condition<MusicInstrument>() {
			@Override 
			public boolean satisfy(MusicInstrument item) {
				return item.getYear() < 2000;
			}
		});

		System.out.println("-------parameterized iterator--------");
		for (MusicInstrument mi : goods) {
			System.out.println(mi);
		}
		System.out.println("---------NOT parameterized iterator--------");
		goods.setIteratorCondition(null);
		for (MusicInstrument mi : goods) {
			System.out.println(mi);
		}
		System.out.println("-------------");
		goods.remove(m);
		for (MusicInstrument mi : goods) {
			System.out.println(mi);
		}
	}
}
