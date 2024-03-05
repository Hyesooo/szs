package com.work.szs.refund.application.port.persistence;

import com.work.szs.scrap.domain.Deduct;

public interface UpdateDeductPort {
    public Deduct saveDeduct(Deduct deduct);
}
