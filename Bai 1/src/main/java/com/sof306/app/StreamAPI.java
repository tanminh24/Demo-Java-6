package com.sof306.app;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.sof306.bean.Student;

public class StreamAPI {

	static List<Student> list = Arrays.asList(new Student("Nguyễn Văn A", true, 8.0),
			new Student("Nguyễn Thị B", false, 7.5), new Student("Nguyễn Văn C", true, 9.0),
			new Student("Nguyễn Thị D", false, 6.5), new Student("Nguyễn Văn E", true, 5.5));
	
	public static void main(String[] args) {
		demo4();
	}

	private static void demo4() {
		double average = list.stream()
				.mapToDouble(sv->sv.getMarks())
				.average().getAsDouble();
		System.out.println("Average: " + average);
		
		double sum = list.stream()
				.mapToDouble(sv->sv.getMarks())
				.sum();
		System.out.println("Sum: " + sum);
		
		double minMarks = list.stream()
				.mapToDouble(sv->sv.getMarks())
				.min().getAsDouble();
		System.out.println("Min: "+ minMarks);
		
		boolean allPassed = list.stream()
				.allMatch(sv->sv.getMarks()>=5);
		System.out.println("All Passed: "+ allPassed);
		
		Student minSV = list.stream()
				.reduce(list.get(0), (min,sv)->sv.getMarks()<min.getMarks() ?sv:min);
		System.out.println("minSV: " + minSV.getName());
	}

	private static void demo3() {
		List<Student> result = list.stream()
				.filter(sv -> sv.getMarks()>7)
				.peek(sv->sv.setName(sv.getName().toUpperCase()))
				.collect(Collectors.toList());
		result.forEach(sv->{
			System.out.println(">>Name: " + sv.getName());
			System.out.println(">>Gender: " + sv.getGender());
			System.out.println(">>Mark: " + sv.getMarks());
			System.out.println();
		});
		
	}

	private static void demo2() {
		List<Integer> list = Arrays.asList(1,5,3,2,8);
		List<Double> result = list.stream()   		//Stream<Integer>
				.filter(n->n%2==0)					//Stream<Integer>
				.map(n->Math.sqrt(n))				//Stream<Double>
				.peek(System.out::println)			//Stream<Double>
				.collect(Collectors.toList());		//List<Double>
				
	}

	private static void demo1() {
		// C1:
		Stream<Integer> stream1 = Stream.of(1, 5, 3, 2, 8);
		stream1.forEach(System.out::println);
		// C2:
		List<Integer> list = Arrays.asList(1, 5, 3, 2, 8);
		list.stream().forEach(System.out::println);
	}

}
