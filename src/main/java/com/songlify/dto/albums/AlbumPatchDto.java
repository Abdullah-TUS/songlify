package com.songlify.dto.albums;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumPatchDto {
    @NotBlank(message = "Album id is required.")
    private Integer id;
    private String title;
    private Date releaseDate;
}
