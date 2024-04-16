package jpql;

import javax.persistence.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team teamA = new Team();
            teamA.setName("teamA");
            em.persist(teamA);
            
            Team teamB = new Team();
            teamA.setName("teamB");
            em.persist(teamB);
//
//            Member member1 = new Member();
//            member1.setUsername("1");
//            member1.changeTeam(teamA);
//            em.persist(member1);
//
//            Member member2 = new Member();
//            member2.setUsername("2");
//            member2.changeTeam(teamA);
//            em.persist(member2);
//
//            Member member3 = new Member();
//            member3.setUsername("3");
//            member3.changeTeam(teamB);
//            em.persist(member3);
            
            em.flush();
            em.clear();

//            int resultCount = em.createQuery("update Member m set m.age = 20")
//                    .executeUpdate();

//            System.out.println("resultCount = " + resultCount);

            List<Team> resultList = em.createQuery("select t from Team t join fetch t.members", Team.class).getResultList();
            System.out.println("resultList = " + resultList.get(0).getName());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();

    }
}














