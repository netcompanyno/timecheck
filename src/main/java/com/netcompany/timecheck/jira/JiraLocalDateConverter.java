package com.netcompany.timecheck.jira;

import com.netcompany.timecheck.PropertiesReader;
import com.opencsv.bean.AbstractBeanField;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class JiraLocalDateConverter extends AbstractBeanField {
    @Override
    protected Object convert(final String s) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PropertiesReader.getString("dateformat.jira"));
        return LocalDate.parse(s, formatter);
    }
}
