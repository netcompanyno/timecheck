package com.netcompany.timecheck.jira;

import com.opencsv.bean.*;

import java.time.LocalDate;

public class CSVJiraRegistration {

    @CsvBindByName(column = "Full name")
    private String employeeName;

    @CsvBindByName(column = "Account Key")
    private String account;

    @CsvBindByName
    private Double hours;

    @CsvCustomBindByName(column = "Work date", converter = JiraLocalDateConverter.class)
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
        return "CSVJiraRegistration{" +
               "employeeName='" + employeeName + '\'' +
               ", account='" + account + '\'' +
               ", hours=" + hours +
               ", date=" + date +
               '}';
    }
}
