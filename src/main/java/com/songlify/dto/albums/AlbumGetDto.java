package com.songlify.dto.albums;


import com.songlify.dto.song.SongListDto;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlbumGetDto {
    private Integer id;
    private String title;
    private Date releaseDate;
    private List<SongListDto> songList;
}
