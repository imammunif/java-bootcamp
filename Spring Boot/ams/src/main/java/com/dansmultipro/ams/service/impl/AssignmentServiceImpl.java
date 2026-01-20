package com.dansmultipro.ams.service.impl;

import com.dansmultipro.ams.dto.UpdateResponseDto;
import com.dansmultipro.ams.dto.assignment.AssignmentCreateResponseDto;
import com.dansmultipro.ams.dto.assignment.AssignmentRequestDto;
import com.dansmultipro.ams.dto.assignment.AssignmentResponseDto;
import com.dansmultipro.ams.dto.assignment.UpdateAssignmentRequestDto;
import com.dansmultipro.ams.exception.DataMissMatchException;
import com.dansmultipro.ams.exception.NotFoundException;
import com.dansmultipro.ams.model.*;
import com.dansmultipro.ams.repository.*;
import com.dansmultipro.ams.service.AssignmentService;
import com.dansmultipro.ams.util.RandomGenerator;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AssignmentServiceImpl extends BaseService implements AssignmentService {

    private final LocationRepo locationRepo;
    private final AssetRepo assetRepo;
    private final EmployeeRepo employeeRepo;
    private final AssignmentRepo assignmentRepo;
    private final AssignmentDetailRepo assignmentDetailRepo;

    public AssignmentServiceImpl(LocationRepo locationRepo, AssetRepo assetRepo, EmployeeRepo employeeRepo, AssignmentRepo assignmentRepo, AssignmentDetailRepo assignmentDetailRepo) {
        this.locationRepo = locationRepo;
        this.assetRepo = assetRepo;
        this.employeeRepo = employeeRepo;
        this.assignmentRepo = assignmentRepo;
        this.assignmentDetailRepo = assignmentDetailRepo;
    }

    @Override
    public List<AssignmentResponseDto> getAll() {
        List<AssignmentResponseDto> result = assignmentRepo.findAll().stream()
                .map(v -> new AssignmentResponseDto(v.getId(), v.getCode(), v.getAssignDate())).toList();
        return result;
    }

    @Override
    public AssignmentResponseDto getById(String id) {
        Assignment assignment = assignmentRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("Assignment not found")
        );
        return new AssignmentResponseDto(assignment.getId(), assignment.getCode(), assignment.getAssignDate());
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public AssignmentCreateResponseDto insert(AssignmentRequestDto request) {
        Assignment assignmentNew = new Assignment();
        Assignment assignmentInsert = prepareForInsert(assignmentNew);
        assignmentInsert.setCode(RandomGenerator.randomizeCode(20));
        assignmentInsert.setAssignDate(LocalDate.now());

        if (request.getTargetLocationId() != null) {
            String targetLocationId = request.getTargetLocationId();
            Location targetLocation = locationRepo.findById(UUID.fromString(targetLocationId)).orElseThrow(
                    () -> new NotFoundException("Target location not found")
            );
            assignmentInsert.setLocation(targetLocation);
        } else if (request.getTargetAssetId() != null) {
            String targetAssetId = request.getTargetAssetId();
            Asset targetAsset = assetRepo.findById(UUID.fromString(targetAssetId)).orElseThrow(
                    () -> new NotFoundException("Target asset not found")
            );
            assignmentInsert.setAsset(targetAsset);
        } else if (request.getTargetEmployeeId() != null) {
            String targetEmployeeId = request.getTargetEmployeeId();
            Employee targetEmployee = employeeRepo.findById(UUID.fromString(targetEmployeeId)).orElseThrow(
                    () -> new NotFoundException("Target employee not found")
            );
            assignmentInsert.setEmployee(targetEmployee);
        }
        Assignment assignment = assignmentRepo.save(assignmentInsert);

        List<String> detailIdList = request.getAssignmentDetialIdList();
        for (String detailId : detailIdList) {
            Asset asset = assetRepo.findById(UUID.fromString(detailId)).orElseThrow(
                    () -> new NotFoundException("Asset not found")
            );
            AssignmentDetail assignmentDetailNew = new AssignmentDetail();
            AssignmentDetail assignmentDetail = prepareForInsert(assignmentDetailNew);
            assignmentDetail.setAsset(asset);
            assignmentDetail.setAssignment(assignment);
            assignmentDetailRepo.save(assignmentDetail);
        }

        return new AssignmentCreateResponseDto(assignment.getId());
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public UpdateResponseDto update(String id, UpdateAssignmentRequestDto request) {
        Assignment assignment = assignmentRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new NotFoundException("Assignment not found")
        );
        if (!assignment.getVersion().equals(request.getVersion())) {
            throw new DataMissMatchException("Version not match");
        }
        Assignment assignmentUpdatePrep = prepareForUpdate(assignment);
        List<String> assignmentDetailIdList = request.getAssignmentDetailIdList();
        for (String detailId : assignmentDetailIdList) {
            AssignmentDetail assignmentDetail = assignmentDetailRepo.findById(UUID.fromString(detailId)).orElseThrow(
                    () -> new NotFoundException("Detail not found")
            );
            AssignmentDetail assignmentDetailUpdate = prepareForUpdate(assignmentDetail);
            assignmentDetailUpdate.setReturnDate(LocalDateTime.now());
            assignmentDetailRepo.save(assignmentDetail);
        }
        Assignment assignmentUpdated = assignmentRepo.saveAndFlush(assignmentUpdatePrep);

        return new UpdateResponseDto(assignmentUpdated.getVersion(), "Updated");
    }

}
