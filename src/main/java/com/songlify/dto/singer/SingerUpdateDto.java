package com.songlify.dto.singer;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SingerUpdateDto {
    private String name;
    private int listeners;
    private String country;
}
