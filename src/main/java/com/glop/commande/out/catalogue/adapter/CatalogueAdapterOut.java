package com.glop.commande.out.catalogue.adapter;

import com.glop.commande.domain.port.out.CataloguePort;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class CatalogueAdapterOut implements CataloguePort {
    private final RestClient restClient;

    public CatalogueAdapterOut(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder.baseUrl("http://catalogue").build();
    }

    @Override
    public String getCatalogueInfo() {
        return restClient.get()
                .uri("/test")
                .retrieve()
                .body(String.class);
    }
}
