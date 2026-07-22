package com.altiacompany.pricing.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.altiacompany.pricing.application.port.input.GetPriceUseCase;
import com.altiacompany.pricing.application.port.output.PriceRepository;
import com.altiacompany.pricing.application.service.GetPriceService;
import com.altiacompany.pricing.domain.service.PriceSelector;

@Configuration
public class BeanConfiguration {

    @Bean
    public PriceSelector priceSelector() {
        return new PriceSelector();
    }

    @Bean
    public GetPriceUseCase getPriceUseCase(PriceRepository priceRepository,
            PriceSelector priceSelector) {
        return new GetPriceService(priceRepository, priceSelector);
    }

}
