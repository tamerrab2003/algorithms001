package com.tdev.algorithms;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

/**
 * get Max hidden chips
 *
 * Problem Description:
 * You are given:
 * A list of chip heights (and widths, since they are squares), e.g., arr = [2, 4, 6, 14].
 * An integer k, which represents the number of times you can hide chips below other chips.
 * The goal is to find the maximum number of chips that can be hidden below other chips, following these rules:
 * A chip of size h x h can hide at most one chip below it, and the hidden chip must have a size smaller than h x h (i.e., its height and width must be less than h).
 * Chips cannot be hidden under chips that are already hiding another chip.
 * Each chip can only be used once—either as the chip being hidden or as the chip hiding another chip.
 * You can perform this operation up to k times.
 *
 * @author Tamer.Awad
 */
public class MaxHiddenChips {

    public static int getMaxHiddenChips(List<Integer> list1, int k) {
        // Step 1: Sort the list in ascending order
        List<Integer> list = Collections.unmodifiableList(list1);
        try {
            Collections.sort(list);
        } catch (UnsupportedOperationException e) {}

        // Step 2: Initialize pointers and counter
        int i = 0; // Pointer for the chip to be hidden
        int j = 1; // Pointer for the chip that will hide another chip
        int hidden = 0; // Counter for the number of hidden chips

        Set<Integer> usedChips = new HashSet<>();
        // Step 3: Iterate through the list and perform hiding operations
        while (i < list.size() && j < list.size() && hidden < k) {
            if(usedChips.contains(i)) {
                i++;
                continue;
            }
            if(usedChips.contains(j)) {
                j++; // Move to the next chip that can hide another chip
                continue;
            }
            if (list.get(i) < list.get(j)) {
                // track added chips
                usedChips.add(i);
                usedChips.add(j);
                // Hide list[i] under list[j]
                hidden++;
                i++; // Move to the next chip to be hidden
                j++; // Move to the next chip that can hide another chip
            } else {
                // Move to the next chip that can hide another chip
                j++;
            }
        }

        // Step 4: Return the total number of hidden chips
        return hidden;
    }

    public static void main(String[] args) {
        // Example usage
        List<Integer> chips = List.of(2, 4, 6, 14);
        int k = 3;
        System.out.println(getMaxHiddenChips(chips, k)); // Output: 2
        System.out.println(getMaxHiddenChips(List.of(2, 4, 6, 14, 7, 21), 3)); // Output: 3
    }
}