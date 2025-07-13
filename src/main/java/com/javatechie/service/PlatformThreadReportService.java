package com.javatechie.service;

import com.javatechie.entity.Customer;
import com.javatechie.repository.CustomerRepository;
import com.javatechie.util.CsvReportUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Executors;
@Slf4j
@Service
public class PlatformThreadReportService {

    @Autowired
    private CustomerRepository repository;

    private final static int POOL_SIZE = 5;
    private final java.util.concurrent.Executor fixedThreadExecutor = Executors.newFixedThreadPool(POOL_SIZE);

    public void generateReportForRegion(String region) {
        fixedThreadExecutor.execute(() -> {
            log.info(" 🧵 generating report for region: {} | {}", region, Thread.currentThread());
            List<Customer> customers = repository.findByRegion(region);
            try {
                CsvReportUtil.writeCustomersToCsv("traditional_" + region, customers);
            } catch (Exception e) {
                System.out.println("❌ Error writing traditional report for region: " + region);
            }
        });
    }



}
