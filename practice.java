import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class HW2_ItaySuthalter {

	private static Scanner s = new Scanner(System.in);
	public static final String Department = "Department.dat";
	public static final String Manager = "Manager.dat";

	public static void main(String[] args) {
		try {
			Map<Department, Manager<?>> tMap;
			setManager();
			setDepartment();
			System.out.println("Files were saved successfully!");
			tMap = setStruct();
			print(tMap);
			search(tMap);
			Set<Manager<?>> manager = managerSort(tMap);
			print(manager);
			Manager<?>[] arr = toArray(manager);
			MergeSort.mergeSort(arr);
			print(arr);
			MyList<?> listManager = toMyList(arr);
			System.out.println("List before reversing:");
			print(listManager);
			reverse(listManager);
			System.out.println("List after reversing:");
			print(listManager);
			sortMyList(listManager);
			System.out.println("List after sorting:");
			print(listManager);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void sortMyList(MyList<?> listManager) {
		MyPriorityQueue<?> pQ = new MyPriorityQueue<>();
		for (int i = 0; i < listManager.size(); i++)
			pQ.enqueue(listManager.get(i));
		for (int i = 0; i < listManager.size(); i++)
			listManager.set(i, pQ.dequeue());
	}

	private static MyList<?> reverse(MyList<?> listManager) {
		if (listManager.size() > 1) {
			MyList<?> value = new MyArrayList<>();
			value.add((listManager.remove(0)));
			reverse(listManager);
			listManager.add(value.get(0));
		}
		return listManager;
	}

	private static void print(MyList<?> listManager) {
		for (int i = 0; i < listManager.size(); i++) {
			System.out.println(listManager.get(i).toString());
		}
	}

	private static MyList<?> toMyList(Manager<?>[] arr) {
		MyList<?> ml = new MyArrayList<>(arr);
		return ml;
	}

	private static void print(Manager<?>[] arr) {
		System.out.println("Array after sorting:");
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i].toString());
		}
	}

	private static Manager<?>[] toArray(Set<Manager<?>> manager2) {
		Manager<?>[] arr = manager2.toArray(new Manager<?>[0]);
		return arr;

	}

	private static void print(Set<Manager<?>> manager2) {
		System.out.println("Managers:");
		for (Manager<?> man : manager2) {
			System.out.println(man.toString());
		}
	}

	private static Set<Manager<?>> managerSort(Map<Department, Manager<?>> tMap) {
		Set<Manager<?>> manager = new TreeSet<Manager<?>>(tMap.values());
		return manager;

	}

	private static void search(Map<Department, Manager<?>> tMap) {
		String departmentName;
		System.out.print("Please enter department name:");
		departmentName = s.next();
		boolean flag = false;
		Set<Map.Entry<Department, Manager<?>>> entrySet = tMap.entrySet();
		for (Map.Entry<Department, Manager<?>> entry : entrySet) {
			if (entry.getKey().getDepName().compareTo(departmentName) == 0) {
				System.out.println(entry.getValue());
				flag = true;
			}
		}
		if (flag == false)
			System.out.println("there is no such department Name");

	}

	private static void print(Map<Department, Manager<?>> tMap) {
		Set<Map.Entry<Department, Manager<?>>> entrySet = tMap.entrySet();
		for (Map.Entry<Department, Manager<?>> entry : entrySet) {
			System.out.println(entry.getKey() + " .... " + entry.getValue());
		}
	}

	private static Map<Department, Manager<?>> setStruct()
			throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream o1 = null;
		ObjectInputStream o2 = null;
		Map<Department, Manager<?>> tM = null;
		try {
			o1 = new ObjectInputStream(new FileInputStream(Department));
			o2 = new ObjectInputStream(new FileInputStream(Manager));
			tM = new TreeMap<Department, Manager<?>>(new ComparatorDepartment());
			while (true) {
				Department dep = (Department) o1.readObject();
				Manager<?> m = (Manager<?>) o2.readObject();
				tM.put(dep, m);
			}
		} catch (EOFException ex) {
			o1.close();
			o2.close();
			return tM;
		}
	}

	private static void setDepartment() throws IOException {
		ObjectOutputStream o = null;
		Department dep;
		try {
			o = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(Department)));
			dep = new Department(3, "C");
			o.writeObject(dep);
			dep = new Department(1, "A");
			o.writeObject(dep);
			dep = new Department(2, "B");
			o.writeObject(dep);
			dep = new Department(5, "E");
			o.writeObject(dep);
			dep = new Department(4, "ZZ");
			o.writeObject(dep);
			dep = new Department(12, "Z");
			o.writeObject(dep);
			dep = new Department(2, "B");
			o.writeObject(dep);
			dep = new Department(5, "E");
			o.writeObject(dep);
			dep = new Department(7, "X99");
			o.writeObject(dep);
		} finally {
			if (o != null)
				o.close();
		}

	}

	private static void setManager() throws IOException {
		ObjectOutputStream o = null;
		Manager<String> ms;
		Manager<Integer> m;
		try {
			o = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(Manager)));
			ms = new Manager<String>("Bis", "Mr.  Bisli");
			o.writeObject(ms);
			m = new Manager<Integer>(10, "Mr.  Bamba");
			o.writeObject(m);
			m = new Manager<Integer>(20, "Mrs. Samba");
			o.writeObject(m);
			m = new Manager<Integer>(15, "Mr.  Main");
			o.writeObject(m);
			m = new Manager<Integer>(10, "Mr.  Bamba");
			o.writeObject(m);
			ms = new Manager<String>("Scan", "Mrs. Scanner");
			o.writeObject(ms);
			ms = new Manager<String>("20", "Mrs. Gamba");
			o.writeObject(ms);
			m = new Manager<Integer>(15, "Mr.  Printf");
			o.writeObject(m);
			m = new Manager<Integer>(20, "Mrs. Samba");
			o.writeObject(m);
		} finally {
			if (o != null)
				o.close();
		}

	}

}
