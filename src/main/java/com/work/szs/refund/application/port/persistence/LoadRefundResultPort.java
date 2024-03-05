package com.work.szs.refund.application.port.persistence;


import com.work.szs.scrap.domain.RefundResult;
import com.work.szs.user.domain.User;

import java.util.Optional;

public interface LoadRefundResultPort {
    Optional<RefundResult> loadResultByUser(User user);
}
