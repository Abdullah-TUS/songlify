package com.songlify.dto.singer;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SingerUpdateDto {
    private Integer id;
    private String name;
    private Integer listeners;
    private String country;
}
