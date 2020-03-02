package com.netcompany.timecheck;

import java.time.LocalDate;

public class Timeregistration {
    private LocalDate date;

    private String account;

    private Double navHours;
    private Double jiraHours;

    public Timeregistration(final LocalDate date, final String account) {
        this.date = date;
        this.account = account;
        navHours = 0.0;
        jiraHours = 0.0;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getAccount() {
        return account;
    }

    public void registerNavHours(final Double navHours) {
        this.navHours += navHours;
    }

    public void registerJiraHours(final Double jiraHours) {
        this.jiraHours += jiraHours;
    }

    public Boolean hasDifference() {
        return !jiraHours.equals(navHours);
    }

    @Override
    public String toString() {
        return date +
               ": account='" + account + '\'' +
               ", navHours=" + navHours +
               ", jiraHours=" + jiraHours;
    }
}
