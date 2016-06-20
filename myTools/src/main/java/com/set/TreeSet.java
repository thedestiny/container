
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class TreeSet {

	/**
	 * @param args
	 * ��һ�������д洢���������ظ����ַ���,����һ������,��������(�ֵ�˳��),���һ�����ȥ���ظ� 
	 * TreeSet ����
	 */
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<>();
		list.add("ccc");
		list.add("ccc");
		list.add("aaa");
		list.add("aaa");
		list.add("bbb");
		list.add("ddd");
		list.add("ddd");
		
		sort(list);
		System.out.println(list);
	}
	
	/*
	 * �Լ����е�Ԫ������,�������ظ�
	 * 1,void
	 * 2,List<String> list
	 */
	public static void sort(List<String> list) {
		TreeSet<String> ts = new TreeSet<>(new Comparator<String>() {		//����Ƚ���(new Comparator(){}��Comparator���������)

			@Override
			public int compare(String s1, String s2) {						//��дcompare����
				int num = s1.compareTo(s2);									//�Ƚ�����
				return num == 0 ? 1 : num;									//�������һ������һ����Ϊ0�����ּ���
			}
		});
		
		ts.addAll(list);													//��list�����е�����Ԫ����ӵ�ts��
		list.clear();														//���list
		list.addAll(ts);													//��ts�����򲢱����ظ��Ľ������ӵ�list��
	}
}
