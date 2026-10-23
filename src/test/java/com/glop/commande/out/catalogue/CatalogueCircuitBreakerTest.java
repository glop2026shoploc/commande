package com.glop.commande.out.catalogue;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CatalogueCircuitBreakerTest {

    @Test
    void opensAfterFourFailedCalls() {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
        .slidingWindowSize(4) // Nombre d'appels à considérer pour calculer le taux d'échec
        .minimumNumberOfCalls(4) // Nombre minimum d'appels avant de commencer à calculer le taux d'échec
        .failureRateThreshold(50) // Seuil de taux d'échec pour ouvrir le circuit (50% dans ce cas)
        .build();
        CircuitBreaker circuitBreaker = CircuitBreaker.of("catalogue", config);

        for (int i = 0; i < 3; i++) {
            callThatFails(circuitBreaker);

            assertThat(circuitBreaker.getState())
                    .isEqualTo(CircuitBreaker.State.CLOSED);
        }

        callThatFails(circuitBreaker);

        assertThat(circuitBreaker.getState())
                .isEqualTo(CircuitBreaker.State.OPEN);
    }

    private void callThatFails(CircuitBreaker circuitBreaker) {
        try {
            circuitBreaker.executeRunnable(() -> {
                throw new IllegalStateException("Catalogue indisponible");
            });
        } catch (IllegalStateException exception) {
            // L'appel échoue : Resilience4j enregistre cet échec.
        }
    }
}