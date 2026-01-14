package com.dansmultipro.ams.controller;

import com.dansmultipro.ams.dto.UpdateResponseDto;
import com.dansmultipro.ams.dto.assignment.AssignmentCreateResponseDto;
import com.dansmultipro.ams.dto.assignment.AssignmentRequestDto;
import com.dansmultipro.ams.dto.assignment.AssignmentResponseDto;
import com.dansmultipro.ams.dto.assignment.UpdateAssignmentRequestDto;
import com.dansmultipro.ams.service.AssignmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("assignments")
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @GetMapping
    public ResponseEntity<List<AssignmentResponseDto>> getAllAssignments() {
        List<AssignmentResponseDto> res = assignmentService.getAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<AssignmentResponseDto> getAssignmentById(@PathVariable String id) {
        AssignmentResponseDto res = assignmentService.getById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("check-out")
    public ResponseEntity<AssignmentCreateResponseDto> createAssignment(@RequestBody AssignmentRequestDto assignment) {
        AssignmentCreateResponseDto res = assignmentService.insert(assignment);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PatchMapping("{id}/check-in")
    public ResponseEntity<UpdateResponseDto> updateAssignment(@PathVariable String id, @RequestBody UpdateAssignmentRequestDto assignment) {
        UpdateResponseDto res = assignmentService.update(id, assignment);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
