package main.java;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class StringUtil {

	//Method to get distinct characters in a string and returns them as a string
		static String getDistinctElements(String input) {
			//LinkedHashSet which can contain only unique elements is used to find distinct characters.
		    Set<Character> inputSet = new LinkedHashSet<Character>();
		    StringBuilder result = new StringBuilder();
		    for (int i = 0; i < input.length(); i++) {
		        if (inputSet.add(input.charAt(i))) {
		        	result.append(input.charAt(i));
		        }
		    }
		    return result.toString();
		}
		
		/*
		 Algorithm description:
		 . First find distinct characters in the string.
		 . If only one distinct character then return 0 as stated.
		 . Store number of occurrences of each character in an array called numberOfOccurences and sort this array.
		 . For example if your string is "ccaaffddecee" your sorted numberOfOccurences array contains [2, 2, 2, 3, 3]
		 . All the numbers should be distinct in this array to achieve our aim. Starting from the maximum and comparing with
		 	the next number in every step:
		 		- If it is greater than the next number; continue because it is different.
		 		- If it is equal, then you have to decrease the next number by 1 which adds 1 point to our result.
		 		- If it is smaller than the next number and <= 1 then you have to decrease all remaining until zero.
		 			So adding all to our result. Because that means there is no chance to make the remaining different.
		 		- If it is smaller than the next number and > 1 then you have to decrease next number until you make it 
		 			smaller than current.
		 */
		public static int getMinimumNumberOfLettersToRemove(String input) {
			int result = 0;
			String distinctElements = getDistinctElements(input);
			if(distinctElements.length() == 1) {
				return 0;
			}
			
			char[] distinctChars = distinctElements.toCharArray();
			long[] numberOfOccurences = new long[distinctElements.length()];
			int i = 0;
			for(char c : distinctChars) {
				long numberOfOccurence = input.chars().filter(ch -> ch == c).count();
				numberOfOccurences[i] = numberOfOccurence;
				i++;
			}
			
			Arrays.sort(numberOfOccurences);
			for(int j = numberOfOccurences.length-1; j>0; j--) {
				
				if(numberOfOccurences[j] > numberOfOccurences[j-1]) {
					continue;
				} else {
					if(numberOfOccurences[j] == numberOfOccurences[j-1]) {
						numberOfOccurences[j-1] = numberOfOccurences[j-1] - 1;
						result ++;
					} else {
						if(numberOfOccurences[j] <= 1) {
							for(int k = j-1; k>-1; k--) {
								result += numberOfOccurences[k];
							}
							break;
						} else {
							while(numberOfOccurences[j-1]>=numberOfOccurences[j]) {
								numberOfOccurences[j-1]--;
								result ++;
							}
						}
					}
				}
			}
			
			return result;
		}
}
