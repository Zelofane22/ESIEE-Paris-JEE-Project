package techsupport.daos;

import jakarta.persistence.*;
import techsupport.entity.Utilisateur;

import java.util.List;


public class UtilisateurDAO {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("supportTechPU");

    public void ajouterUtilisateur(Utilisateur utilisateur) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            // Si aucun rôle n'est défini, attribuer UTILISATEUR par défaut
            if (utilisateur.getRole() == null) {
                utilisateur.setRole(Utilisateur.Role.UTILISATEUR);
            }

            em.persist(utilisateur);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }


    public Utilisateur trouverUtilisateurParId(int id) {
        // Méthode vide à compléter ultérieurement
        return null;
    }

    public void mettreAJourUtilisateur(Utilisateur utilisateur) {
        // Méthode vide à compléter ultérieurement
    }

    public void supprimerUtilisateur(int id) {
        // Méthode vide à compléter ultérieurement
    }

    public List<Utilisateur> recupererTousLesUtilisateurs() {
        // Méthode vide à compléter ultérieurement
        return null;
    }

    public Utilisateur verifierIdentifiants(String email, String password) {
        EntityManager em = emf.createEntityManager();
        try {
            // Requête JPQL pour trouver l'utilisateur par email et mot de passe
            TypedQuery<Utilisateur> query = em.createQuery(
                    "SELECT u FROM Utilisateur u WHERE u.email = :email AND u.password = :password", Utilisateur.class
            );
            query.setParameter("email", email);
            query.setParameter("password", password);

            // Récupérer l'utilisateur ou lever une exception si aucun résultat
            return query.getSingleResult();
        } catch (NoResultException e) {
            // Aucun utilisateur trouvé pour les identifiants donnés
            return null;
        } finally {
            em.close();
        }
    }

    public boolean emailExiste(String email) {
        EntityManager em = emf.createEntityManager();
        try {
            // Requête JPQL pour compter les utilisateurs avec l'email donné
            TypedQuery<Long> query = em.createQuery(
                    "SELECT COUNT(u) FROM Utilisateur u WHERE u.email = :email", Long.class
            );
            query.setParameter("email", email);

            // Récupérer le résultat de la requête
            Long count = query.getSingleResult();
            return count > 0; // Retourne true si un utilisateur est trouvé
        } finally {
            em.close(); // Toujours fermer l'EntityManager
        }
    }
}