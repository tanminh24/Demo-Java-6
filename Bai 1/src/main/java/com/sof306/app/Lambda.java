package com.sof306.app;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.sof306.bean.Student;

public class Lambda {

	static List<Student> list = Arrays.asList(new Student("Nguyễn Văn A", true, 8.0),
			new Student("Nguyễn Thị B", false, 7.5), new Student("Nguyễn Văn C", true, 9.0),
			new Student("Nguyễn Thị D", false, 6.5), new Student("Nguyễn Văn E", true, 3.5));

	public static void main(String[] args) {
		demo4();
	}

	private static void demo4() {
		Demo4Inter o = x -> System.out.println(x); 
		o.m1(2401);
	}

	private static void demo3() {
		// Dấu "-" trong "-sv1" cho giảm dần, bỏ đi để tăng dần
		Collections.sort(list, (sv1, sv2) -> -sv1.getMarks().compareTo(sv2.getMarks()));
		list.forEach(sv -> {
			System.out.println(">>Name: " + sv.getName());
			System.out.println(">>Name: " + sv.getGender());
			System.out.println(">>Name: " + sv.getMarks());
			System.out.println();
		});
	}

	private static void demo2() {
		list.forEach(sv -> {
			System.out.println(">>Name: " + sv.getName());
			System.out.println(">>Name: " + sv.getGender());
			System.out.println(">>Name: " + sv.getMarks());
			System.out.println();
		});
	}

	private static void demo1() {
		List<Integer> list = Arrays.asList(1, 8, 5, 6, 9, 3);
		list.forEach(n -> System.out.println(n));
	}

}

@FunctionalInterface
interface Demo4Inter {
	void m1(int x);

	default void m2() {
	}

	public static void m3() {
	}
}
