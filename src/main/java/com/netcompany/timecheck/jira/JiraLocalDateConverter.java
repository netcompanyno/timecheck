package com.netcompany.timecheck.jira;

import com.opencsv.bean.AbstractBeanField;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class JiraLocalDateConverter extends AbstractBeanField {
    @Override
    protected Object convert(final String s) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDate.parse(s, formatter);
    }
}
