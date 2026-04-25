package com.project.sMedia.entity;


import jakarta.persistence.*;
import lombok.*;


@Table(name="bots")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String persona_description;


}
