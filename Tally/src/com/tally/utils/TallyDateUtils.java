package com.tally.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TallyDateUtils {

    private static final SimpleDateFormat DAY_FORMAT = new SimpleDateFormat("MM月dd日");
    
    private static final SimpleDateFormat MONTH_FORMAT = new SimpleDateFormat("MM月");
    
    private static final SimpleDateFormat YEAR_FORMAT = new SimpleDateFormat("yyyy年");
    
    public static String getCurrentDay () {
        return DAY_FORMAT.format(new Date());
    }
    
    public static String getCurrentMonth () {
        return MONTH_FORMAT.format(new Date());
    }
    
    public static String getCurrentYear () {
        return YEAR_FORMAT.format(new Date());
    }
    
}
