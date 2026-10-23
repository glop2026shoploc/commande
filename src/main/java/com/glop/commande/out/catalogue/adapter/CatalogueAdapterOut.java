package com.glop.commande.out.catalogue.adapter;

import com.glop.commande.domain.port.out.CataloguePort;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
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
    @CircuitBreaker(name = "catalogue", fallbackMethod = "getCatalogueFallback")
    @Retry(name = "catalogue", fallbackMethod = "getCatalogueFallback")
    public String getCatalogueInfo() {
        return restClient.get()
                .uri("/api/catalogue/test")
                .retrieve()
                .body(String.class);
    }

    // Méthode de secours appelée quand le circuit est OUVERT ou en cas d'erreur
    private String getCatalogueFallback(Throwable t) {
        return "Catalogue indisponible";
    }
}
