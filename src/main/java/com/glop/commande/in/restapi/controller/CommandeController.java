package com.glop.commande.in.restapi.controller;

import com.glop.commande.domain.service.CommandeCatalogueService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/commandes")
public class CommandeController {
    private final CommandeCatalogueService commandeCatalogueService;

    public CommandeController(CommandeCatalogueService commandeCatalogueService) {
        this.commandeCatalogueService = commandeCatalogueService;
    }

    @GetMapping("/test-catalogue")
    public String testCatalogueCall() {
        return commandeCatalogueService.getCatalogueInfo();
    }
}
