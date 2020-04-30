/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.uaic.info.repo;

import java.util.List;
import ro.uaic.info.entity.Artist;
import ro.uaic.info.util.PersistenceUtil;

/**
 *
 * @author octavian
 */
public class ArtistRepository {
    public static void create(Artist obj) {
        var emf = PersistenceUtil.getEntityManagerFactory();
        
        emf.getTransaction().begin();
        emf.persist(obj);
        emf.getTransaction().commit();
    }
    
    public static Artist findById(int id) {
        return PersistenceUtil.getEntityManagerFactory()
            .createNamedQuery("Artist.findById", Artist.class)
            .setParameter("id", id)
            .getSingleResult();
    }
    
    public static List<Artist> findByName(String name) {
        return PersistenceUtil.getEntityManagerFactory()
            .createNamedQuery("Artist.findByName", Artist.class)
            .setParameter("name", name)
            .getResultList();
    }
}
