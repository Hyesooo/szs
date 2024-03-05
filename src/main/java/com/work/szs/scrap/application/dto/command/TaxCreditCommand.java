package com.work.szs.scrap.application.dto.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TaxCreditCommand {
    private int year;
    private long amount;
}
