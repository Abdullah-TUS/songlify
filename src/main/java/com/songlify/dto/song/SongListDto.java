package com.songlify.dto.song;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongListDto {
    private Integer id;
    private String title;
    private Double duration;
}