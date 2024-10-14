package com.talentica.services;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

public class SpringModulithTests {

    ApplicationModules modules = ApplicationModules.of(SpringModulith.class);

    @Test
    void getCurrentApplicationModules() {
        ApplicationModules modules = ApplicationModules.of(SpringModulith.class);
        modules.forEach(module -> System.out.println(module.getDisplayName()));
    }


    @Test
    void shouldBeCompliant() {
        modules.verify();
    }

    @Test
    void writeDocumentationSnippets() {
        new Documenter(modules)
                .writeModuleCanvases()   //feature is used to generate visual representations of the relationships and interactions between the different modules
                .writeModulesAsPlantUml()
                .writeIndividualModulesAsPlantUml();
    }
}
