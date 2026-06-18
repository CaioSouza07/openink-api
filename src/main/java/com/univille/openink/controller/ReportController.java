package com.univille.openink.controller;

import com.univille.openink.domain.report.ReportService;
import com.univille.openink.domain.report.dto.ReportRequest;
import com.univille.openink.domain.report.dto.ReportResponse;
import com.univille.openink.domain.user.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @PostMapping
    public ResponseEntity<ReportResponse> add(
            @RequestBody @Valid ReportRequest request,
            @AuthenticationPrincipal User user
    ){
        var response = reportService.addReport(request, user);
        return ResponseEntity.ok(response);
    }
}
