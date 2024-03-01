package com.work.szs.user.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JoinUserRequest {
    @Schema(description = "사용자ID")
    @NotBlank
    private String userId;

    @Schema(description = "주민번호")
    @NotBlank
    private String regNo;

    @Schema(description = "패스워드")
    @NotBlank
    private String password;

    @Schema(description = "사용자명")
    @NotBlank
    private String name;
}
