package com.esprit.examen.services;

import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.esprit.examen.entities.CategorieProduit;
import com.esprit.examen.repositories.CategorieProduitRepository;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RunWith(SpringRunner.class)
@SpringBootTest

public class CategorieProduitTestImpl {
    @Autowired
    ICategorieProduitService cp;
    @Autowired
    CategorieProduitRepository catRepo;

    private static final Logger l = LogManager.getLogger(CategorieProduitTestImpl.class);

    @Test
    public void testretrieveAllCategorieProduits(){
        List<CategorieProduit> listCat = cp.retrieveAllCategorieProduits();
        long size = listCat.size();
        assertThat(listCat).size().isGreaterThan(0);
        l.info("Le Size des CategorieProduit est : " + size);
    }

    @Test
    public void testaddCategorieProduit(){
        CategorieProduit c = new CategorieProduit(1L,"codeCat1","libCat1",null);
        Assertions.assertNotNull(c, "Champ vide !!");
        cp.addCategorieProduit(c);
        l.info("Categorie added successfuly !");
    }

    @Test
    public void testdeleteCategorieProduit(){
        CategorieProduit c = new CategorieProduit(1L,"codeCat1","libCat1",null);
        cp.addCategorieProduit(c);
        cp.deleteCategorieProduit(c.getIdCategorieProduit());
        l.info("Categorie deleted successfuly !");
    }

    @Test
    public void testupdateCategorieProduit(){
        CategorieProduit c = new CategorieProduit(1L,"codeCat1","libCat1",null);
        Assertions.assertNotNull(c);
        cp.updateCategorieProduit(c);
        l.info("Categorie updated successfuly !");
    }


}
