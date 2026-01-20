package com.dansmultipro.ams.service.unit;

import com.dansmultipro.ams.dto.assignment.AssignmentRequestDto;
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
}
