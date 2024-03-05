package com.work.szs.scrap.application.dto.result;

import com.work.szs.refund.domain.enums.DeductType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DeductResult {
    private int year;
    private int month;
    private long amount;
    private DeductType type;
}
