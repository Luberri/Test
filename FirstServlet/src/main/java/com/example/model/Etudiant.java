package com.example.model;

import com.example.util.PostgresConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Etudiant {
    private Integer id;
    private String nom;
    private String prenom;
    private String email;
    private Date dateNaissance;
    private String numeroEtudiant;
    private String promotion;
    private Timestamp dateInscription;

    // Constructeurs
    public Etudiant() {}

    public Etudiant(String nom, String prenom, String email, Date dateNaissance, 
                    String numeroEtudiant, String promotion) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.numeroEtudiant = numeroEtudiant;
        this.promotion = promotion;
    }

    // Getters et Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getNumeroEtudiant() {
        return numeroEtudiant;
    }

    public void setNumeroEtudiant(String numeroEtudiant) {
        this.numeroEtudiant = numeroEtudiant;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public Timestamp getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Timestamp dateInscription) {
        this.dateInscription = dateInscription;
    }

    // Méthodes CRUD
    
    /**
     * Sauvegarde un nouvel étudiant en base de données
     */
    public void save() throws SQLException {
        String sql = "INSERT INTO etudiant (nom, prenom, email, date_naissance, numero_etudiant, promotion) " +
                     "VALUES (?, ?, ?, ?, ?, ?) RETURNING id";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = PostgresConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, this.nom);
            stmt.setString(2, this.prenom);
            stmt.setString(3, this.email);
            stmt.setDate(4, this.dateNaissance);
            stmt.setString(5, this.numeroEtudiant);
            stmt.setString(6, this.promotion);
            
            rs = stmt.executeQuery();
            if (rs.next()) {
                this.id = rs.getInt(1);
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            PostgresConnection.closeConnection(conn);
        }
    }
    
    /**
     * Met à jour un étudiant existant
     */
    public void update() throws SQLException {
        if (this.id == null) {
            throw new SQLException("Cannot update: ID is null");
        }
        
        String sql = "UPDATE etudiant SET nom=?, prenom=?, email=?, date_naissance=?, " +
                     "numero_etudiant=?, promotion=? WHERE id=?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = PostgresConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, this.nom);
            stmt.setString(2, this.prenom);
            stmt.setString(3, this.email);
            stmt.setDate(4, this.dateNaissance);
            stmt.setString(5, this.numeroEtudiant);
            stmt.setString(6, this.promotion);
            stmt.setInt(7, this.id);
            
            stmt.executeUpdate();
        } finally {
            if (stmt != null) stmt.close();
            PostgresConnection.closeConnection(conn);
        }
    }
    
    /**
     * Supprime un étudiant par son ID
     */
    public void delete() throws SQLException {
        if (this.id == null) {
            throw new SQLException("Cannot delete: ID is null");
        }
        
        String sql = "DELETE FROM etudiant WHERE id=?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = PostgresConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, this.id);
            stmt.executeUpdate();
        } finally {
            if (stmt != null) stmt.close();
            PostgresConnection.closeConnection(conn);
        }
    }
    
    /**
     * Récupère tous les étudiants
     */
    public static List<Etudiant> getAll() throws SQLException {
        String sql = "SELECT * FROM etudiant ORDER BY id";
        List<Etudiant> etudiants = new ArrayList<>();
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = PostgresConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                etudiants.add(mapResultSetToEtudiant(rs));
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            PostgresConnection.closeConnection(conn);
        }
        
        return etudiants;
    }
    
    /**
     * Récupère un étudiant par son ID
     */
    public static Etudiant getById(int id) throws SQLException {
        String sql = "SELECT * FROM etudiant WHERE id=?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = PostgresConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapResultSetToEtudiant(rs);
            }
            return null;
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            PostgresConnection.closeConnection(conn);
        }
    }
    
    /**
     * Récupère un étudiant par son email
     */
    public static Etudiant getByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM etudiant WHERE email=?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = PostgresConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapResultSetToEtudiant(rs);
            }
            return null;
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            PostgresConnection.closeConnection(conn);
        }
    }
    
    /**
     * Recherche des étudiants par nom (LIKE)
     */
    public static List<Etudiant> searchByNom(String nom) throws SQLException {
        String sql = "SELECT * FROM etudiant WHERE nom ILIKE ? ORDER BY nom";
        List<Etudiant> etudiants = new ArrayList<>();
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = PostgresConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + nom + "%");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                etudiants.add(mapResultSetToEtudiant(rs));
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            PostgresConnection.closeConnection(conn);
        }
        
        return etudiants;
    }
    
    /**
     * Récupère les étudiants par promotion
     */
    public static List<Etudiant> getByPromotion(String promotion) throws SQLException {
        String sql = "SELECT * FROM etudiant WHERE promotion=? ORDER BY nom";
        List<Etudiant> etudiants = new ArrayList<>();
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = PostgresConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, promotion);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                etudiants.add(mapResultSetToEtudiant(rs));
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            PostgresConnection.closeConnection(conn);
        }
        
        return etudiants;
    }
    
    /**
     * Compte le nombre total d'étudiants
     */
    public static int count() throws SQLException {
        String sql = "SELECT COUNT(*) FROM etudiant";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = PostgresConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            PostgresConnection.closeConnection(conn);
        }
    }
    
    /**
     * Méthode utilitaire pour mapper un ResultSet vers un objet Etudiant
     */
    private static Etudiant mapResultSetToEtudiant(ResultSet rs) throws SQLException {
        Etudiant etudiant = new Etudiant();
        etudiant.setId(rs.getInt("id"));
        etudiant.setNom(rs.getString("nom"));
        etudiant.setPrenom(rs.getString("prenom"));
        etudiant.setEmail(rs.getString("email"));
        etudiant.setDateNaissance(rs.getDate("date_naissance"));
        etudiant.setNumeroEtudiant(rs.getString("numero_etudiant"));
        etudiant.setPromotion(rs.getString("promotion"));
        etudiant.setDateInscription(rs.getTimestamp("date_inscription"));
        return etudiant;
    }
    
    @Override
    public String toString() {
        return "Etudiant{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", numeroEtudiant='" + numeroEtudiant + '\'' +
                ", promotion='" + promotion + '\'' +
                ", dateInscription=" + dateInscription +
                '}';
    }
}