import java.util.List;
import java.util.LinkedList;

/**
 * Your implementations of various string searching algorithms.
 *
 * @author Sreeramamurthy Tripuramallu
 * @version 1.0
 */
public class StringSearching {

    /**
     * Knuth-Morris-Pratt (KMP) algorithm that relies on the failure table (also
     * called failure function). Works better with small alphabets.
     *
     * Make sure to implement the failure table before implementing this method.
     *
     * @throws IllegalArgumentException if the pattern is null or of length 0
     * @throws IllegalArgumentException if text is null
     * @param pattern the pattern you are searching for in a body of text
     * @param text the body of text where you search for pattern
     * @return list of integers representing the first index a match occurs or
     * an empty list if the text is of length 0
     */
    public static List<Integer> kmp(CharSequence pattern, CharSequence text) {
        if (pattern == null || pattern.length() == 0 || text == null) {
            throw new IllegalArgumentException("Input Data not valid");
        } else {
            LinkedList<Integer> returnList = new LinkedList<Integer>();
            int[] failureTable = buildFailureTable(pattern);
            int m = pattern.length();
            int n = text.length();
            int i = 0;
            int j = 0;
            while ((i < n) && ((n - i) >= (m - j))) {
                char textChar = text.charAt(i);
                char patternChar = pattern.charAt(j);
                if (textChar == patternChar) {
                    if (j == m - 1) {
                        returnList.add(i - j);
                        i++;
                        j = failureTable[j];
                    } else {
                        i++;
                        j++;
                    }
                } else if (j > 0) {
                    j = failureTable[j - 1];
                } else {
                    i++;
                }
            }
            return returnList;
        }

    }

    /**
     * Builds failure table that will be used to run the Knuth-Morris-Pratt
     * (KMP) algorithm.
     *
     * The table built should be the length of the input text.
     *
     * Note that a given index i will be the largest prefix of the pattern
     * indices [0..i] that is also a suffix of the pattern indices [1..i].
     * This means that index 0 of the returned table will always be equal to 0
     *
     * Ex. ababac
     *
     * table[0] = 0
     * table[1] = 0
     * table[2] = 1
     * table[3] = 2
     * table[4] = 3
     * table[5] = 0
     *
     * @throws IllegalArgumentException if the pattern is null
     * @param pattern a {@code CharSequence} you are building failure table for
     * @return integer array of size text.length that you are building a failure
     * table for
     */
    public static int[] buildFailureTable(CharSequence pattern) {
        if (pattern == null)  {
            throw new IllegalArgumentException("Input not valid");
        } else {
            int m = pattern.length();
            int[] failureTable = new int[m];
            int j = 1;
            int k = 0;
            int counter = 0;
            while (j < m) {
                if (pattern.charAt(j) == pattern.charAt(k)) {
                    counter++;
                    failureTable[j] = counter;
                    j++;
                    k++;
                } else if (k != 0) {
                    k = 0;
                    counter = 0;
                } else {
                    counter = 0;
                    failureTable[j] = counter;
                    j++;
                }
            }
            return failureTable;
        }
    }

    /**
     * Boyer Moore algorithm that relies on last table. Works better with large
     * alphabets.
     *
     * Make sure to implement the table before implementing this method.
     *
     * @throws IllegalArgumentException if the pattern is null or of length 0
     * @throws IllegalArgumentException if text is null
     * @param pattern the pattern you are searching for in a body of text
     * @param text the body of text where you search for pattern
     * @return list of integers representing the first index a match occurs or
     * an empty list if the text is of length 0
     */
    public static List<Integer> boyerMoore(CharSequence pattern,
            CharSequence text) {
        if (pattern == null || pattern.length() == 0 || text == null) {
            throw new IllegalArgumentException("Inputs not valid");
        } else {
            LinkedList<Integer> returnList = new LinkedList<Integer>();
            int[] lastTable = buildLastTable(pattern);
            int m = pattern.length();
            int n = text.length();
            int i = m - 1;
            int j = m - 1;
            while (i < n) {
                char textChar = text.charAt(i);
                char patternChar = pattern.charAt(j);
                if (textChar == patternChar) {
                    if (j == 0) {
                        returnList.add(i);
                        i += m;
                        j = m - 1;
                    } else {
                        j--;
                        i--;
                    }
                } else {
                    int l = lastTable[textChar];
                    i += m - Math.min(j, l + 1);
                    j = m - 1;
                }
            }
            return returnList;
        }
    }

    /**
     * Builds last occurrence table that will be used to run the Boyer Moore
     * algorithm.
     *
     * Note that each char x will have an entry at table[x].
     * Each entry should be -1 if x is not in the pattern or the last index of x
     * where x is a particular character in your pattern.
     *
     * Ex. octocat
     *
     * table[o] = 3
     * table[c] = 4
     * table[t] = 6
     * table[a] = 5
     * table[everything else] = -1
     *
     * HINT: Characters auto cast to their corresponding int in Unicode (UTF-16)
     *
     * @throws IllegalArgumentException if the pattern is null
     * @param pattern a {@code CharSequence} you are building last table for
     * @return integer array of size {@code (Character.MAX_VALUE + 1)}
     * containing the mapping for all characters in Unicode
     */
    public static int[] buildLastTable(CharSequence pattern) {
        if (pattern == null) {
            throw new IllegalArgumentException("Input not valid");
        } else {
            int[] table = new int[Character.MAX_VALUE + 1];
            for (int i = 0; i < table.length; i++) {
                table[i] = -1;
            }
            for (int i = 0; i < pattern.length(); i++) {
                table[pattern.charAt(i)] = i;
            }
            return table;
        }

    }

    /**
     * Prime base used for Rabin-Karp hashing.
     * DO NOT EDIT!
     */
    private static final int BASE = 433;

    /**
     * Runs Rabin-Karp algorithm. Generate initial hash, and compare it with
     * hash from substring of text same length as pattern. If the two
     * hashes match compare their individual characters, else update hash
     * and continue.
     *
     * @throws IllegalArgumentException if the pattern is null or of length 0
     * @throws IllegalArgumentException if text is null
     * @param pattern a string you're searching for in a body of text
     * @param text the body of text where you search for pattern
     * @return list of integers representing the first index a match occurs or
     * an empty list if the text is of length 0
     */
    public static List<Integer> rabinKarp(CharSequence pattern,
            CharSequence text) {
        if (pattern == null || pattern.length() == 0 || text == null) {
            throw new IllegalArgumentException("Inputs not valid");
        } else {
            LinkedList<Integer> returnList = new LinkedList<Integer>();
            if (text.length() == 0) {
                return returnList;
            }
            int m = pattern.length();
            int n = text.length();
            int i = 0;
            int j = 0;
            int hpattern = generateHash(pattern, m);
            int htext = generateHash(text, m);
            while (i < (n - m)) {
                if (hpattern == htext) {
                    if (checkHash(i, pattern, text)) {
                        returnList.add(i);
                    }
                    i++;
                    j = 0;
                } else {
                    htext = updateHash(
                            htext, m, text.charAt(i), text.charAt(
                                    i + m));
                    i++;
                    j = 0;
                }
            }
            if (checkHash(n - m, pattern, text)) {
                returnList.add(n - m);
            }
            return returnList;
        }
    }

    /**
     * Hash function used for Rabin-Karp. The formula for hashing a string is:
     *
     * sum of: c * BASE ^ (pattern.length - 1 - i), where c is the integer
     * value of the current character, and i is the index of the character
     *
     * For example: Hashing "bunn" as a substring of "bunny" with base 433 hash
     * = b * 433 ^ 3 + u * 433 ^ 2 + n * 433 ^ 1 + n * 433 ^ 0 = 98 * 433 ^ 3 +
     * 117 * 433 ^ 2 + 110 * 433 ^ 1 + 110 * 433 ^ 0 = 7977892179
     *
     * Do NOT use {@code Math.pow()} in this method.
     *
     * @throws IllegalArgumentException if current is null
     * @throws IllegalArgumentException if length is negative or greater
     *     than the length of current
     * @param current substring you are generating hash function for
     * @param length the length of the string you want to generate the hash for,
     * starting from index 0. For example, if length is 4 but current's length
     * is 6, then you include indices 0-3 in your hash (and pretend the actual
     * length is 4)
     * @return hash of the substring
     */
    public static int generateHash(CharSequence current, int length) {
        if (current == null || length < 0 || length > current.length()) {
            throw new IllegalArgumentException("Inputs not valid");
        } else {
            int total = 0;
            for (int i = 0; i < length; i++) {
                total += current.charAt(i) * pow(BASE, length - 1 - i);
            }
            return total;
        }

    }

    /**
     * Updates a hash in constant time to avoid constantly recalculating
     * entire hash. To update the hash:
     *
     *  remove the oldChar times BASE raised to the length - 1, multiply by
     *  BASE, and add the newChar.
     *
     * For example: Shifting from "bunn" to "unny" in "bunny" with base 433
     * hash("unny") = (hash("bunn") - b * 433 ^ 3) * 433 + y * 433 ^ 0 =
     * (7977892179 - 98 * 433 ^ 3) * 433 + 121 * 433 ^ 0 = 9519051770
     *
     * The computation of BASE raised to length - 1 may require O(n) time,
     * but the method should otherwise run in O(1).
     *
     * Do NOT use {@code Math.pow()} in this method.
     *
     * @throws IllegalArgumentException if length is negative
     * @param oldHash hash generated by generateHash
     * @param length length of pattern/substring of text
     * @param oldChar character we want to remove from hashed substring
     * @param newChar character we want to add to hashed substring
     * @return updated hash of this substring
     */
    public static int updateHash(int oldHash, int length, char oldChar,
            char newChar) {
        if (length < 0) {
            throw new IllegalArgumentException("Inputs not valid");
        } else {
            return BASE * (oldHash
                    - (oldChar * pow(BASE, length - 1))) + ((int) newChar);
        }
    }

    /**
     * Determines the power based on the value and degree
     * @param value the value to be multiplied
     * @param degree the number of times value is multiplied
     * @return the total
     */
    private static int pow(int value, int degree) {
        int total = 1;
        for (int i = 0; i < degree; i++) {
            total = (value * total);
        }
        return total;
    }

    /**
     * Checks the hash based on the pattern and text
     * @param i the index of the hash
     * @param pattern the pattern of the string
     * @param text the text
     * @return if the hash is valid
     */
    private static boolean checkHash(int i,
                                     CharSequence pattern, CharSequence text) {
        int m = pattern.length();
        int count = 0;
        while (count < pattern.length()
                && text.charAt(i) == pattern.charAt(count)) {
            if (count == (m - 1)) {
                return true;
            }
            i++;
            count++;
        }
        return false;
    }
}
