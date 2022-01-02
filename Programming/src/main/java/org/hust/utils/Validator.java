package org.hust.utils;

import java.util.ArrayList;
import java.util.List;

public class Validator {
    public static boolean validateNoSpecialCharacterString(String string) {
        if (string.isBlank()) {
            return false;
        }

        for (char c : string.toCharArray()) {
            if (!(Character.isLetterOrDigit(c))) {
                return false;
            }
        }
        return true;
    }

    public static boolean validateSomeSpecialCharacterString(String string, char... allowedSpecialCharacters) {
        if (string.isBlank()) {
            return false;
        }

        List<Character> specialCharacters = new ArrayList<>();
        for(char c: allowedSpecialCharacters){
            specialCharacters.add(c);
        }
        for (char c : string.toCharArray()) {
            if (!(Character.isLetterOrDigit(c) || (specialCharacters.contains(c)))) {
                return false;
            }
        }
        return true;
    }
}
