package com.songlify.dto.song;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SongGetDto {
    private Integer id;
    private String title;
    private Double duration;
}
