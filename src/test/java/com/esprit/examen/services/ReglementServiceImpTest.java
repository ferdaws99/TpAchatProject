package com.esprit.examen.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.esprit.examen.entities.Facture;
import com.esprit.examen.entities.Reglement;
import com.esprit.examen.repositories.ReglementRepository;
import com.esprit.examen.services.ReglementServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class ReglementServiceImpTest {
    @InjectMocks
    private ReglementServiceImpl reglementServiceImpl;
    @Mock
    private ReglementRepository reglementRepository;

    @Test
    public void addReglement() {
        Reglement reglement = new Reglement();
        when(reglementRepository.save(reglement)).thenReturn(reglement);
        assertEquals(reglement, reglementServiceImpl.addReglement(reglement));
    }

    @Test
    public void retrieveReglementTest() {
        Reglement reglement = new Reglement();
        when(reglementRepository.findById(reglement.getIdReglement())).thenReturn(java.util.Optional.of(reglement));
        assertEquals(reglement.getIdReglement(),
                reglementServiceImpl.retrieveReglement(reglement.getIdReglement()).getIdReglement());
    }

    @Test
    public void retrieveAllReglementTest() {
        when(reglementRepository.findAll())
                .thenReturn(Stream.of(new Reglement(), new Reglement(), new Reglement()).collect(Collectors.toList()));
        assertEquals(3, reglementServiceImpl.retrieveAllReglements().size());
    }

    @Test
    public void retrieveReglementByFactureTest() {
        Reglement reglement = new Reglement();
        Facture facture = new Facture();
        reglement.setFacture(facture);

        when(reglementRepository.retrieveReglementByFacture(reglement.getFacture().getIdFacture()))
                .thenReturn(Stream.of(new Reglement(), new Reglement(), new Reglement()).collect(Collectors.toList()));
        assertEquals(3, reglementServiceImpl.retrieveReglementByFacture(reglement.getFacture().getIdFacture()).size());
    }

}
