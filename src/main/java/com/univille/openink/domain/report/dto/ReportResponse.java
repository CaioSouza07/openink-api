package com.univille.openink.domain.report.dto;

import com.univille.openink.domain.report.Report;
import com.univille.openink.domain.report.Type;

public record ReportResponse(
        Long id,
        Long postId,
        Long userId,
        Type type
) {
    public ReportResponse(Report data){
        this(data.getId(), data.getPost().getId(), data.getUser().getId(), data.getType());
    }
}
