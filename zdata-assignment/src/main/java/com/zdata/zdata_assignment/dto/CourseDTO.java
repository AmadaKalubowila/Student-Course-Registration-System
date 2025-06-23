package com.zdata.zdata_assignment.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    @NotNull
    private String code;
    @NotNull
    private String title;
    @NotNull
    private String instructor;
}
