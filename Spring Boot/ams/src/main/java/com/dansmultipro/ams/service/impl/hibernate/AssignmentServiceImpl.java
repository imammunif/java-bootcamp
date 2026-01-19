package com.dansmultipro.ams.service.impl.hibernate;

import com.dansmultipro.ams.dao.*;
import com.dansmultipro.ams.dto.UpdateResponseDto;
import com.dansmultipro.ams.dto.assignment.AssignmentCreateResponseDto;
import com.dansmultipro.ams.dto.assignment.AssignmentRequestDto;
import com.dansmultipro.ams.dto.assignment.AssignmentResponseDto;
import com.dansmultipro.ams.dto.assignment.UpdateAssignmentRequestDto;
import com.dansmultipro.ams.model.*;
import com.dansmultipro.ams.service.AssignmentService;
import com.dansmultipro.ams.service.impl.BaseService;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Profile("hibernate")
@Service
public class AssignmentServiceImpl extends BaseService implements AssignmentService {

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

    @Transactional(rollbackOn = Exception.class)
    @Override
    public AssignmentCreateResponseDto insert(AssignmentRequestDto data) {
        Assignment assignmentNew = new Assignment();
        Assignment assignmentInsert = prepareForInsert(assignmentNew);
        assignmentInsert.setCode(randomizeCode(20));
        assignmentInsert.setAssignDate(LocalDate.now());

        if (data.getTargetLocationId() != null) {
            String targetLocationId = data.getTargetLocationId();
            Location targetLocation = locationDao.getById(UUID.fromString(targetLocationId)).orElseThrow(
                    () -> new RuntimeException("Target location not found")
            );
            assignmentInsert.setLocation(targetLocation);
        } else if (data.getTargetAssetId() != null) {
            String targetAssetId = data.getTargetAssetId();
            Asset targetAsset = assetDao.getById(UUID.fromString(targetAssetId)).orElseThrow(
                    () -> new RuntimeException("Target asset not found")
            );
            assignmentInsert.setAsset(targetAsset);
        } else if (data.getTargetEmployeeId() != null) {
            String targetEmployeeId = data.getTargetEmployeeId();
            Employee targetEmployee = employeeDao.getById(UUID.fromString(targetEmployeeId)).orElseThrow(
                    () -> new RuntimeException("Target employee not found")
            );
            assignmentInsert.setEmployee(targetEmployee);
        }
        Assignment assignment = assignmentDao.insert(assignmentInsert);

        List<String> detailIdList = data.getAssignmentDetialIdList();
        for (String detailId : detailIdList) {
            Asset asset = assetDao.getById(UUID.fromString(detailId)).orElseThrow(
                    () -> new RuntimeException("Asset not found")
            );
            AssignmentDetail assignmentDetailNew = new AssignmentDetail();
            AssignmentDetail assignmentDetail = prepareForInsert(assignmentDetailNew);
            assignmentDetail.setAsset(asset);
            assignmentDetail.setAssignment(assignment);
            assignmentDetailDao.insert(assignmentDetail);
        }

        return new AssignmentCreateResponseDto(assignment.getId());
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public UpdateResponseDto update(String id, UpdateAssignmentRequestDto data) {
        Assignment assignment = assignmentDao.getById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("Assignment not found")
        );
        List<String> assignmentDetailIdList = data.getAssignmentDetailIdList();
        for (String detailId : assignmentDetailIdList) {
            AssignmentDetail assignmentDetail = assignmentDetailDao.getById(UUID.fromString(detailId)).orElseThrow(
                    () -> new RuntimeException("Detail not found")
            );
            AssignmentDetail assignmentDetailUpdate = prepareForUpdate(assignmentDetail);
            assignmentDetailUpdate.setReturnDate(LocalDateTime.now());
            assignmentDetailDao.update(assignmentDetailUpdate);
        }
        Assignment assignmentUpdate = prepareForUpdate(assignment);
        em.flush();

        return new UpdateResponseDto(assignmentUpdate.getVersion(), "Updated");
    }

}
