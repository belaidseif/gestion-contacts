package com.genesis.gestioncontacts.controller;

import com.genesis.gestioncontacts.dto.EntrepriseDto;
import com.genesis.gestioncontacts.dto.request.EntrepriseRequestDto;
import com.genesis.gestioncontacts.service.EntrepriseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/enterprises")
@Tag(name = "enterprise api")
public class EntrepriseController {

    private  final EntrepriseService entrepriseServicer;

    public EntrepriseController(EntrepriseService entrepriseServicer) {
        this.entrepriseServicer = entrepriseServicer;
    }


    @GetMapping("all")
    @Operation(description = "get all enterprises")
    public ResponseEntity<List<EntrepriseDto>> listeEntreprises(){
        List<EntrepriseDto> all = entrepriseServicer.getAllEnterprises();
        return ResponseEntity.ok(all);
    }


    @PostMapping("add")
    @Operation(description = "add new enterprise")
    public ResponseEntity<EntrepriseDto> ajouterEntreprise(
            @RequestBody @Valid EntrepriseRequestDto entrepriseRequestDto){

        EntrepriseDto entrepriseDto =  entrepriseServicer.addNewEnterprise(entrepriseRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(entrepriseDto);
    }

    @PutMapping("edit/{id}")
    @Operation(description = "edit existing enterprise")
    public ResponseEntity<EntrepriseDto> modifierEntreprise(
            @PathVariable @Valid Long id,
            @RequestBody @Valid EntrepriseRequestDto entrepriseRequestDto){

        EntrepriseDto entrepriseDto =  entrepriseServicer.editEnterprise(id,entrepriseRequestDto);
        return ResponseEntity.ok(entrepriseDto);
    }

    @GetMapping("find-with-tva/{tva}")
    @Operation(description = "search for enterprise using tva")
    public ResponseEntity<EntrepriseDto> rechercherEntrepriseAvecTva(
            @PathVariable("tva") @Valid Integer tva){

        EntrepriseDto entrepriseDto =  entrepriseServicer.getEnterpriseByTva(tva);
        return ResponseEntity.ok(entrepriseDto);
    }
}
