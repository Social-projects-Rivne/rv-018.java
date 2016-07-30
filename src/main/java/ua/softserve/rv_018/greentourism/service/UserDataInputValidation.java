package ua.softserve.rv_018.greentourism.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDataInputValidation {

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*.(com|org|au|net|biz|me|name|tel|pw|asia|cc|in|it|tv|ws|global|host|email|club|company|domains|fitness|finansia|help|hous|works|top|uno|website)$";


    public static boolean validateEmail(final String hex) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(hex);
        return matcher.matches();
    }
}
