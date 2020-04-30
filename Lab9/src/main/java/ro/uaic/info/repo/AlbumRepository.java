/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.uaic.info.repo;

import java.util.List;
import ro.uaic.info.entity.Album;
import ro.uaic.info.entity.Artist;
import ro.uaic.info.util.PersistenceUtil;

/**
 *
 * @author octavian
 */
public class AlbumRepository {
    public static void create(Album obj) {
        var emf = PersistenceUtil.getEntityManagerFactory();
        
        emf.getTransaction().begin();
        emf.persist(obj);
        emf.getTransaction().commit();
    }
    
    public static Album findById(int id) {
        return PersistenceUtil.getEntityManagerFactory()
                .createNamedQuery("Album.findById", Album.class)
                .setParameter("id", id)
                .getSingleResult();
    }
    
    public static List<Album> findByName(String name) {
        return PersistenceUtil.getEntityManagerFactory()
                .createNamedQuery("Album.findByName", Album.class)
                .setParameter("name", name)
                .getResultList();
    }
    
    public static List<Album> findByArtist(Artist artist) {
                return PersistenceUtil.getEntityManagerFactory()
                .createNamedQuery("Album.findByArtistId", Album.class)
                .setParameter("artistId", artist.getId())
                .getResultList();
    }
}
