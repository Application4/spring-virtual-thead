package com.javatechie.service;

import com.javatechie.entity.Customer;
import com.javatechie.repository.CustomerRepository;
import com.javatechie.util.CsvReportUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ReportService {
    @Autowired
    private CustomerRepository repository;


    public void generateReportForRegion(String region) {

        log.info("generating report for region: {} | {}", region, Thread.currentThread());

        List<Customer> customers = repository.findByRegion(region);
        try {
            CsvReportUtil.writeCustomersToCsv("simple_" + region, customers);
        } catch (Exception e) {
            System.out.println("❌ Error writing report for region: " + region);
        }

    }
}

