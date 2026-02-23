package com.medilabosolutions.riskassessmentapi.controller; 

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medilabosolutions.riskassessmentapi.model.RiskAssessment;
import com.medilabosolutions.riskassessmentapi.service.RiskAssessmentService;

@RestController
@RequestMapping("/risk-assessment")
public class RiskAssessmentController {

    private final RiskAssessmentService riskAssessmentService; 

    public RiskAssessmentController(RiskAssessmentService riskAssessmentService) {
        this.riskAssessmentService = riskAssessmentService;         
    }

    @GetMapping("/diabetes-report/{id}")
    public RiskAssessment getRiskAssessment(@PathVariable String id) {
        return riskAssessmentService.getDiabetesReport(id);
    }


}
