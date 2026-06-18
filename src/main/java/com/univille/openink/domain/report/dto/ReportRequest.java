package com.univille.openink.domain.report.dto;

import com.univille.openink.domain.report.Type;
import jakarta.validation.constraints.NotNull;

public record ReportRequest(
        @NotNull
        Long postId,

        @NotNull
        Type type
) {
}
