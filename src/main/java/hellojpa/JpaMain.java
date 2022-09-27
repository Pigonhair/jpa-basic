package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            //삽입
            Member member = new Member();
            member.setId(1L);
            member.setName("HelloA");

            em.persist(member);


            /** 찾기
            Member findMember = em.find(Member.class, 1L);
            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.name = " + findMember.getName());
            **/

            /** 수정
            Member findMember = em.find(Member.class, 1L);
            findMember.setName("HelloJPA");
            **/

            /** 삭제
            Member findMember = em.find(Member.class, 1L);
            em.remove(findMember);
            **/

            /*전체 리스트 출력(JPQL사용) + 페이지처리
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(10)           //1번부터 10개 가져와 라는뜻
                    .getResultList();

            for (Member member: result) {
                System.out.println("member.name = " + member.getName());
            }
            */
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
