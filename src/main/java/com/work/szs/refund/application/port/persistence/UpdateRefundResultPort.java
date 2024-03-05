package com.work.szs.refund.application.port.persistence;

import com.work.szs.refund.domain.RefundResult;

public interface UpdateRefundResultPort {
    public RefundResult saveResult(RefundResult r);
}
