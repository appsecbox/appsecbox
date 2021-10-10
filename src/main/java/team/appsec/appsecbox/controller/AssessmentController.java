package team.appsec.appsecbox.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import team.appsec.appsecbox.controller.request.StartAssessmentRequest;
import team.appsec.appsecbox.domain.assessment.Assessment;
import team.appsec.appsecbox.service.AssessmentService;


@RestController
@RequiredArgsConstructor
public class AssessmentController {

    private final AssessmentService assessmentService;

    @PostMapping("/api/assessment")
    Assessment startAssessment(@RequestBody StartAssessmentRequest req) {
        return assessmentService.startAssessment(req.technicalComponents, req.controls);
    }

}
