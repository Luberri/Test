package com.itu.demo.controller;

import com.itu.demo.annotations.*;
import com.itu.demo.entity.Etudiant;
import com.itu.demo.ModelView;
import java.util.List;

@Controller
public class EtudiantController {

    @Get("/etudiant/list")
    public ModelView listEtudiants() {
        List<Etudiant> etudiants = Etudiant.readAll();
        ModelView modelView = new ModelView("etudiant/list.jsp");
        modelView.addObject("etudiants", etudiants);
        return modelView;
    }

    @Get("/etudiant/ajouter")
    public ModelView afficherFormulaire() {
        ModelView modelView = new ModelView("etudiant/form.jsp");
        return modelView;
    }

    @Post("/etudiant/ajouter")
    public ModelView ajouterEtudiant(
            @Param("nom") String nom, 
            @Param("prenom") String prenom, 
            @Param("mail") String mail,
            @Param("dateNaissance") String dateNaissance,
            @Param("numeroEtudiant") String numeroEtudiant,
            @Param("promotion") String promotion) {
        
        Etudiant etudiant = new Etudiant();
        etudiant.setNom(nom);
        etudiant.setPrenom(prenom);
        etudiant.setEmail(mail);
        etudiant.setDateNaissance(dateNaissance);
        etudiant.setNumeroEtudiant(numeroEtudiant);
        etudiant.setPromotion(promotion);
        etudiant.create();
        
        ModelView modelView = new ModelView("redirect:/etudiant/list");
        return modelView;
    }

    @Get("/etudiant/{id}")
    public ModelView getEtudiant(int id) {
        Etudiant etudiant = Etudiant.readById(id);
        ModelView modelView = new ModelView("etudiant/detail.jsp");
        modelView.addObject("etudiant", etudiant);
        return modelView;
    }
}
