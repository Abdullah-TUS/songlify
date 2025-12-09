package com.songlify.dto.albums;


import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlbumGetDto {
    private Integer id;
    private String title;
    private Date release_date;
}
