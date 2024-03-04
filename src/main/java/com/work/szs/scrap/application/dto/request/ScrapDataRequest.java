package com.work.szs.scrap.application.dto.request;

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
public class ScrapDataRequest {
    @Schema(description = "사용자명")
    @NotBlank
    private String name;

    @Schema(description = "주민번호")
    @NotBlank
    private String regNo;
}
