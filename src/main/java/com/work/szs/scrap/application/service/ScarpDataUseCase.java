package com.work.szs.scrap.application.service;

import com.work.szs.scrap.application.dto.command.ScrapDataCommand;
import com.work.szs.scrap.application.dto.request.ScrapDataRequest;
import com.work.szs.scrap.application.port.client.ScrapDataPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScarpDataUseCase {
    private final ScrapDataPort scrapDataPort;

    public ScrapDataCommand inputBaseData(ScrapDataRequest request) {
        return scrapDataPort.getScrapData(request);
    }

}
