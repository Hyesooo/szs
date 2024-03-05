package com.work.szs.scrap.adapter.out.client;

import com.work.szs.common.annotation.ClientAdapter;
import com.work.szs.scrap.adapter.out.client.model.ScrapResponse;
import com.work.szs.scrap.application.dto.result.ScrapDataResult;
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
    public ScrapDataResult getScrapData(ScrapDataRequest request) {
        ScrapResponse info = scrapClient.scrapBaseInfo(request).getData();
        return info.convertToCommand();
    }
}