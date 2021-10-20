package ru.sysout;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.sysout.service.ReportService;

@SpringBootTest
class ReportTests {
@Autowired
private ReportService reportService;
    @Test
    void shouldSendReport()
    {
        reportService.sendReport(1l);
    }

}
