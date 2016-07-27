package Collections;

import java.util.LinkedList;
import java.util.List;

public class HashSetPractice<T> {

	private List<T>[] entries;

	public HashSetPractice(int size) {
		entries = (List<T>[]) new List[size / 10];
		for (int i = 0; i < size / 10; i++) {
			entries[i] = new LinkedList<T>();
		}
	}

	public void add(T t) {
		int hashCode = t.hashCode();
		int bucket = hashCode % entries.length;
		List<T> list = entries[bucket];
		synchronized (list) {
			for (T tt : list) {
				if (tt.equals(t)) {
					return;
				}
			}
			list.add(t);
		}
	}

	public void remove(T t) {
		int hashCode = t.hashCode();
		int bucket = hashCode % entries.length;
		List<T> list = entries[bucket];
		synchronized (list) {
			int i = -1;
			for (T tt : list) {
				++i;
				if (tt.equals(t)) {
					break;
				}
			}
			if (i >= 0)
				list.remove(i);
		}
	}

	public boolean contains(T t) {
		int hashCode = t.hashCode();
		int bucket = hashCode % entries.length;
		List<T> list = entries[bucket];
		synchronized (list) {
			for (T tt : list) {
				if (tt.equals(t)) {
					return true;
				}
			}
		}
		return false;
	}
}