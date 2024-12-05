package techsupport.daos;

import jakarta.persistence.*;
import techsupport.entity.Requete;

import java.util.List;

public class RequeteDAO {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("supportTechPU");

    // Ajouter une nouvelle requête
    public void creerRequete(Requete requete) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(requete);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Récupérer une requête par ID
    public Requete getRequeteParId(int idToRespond) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Requete.class, idToRespond);
        } finally {
            em.close();
        }
    }

    // Récupérer toutes les requêtes
    public List<Requete> recupererToutesLesRequetes() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Requete> query = em.createQuery("SELECT r FROM Requete r", Requete.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Récupérer les requêtes par statut
    public List<Requete> getRequetesParStatut(Requete.Statut statut) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Requete> query = em.createQuery("SELECT r FROM Requete r WHERE r.statut = :statut", Requete.class);
            query.setParameter("statut", statut);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Récupérer les requêtes pour un utilisateur spécifique
    public List<Requete> getRequetesParUtilisateur(int utilisateur_id) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Requete> query = em.createQuery("SELECT r FROM Requete r WHERE r.utilisateur.id = :utilisateur_id", Requete.class);
            query.setParameter("utilisateur_id", utilisateur_id);
            System.out.println(query.getResultList());
            return query.getResultList();
        } finally {
            em.close();
        }
    }


    // Mettre à jour le statut d'une requête (utiliser par l'admin)
    public void mettreAJourStatut(int id, Requete.Statut nouveauStatut) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Requete requete = em.find(Requete.class, id);
            if (requete != null) {
                requete.setStatut(nouveauStatut);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Supprimer une requête
    public void supprimerRequete(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Requete requete = em.find(Requete.class, id);
            if (requete != null) {
                em.remove(requete);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }


    // Vérifier qu'une requête appartient à l'utilisateur
    public boolean verifierProprieteRequete(int requeteId, int utilisateurId) {
        EntityManager em = emf.createEntityManager();
        try {
            // Requête JPQL pour vérifier si la requête appartient à l'utilisateur
            TypedQuery<Long> query = em.createQuery(
                    "SELECT COUNT(r) FROM Requete r WHERE r.id = :requeteId AND r.utilisateur.id = :utilisateurId",
                    Long.class
            );
            query.setParameter("requeteId", requeteId);
            query.setParameter("utilisateurId", utilisateurId);

            // Récupérer le nombre de résultats (0 ou 1)
            Long count = query.getSingleResult();
            return count > 0; // Retourne true si une correspondance est trouvée
        } finally {
            em.close();
        }
    }


    public void mettreAJourRequete(Requete requete) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = em.getTransaction();
            transaction.begin();
            em.merge(requete);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
