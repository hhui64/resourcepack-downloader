package cn.cscmoe.resourcepackdownloader.util;

import java.util.regex.Pattern;

public class ValidationHelper {
    public static boolean isURL(String str) {
        String regex = "^(http|https)://([a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}(/[a-zA-Z0-9-_.?=/#%&]*)?$";

        return Pattern.compile(regex).matcher(str).matches();
    }
}
