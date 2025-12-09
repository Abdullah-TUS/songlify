package com.songlify.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "singers")
public class Singer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    private String genre;

    @Column(nullable = false)
    private Integer listeners = 0;

    private String country;

    private LocalDate birthdate;

    @Column(columnDefinition = "TEXT")
    private String bio;

    @Column(name = "image_url")
    private String imageUrl;
}
