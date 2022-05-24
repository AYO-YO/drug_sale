package cn.fanbaby.drug_sale.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class MyDB {
    private static ComboPooledDataSource source = new ComboPooledDataSource();

    public static ComboPooledDataSource getSource() {
        return source;
    }
}
