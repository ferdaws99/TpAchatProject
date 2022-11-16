

package com.esprit.examen.services;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.Optional;

        import com.esprit.examen.entities.CategorieFournisseur;
        import com.esprit.examen.entities.Fournisseur;
        import com.esprit.examen.repositories.DetailFournisseurRepository;
        import com.esprit.examen.repositories.FournisseurRepository;
        import org.junit.runner.RunWith;
        import org.mockito.Mockito;
        import org.mockito.junit.jupiter.MockitoExtension;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.test.context.junit4.SpringRunner;


        import lombok.extern.slf4j.Slf4j;

        import static org.junit.Assert.*;

        import org.junit.Test;
        import org.junit.jupiter.api.Assertions;
        import org.junit.jupiter.api.Order;
        import org.junit.jupiter.api.extension.ExtendWith;
        import org.springframework.boot.test.mock.mockito.MockBean;

        import static org.mockito.Mockito.times;
        import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@ExtendWith(MockitoExtension.class)
public class FournisseurServiceMock {
    @Autowired
    FournisseurServiceImpl fournisseurService;
    @MockBean
    FournisseurRepository   fournisseurRepository;
    @MockBean
    DetailFournisseurRepository detailFournisseurRepository;
    List<Fournisseur> list = new ArrayList<Fournisseur> () {
        {
            add(new Fournisseur("CodeFournisseur","LibelleFournisseur",CategorieFournisseur.ORDINAIRE,null));
            add(new Fournisseur("CodeFournisseur","LibelleFournisseur",CategorieFournisseur.ORDINAIRE,null));

        }
    };


    @Test
    @Order(1)
    public void addFournisseur() {

        Fournisseur f = new Fournisseur("test", "test mock", CategorieFournisseur.CONVENTIONNE, null);
        Mockito.when(fournisseurRepository.save(f)).thenReturn(f);
        assertEquals(f, fournisseurService.addFournisseur(f));
        log.info("fournisseur est bien ajouter");
    }


    @Test
    @Order(2)
    public void deleteFournisseur() {
        Fournisseur f2 = new Fournisseur("test2", "test mock", CategorieFournisseur.ORDINAIRE, null);
        assertNotNull(f2.getCode());
        assertNotNull(f2.getLibelle());
        fournisseurService.deleteFournisseur((f2.getIdFournisseur()));
        verify(fournisseurRepository,times(1)).deleteById(f2.getIdFournisseur());
        log.info("fournisseur est bien supprimer");
    }
    @Test
    @Order(4)
    public void retrieveAllFournisseurs() {
        Mockito.when(fournisseurRepository.findAll()).thenReturn(list);
        assertEquals(list.size(), fournisseurService.retrieveAllFournisseurs().size());
        log.info("les fournisseurs sont bien afficher");

    }

    @Test
    @Order(3)
    public void retrieveFournisseur() {
        Fournisseur f3 = new Fournisseur("test3 ", "test mock", CategorieFournisseur.CONVENTIONNE, null);
        Mockito.when(fournisseurRepository.findById(f3.getIdFournisseur())).thenReturn(Optional.of(f3));
        Assertions.assertNotNull(fournisseurService.retrieveFournisseur(f3.getIdFournisseur()));
        log.info("fournisseur est bien afficher");
    }

    @Test
    @Order(5)
    public void UpdateFournisseur() {
        Fournisseur f = new Fournisseur("CodeFournisseur","LibelleFournisseur",CategorieFournisseur.ORDINAIRE,null);
        System.out.println("new Fournisseur"+f);
        Mockito.when(fournisseurRepository.save(f)).thenReturn(f);
        f.setLibelle("LibelleUpdated");
        f.setCode("CodeUpdated");
        Fournisseur fUpdated = fournisseurService.updateFournisseur(f);
        assertEquals(f.getLibelle(),fUpdated.getLibelle());
        log.info("fournisseur is updated");

    }

}