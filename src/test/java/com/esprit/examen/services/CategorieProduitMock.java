package com.esprit.examen.services;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.esprit.examen.entities.CategorieProduit;
import com.esprit.examen.repositories.CategorieProduitRepository;

@SpringBootTest
//@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.Silent.class)
public class CategorieProduitMock {

    @Mock
    CategorieProduitRepository cp;
    @InjectMocks
    CategorieProduitServiceImpl cp2;


    CategorieProduit c = CategorieProduit.builder().codeCategorie("123").libelleCategorie("libelle").build();
    List<CategorieProduit> list = new ArrayList<CategorieProduit>() {
        {
            add(CategorieProduit.builder().codeCategorie("1234").libelleCategorie("libelle1").build());
            add(CategorieProduit.builder().codeCategorie("12345").libelleCategorie("libelle2").build());

        }
    };
        @Test
        public void retreiveCategorieProduitTest() {
            Mockito.when(cp.findById(Mockito.anyLong())).thenReturn(Optional.of(c));

            CategorieProduit categorieProduit = cp2.retrieveCategorieProduit((long) 2);
            assertNotNull(categorieProduit);


        }

        @Test
        public void testAddCategorie()
        {
            Mockito.when(cp.save(c)).thenReturn(c);
            CategorieProduit Caprod= cp2.addCategorieProduit(c);
            assertNotNull(Caprod);
        }

    @Test
    public void testdeletefournisseur() {
        CategorieProduit f2 = CategorieProduit.builder() .codeCategorie("DelTest") .libelleCategorie("LibDelete").build();
        cp2.deleteCategorieProduit(f2.getIdCategorieProduit());
        Mockito.verify(cp).deleteById(f2.getIdCategorieProduit());
        System.out.println("delete fournisseur works !");


    }

}