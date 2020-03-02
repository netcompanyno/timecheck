package com.netcompany.timecheck.nav;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

public class CaseNameToAccountKeyConverter extends AbstractBeanField {
    @Override
    protected Object convert(final String s) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        return s.split(" ")[0];
    }
}
