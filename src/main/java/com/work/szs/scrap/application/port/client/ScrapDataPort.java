package com.work.szs.scrap.application.port.client;

import com.work.szs.scrap.application.dto.command.ScrapDataCommand;
import com.work.szs.scrap.application.dto.request.ScrapDataRequest;

public interface ScrapDataPort {
    ScrapDataCommand getScrapData(ScrapDataRequest request);
}
