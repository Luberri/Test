package com.itu.demo.controller;

import com.itu.demo.annotations.HandleURL;
import com.itu.demo.annotations.Controller;
import com.itu.demo.entity.Etudiant;
import com.itu.demo.ModelView;
import java.util.List;

@Controller
public class EtudiantController {

    @HandleURL("/etudiant/list")
    public ModelView listEtudiants() {
        List<Etudiant> etudiants = Etudiant.readAll();
        ModelView modelView = new ModelView("etudiant/list.jsp");
        modelView.addObject("etudiants", etudiants);
        return modelView;
    }
}
