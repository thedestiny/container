

import java.util.Comparator;
import java.util.TreeMap;

import cn.itcast.bean.Person;

public class TreeMap {

	/**
	 * @param args
	 * TreeMap���ϱȽϼ�
	 * ʵ��Comparator�ӿڣ���дcompare������
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
		tm.put(new Person("����", 33), "����");
		tm.put(new Person("����", 23), "����");
		tm.put(new Person("����", 13), "�Ϻ�");
		tm.put(new Person("����", 43), "����");
		
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
