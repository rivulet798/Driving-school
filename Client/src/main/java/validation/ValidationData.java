package validation;

import gui.table.teacher.AddTeacher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationData {

    private static final String REGEX_FOR_NAME_SURNAME = "([A-Z]{1}[a-z]+)|([А-Я]{1}[а-я]+)";
    private static final String REGEX_FOR_LOGIN = "([a-zA-Z]+\\d*)+";
    private static final String REGEX_FOR_PASSWORD = "\\w+";
    private static final String REGEX_FOR_PHONE = "\\+375[0-9]{9}";
    private static final String REGEX_FOR_EXPIRIENCE = "\\d+";
    private static final String REGEX_FOR_CATEGORY = "[A-D]";
    private static final String REGEX_FOR_DATE = "(19|20)\\d\\d-((0[1-9]|1[012])-(0[1-9]|[12]\\d)|(0[13-9]|1[012])-30|(0[13578]|1[02])-31)";
    private static Pattern pattern;
    private static Matcher matcher;

    public static boolean checkNameSurnamePatronymic(String name) {
        if(name.isEmpty())
            return false;
        pattern = Pattern.compile(REGEX_FOR_NAME_SURNAME);
        matcher = pattern.matcher(name);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkLogin(String login) {
        if(login.isEmpty())
            return false;
        pattern = Pattern.compile(REGEX_FOR_LOGIN);
        matcher = pattern.matcher(login);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkPassword(char[] password) {
        String str = new String(password);
        if(str.isEmpty())
            return false;
        pattern = Pattern.compile(REGEX_FOR_PASSWORD);
        matcher = pattern.matcher(str);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkPhone(String phoneNumber) {
        if(phoneNumber.isEmpty())
            return false;
        pattern = Pattern.compile(REGEX_FOR_PHONE);
        matcher = pattern.matcher(phoneNumber);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkExpirience(String expirience) {
        if(expirience.isEmpty())
            return false;
        pattern = Pattern.compile(REGEX_FOR_EXPIRIENCE);
        matcher = pattern.matcher(expirience);
        if (matcher.matches()) {
            if(Integer.parseInt(expirience) < 60) {
                return true;
            }
            else{
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean checkGroupNumber(String groupNumber) {
        if(groupNumber.isEmpty())
            return false;
        pattern = Pattern.compile(REGEX_FOR_EXPIRIENCE);
        matcher = pattern.matcher(groupNumber);
        if (matcher.matches()) {
            if(Integer.parseInt(groupNumber) < 300) {
                return true;
            }
            else{
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean checkCategory(String category) {
        if(category.isEmpty())
            return false;
        pattern = Pattern.compile(REGEX_FOR_CATEGORY);
        matcher = pattern.matcher(category);
        if (matcher.matches()) {
                return true;
            }
            else{
                return false;
            }
    }

    public static boolean checkMaxNumberOfStudents(String maxNumberOfStudents) {
        if(maxNumberOfStudents.isEmpty())
            return false;
        pattern = Pattern.compile(REGEX_FOR_EXPIRIENCE);
        matcher = pattern.matcher(maxNumberOfStudents);
        if (matcher.matches()) {
            if(Integer.parseInt(maxNumberOfStudents) < 30) {
                return true;
            }
            else{
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean checkDate(String date) {
        if(date.isEmpty())
            return false;
        pattern = Pattern.compile(REGEX_FOR_DATE);
        matcher = pattern.matcher(date);
        if (matcher.matches()) {
            return true;
        }
        else{
            return false;
        }
    }


}
