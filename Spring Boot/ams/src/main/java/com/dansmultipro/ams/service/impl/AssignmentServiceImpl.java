package com.dansmultipro.ams.service.impl;

import com.dansmultipro.ams.dao.*;
import com.dansmultipro.ams.dto.UpdateResponseDto;
import com.dansmultipro.ams.dto.assignment.AssignmentCreateResponseDto;
import com.dansmultipro.ams.dto.assignment.AssignmentRequestDto;
import com.dansmultipro.ams.dto.assignment.AssignmentResponseDto;
import com.dansmultipro.ams.model.*;
import com.dansmultipro.ams.service.AssignmentService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AssignmentServiceImpl implements AssignmentService {

    private final LocationDao locationDao;
    private final AssetDao assetDao;
    private final EmployeeDao employeeDao;

    private final AssignmentDao assignmentDao;
    private final AssignmentDetailDao assignmentDetailDao;

    public AssignmentServiceImpl(LocationDao locationDao, AssetDao assetDao, EmployeeDao employeeDao, AssignmentDao assignmentDao, AssignmentDetailDao assignmentDetailDao) {
        this.locationDao = locationDao;
        this.assetDao = assetDao;
        this.employeeDao = employeeDao;
        this.assignmentDao = assignmentDao;
        this.assignmentDetailDao = assignmentDetailDao;
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
        List<AssignmentResponseDto> result = assignmentDao.getAll().stream()
                .map(v -> new AssignmentResponseDto(v.getId(), v.getCode(), v.getAssignDate())).toList();
        return result;
    }

    @Override
    public AssignmentResponseDto getById(String id) {
        Assignment assignment = assignmentDao.getById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("Assignment not found")
        );
        return new AssignmentResponseDto(assignment.getId(), assignment.getCode(), assignment.getAssignDate());
    }

    @Transactional
    @Override
    public AssignmentCreateResponseDto insert(AssignmentRequestDto data) {
        Assignment assignmentInsert = new Assignment();
        assignmentInsert.setId(UUID.randomUUID());
        assignmentInsert.setCreatedBy(UUID.randomUUID().toString());
        assignmentInsert.setCreatedAt(LocalDateTime.now());
        assignmentInsert.setCode(randomizeCode(20));
        assignmentInsert.setAssignDate(LocalDate.now());

        if (data.getTargetAssetId() == null && data.getTargetEmployeeId() == null) {
            String targetLocationId = data.getTargetLocationId();
            Location targetLocation = locationDao.getById(UUID.fromString(targetLocationId)).orElseThrow(
                    () -> new RuntimeException("Target location not found")
            );
            assignmentInsert.setLocation(targetLocation);
        }
        if (data.getTargetLocationId() == null && data.getTargetEmployeeId() == null) {
            String targetAssetId = data.getTargetAssetId();
            Asset targetAsset = assetDao.getById(UUID.fromString(targetAssetId)).orElseThrow(
                    () -> new RuntimeException("Target asset not found")
            );
            assignmentInsert.setAsset(targetAsset);
        }
        if (data.getTargetLocationId() == null && data.getTargetAssetId() == null) {
            String targetEmployeeId = data.getTargetEmployeeId();
            Employee targetEmployee = employeeDao.getById(UUID.fromString(targetEmployeeId)).orElseThrow(
                    () -> new RuntimeException("Target employee not found")
            );
            assignmentInsert.setEmployee(targetEmployee);
        }
        Assignment assignment = assignmentDao.insert(assignmentInsert);

        List<String> detailIdList = data.getAssignmentDetialIdList();
        for (String detailId : detailIdList) {
            AssignmentDetail assignmentDetail = new AssignmentDetail();
            Asset asset = assetDao.getById(UUID.fromString(detailId)).orElseThrow(
                    () -> new RuntimeException("Asset not found")
            );
            assignmentDetail.setId(UUID.randomUUID());
            assignmentDetail.setCreatedBy(UUID.randomUUID().toString());
            assignmentDetail.setCreatedAt(LocalDateTime.now());
            assignmentDetail.setAsset(asset);
            assignmentDetail.setAssignment(assignment);
            assignmentDetailDao.insert(assignmentDetail);
        }

        return new AssignmentCreateResponseDto(assignment.getId());
    }

    @Transactional
    @Override
    public UpdateResponseDto update(String id, List<String> detailIdList) {
        Assignment assignmentUpdate = assignmentDao.getById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("Assignment not found")
        );
        for (String detailId : detailIdList) {
            AssignmentDetail assignmentDetail = assignmentDetailDao.getById(UUID.fromString(detailId)).orElseThrow(
                    () -> new RuntimeException("No not found")
            );
            assignmentDetail.setReturnDate(LocalDateTime.now());
            assignmentDetail.setUpdatedBy(UUID.randomUUID().toString());
            assignmentDetail.setUpdatedAt(LocalDateTime.now());
            assignmentDetailDao.update(assignmentDetail);
        }
        assignmentUpdate.setUpdatedBy(UUID.randomUUID().toString());
        assignmentUpdate.setUpdatedAt(LocalDateTime.now());

        return new UpdateResponseDto(assignmentUpdate.getVersion(), "Updated");
    }

}
