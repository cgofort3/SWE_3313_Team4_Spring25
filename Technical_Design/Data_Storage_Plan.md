# Data Storage Plan

Our application will use SQL for data storage to ensure persistence between application runs.

## Data Storage Technology

- **Storage Type**: SQL Database
- **Reason for Selection**:
  - SQL provides structured, reliable, and efficient storage for application data.
  - SQL databases support complex queries and relationships between entities.
  - SQL is widely supported, well-documented, and commonly used in production systems.

## Libraries and Technologies Used

- **Java JDBC Driver**:
  - JDBC (Java Database Connectivity) is the standard Java API for connecting to relational databases.
  - Allows our application to execute SQL queries directly from Java code.
- **Database System**: 
  - SQLite (embedded SQL database) will be used for local data persistence.
  - SQLite requires no separate server process, making it simple to manage during development.

## Useful Resources

- [JDBC Documentation (Oracle)](https://docs.oracle.com/javase/8/docs/technotes/guides/jdbc/)
- [SQLite Documentation](https://www.sqlite.org/docs.html)
- [JDBC and SQLite Tutorial](https://www.sqlitetutorial.net/sqlite-java/)

## Key Notes

- Data written during application use will be stored in the SQLite database.
- The SQLite database file will persist across application restarts.
- JDBC will manage database connections, queries, and transactions from Java.
