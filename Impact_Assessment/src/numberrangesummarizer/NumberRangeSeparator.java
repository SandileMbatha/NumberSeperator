package numberrangesummarizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/*
 * This class |NumberSeparator| Implements the |NumberRangeSummarizer| Interface
 * to produce a comma delimited list of numbers,
 * grouping the numbers into a range when they are sequential.
 * 
 */
public class NumberRangeSeparator implements NumberRangeSummarizer {
	
	// holds a state whether the current input from the user is correct or not-correct
	private boolean isInputFormatCorrect; 
	
	// returns the state of the input
	public boolean getInputFormatResults() {
		return this.isInputFormatCorrect;
	}
	
	// changes the state of the input from the user
	public void setIsInputFormatCorrect(boolean pIsInputFormatCorrect) {
		this.isInputFormatCorrect = pIsInputFormatCorrect;
	}
	
	/*
	 * Collects an input from the user
	 * validates if the user-input is in the correct format
	 * @param input - input from the user
	 * @return Collection - from the user's input
	 * @exception - throws an exception if the input is not the required format
	 */
	
	/* --------------------------------------------------[collect]---------*/
	@Override
	public List<Integer> collect(String pInput)throws IllegalArgumentException {
		
		List<Integer> collectInput = new ArrayList<>();
		try {
			String[] vInput = pInput.split(",");
			checkInput(vInput);
			for(int i=0; i < vInput.length; i++) {
				collectInput.add(Integer.parseInt(vInput[i]));
			}//for
			//ensure that the elements in the collection are in natural order.
			Collections.sort(collectInput);
		}//try
		catch(IllegalArgumentException ex) {		
			System.out.println(ex.getMessage());
			
		}//catch
		catch(NullPointerException ex) {
			System.out.println("Input from the user is null: "+ex.getMessage());
			throw ex;
		}//catch
		
		return collectInput;
	}//collect
	
	/* @helper function
	 * Collects an input from the user
	 * validates if the user-input is in the correct format
	 * @param input - input from the user
	 * @exception - throws an IllegalArgumentException if the input is not the required format
	 */
	/* ---------------------------------------------------[checkInput]---------*/
	public boolean checkInput(String[] pInput) throws IllegalArgumentException {
		
		// avoids code duplication since we use these lines of code in different lines
		String vSpace = ""; 
		String vInputCharAtI = "";  // variable for a character in the specified index position
		String vInputCharAtJ = "";  // variable for a character in the specified index position
		int size = pInput.length;   // length of the array
		
		// ----------------------------------
		// @switch - switch conditions according to the size of the input
		switch(size) {
			
			// case 0 - input length == 0
			case 0:
				
				this.isInputFormatCorrect = false;
				throw new  IllegalArgumentException("Input length must be greater than 0. Check the following example."
						+ "Expected [1,2,3,4,5] vs found []");
				
			// case 1 - input length == 1
			case 1:
				
				vInputCharAtI = pInput[0];
				if(vInputCharAtI.equals(vSpace)) {
					this.isInputFormatCorrect = false;
					throw new  IllegalArgumentException("Input Invalid, a number cannot be found. Found a comma instead in an input that has a length of 1. Check the following example."
							+ "Expected [1] vs found [,]");
				}//if
			// default - input length > 1
			default:
				
				for(int i=0, j=1; i < pInput.length-1; i++,j++) {
					
					//case 1: comma at first index
					if(i == 0) {
						vInputCharAtI = pInput[i];
						if(vInputCharAtI.equals(vSpace)) {
							this.isInputFormatCorrect = false;
							throw new  IllegalArgumentException("Input Invalid, expected to find a number in the first index position of the input but found a comma instead."
									+ "Check the following example. Expected [1,2,3,4] vs found [,1,2,3,4,5]");
						}//if
					}//if
					//case 2: comma at last index
					else if(j == size - 1) {
						vInputCharAtJ = pInput[j];
						if(vInputCharAtJ.equals("")) {
							this.isInputFormatCorrect = false;
							throw new  IllegalArgumentException("Input Invalid, expected to find a number in the last index position of the input but found a comma instead. "
									+ "check the following example. Expected [1,2,3,4,5] vs found [1,2,,3,4,5,]");
						}//if
						
					}//if
					//case 3: numbers in the input collection are separated by a single comma
					else {
						vInputCharAtI = pInput[i];
						vInputCharAtJ = pInput[j];
						if(vInputCharAtI.equals(vSpace) || vInputCharAtJ.equals(vSpace)) {
							this.isInputFormatCorrect = false;
							throw new  IllegalArgumentException("Input Invalid, expected to find a number but found a comma instead. check the following example."
									+ " Expected [1,2,3,4,5] vs found [1,2,,3,4,5]");
						}//if
					}//else		
				}//for
				
		}//switch
		return this.isInputFormatCorrect;
	}//checkInput
	
	
	/* @summarizeCollection
	 * Collects the parameter pInput after being checked for validity.
	 * @exception - throws an IllegalArgumentException if the input is not the required format
	 */
	/* -----------------------[summarizeCollection]---------*/
	@Override
	public String summarizeCollection(List<Integer> pInput) throws NullPointerException{
		
		try {
			if(pInput == null) {
				throw new NullPointerException();
			}//if
			// | The string to be returned
			String vSummarizedCollection = "";
			
			// | Checks if two consecutive numbers are in range
			boolean vNumbersAreInIncrementalByOne = false;
			
			// | Checks if two consecutive numbers are identical
			boolean vDuplicateNums = false;
		
			for(int i=0,j=1; i < pInput.size()-1; i++,j++) {
				if(pInput.get(i) == pInput.get(j)-1) {
					
					if(!vNumbersAreInIncrementalByOne) {
						
						if(vDuplicateNums) {
							vSummarizedCollection += pInput.get(i) + "-";
							vNumbersAreInIncrementalByOne = true;		
							vDuplicateNums = false;
						}//if
						else {
							vSummarizedCollection += pInput.get(i) + "-";
							vNumbersAreInIncrementalByOne = true;	
						}//else
						
					}//if
					if(j == pInput.size()-1) {
						vSummarizedCollection += pInput.get(j);
					}//if
				}//if
				else if(pInput.get(i) == pInput.get(j)) {
					if(!vDuplicateNums) {		
						vDuplicateNums = true;
					}//if
					if(j == pInput.size()-1) {
						vSummarizedCollection += pInput.get(j);
					}//if
					
				}
				else {
					vDuplicateNums = false;
					vNumbersAreInIncrementalByOne = false;
					vSummarizedCollection += pInput.get(i) + ",";
					if(j == pInput.size()-1) {
						vSummarizedCollection += pInput.get(j);
					}//if
				}//else
			}//for
			
			if(String.valueOf(vSummarizedCollection.charAt(vSummarizedCollection.length()-1)).equals(",")) {
				vSummarizedCollection = vSummarizedCollection.substring(0, vSummarizedCollection.length()-1);
			}//if
			return vSummarizedCollection;
		}//try
		catch(NullPointerException ex) {
			System.out.println("pInput is null :"+ex.getMessage());
			return "Method failed by a null pointer while processing";
		}//catch
		
	}//summarizeCollection

}//NumberSeparator

