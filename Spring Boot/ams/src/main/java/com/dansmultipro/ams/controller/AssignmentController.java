package com.dansmultipro.ams.controller;

import com.dansmultipro.ams.dto.UpdateResponse;
import com.dansmultipro.ams.dto.assignment.AssignmentCreateResponseDto;
import com.dansmultipro.ams.dto.assignment.AssignmentRequestDto;
import com.dansmultipro.ams.dto.assignment.AssignmentResponseDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("assignments")
public class AssignmentController {

    @GetMapping
    public List<AssignmentResponseDto> getAllAssignments() {
        return null;
    }

    @GetMapping("/{id}")
    public AssignmentResponseDto getAssignmentById(@PathVariable String id) {
        return null;
    }

    @PostMapping("check-out")
    public AssignmentCreateResponseDto createAssignment(@RequestBody AssignmentRequestDto assignment) {
        return null;
    }

    @PatchMapping("{id}/check-in")
    public UpdateResponse updateAssignment(@PathVariable String id, @RequestBody List<String> assignmentDetailIds) {
        return null;
    }

}
