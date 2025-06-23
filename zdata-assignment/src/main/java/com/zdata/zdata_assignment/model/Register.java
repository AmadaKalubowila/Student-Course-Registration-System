package com.zdata.zdata_assignment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Register {
    private UUID studentId;
    private UUID courseId;
    private LocalDateTime registerAt;
}
