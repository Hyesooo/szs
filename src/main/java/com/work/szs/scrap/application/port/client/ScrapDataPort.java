package com.work.szs.scrap.application.port.client;

import com.work.szs.scrap.application.dto.result.ScrapDataResult;
import com.work.szs.scrap.application.dto.request.ScrapDataRequest;

public interface ScrapDataPort {
    ScrapDataResult getScrapData(ScrapDataRequest request);
}
