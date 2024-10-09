#!/bin/bash

# Wait for the SQL Server to be ready
echo "Waiting for SQL Server to be available..."
echo "DB_PASSWORD: $DB_PASSWORD"
echo "DB_NAME: $DB_NAME"
until /opt/mssql-tools18/bin/sqlcmd -S ms-sql-server -U sa -P $DB_PASSWORD -No -Q "SELECT 1" &> /dev/null
do
  /opt/mssql-tools18/bin/sqlcmd -S ms-sql-server -U sa -P $DB_PASSWORD -No -Q "SELECT 1"
  echo "SQL Server is unavailable - sleeping"
  sleep 5
done

echo "SQL Server is up - executing command"

/opt/mssql-tools18/bin/sqlcmd -S ms-sql-server -U sa -P $DB_PASSWORD -No -Q "
  IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = '$DB_NAME')
  BEGIN
    CREATE DATABASE $DB_NAME
  END
  GO
"
