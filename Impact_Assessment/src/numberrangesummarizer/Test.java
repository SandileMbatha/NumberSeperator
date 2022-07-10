package numberrangesummarizer;

import java.util.List;


public class Test {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		NumberRangeSeparator numSeparator = new NumberRangeSeparator();
		String input = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
		
		List<Integer> collectInput = numSeparator.collect(input);
		System.out.println(numSeparator.getInputFormatResults());
		System.out.println(collectInput);
		System.out.println("-------------------------------------------");
		System.out.println(numSeparator.summarizeCollection(collectInput));

	}
}
