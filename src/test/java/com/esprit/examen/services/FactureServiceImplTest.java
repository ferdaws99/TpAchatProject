package com.esprit.examen.services;

import com.esprit.examen.entities.DetailFacture;
import com.esprit.examen.entities.Fournisseur;
import com.esprit.examen.entities.Reglement;
import com.esprit.examen.repositories.FactureRepository;
import lombok.var;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.esprit.examen.TpAchatProjectApplication;
import com.esprit.examen.entities.Facture;

import java.util.Date;
import java.util.Set;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = TpAchatProjectApplication.class)
public class FactureServiceImplTest {
    @Autowired
    private IFactureService FactureService;

    @Test
    @Order(1)
    public void testAddFacture() {
        Facture op = FactureService.addFacture(Facture.builder().montantFacture((float) 45.700).montantRemise((float) 10.200).dateCreationFacture(new Date()).
                archivee(false).dateDerniereModificationFacture(new Date(0)).
                detailsFacture((Set<DetailFacture>) new DetailFacture()).fournisseur(new Fournisseur()).reglements((Set<Reglement>) new Reglement()).build());
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
    public void testCancelFacture() {
        Facture factureAjoute = FactureService.addFacture(Facture.builder().idFacture(1L).montantFacture((float) 85.700).montantRemise((float) 25.200).dateCreationFacture(new Date()).
                archivee(false).dateDerniereModificationFacture(new Date(0)).
                detailsFacture((Set<DetailFacture>) new DetailFacture()).fournisseur(new Fournisseur()).reglements((Set<Reglement>) new Reglement()).build());
        Assertions.assertNotNull(factureAjoute.getIdFacture());
        Facture factureRecu = FactureService.retrieveFacture(factureAjoute.getIdFacture());
        FactureService.cancelFacture(factureRecu.getIdFacture());
        Assertions.assertTrue(factureRecu.getArchivee());
    }

    @Test
    @Order(4)
    public void testGetFacture() {
        Facture factureAjoute = FactureService.addFacture(Facture.builder().idFacture(1L).montantRemise(2f).montantFacture(10f).archivee(false).build());
        Assertions.assertNotNull(factureAjoute.getIdFacture());

        Facture factureRecu = FactureService.retrieveFacture(factureAjoute.getIdFacture());
        Assertions.assertNotNull(factureRecu.getIdFacture());
        Assertions.assertEquals(10f, factureRecu.getMontantFacture());
        Assertions.assertEquals(2f, factureRecu.getMontantRemise());

    }


}
