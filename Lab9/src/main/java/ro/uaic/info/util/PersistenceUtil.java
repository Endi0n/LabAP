/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.uaic.info.util;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author octavian
 */
public class PersistenceUtil {
    private static EntityManager entityManagerFactory;
    
    public static EntityManager getEntityManagerFactory() {
        if (entityManagerFactory == null)
            entityManagerFactory = Persistence.createEntityManagerFactory("MusicAlbumsPU")
                    .createEntityManager();
        
        return entityManagerFactory;
    }
}
