package com.univille.openink;

import com.univille.openink.domain.post.Post;
import com.univille.openink.domain.post.PostRepository;
import com.univille.openink.domain.report.ReportRepository;
import com.univille.openink.domain.report.ReportService;
import com.univille.openink.domain.report.Type;
import com.univille.openink.domain.report.dto.ReportRequest;
import com.univille.openink.domain.report.dto.ReportResponse;
import com.univille.openink.domain.user.User;
import com.univille.openink.infra.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReportServiceTest {

    @Mock
    private ReportRepository reportRepository;

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private ReportService reportService;

    @Test
    void addReport_whenPostExists_savesReportAndReturnsResponse() {
        Post p = new Post();
        p.setId(55L);

        when(postRepository.findById(55L)).thenReturn(Optional.of(p));

        User user = new User();
        user.setId(66L);

        ReportRequest req = new ReportRequest(55L, Type.OUTROS);

        ReportResponse resp = reportService.addReport(req, user);

        assertNotNull(resp);
        assertEquals(55L, resp.postId());
        verify(reportRepository, times(1)).save(any());
    }

    @Test
    void addReport_whenPostNotFound_throwsNotFoundException() {
        when(postRepository.findById(999L)).thenReturn(Optional.empty());

        User user = new User();
        user.setId(1L);

        ReportRequest req = new ReportRequest(999L, Type.OUTROS);

        NotFoundException ex = assertThrows(NotFoundException.class, () -> reportService.addReport(req, user));
        assertTrue(ex.getMessage().contains("post não encontrado"));
    }
}
