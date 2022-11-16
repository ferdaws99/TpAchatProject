package com.esprit.examen.services;
import java.util.List;

import com.esprit.examen.TpAchatProjectApplication;
import com.esprit.examen.entities.SecteurActivite;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = TpAchatProjectApplication.class)
@RunWith(SpringRunner.class)
public class SecteurServiceJunit {
    @Autowired
    ISecteurActiviteService sas;
    @Test
    @Order(2)
    public void testretrieveAllSecteurs(){
        int listSecteur = sas.retrieveAllSecteurActivite().size();
        List<SecteurActivite> listSecteurs = sas.retrieveAllSecteurActivite();
        Assertions.assertEquals(listSecteur,listSecteurs.size());
    }

    @Test
    @Order(3)
    public void testretrieveSecteurActivite(){
        SecteurActivite sa = sas.addSecteurActivite(SecteurActivite.builder()
                .codeSecteurActivite("code1secteur")
                .libelleSecteurActivite("sect1")
                .build());
        Assertions.assertEquals(sa.getIdSecteurActivite(), sas.retrieveSecteurActivite(sa.getIdSecteurActivite()).getIdSecteurActivite()) ;
        System.out.println("junit retrieve!");

    }

    @Test
    @Order(1)
    public void testaddSecteurActivite(){
        SecteurActivite sa = sas.addSecteurActivite(SecteurActivite.builder()
                .codeSecteurActivite("code2secteur")
                .libelleSecteurActivite("sect2")
                .build());
        Assertions.assertNotNull(sa);
        System.out.println("junit add!");



    }

    @Test
    @Order(5)
    public void testdeleteSecteur(){
        SecteurActivite sa = sas.addSecteurActivite(SecteurActivite.builder()
                .codeSecteurActivite("code3secteur")
                .libelleSecteurActivite("sect3")
                .build());
        sas.deleteSecteurActivite(sa.getIdSecteurActivite());
        //Assertions.assertEquals(- 1,os.retrieveAllOperateurs().size());
        Assertions.assertNull(sas.retrieveSecteurActivite(sa.getIdSecteurActivite()));
        System.out.println("junit delete!");


    }

    @Test
    @Order(4)
    public void tesupdateSecteur(){
        SecteurActivite sa = sas.addSecteurActivite(SecteurActivite.builder()
                .codeSecteurActivite("code4secteur")
                .libelleSecteurActivite("sect4")
                .build());
        sa.setCodeSecteurActivite("test");
        sas.updateSecteurActivite(sa);
        Assertions.assertEquals("test", sas.updateSecteurActivite(sa).getCodeSecteurActivite());
        System.out.println("junit update!");


    }

}
