package com.netcompany.timecheck.nav;

import com.opencsv.bean.AbstractBeanField;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NavLocalDateConverter extends AbstractBeanField {
    @Override
    protected Object convert(final String s) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(s, formatter);
    }
}
