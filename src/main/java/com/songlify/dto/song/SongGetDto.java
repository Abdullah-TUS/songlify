package com.songlify.dto.song;

import com.songlify.models.Album;
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
    private Integer albumId;
    private String albumTitle;

}
