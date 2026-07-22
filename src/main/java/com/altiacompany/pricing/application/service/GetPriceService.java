package com.altiacompany.pricing.application.service;

import java.util.List;

import com.altiacompany.pricing.application.port.input.GetPriceQuery;
import com.altiacompany.pricing.application.port.input.GetPriceUseCase;
import com.altiacompany.pricing.application.port.output.PriceRepository;
import com.altiacompany.pricing.domain.exception.PriceNotFoundException;
import com.altiacompany.pricing.domain.model.Price;
import com.altiacompany.pricing.domain.service.PriceSelector;

/**
 * Application service that orchestrates the use case for retrieving the
 * applicable product price.
 */
public class GetPriceService implements GetPriceUseCase {

    private final PriceRepository priceRepository;
    private final PriceSelector priceSelector;

    public GetPriceService(PriceRepository priceRepository,
            PriceSelector priceSelector) {
        this.priceRepository = priceRepository;
        this.priceSelector = priceSelector;
    }

    @Override
    public Price getApplicablePrice(GetPriceQuery query) {
        List<Price> prices =
                priceRepository.findByApplicationDateAndProductIdAndBrandId(
                        query.getApplicationDate(), query.getProductId(),
                        query.getBrandId());

        return priceSelector.selectApplicablePrice(prices).orElseThrow(
                () -> new PriceNotFoundException(query.getApplicationDate(),
                        query.getProductId(), query.getBrandId()));
    }
}
