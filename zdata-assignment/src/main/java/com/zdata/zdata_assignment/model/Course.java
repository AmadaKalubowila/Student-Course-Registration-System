package com.zdata.zdata_assignment.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    private UUID id;
    @NotNull
    private String code;
    @NotNull
    private String title;
    @NotNull
    private String instructor;


}
