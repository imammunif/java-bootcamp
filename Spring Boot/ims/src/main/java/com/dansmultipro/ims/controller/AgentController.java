package com.dansmultipro.ims.controller;

import com.dansmultipro.ims.dto.CreateResponseDto;
import com.dansmultipro.ims.dto.DeleteResponseDto;
import com.dansmultipro.ims.dto.PaginatedResponseDto;
import com.dansmultipro.ims.dto.UpdateResponseDto;
import com.dansmultipro.ims.dto.agent.AgentResponseDto;
import com.dansmultipro.ims.dto.agent.CreateAgentRequestDto;
import com.dansmultipro.ims.dto.agent.UpdateAgentRequestDto;
import com.dansmultipro.ims.service.AgentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("agents")
public class AgentController {

    private final AgentService supplierService;

    public AgentController(AgentService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public ResponseEntity<PaginatedResponseDto<AgentResponseDto>> getAllAgents(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        PaginatedResponseDto<AgentResponseDto> res = supplierService.getAll(page, size);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<AgentResponseDto> getAgentById(@PathVariable String id) {
        AgentResponseDto res = supplierService.getById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreateResponseDto> createAgent(@RequestBody @Valid CreateAgentRequestDto requestDto) {
        CreateResponseDto res = supplierService.create(requestDto);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<UpdateResponseDto> updateAgent(@PathVariable String id, @RequestBody @Valid UpdateAgentRequestDto requestDto) {
        UpdateResponseDto res = supplierService.update(id, requestDto);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DeleteResponseDto> deleteAgent(@PathVariable String id) {
        DeleteResponseDto res = supplierService.delete(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
