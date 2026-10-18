package com.glop.commande.domain.service;

import com.glop.commande.domain.port.out.CataloguePort;
import org.springframework.stereotype.Service;

@Service
public class CommandeCatalogueService {
    private final CataloguePort cataloguePort;

    public CommandeCatalogueService(CataloguePort cataloguePort) {
        this.cataloguePort = cataloguePort;
    }

    public String getCatalogueInfo() {
        return cataloguePort.getCatalogueInfo();
    }
}
