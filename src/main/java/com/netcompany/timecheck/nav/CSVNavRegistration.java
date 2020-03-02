package com.netcompany.timecheck.nav;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;

import java.time.LocalDate;

public class CSVNavRegistration {
    @CsvBindByName(column = "Description")
    private String employeeName;

    @CsvCustomBindByName(column = "Case Description", converter = CaseNameToAccountKeyConverter.class)
    private String account;

    @CsvBindByName(column = "Quantity")
    private Double hours;

    @CsvCustomBindByName(column = "Posting Date", converter = NavLocalDateConverter.class)
    private LocalDate date;

    public String getEmployeeName() {
        return employeeName;
    }

    public String getAccount() {
        return account;
    }

    public Double getHours() {
        return hours;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "CSVNavRegistration{" +
               "employeeName='" + employeeName + '\'' +
               ", caseName='" + account + '\'' +
               ", hours=" + hours +
               ", date=" + date +
               '}';
    }
}
