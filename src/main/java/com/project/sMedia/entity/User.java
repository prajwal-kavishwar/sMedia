package com.project.sMedia.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


//created entity user and made rows to store data in table users
@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//automaticallay assign id
    private Long  id;

    private String userName;

    private Boolean isPremium;



}
