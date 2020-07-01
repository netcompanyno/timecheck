package com.netcompany.timecheck.nav;

import com.netcompany.timecheck.PropertiesReader;
import com.opencsv.bean.AbstractBeanField;

import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class NavLocalDateConverter extends AbstractBeanField {
    @Override
    protected Object convert(final String s) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PropertiesReader.getString("dateformat.nav"));
        return LocalDate.parse(s, formatter);
    }
}
