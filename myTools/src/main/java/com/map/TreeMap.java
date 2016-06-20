

import java.util.Comparator;
import java.util.TreeMap;

import cn.itcast.bean.Person;

public class TreeMap {

	/**
	 * @param args
	 * TreeMap集合比较键
	 * 实现Comparator接口，重写compare方法。
	 */
	public static void main(String[] args) {
		//demo1();
		TreeMap<Person, String> tm = new TreeMap<>(new Comparator<Person>() {

			@Override
			public int compare(Person p1, Person p2) {
				int num = p1.getName().compareTo(p2.getName());
				return num == 0 ? p1.getAge() - p2.getAge() : num;
			}
		});
		tm.put(new Person("张三", 33), "北京");
		tm.put(new Person("王五", 23), "广州");
		tm.put(new Person("李四", 13), "上海");
		tm.put(new Person("赵六", 43), "深圳");
		
		System.out.println(tm);
	}

	public static void demo1() {
		TreeMap<String, String> tm = new TreeMap<>();
		tm.put("c", "1");
		tm.put("a", "3");
		tm.put("b", "4");
		tm.put("d", "2");
		
		System.out.println(tm);
	}

}
