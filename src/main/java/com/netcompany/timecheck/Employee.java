package com.netcompany.timecheck;

import com.netcompany.timecheck.jira.CSVJiraRegistration;
import com.netcompany.timecheck.nav.CSVNavRegistration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Employee {

    private String name;

    private List<Timeregistration> timeregistrations = new ArrayList<>();

    public Employee(final String name) {
        this.name = name;
    }

    public void registerJiraTime(final CSVJiraRegistration csvJiraRegistration) {
        final Timeregistration timeregistration =
                findTimeregistration(csvJiraRegistration.getDate(), csvJiraRegistration.getAccount());
        timeregistration.registerJiraHours(csvJiraRegistration.getHours());
    }

    public void registerNavTime(final CSVNavRegistration csvNavRegistration) {
        final Timeregistration timeregistration =
                findTimeregistration(csvNavRegistration.getDate(), csvNavRegistration.getAccount());
        timeregistration.registerNavHours(csvNavRegistration.getHours());
    }

    private Timeregistration findTimeregistration(final LocalDate date, final String account) {
        for (final Timeregistration timeregistration : timeregistrations) {
            if (timeregistration.getAccount().equals(account) && timeregistration.getDate().equals(date)) {
                return timeregistration;
            }
        }
        final Timeregistration timeregistration = new Timeregistration(date, account);
        timeregistrations.add(timeregistration);
        return timeregistration;
    }

    public String checkHours() {
        final List<Timeregistration> diffs = timeregistrations.stream()
                                                              .sorted(Comparator.comparing(Timeregistration::getDate))
                                                              .filter(Timeregistration::hasDifference)
                                                              .collect(Collectors.toList());
        final StringBuilder sb = new StringBuilder(name);

        if (diffs.isEmpty()) {
            sb.append(" - OK").append(System.lineSeparator());
        } else {
            sb.append(" - Found differences:").append(System.lineSeparator());
            for (final Timeregistration diff : diffs) {
                sb.append("\t").append(diff).append(System.lineSeparator());
            }
        }

        return sb.toString();
    }

    @Override
    public String toString() {
        return "Employee{" +
               "name='" + name + '\'' +
               ", timeregistrations=" + timeregistrations +
               '}';
    }
}
