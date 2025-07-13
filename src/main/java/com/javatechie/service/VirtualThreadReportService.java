package com.javatechie.service;

import com.javatechie.entity.Customer;
import com.javatechie.repository.CustomerRepository;
import com.javatechie.util.CsvReportUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Executor;

@Slf4j
@Service
public class VirtualThreadReportService {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private Executor virtualThreadExecutor;

    public void generateReportForRegion(String region) {
        virtualThreadExecutor.execute(() -> {

            log.info("generating report for region: {} | {}", region, Thread.currentThread());
            // Simulate a delay to mimic a long-running task
            List<Customer> customers = repository.findByRegion(region);

            try {
                CsvReportUtil.writeCustomersToCsv("virtual" +region, customers);
            } catch (Exception e) {
                System.err.println("Error writing report for region: " + region);
            }
        });
    }
}
