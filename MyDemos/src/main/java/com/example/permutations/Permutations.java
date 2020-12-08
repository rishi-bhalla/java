package com.example.permutations;

import java.util.ArrayList;
import java.util.List;

/**
 * This class finds all the permutations of the input string. It follows the backtracking paradigm. Below is the approach of the algorithm :
 * 1. Swap the two elements and fix one of them.
 * 2. After fixing one of the element, find the remaining permutations of the string.
 * 3. One all the permutations are found, we backtrack.
 *
 * The resource folder contains the detailed explanation of the algorithm along with the diagram for better explanation.
 */
public class Permutations {

    private List<String> permutationsList = new ArrayList<String>();

    private String swap(String str, int i, int j) {
        char[] charArray = str.toCharArray();
        char temp;
        temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }

    private void permute(String str, int l, int r) {
        if(l == r) {
            //System.out.println(str);
            permutationsList.add(str);
        } else {
            for(int i=l; i<=r; i++) {
                str = swap(str, l, i);      //swap
                permute(str, l+1, r);    // find remaining permutations
                str = swap(str, l, i);      //backtracking
            }
        }
    }

    public List<String> getPermutationsList() {
        return permutationsList;
    }

    public static void main(String args[]) {
        Permutations permutations = new Permutations();
        String str = "1234";
        int len = str.length();
        permutations.permute(str, 0, len-1);
        System.out.println("The list of all the permutations is : " + permutations.getPermutationsList());
        System.out.println("Length : " + permutations.getPermutationsList().size());
    }
}
