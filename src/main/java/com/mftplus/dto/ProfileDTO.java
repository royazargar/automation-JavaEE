package com.mftplus.dto;

import com.mftplus.model.enums.Gender;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class ProfileDTO {
    private Long id;

    private String name;

    private String family;

    private String nationalCode;

    private Gender gender;

    private Long roleId;

    private String username;
}
