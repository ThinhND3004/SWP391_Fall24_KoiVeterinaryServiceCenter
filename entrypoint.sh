#!/bin/bash

echo "Waiting for SQL Server to be available..."
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
