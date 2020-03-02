# Time registration checker

Utility for checking time registrations in NAV against Jira.

For projects using the Tempo Jira plugin.

## Prerequisites

The utility assumes that deliveries in NAV has a name that starts with the key of the corresponding account in Tempo.

## Usage

Extract an excel-report from NAV, and save it as csv.
Extract a csv-report from Tempo.

Run the utility:

`java -jar timecheck-1.0.jar <path to csv from jira/tempo> <path to csv from nav> [<output-file name>]`
