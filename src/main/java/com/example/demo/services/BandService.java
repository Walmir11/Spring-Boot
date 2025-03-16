package com.example.demo.services;

import com.example.demo.models.Band;
import com.example.demo.models.User;
import com.example.demo.repositories.BandRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BandService {

    @Autowired
    private BandRepository bandRepository;

    @Autowired
    private UserRepository userRepository;

    public Band createBand(String name, Long idLeader) {

        User leader = userRepository.
        findById(idLeader).orElseThrow(() -> new RuntimeException("User not found"));
        Band band = Band.builder()
                .name(name)
                .leader(leader)
                .build();
        return bandRepository.save(band);
    }

    public List<Band> listUserBands(Long idUser) {
        return bandRepository.findAll()
                .stream()
                .filter(b-> b.getMembers().stream().anyMatch(i->i.getId().equals(idUser)))
                .toList();
    }

    public Band findBandById(Long id) {
        return bandRepository.findById(id).orElseThrow(() -> new RuntimeException("Band not found"));
    }

    public Band addMembersToBand(Long bandId, List<Long> memberIds) {
        Band band = bandRepository.findById(bandId)
                .orElseThrow(() -> new RuntimeException("Band not found"));

        List<User> members = userRepository.findAllById(memberIds);
        band.getMembers().addAll(members);

        return bandRepository.save(band);
    }
    public Band addMemberToBand(Long bandId, Long memberId) {
        Band band = bandRepository.findById(bandId)
                .orElseThrow(() -> new RuntimeException("Band not found"));

        User member = userRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        band.getMembers().add(member);

        return bandRepository.save(band);
    }
}