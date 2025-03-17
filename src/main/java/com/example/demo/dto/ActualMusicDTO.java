package com.example.demo.dto;

import com.example.demo.models.Music;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Base64;

@Data
@AllArgsConstructor
public class ActualMusicDTO {

    private Long id;
    private String title;
    private String pdfBase64;

    public static ActualMusicDTO fromEntity(Music music) {
        String pdfBase64 = Base64.getEncoder().encodeToString(music.getPdfFile());
        return new ActualMusicDTO(music.getId(), music.getTitle(), pdfBase64);
    }
}