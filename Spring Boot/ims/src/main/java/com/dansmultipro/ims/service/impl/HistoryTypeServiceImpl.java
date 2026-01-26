package com.dansmultipro.ims.service.impl;

import com.dansmultipro.ims.dto.historytype.HistoryTypeResponseDto;
import com.dansmultipro.ims.exception.NotFoundException;
import com.dansmultipro.ims.model.HistoryType;
import com.dansmultipro.ims.repo.HistoryTypeRepo;
import com.dansmultipro.ims.service.HistoryTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HistoryTypeServiceImpl extends BaseService implements HistoryTypeService {

    private final HistoryTypeRepo historyTypeRepo;

    public HistoryTypeServiceImpl(HistoryTypeRepo historyTypeRepo) {
        this.historyTypeRepo = historyTypeRepo;
    }

    @Override
    public List<HistoryTypeResponseDto> getAll() {
        List<HistoryTypeResponseDto> result = historyTypeRepo.findAll().stream()
                .map(v -> new HistoryTypeResponseDto(v.getId(), v.getCode(), v.getName()))
                .toList();
        return result;
    }

    @Override
    public HistoryTypeResponseDto getById(String id) {
        HistoryType historyType = historyTypeRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new NotFoundException("History type not found")
        );
        return new HistoryTypeResponseDto(historyType.getId(), historyType.getCode(), historyType.getName());
    }

}
