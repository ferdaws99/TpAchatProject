package com.esprit.examen.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.esprit.examen.TpAchatProjectApplication;
import com.esprit.examen.entities.Facture;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = TpAchatProjectApplication.class)
public class FactureServiceImplTest {
    @Autowired
    private IFactureService FactureService;

    @Test
    @Order(1)
    public void testAddFacture() {
        Facture op = FactureService.addFacture(Facture.builder().montantFacture((float) 45.700).montantRemise((float) 10.200).build());
        Assertions.assertNotNull(op);
    }

    @Test
    @Order(2)
    public void testRetrieveAllFacture() {
        int listOperateur = FactureService.retrieveAllFactures().size();
        FactureService.addFacture(Facture.builder().montantFacture((float) 40.700).montantRemise((float) 5.700).build());
        Assertions.assertEquals(listOperateur+1,FactureService.retrieveAllFactures().size());
    }

    @Test
    @Order(3)
    public void testUpdateFacture() {
        Facture p = FactureService.updateFacture(Facture.builder().montantFacture((float) 45.700).montantRemise((float) 10.200).build());
        Assertions.assertNotNull(p);
    }


}
