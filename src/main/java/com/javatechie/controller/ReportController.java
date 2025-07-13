package com.javatechie.controller;

import com.javatechie.service.PlatformThreadReportService;
import com.javatechie.service.ReportService;
import com.javatechie.service.VirtualThreadReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private PlatformThreadReportService platformService;


    @Autowired
    private VirtualThreadReportService virtualService;


    @PostMapping("/{region}")
    public ResponseEntity<String> generateReport(@PathVariable String region) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        reportService.generateReportForRegion(region);
        return ResponseEntity.ok( "✅ report started for region: " + region);
    }

    @PostMapping("/platform/{region}")
    public ResponseEntity<String> generateWithPlatform(@PathVariable String region) {
        platformService.generateReportForRegion(region);
        return ResponseEntity.ok(  "✅ Platform thread report started for region: " + region);
    }

    @PostMapping("/virtual/{region}")
    public ResponseEntity<String> generateWithVirtual(@PathVariable String region) {
        virtualService.generateReportForRegion(region);
        return ResponseEntity.ok(  "✅ Virtual thread report started for region: " + region);
    }

}
