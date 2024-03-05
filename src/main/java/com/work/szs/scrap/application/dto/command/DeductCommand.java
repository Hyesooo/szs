package com.work.szs.scrap.application.dto.command;

import com.work.szs.scrap.domain.enums.DeductType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DeductCommand {
    private int year;
    private int month;
    private long amount;
    private DeductType type;
}
