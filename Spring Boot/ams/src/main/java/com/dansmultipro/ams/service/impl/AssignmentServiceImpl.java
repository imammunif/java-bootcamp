package com.dansmultipro.ams.service.impl;

import com.dansmultipro.ams.dao.AssignmentDao;
import com.dansmultipro.ams.dao.AssignmentDetailDao;
import com.dansmultipro.ams.dto.CreateResponseDto;
import com.dansmultipro.ams.dto.UpdateResponseDto;
import com.dansmultipro.ams.dto.assignment.AssignmentRequestDto;
import com.dansmultipro.ams.dto.assignment.AssignmentResponseDto;
import com.dansmultipro.ams.model.Assignment;
import com.dansmultipro.ams.model.AssignmentDetail;
import com.dansmultipro.ams.service.AssignmentService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AssignmentServiceImpl implements AssignmentService {

    private final AssignmentDao assignmentDao;
    private final AssignmentDetailDao assignmentDetailDao;

    public AssignmentServiceImpl(AssignmentDao assignmentDao, AssignmentDetailDao assignmentDetailDao) {
        this.assignmentDao = assignmentDao;
        this.assignmentDetailDao = assignmentDetailDao;
    }

    private String randomizeCode(int length) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789";
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
                .map(v -> new AssignmentResponseDto(
                        v.getId(), v.getCode(), v.getAssignDate()))
                .toList();
        return result;
    }

    @Override
    public AssignmentResponseDto getById(UUID id) {
        Assignment assignment = assignmentDao.getById(id).orElseThrow(
                () -> new RuntimeException("Assignment not found")
        );
        return new AssignmentResponseDto(assignment.getId(), assignment.getCode(), assignment.getAssignDate());
    }

    @Override
    public CreateResponseDto insert(AssignmentRequestDto data) {
        Assignment assignmentInsert = new Assignment();

        assignmentInsert.setId(UUID.randomUUID());
        assignmentInsert.setCreatedBy(UUID.randomUUID().toString());
        assignmentInsert.setCreatedAt(LocalDateTime.now());
        assignmentInsert.setCode(randomizeCode(20));
        assignmentInsert.setAssignDate(LocalDate.now());

        /* only one target of location/employee/asset should be allowed */
        // assignmentInsert.setLocation(data.getTargetLocationName());
        // assignmentInsert.setAsset(data.getTargetAssetName());
        // assignmentInsert.setEmployee(data.getTargetEmployeeName());

        Assignment assignment = assignmentDao.insert(assignmentInsert);

        return new CreateResponseDto(assignment.getId(), "Saved");
    }

    @Override
    public UpdateResponseDto update(UUID id, List<String> detailIdList) {
        Assignment assignmentUpdate = assignmentDao.getById(id).orElseThrow(
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
