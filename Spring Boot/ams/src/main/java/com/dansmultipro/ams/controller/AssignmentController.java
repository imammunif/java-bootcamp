package com.dansmultipro.ams.controller;

import com.dansmultipro.ams.dto.CreateResponse;
import com.dansmultipro.ams.dto.DeleteResponse;
import com.dansmultipro.ams.dto.UpdateResponse;
import com.dansmultipro.ams.dto.asset.AssetRequest;
import com.dansmultipro.ams.dto.assignment.AssignmentCreateResponse;
import com.dansmultipro.ams.dto.assignment.AssignmentRequest;
import com.dansmultipro.ams.dto.assignment.AssignmentResponse;
import com.dansmultipro.ams.dto.assignment.AssignmentUpdateResponse;
import com.dansmultipro.ams.dto.company.CompanyRequest;
import com.dansmultipro.ams.dto.company.CompanyResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("assignments")
public class AssignmentController {

    @GetMapping
    public List<AssignmentResponse> getAllAssignments() {
        return null;
    }

    @GetMapping("/{id}")
    public AssignmentResponse getAssignmentById(@PathVariable String id) {
        return null;
    }

    @PostMapping("check-out")
    public AssignmentCreateResponse createAssignment(@RequestBody AssignmentRequest assignment) {
        return null;
    }

    @PatchMapping("check-in/{id}")
    public AssignmentUpdateResponse updateAssignment(@PathVariable String id, @RequestBody AssignmentRequest assignment) {
        return null;
    }

}
