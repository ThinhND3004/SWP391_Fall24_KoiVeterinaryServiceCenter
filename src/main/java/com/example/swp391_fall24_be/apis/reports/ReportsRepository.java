package com.example.swp391_fall24_be.apis.reports;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReportsRepository extends JpaRepository<ReportEntity, String> {

}
