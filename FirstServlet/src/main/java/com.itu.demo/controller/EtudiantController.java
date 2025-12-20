package com.itu.demo.controller;

import com.itu.demo.ApiResponse;
import com.itu.demo.annotations.*;
import com.itu.demo.entity.Etudiant;
import com.itu.demo.ModelView;
import com.itu.demo.UploadedFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    // Nouvelle version avec Map<String, Object>
    @Post("/etudiant/ajouter")
    public ModelView ajouterEtudiant(Map<String, Object> params) {
        Etudiant etudiant = new Etudiant();
        etudiant.setNom((String) params.get("nom"));
        etudiant.setPrenom((String) params.get("prenom"));
        etudiant.setEmail((String) params.get("mail"));
        etudiant.setDateNaissance((String) params.get("dateNaissance"));
        etudiant.setNumeroEtudiant((String) params.get("numeroEtudiant"));
        etudiant.setPromotion((String) params.get("promotion"));
        
        boolean success = etudiant.create();
        
        ModelView modelView = new ModelView("redirect:/etudiant/list");
        if (success) {
            modelView.addObject("message", "Étudiant ajouté avec succès");
        } else {
            modelView.addObject("error", "Erreur lors de l'ajout");
        }
        
        return modelView;
    }

    @Get("/etudiant/{id}")
    public ModelView getEtudiant(int id) {
        Etudiant etudiant = Etudiant.readById(id);
        
        ModelView modelView = new ModelView("etudiant/detail.jsp");
        modelView.addObject("etudiant", etudiant);
        
        if (etudiant == null) {
            modelView.addObject("error", "Étudiant non trouvé");
        }
        
        return modelView;
    }
    
    // Exemple avec Map pour la mise à jour
    @Post("/etudiant/modifier")
    public ModelView modifierEtudiant(Map<String, Object> params) {
        int id = Integer.parseInt((String) params.get("id"));
        Etudiant etudiant = Etudiant.readById(id);
        
        if (etudiant != null) {
            etudiant.setNom((String) params.get("nom"));
            etudiant.setPrenom((String) params.get("prenom"));
            etudiant.setEmail((String) params.get("mail"));
            etudiant.setDateNaissance((String) params.get("dateNaissance"));
            etudiant.setNumeroEtudiant((String) params.get("numeroEtudiant"));
            etudiant.setPromotion((String) params.get("promotion"));
            
            boolean success = etudiant.update();
            
            ModelView modelView = new ModelView("redirect:/etudiant/" + id);
            modelView.addObject("message", success ? "Mise à jour réussie" : "Erreur lors de la mise à jour");
            return modelView;
        }
        
        ModelView modelView = new ModelView("redirect:/etudiant/list");
        modelView.addObject("error", "Étudiant non trouvé");
        return modelView;
    }

    @Get("/api/etudiant/list")
    @RestApi
    public ApiResponse apiListEtudiants() {
        List<Etudiant> etudiants = Etudiant.readAll();
        return ApiResponse.success(etudiants);
    }

    @Get("/api/etudiant/{id}")
    @RestApi
    public ApiResponse apiGetEtudiant(int id) {
        Etudiant etudiant = Etudiant.readById(id);
        if (etudiant == null) {
            return ApiResponse.error("Étudiant non trouvé");
        }
        return ApiResponse.success(etudiant);
    }

    @Post("/api/etudiant/ajouter")
    @RestApi
    public ApiResponse apiAjouterEtudiant(Map<String, Object> params) {
        Etudiant etudiant = new Etudiant();
        etudiant.setNom((String) params.get("nom"));
        etudiant.setPrenom((String) params.get("prenom"));
        etudiant.setEmail((String) params.get("mail"));
        etudiant.setDateNaissance((String) params.get("dateNaissance"));
        etudiant.setNumeroEtudiant((String) params.get("numeroEtudiant"));
        etudiant.setPromotion((String) params.get("promotion"));
        
        boolean success = etudiant.create();
        if (success) {
            return ApiResponse.success(etudiant);
        }
        return ApiResponse.error("Erreur lors de l'ajout");
    }

    // Version avec 2 Maps : données + fichiers
    @Post("/etudiant/ajouter-avec-photo")
    public ModelView ajouterEtudiantAvecPhoto(
            Map<String, Object> params,
            @Param("files") Map<String, UploadedFile> files) {
        
        Etudiant etudiant = new Etudiant();
        etudiant.setNom((String) params.get("nom"));
        etudiant.setPrenom((String) params.get("prenom"));
        etudiant.setEmail((String) params.get("mail"));
        
        // Récupérer le fichier uploadé
        UploadedFile photo = files.get("photo");
        if (photo != null) {
            // Sauvegarder le fichier
            String uploadPath = "uploads/" + photo.getFileName();
            saveFile(photo.getContent(), uploadPath);
            // etudiant.setPhotoPath(uploadPath);
        }
        
        boolean success = etudiant.create();
        
        ModelView modelView = new ModelView("redirect:/etudiant/list");
        modelView.addObject("message", success ? "Étudiant ajouté" : "Erreur");
        return modelView;
    }

    // Version avec UploadedFile directement
    @Post("/etudiant/upload-document")
    public ModelView uploadDocument(
            @Param("id") int id,
            @Param("document") UploadedFile document) {
        
        if (document != null) {
            String fileName = document.getFileName();
            byte[] content = document.getContent();
            String type = document.getContentType();
            
            // Traitement du fichier...
            System.out.println("Fichier reçu: " + fileName + " (" + content.length + " bytes)");
        }
        
        return new ModelView("redirect:/etudiant/" + id);
    }

    // API REST avec upload
    @Post("/api/etudiant/upload")
    @RestApi
    public ApiResponse apiUploadDocument(
            Map<String, Object> params,
            @Param("files") Map<String, UploadedFile> files) {
        
        UploadedFile file = files.get("document");
        if (file == null) {
            return ApiResponse.error("Aucun fichier reçu");
        }
        
        Map<String, Object> result = new HashMap<>();
        result.put("fileName", file.getFileName());
        result.put("size", file.getSize());
        result.put("contentType", file.getContentType());
        
        return ApiResponse.success(result);
    }

    private void saveFile(byte[] content, String path) {
        // Implémentation de la sauvegarde
        try (java.io.FileOutputStream fos = new java.io.FileOutputStream(path)) {
            fos.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}