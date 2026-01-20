package com.dansmultipro.ams.service.unit;

import com.dansmultipro.ams.dto.assignment.AssignmentRequestDto;
import com.dansmultipro.ams.model.Asset;
import com.dansmultipro.ams.model.Assignment;
import com.dansmultipro.ams.model.AssignmentDetail;
import com.dansmultipro.ams.repository.*;
import com.dansmultipro.ams.service.impl.AssignmentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AssignmentService {

    @Mock
    private LocationRepo locationRepo;

    @Mock
    private AssetRepo assetRepo;

    @Mock
    private EmployeeRepo employeeRepo;

    @Mock
    private AssignmentRepo assignmentRepo;

    @Mock
    private AssignmentDetailRepo assignmentDetailRepo;

    @InjectMocks
    private AssignmentServiceImpl assignmentService;

    @Test
    public void shouldCreated_whenDataValid() {
        var dto = new AssignmentRequestDto();
        List<String> detailIdList = new ArrayList<>();
        String detailId1 = "29133436-2f2d-486b-8553-8ececd2d17e8";
        detailIdList.add(detailId1);

        dto.setTargetAssetId(UUID.randomUUID().toString());
        dto.setAssignmentDetialIdList(detailIdList);

        var assignmentSaved = new Assignment();
        var id = UUID.randomUUID();
        assignmentSaved.setId(id);

        var asset1 = new Asset();
        asset1.setName("TV LG 32 INCH");
        var assignmentDetail = new AssignmentDetail();
        assignmentDetail.setAsset(asset1);
        assignmentDetail.setAssignment(assignmentSaved);

        Mockito.when(assetRepo.findById(Mockito.any())).thenReturn(Optional.of(new Asset()));
        Mockito.when(assignmentRepo.save(Mockito.any())).thenReturn(assignmentSaved);
        Mockito.when(assignmentDetailRepo.save(Mockito.any())).thenReturn(assignmentDetail);

        var result = assignmentService.insert(dto);

        Assertions.assertEquals(id, result.getId());
        Mockito.verify(assetRepo, Mockito.atLeast(1)).save(Mockito.any());
        Mockito.verify(assignmentRepo, Mockito.atLeast(1)).save(Mockito.any());
        Mockito.verify(assignmentDetailRepo, Mockito.atLeast(1)).save(Mockito.any());

    }
}
