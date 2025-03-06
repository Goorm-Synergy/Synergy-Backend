package com.synergy.backend.domain.booth.service;

import com.synergy.backend.domain.booth.dto.BoothRequestDto;
import com.synergy.backend.domain.booth.dto.BoothResponseDto;
import com.synergy.backend.domain.booth.entity.AttendeeBooth;
import com.synergy.backend.domain.booth.entity.Booth;
import com.synergy.backend.domain.booth.exception.NotFoundBoothException;
import com.synergy.backend.domain.booth.repository.BoothRepository;
import com.synergy.backend.domain.booth.repository.AttendeeBoothRepository;
import com.synergy.backend.domain.member.repository.AttendeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoothService {

    private final BoothRepository boothRepository;
    private final AttendeeBoothRepository attendeeBoothRepository;

    @Transactional(readOnly = true)
    public BoothResponseDto getBoothById(Long id) {
        Booth booth = boothRepository.findById(id)
                .orElseThrow(NotFoundBoothException::new);
        return new BoothResponseDto(booth);
    }

    public List<BoothResponseDto> getAllBooths() {
        return boothRepository.findAll().stream()
                .map(BoothResponseDto::new)
                .toList();
    }

    @Transactional
    public BoothResponseDto createBooth(BoothRequestDto request) {
        Booth booth = new Booth(
                request.getName(),
                request.getCompany(),
                request.getLocation(),
                request.getDescription()
        );
        Booth savedBooth = boothRepository.save(booth);
        return new BoothResponseDto(savedBooth);
    }

    @Transactional
    public BoothResponseDto updateBooth(Long id, BoothRequestDto request) {
        Booth booth = boothRepository.findById(id)
                .orElseThrow(NotFoundBoothException::new);

        booth.updateInfo(
                request.getName(),
                request.getCompany(),
                request.getLocation(),
                request.getDescription()
        );

        return new BoothResponseDto(booth);
    }

    @Transactional
    public void deleteBooth(Long id) {
        Booth booth = boothRepository.findById(id)
                .orElseThrow(NotFoundBoothException::new);
        boothRepository.delete(booth);
    }
}
