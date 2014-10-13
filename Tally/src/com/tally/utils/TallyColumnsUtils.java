package com.tally.utils;

import com.tally.provider.TalliesContentProvider.CostTypesColumns;
import com.tally.provider.TalliesContentProvider.InComeTypesColumns;
import com.tally.provider.TalliesContentProvider.TalliesColumns;
import com.tally.provider.TalliesContentProvider.TallyUserColumns;

public class TallyColumnsUtils {

    public static final String[] TALLY_COLUMNS = new String[] {
        TalliesColumns.TALLIES_ID ,
        TalliesColumns.TALLY_USER_ID ,
        TalliesColumns.TYPE_ID ,
        TalliesColumns.COST_TYPE_ID ,
        TalliesColumns.INCOME_TYPE_ID ,
        TalliesColumns.TALLIES_YEAR ,
        TalliesColumns.TALLIES_MONTH ,
        TalliesColumns.TALLIES_DAY ,
        TalliesColumns.CREATE_TIME ,
        TalliesColumns.EXPENDITURE ,
        TalliesColumns.INCOME ,
        TalliesColumns.REMARKS ,
    };
    
    public static final String[] COST_COLUMNS = new String[] {
        CostTypesColumns.COST_TYPE_ID ,
        CostTypesColumns.COST_TYPE_NAME ,
    };
    
    public static final String[] INCOME_COLUMNS = new String[] {
        InComeTypesColumns.INCOME_TYPE_ID ,
        InComeTypesColumns.INCOME_TYPE_NAME ,
    };
    
    public static final String[] USERS_COLUMNS = new String[] {
        TallyUserColumns.TALLY_USER_ID ,
        TallyUserColumns.USER_CREATE_TIME ,
        TallyUserColumns.TALLY_USER_NAME ,
    };
    
}
