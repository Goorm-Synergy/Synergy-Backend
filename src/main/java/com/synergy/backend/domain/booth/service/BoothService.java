package com.synergy.backend.domain.booth.service;

import com.synergy.backend.domain.booth.dto.BoothRequestDto;
import com.synergy.backend.domain.booth.dto.BoothResponseDto;
import com.synergy.backend.domain.booth.entity.Booth;
import com.synergy.backend.domain.booth.exception.NotFoundBoothException;
import com.synergy.backend.domain.booth.repository.BoothRepository;
import com.synergy.backend.domain.booth.repository.AttendeeBoothRepository;
import com.synergy.backend.domain.conference.entity.Conference;
import com.synergy.backend.domain.conference.repository.ConferenceRepository;
import com.synergy.backend.domain.conference.exception.NotFoundConference;
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
    private final ConferenceRepository conferenceRepository;

    private Booth findBoothByIdAndConference(Long conferenceId, Long id) {
        return boothRepository.findByIdAndConferenceId(id, conferenceId)
                .orElseThrow(NotFoundBoothException::new);
    }

    @Transactional(readOnly = true)
    public BoothResponseDto getBoothById(Long conferenceId, Long id) {
        return new BoothResponseDto(findBoothByIdAndConference(conferenceId, id));
    }

    @Transactional(readOnly = true)
    public List<BoothResponseDto> getAllBooths(Long conferenceId) {
        return boothRepository.findAllByConferenceId(conferenceId)
                .stream().map(BoothResponseDto::new).toList();
    }

    @Transactional
    public BoothResponseDto createBooth(Long conferenceId, BoothRequestDto request) {
        Conference conference = conferenceRepository.findById(conferenceId)
                .orElseThrow(NotFoundConference::new);

        Booth booth = new Booth(
                request.name(),
                request.company(),
                request.location(),
                request.description(),
                conference
        );

        Booth savedBooth = boothRepository.save(booth);
        return new BoothResponseDto(savedBooth);
    }

    @Transactional
    public BoothResponseDto updateBooth(Long conferenceId, Long id, BoothRequestDto request) {
        Booth booth = findBoothByIdAndConference(conferenceId, id);

        booth.updateInfo(
                request.name() != null ? request.name() : booth.getName(),
                request.company() != null ? request.company() : booth.getCompany(),
                request.location() != null ? request.location() : booth.getLocation(),
                request.description() != null ? request.description() : booth.getDescription()
        );

        return new BoothResponseDto(booth);
    }

    @Transactional
    public void deleteBooth(Long conferenceId, Long id) {
        Booth booth = findBoothByIdAndConference(conferenceId, id);

        attendeeBoothRepository.deleteByBooth(booth);
        boothRepository.delete(booth);
    }
}
