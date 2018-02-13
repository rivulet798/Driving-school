package gui.table.group;

import javax.swing.JFormattedTextField.AbstractFormatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateLabelFormatter extends AbstractFormatter {

    private String datePattern = "yyyy-MM-dd";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parseObject(text);
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            Calendar cal = (Calendar) value;
            String str = dateFormatter.format(cal.getTime());
            String[] strings = str.split("\\-");
            Calendar calendar = Calendar.getInstance();
            if (Integer.valueOf(strings[0]) < calendar.get(Calendar.YEAR)) {
                return "Неверная дата";
            }
            if (Integer.valueOf(strings[0]) > calendar.get(Calendar.YEAR)) {
                return str;
            }
            if (Integer.valueOf(strings[1])+1 < calendar.get(Calendar.MONTH)) {
                return "Неверная дата";
            }
            if (Integer.valueOf(strings[1]) > calendar.get(Calendar.MONTH)) {
                return str;
            }
            if (Integer.valueOf(strings[2]) < calendar.get(Calendar.DAY_OF_MONTH)) {
                return "Неверная дата";
            }
            return str;
        }
        return "";
    }

}
