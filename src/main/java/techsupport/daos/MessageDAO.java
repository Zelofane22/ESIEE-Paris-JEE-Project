package techsupport.daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import techsupport.entity.Message;
import techsupport.entity.Requete;

public class MessageDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("supportTechPU");

    public void ajouterMessage(int requeteId, String auteur, String contenu) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            Requete requete = em.find(Requete.class, requeteId);
            if (requete != null) {
                Message message = new Message();
                message.setRequete(requete);
                message.setAuteur(auteur);
                message.setContenu(contenu);
                em.persist(message);
            }

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
