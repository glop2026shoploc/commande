package com.glop.commande.domain.service;

import com.glop.commande.domain.port.out.CataloguePort;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CommandeCatalogueServiceTest {
    @Test
    void returnsCatalogueInformationFromPort() {
        CataloguePort cataloguePort = () -> "Catalogue response";
        CommandeCatalogueService service = new CommandeCatalogueService(cataloguePort);

        assertThat(service.getCatalogueInfo()).isEqualTo("Catalogue response");
    }
}
