/** Functions for checking if a given string is an anagram. */
public class Anagram {
	public static void main(String args[]) {
		// Tests the isAnagram function.
		System.out.println(isAnagram("silent", "listen")); // true
		System.out.println(isAnagram("William Shakespeare", "I am a weakish	speller")); // true
		System.out.println(isAnagram("Madam Curie", "Radium came")); // true
		System.out.println(isAnagram("Tom Marvolo Riddle", "I am Lord Voldemort"));
		// true

		// // Tests the preProcess function.
		System.out.println(preProcess("What? No way!!!"));

		// Tests the randomAnagram function.
		System.out.println("silent and " + randomAnagram("silent") + " are  anagrams.");

		// Performs a stress test of randomAnagram
		String str = "1234567";
		Boolean pass = true;
		// 10 can be changed to much larger values, like 1000
		for (int i = 0; i < 10; i++) {
			String randomAnagram = randomAnagram(str);
			System.out.println(randomAnagram);
			pass = pass && isAnagram(str, randomAnagram);
			if (!pass)
				break;
		}
		System.out.println(pass ? "test passed" : "test Failed");
	}

	// Returns true if the two given strings are anagrams, false otherwise.
	public static boolean isAnagram(String str1, String str2) {
		String cleanStr1 = preProcess(str1);
		String cleanStr2 = preProcess(str2);
		if (cleanStr1.length() != cleanStr2.length())
			return false;
		for (int i = 0; i < cleanStr2.length(); i++) {
			if (timesCharAtStr(cleanStr1.charAt(i), cleanStr2) != timesCharAtStr(cleanStr1.charAt(i), cleanStr1))
				return false;
		}

		return true;
	}

	public static int timesCharAtStr(char c, String str) {
		if (str.indexOf(c) == -1)
			return 0;

		int cnt = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == c) {
				cnt++;
			}
		}
		return cnt;
	}

	// Returns a preprocessed version of the given string: all the letter characters
	// are converted
	// to lower-case, and all the other characters are deleted, except for spaces,
	// which are left
	// as is. For example, the string "What? No way!" becomes "whatnoway"
	public static String preProcess(String str) {
		String tmpStr = "";
		for (int i = 0; i < str.length(); i++) {
			char currentChar = str.charAt(i);
			if (currentChar >= 65 && currentChar <= 90) {
				tmpStr += (char) (currentChar + 32);
			} else if (currentChar >= 97 && currentChar <= 122) {
				tmpStr += (char) currentChar;
			}
		}
		return tmpStr;
	}

	// Returns a random anagram of the given string. The random anagram consists of
	// the same
	// characters as the given string, re-arranged in a random order.
	public static String randomAnagram(String str) {
		String mixStr = "";
		String tmpStr = str;
		for (int i = 0; i < str.length(); i++) {
			int randomNum = (int) (Math.random() * (tmpStr.length()));
			mixStr += tmpStr.charAt(randomNum);
			tmpStr = tmpStr.substring(0, randomNum) + tmpStr.substring(randomNum + 1);
		}
		return mixStr;
	}
}
