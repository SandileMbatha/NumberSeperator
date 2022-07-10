package numberrangesummarizer;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;



/*
 * @TestRunner
 * Runs all the test cases
 * Prints false if there is a test case that failed
 * Other prints true
 */
public class TestRunner {
	
	 public static void main(String[] args) {
	      Result result = JUnitCore.runClasses(TestNumberSummarizer.class);
			
	      for (Failure failure : result.getFailures()) {
	         System.out.println(failure.getMessage());
	      }
	      System.out.println(result.wasSuccessful());
	}//main
}//TestRunner
