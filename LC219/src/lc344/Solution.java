package lc344;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(reverseString("abcd"));
	}

	 public static String reverseString(String s) {
		 	char[] chars = s.toCharArray();
	        for(int i=0; i<s.length()/2; i++){
	        	char tmp = chars[i];
	        	int idx = s.length()-i-1;
	        	chars[i] = chars[idx];
	        	chars[idx] = tmp;
	        }
	        return new String(chars);
	    }
}
