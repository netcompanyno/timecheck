package com.netcompany.timecheck;

import com.netcompany.timecheck.jira.CSVJiraRegistration;
import com.netcompany.timecheck.nav.CSVNavRegistration;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TimeChecker {

    private final Map<String, Employee> employees = new HashMap<>();

    private static void printUsageAndExit() {
        System.out.println("Usage: java -jar timecheck-<version>.jar <path to csv from jira/tempo> <path to csv from nav> [<output-file name>]");
        System.exit(1);
    }

    public static void main(final String... args) {
        if (args.length >= 1 && args[0].equals("--help")) {
            printUsageAndExit();
        }
        if (args.length < 2) {
            System.err.println("Too few arguments!");
            printUsageAndExit();
        }

        final String csvJiraPath = args[0];
        final String csvNavPath = args[1];
        final String outputFilename = args.length > 2 ? args[2] : null;

        new TimeChecker().checkTime(csvJiraPath, csvNavPath, outputFilename);
    }

    public void checkTime(final String csvJiraPath, final String csvNavPath, final String outputFilename) {
        final List<CSVJiraRegistration> jiraRegistrations = readJiraRegistrations(csvJiraPath);
        final List<CSVNavRegistration> navRegistrations = readNavRegistrations(csvNavPath);

        for (final CSVJiraRegistration jiraRegistration : jiraRegistrations) {
            final Employee employee = getEmployee(jiraRegistration.getEmployeeName());
            employee.registerJiraTime(jiraRegistration);
        }

        for (final CSVNavRegistration navRegistration : navRegistrations) {
            getEmployee(navRegistration.getEmployeeName()).registerNavTime(navRegistration);
        }

        final List<String> output = employees.values().stream().map(Employee::checkHours).collect(Collectors.toList());
        outputResult(output, outputFilename);
    }

    private void outputResult(final List<String> output, final String outputFilename) {
        if (outputFilename == null) {
            output.forEach(System.out::println);
        } else {
            try {
                Files.write(Paths.get(outputFilename), output);
            } catch (final IOException e) {
                throw new RuntimeException("Could not write to file " + outputFilename, e);
            }
        }
    }

    private Employee getEmployee(final String name) {
        final String trimmedName = name.trim();
        final Employee existingEmployee = employees.get(trimmedName);

        if (existingEmployee != null) {
            return existingEmployee;
        }

        final Employee newEmployee = new Employee(trimmedName);
        employees.put(trimmedName, newEmployee);
        return newEmployee;
    }

    private List<CSVJiraRegistration> readJiraRegistrations(final String csvJiraPath) {
        try (final Reader reader =
                     Files.newBufferedReader(Paths.get(csvJiraPath),
                                             Charset.forName(PropertiesReader.getString("encoding.jira")))) {
            final CsvToBean<CSVJiraRegistration> csvToBean =
                    new CsvToBeanBuilder<CSVJiraRegistration>(reader).withType(CSVJiraRegistration.class)
                                                                     .withIgnoreLeadingWhiteSpace(true)
                                                                     .build();
            final List<CSVJiraRegistration> registrations = new ArrayList<>();
            csvToBean.iterator().forEachRemaining(registrations::add);
            return registrations;
        } catch (final IOException e) {
            throw new RuntimeException("Could not read csv from jira from path " + csvJiraPath, e);
        }
    }

    private List<CSVNavRegistration> readNavRegistrations(final String csvNavPath) {
        try (final Reader reader =
                     Files.newBufferedReader(Paths.get(csvNavPath),
                                             Charset.forName(PropertiesReader.getString("encoding.nav")))) {
            final CsvToBean<CSVNavRegistration> csvToBean =
                    new CsvToBeanBuilder<CSVNavRegistration>(reader).withType(CSVNavRegistration.class)
                                                                     .withIgnoreLeadingWhiteSpace(true)
                                                                     .build();
            final List<CSVNavRegistration> registrations = new ArrayList<>();
            csvToBean.iterator().forEachRemaining(registrations::add);
            return registrations;
        } catch (final IOException e) {
            throw new RuntimeException("Could not read csv from jira from path " + csvNavPath, e);
        }
    }
}
