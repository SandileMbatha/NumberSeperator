package numberrangesummarizer;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.*;

public class TestNumberSummarizer {

	NumberRangeSeparator numSeparator = new NumberRangeSeparator();
	
	
	/*
	 * @testCommaAtFirstIndex
	 * Checks if the user input a correct list of numbers
	 * @throws an IllegalArgumentException if a decimal is in the first index
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCommaAtFirstIndex() {
		
		// String vInput = ",1,3,6,7,8"; Original input from the user
		String[] vInput = {"","1","3","6","7","8"};
		numSeparator.checkInput(vInput);
		
	}//testCommaAtFirstIndex
	
	
	/*
	 * @testCommaAtLastIndex
	 * Checks if the user input a correct list of numbers
	 * @throws an IllegalArgumentException if a decimal is in the last index
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCommaAtLastIndex() {
		
		
		// String vInput = "1,3,6,7,8,"; Original input from the user
		String[] vInput = {"1","3","6","7","8",""};
		numSeparator.checkInput(vInput);
		
	}//testCommaAtLastIndex
	
	
	/*
	 * @testCommasAtConsecutiveIndexes
	 * Checks if the user input a correct list of numbers
	 * @throws an IllegalArgumentException if the comma(s) is/are any where in between
	 * the input. IF they are in consecutive indexes
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCommasAtConsecutiveIndexes() {
		
		//String input = "11,32,,6,7,8,"; Original input from the user
		String[] vInput = {"11","32","","","6","7","8"};
		numSeparator.checkInput(vInput);
		
		
		// five commas	
		//String input2 = "11,32,,,,,6,7,8,"; Original input from the user
		String[] vInput2 = {"11","32","","","","","6","7","8"};
		numSeparator.checkInput(vInput2);
		
		
		// several consecutive commas at different locations
		//String input3 = ",,,,11,32,,,,,6,7,8,"; Original input from the user
		String[] vInput3 = {"","","","11","32","","","","","6","7","8"};
		numSeparator.checkInput(vInput3);
		
		
	}//testCommasAtConsecutiveIndexes
	
	
	/*
	 * @testNullPointer
	 * Checks if the user input a correct list of numbers
	 * @throws an IllegalArgumentException if the comma(s) is/are any where in between
	 * the input. IF they are in consecutive indexes
	 */
	@Test(expected = NullPointerException.class)
	public void testNullPointer() {
		
		// String vInput = ",1,3,6,7,8"; Original input from the user
		String vInput = null;
		numSeparator.collect(vInput);
		
	}//testNullPointer
	
	
	/*
	 * @testCorrectFormat
	 * Checks if the user input a correct list of numbers
	 * Uses checkInput method - returns a boolean output
	 */
	@Test
	public void testCorrectFormat() {
		
		// String input = "1,3,6,7,8";  Original input from the user
		String[] vInput = {"1","3","6","7","8"}; 
		numSeparator.setIsInputFormatCorrect(true);
		numSeparator.checkInput(vInput);
		boolean expected = true;
		boolean actual = numSeparator.getInputFormatResults();
		assertEquals(expected,actual);
		
		
		//String input2 = "100,32,6,7,1,-66,55"; Original input from the user
		String[] vInput2 = {"100","32","6","71","1","-66","55"};
		numSeparator.setIsInputFormatCorrect(true);
		numSeparator.checkInput(vInput2);
		expected = true;
		actual = numSeparator.getInputFormatResults();
		assertEquals(expected,actual);
		
		
		//String input3 = "1,2,2,2,3,4,4,5,7,7,7,7,8,9,10"; Original input from the user
		String[] vInput3 = {"1","2","2","2","4","4","5","7","7","7","7","8","9","10"};
		numSeparator.setIsInputFormatCorrect(true);
		numSeparator.checkInput(vInput3);
		expected = true;
		actual = numSeparator.getInputFormatResults();
		assertEquals(expected,actual);
	}//testCommaAtLastIndex
	
	
	/*
	 * @testSummarizedCollection
	 * Checks if the numbers have been properly summarized according
	 * to their range(s) in a case where such case occurs.
	 * returns a |summarized string| from the method summarizeCollection -
	 * we compare that  |summarized string| with what we expect the method to return 
	 */
	@Test
	public void testSummarizedCollection() {
		
		String input1 = "1,3,6,7,8";
		List<Integer> list = numSeparator.collect(input1);
		String expected = "1,3,6-8";
		String actual = numSeparator.summarizeCollection(list);
		assertEquals(expected,actual);
		
		String input2 = "1,2,3,4,5,8,10,11,12,13,22,78";
		list = numSeparator.collect(input2);
		expected = "1-5,8,10-13,22,78";
		actual = numSeparator.summarizeCollection(list);
		assertEquals(expected,actual);
		
		String input3 = "1,2,2,2,3,4,4,5,7,7,7,7,8,9,10";
		list = numSeparator.collect(input3);
		expected = "1-5,7-10";
		actual = numSeparator.summarizeCollection(list);
		assertEquals(expected,actual);
		
		String input4 = "1,2,3,8,-2,44,-222,4456,7,15,9,64,-2,-55";
		list = numSeparator.collect(input4);
		expected = "-222,-55,-2,1-3,7-9,15,44,64,4456";
		actual = numSeparator.summarizeCollection(list);
		assertEquals(expected,actual);
		
		String input5 = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
		list = numSeparator.collect(input5);
		expected = "1,3,6-8,12-15,21-24,31";
		actual = numSeparator.summarizeCollection(list);
		assertEquals(expected,actual);
	}//testSummarizedCollection
	

}//TestNumberSeparator

