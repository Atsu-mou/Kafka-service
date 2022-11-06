package com.example.demo.Model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetFull {

    private Long id;

    private String name;

    private String gender;

    private Integer age;

    private String animal;
}
