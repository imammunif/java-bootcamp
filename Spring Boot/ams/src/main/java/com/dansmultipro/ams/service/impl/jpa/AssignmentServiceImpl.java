package com.dansmultipro.ams.service.impl.jpa;

import com.dansmultipro.ams.dao.*;
import com.dansmultipro.ams.dto.UpdateResponseDto;
import com.dansmultipro.ams.dto.assignment.AssignmentCreateResponseDto;
import com.dansmultipro.ams.dto.assignment.AssignmentRequestDto;
import com.dansmultipro.ams.dto.assignment.AssignmentResponseDto;
import com.dansmultipro.ams.dto.assignment.UpdateAssignmentRequestDto;
import com.dansmultipro.ams.model.*;
import com.dansmultipro.ams.repository.*;
import com.dansmultipro.ams.service.AssignmentService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Profile("jpa")
@Service
public class AssignmentServiceImpl implements AssignmentService {

    private final LocationRepo locationRepo;
    private final AssetRepo assetRepo;
    private final EmployeeRepo employeeRepo;
    private final AssignmentRepo assignmentRepo;
    private final AssignmentDetailRepo assignmentDetailRepo;

    @PersistenceContext
    private EntityManager em;

    public AssignmentServiceImpl(LocationRepo locationRepo, AssetRepo assetRepo, EmployeeRepo employeeRepo, AssignmentRepo assignmentRepo, AssignmentDetailRepo assignmentDetailRepo) {
        this.locationRepo = locationRepo;
        this.assetRepo = assetRepo;
        this.employeeRepo = employeeRepo;
        this.assignmentRepo = assignmentRepo;
        this.assignmentDetailRepo = assignmentDetailRepo;
    }

    private String randomizeCode(int length) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return sb.toString();
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
    public AssignmentCreateResponseDto insert(AssignmentRequestDto data) {
        Assignment assignmentInsert = new Assignment();
        assignmentInsert.setId(UUID.randomUUID());
        assignmentInsert.setCreatedBy(UUID.randomUUID());
        assignmentInsert.setCreatedAt(LocalDateTime.now());
        assignmentInsert.setCode(randomizeCode(20));
        assignmentInsert.setAssignDate(LocalDate.now());

        if (data.getTargetAssetId() == null && data.getTargetEmployeeId() == null) {
            String targetLocationId = data.getTargetLocationId();
            Location targetLocation = locationRepo.findById(UUID.fromString(targetLocationId)).orElseThrow(
                    () -> new RuntimeException("Target location not found")
            );
            assignmentInsert.setLocation(targetLocation);
        }
        if (data.getTargetLocationId() == null && data.getTargetEmployeeId() == null) {
            String targetAssetId = data.getTargetAssetId();
            Asset targetAsset = assetRepo.findById(UUID.fromString(targetAssetId)).orElseThrow(
                    () -> new RuntimeException("Target asset not found")
            );
            assignmentInsert.setAsset(targetAsset);
        }
        if (data.getTargetLocationId() == null && data.getTargetAssetId() == null) {
            String targetEmployeeId = data.getTargetEmployeeId();
            Employee targetEmployee = employeeRepo.findById(UUID.fromString(targetEmployeeId)).orElseThrow(
                    () -> new RuntimeException("Target employee not found")
            );
            assignmentInsert.setEmployee(targetEmployee);
        }
        Assignment assignment = assignmentRepo.save(assignmentInsert);

        List<String> detailIdList = data.getAssignmentDetialIdList();
        for (String detailId : detailIdList) {
            AssignmentDetail assignmentDetail = new AssignmentDetail();
            Asset asset = assetRepo.findById(UUID.fromString(detailId)).orElseThrow(
                    () -> new RuntimeException("Asset not found")
            );
            assignmentDetail.setId(UUID.randomUUID());
            assignmentDetail.setCreatedBy(UUID.randomUUID());
            assignmentDetail.setCreatedAt(LocalDateTime.now());
            assignmentDetail.setAsset(asset);
            assignmentDetail.setAssignment(assignment);
            assignmentDetailRepo.save(assignmentDetail);
        }

        return new AssignmentCreateResponseDto(assignment.getId());
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public UpdateResponseDto update(String id, UpdateAssignmentRequestDto data) {
        Assignment assignmentUpdate = assignmentRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("Assignment not found")
        );
        List<String> assignmentDetailIdList = data.getAssignmentDetailIdList();
        for (String detailId : assignmentDetailIdList) {
            AssignmentDetail assignmentDetail = assignmentDetailRepo.findById(UUID.fromString(detailId)).orElseThrow(
                    () -> new RuntimeException("Detail not found")
            );
            assignmentDetail.setReturnDate(LocalDateTime.now());
            assignmentDetail.setUpdatedBy(UUID.randomUUID());
            assignmentDetail.setUpdatedAt(LocalDateTime.now());
            assignmentDetailRepo.save(assignmentDetail);
        }
        assignmentUpdate.setUpdatedBy(UUID.randomUUID());
        assignmentUpdate.setUpdatedAt(LocalDateTime.now());
        em.flush();

        return new UpdateResponseDto(assignmentUpdate.getVersion(), "Updated");
    }

}
