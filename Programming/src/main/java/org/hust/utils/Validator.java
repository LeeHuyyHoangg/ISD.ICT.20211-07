package org.hust.utils;

import org.junit.platform.commons.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Validator {
    public static boolean validateNoSpecialCharacterString(String string) {
        if (StringUtils.isBlank(string)) {
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
        if (StringUtils.isBlank(string)) {
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
