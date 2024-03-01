package com.work.szs.user.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private String userId;

    @Schema(description = "주민번호")
    @NotNull
    private String regNo;

    @Schema(description = "패스워드")
    @NotNull
    private String password;

    @Schema(description = "사용자명")
    @NotNull
    private String name;
}
