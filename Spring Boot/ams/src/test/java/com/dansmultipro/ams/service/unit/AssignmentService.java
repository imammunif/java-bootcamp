package com.dansmultipro.ams.service.unit;

import com.dansmultipro.ams.dto.assignment.AssignmentRequestDto;
import com.dansmultipro.ams.dto.assignment.UpdateAssignmentRequestDto;
import com.dansmultipro.ams.model.Asset;
import com.dansmultipro.ams.model.Assignment;
import com.dansmultipro.ams.model.AssignmentDetail;
import com.dansmultipro.ams.model.Location;
import com.dansmultipro.ams.repository.AssetRepo;
import com.dansmultipro.ams.repository.AssignmentDetailRepo;
import com.dansmultipro.ams.repository.AssignmentRepo;
import com.dansmultipro.ams.repository.LocationRepo;
import com.dansmultipro.ams.service.impl.AssignmentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class AssignmentService {

    @Mock
    private LocationRepo locationRepo;

    @Mock
    private AssetRepo assetRepo;

    @Mock
    private AssignmentRepo assignmentRepo;

    @Mock
    private AssignmentDetailRepo assignmentDetailRepo;

    @InjectMocks
    private AssignmentServiceImpl assignmentService;

    @Test
    public void shouldCreated_whenDataValid() {
        var location = new Location();
        location.setId(UUID.randomUUID());
        location.setName("Room 3007 Soho Building");

        var asset = new Asset();
        asset.setId(UUID.randomUUID());
        asset.setName("TV LG 32 INCH");

        var dto = new AssignmentRequestDto();
        dto.setTargetLocationId(location.getId().toString());
        dto.setAssignmentDetialIdList(List.of(asset.getId().toString()));

        var assignmentSaved = new Assignment();
        var id = UUID.randomUUID();
        assignmentSaved.setId(id);

        var assignmentDetail = new AssignmentDetail();
        assignmentDetail.setAsset(asset);
        assignmentDetail.setAssignment(assignmentSaved);

        Mockito.when(locationRepo.findById(Mockito.any())).thenReturn(Optional.of(location));
        Mockito.when(assetRepo.findById(Mockito.any())).thenReturn(Optional.of(asset));
        Mockito.when(assignmentRepo.save(Mockito.any())).thenReturn(assignmentSaved);
        Mockito.when(assignmentDetailRepo.save(Mockito.any())).thenReturn(assignmentDetail);

        var result = assignmentService.insert(dto);

        Assertions.assertEquals(id, result.getId());
        Mockito.verify(locationRepo, Mockito.atLeast(1)).findById(Mockito.any());
        Mockito.verify(assetRepo, Mockito.atLeast(1)).findById(Mockito.any());
        Mockito.verify(assignmentRepo, Mockito.atLeast(1)).save(Mockito.any());
        Mockito.verify(assignmentDetailRepo, Mockito.atLeast(1)).save(Mockito.any());

    }

    @Test
    public void shouldReturnData_whenIdValid() {
        var id = UUID.randomUUID();
        var assignmentSaved = new Assignment();
        assignmentSaved.setId(id);
        assignmentSaved.setCode("ASGN2026");

        Mockito.when(assignmentRepo.findById(Mockito.any())).thenReturn(Optional.of(assignmentSaved));

        var result = assignmentService.getById(id.toString());

        Assertions.assertEquals("ASGN2026", result.getCode());
        Mockito.verify(assignmentRepo, Mockito.atLeast(1)).findById(Mockito.any());
    }

    @Test
    public void shouldUpdateData_whenVersionValid() {
        var asset = new Asset();
        asset.setId(UUID.randomUUID());
        asset.setName("TV LG 32 INCH");

        var id = UUID.randomUUID();
        var assignment = new Assignment();
        assignment.setId(id);
        assignment.setCode("ASGN2026");
        assignment.setVersion(0);

        var assignmentDetail = new AssignmentDetail();
        assignmentDetail.setAsset(asset);
        assignmentDetail.setAssignment(assignment);

        var dto = new UpdateAssignmentRequestDto();
        dto.setAssignmentDetailIdList(List.of(UUID.randomUUID().toString()));
        dto.setVersion(0);

        var updatedAssignment = new Assignment();
        updatedAssignment.setVersion(1);

        var updatedDetail = new AssignmentDetail();
        updatedDetail.setAsset(asset);
        updatedDetail.setAssignment(assignment);

        Mockito.when(assignmentRepo.findById(Mockito.any())).thenReturn(Optional.of(assignment));
        Mockito.when(assignmentDetailRepo.findById(Mockito.any())).thenReturn(Optional.of(assignmentDetail));
        Mockito.when(assignmentDetailRepo.save(Mockito.any())).thenReturn(updatedDetail);
        Mockito.when(assignmentRepo.saveAndFlush(Mockito.any())).thenReturn(updatedAssignment);

        var result = assignmentService.update(id.toString(), dto);

        Assertions.assertEquals(1, result.getVersion());
        Mockito.verify(assignmentRepo, Mockito.atLeast(1)).findById(Mockito.any());
        Mockito.verify(assignmentDetailRepo, Mockito.atLeast(1)).findById(Mockito.any());
        Mockito.verify(assignmentDetailRepo, Mockito.atLeast(1)).save(Mockito.any());
        Mockito.verify(assignmentRepo, Mockito.atLeast(1)).saveAndFlush(Mockito.any());

    }

    @Test
    public void shouldReturnAll_whenExist() {
        List<Assignment> assignmentList = new ArrayList<>();

        var assignment1 = new Assignment();
        assignment1.setId(UUID.randomUUID());
        assignment1.setCode("ASGN2026");
        var assignment2 = new Assignment();
        assignment2.setId(UUID.randomUUID());
        assignment2.setCode("ASGN2025");

        assignmentList.add(assignment1);
        assignmentList.add(assignment2);

        Mockito.when(assignmentRepo.findAll()).thenReturn(assignmentList);

        var result = assignmentService.getAll();

        Assertions.assertEquals(assignmentList.size(), result.size());
        Assertions.assertEquals("ASGN2026", result.getFirst().getCode());

        Mockito.verify(assignmentRepo, Mockito.atLeast(1)).findAll();
    }

}
