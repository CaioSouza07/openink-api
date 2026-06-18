package com.univille.openink.domain.report;

import com.univille.openink.domain.post.PostRepository;
import com.univille.openink.domain.report.dto.ReportRequest;
import com.univille.openink.domain.report.dto.ReportResponse;
import com.univille.openink.domain.user.User;
import com.univille.openink.infra.exception.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;
    private final PostRepository postRepository;

    @Transactional
    public ReportResponse addReport(ReportRequest data, User user){
        var post = postRepository.findById(data.postId())
                .orElseThrow(() -> new NotFoundException("post não encontrado"));

        var report = new Report();
        report.setPost(post);
        report.setUser(user);
        report.setType(data.type());
        reportRepository.save(report);
        return new ReportResponse(report);
    }
}
