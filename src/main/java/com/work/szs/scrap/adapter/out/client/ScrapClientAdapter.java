package com.work.szs.scrap.adapter.out.client;

import com.work.szs.common.annotation.ClientAdapter;
import com.work.szs.scrap.adapter.out.client.dto.ScrapResult;
import com.work.szs.scrap.application.dto.command.ScrapDataCommand;
import com.work.szs.scrap.application.dto.request.ScrapDataRequest;
import com.work.szs.scrap.application.port.client.ScrapDataPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@ClientAdapter
@Component
@RequiredArgsConstructor
@Slf4j
public class ScrapClientAdapter implements ScrapDataPort {
    private final ScrapClient scrapClient;

    @Override
    public ScrapDataCommand getScrapData(ScrapDataRequest request) {
        ScrapResult info = scrapClient.scrapBaseInfo(request).getData();
        return info.convertToCommand();
    }
}