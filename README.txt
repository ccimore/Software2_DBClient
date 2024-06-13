
iCalendar (DBClientAppV5)

The purpose of this application is to provide a GUI based appointment scheduling application that interfaces
with a database.

IntelliJ IDEA 2022.3.1 (Community Edition)
Java SE Development Kit 17.0.6 (64bit)
JavaFX: openjfx-17.0.2
Maven: mysql:mysql-connector-java:8.0.25

How to run program:
When the application is launched, a login screen will appear.
To login, a valid username and password are for input in the
login text fields.  The username and password will be validated
against matching information in a mysql database.  The application
was created with java 17 and has not been tested with any other jvm.

Additional Report:
I created a report that aggregates the total customers by division.
I implemented this by writing a DivisionTotal class and a helper method
to retrieve data from the database to create a list of DivisionTotal objects.
I then populated the report using the DivisionTotal objects.
