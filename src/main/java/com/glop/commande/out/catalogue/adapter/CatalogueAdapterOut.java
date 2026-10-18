package com.glop.commande.out.catalogue.adapter;

import com.glop.commande.domain.port.out.CataloguePort;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class CatalogueAdapterOut implements CataloguePort {

    private final RestClient restClient;

    public CatalogueAdapterOut(@LoadBalanced RestClient.Builder builder) {
    this.restClient = builder.clone().baseUrl("http://catalogue").build();
    }

    @Override
    public String getCatalogueInfo() {
        return restClient.get()
                .uri("/test")
                .retrieve()
                .body(String.class);
    }
}