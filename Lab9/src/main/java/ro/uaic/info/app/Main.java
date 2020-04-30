/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.uaic.info.app;

import ro.uaic.info.entity.Album;
import ro.uaic.info.entity.Artist;
import ro.uaic.info.repo.AlbumRepository;
import ro.uaic.info.repo.ArtistRepository;

/**
 *
 * @author octavian
 */
public class Main {
    public static void main(String[] args) {
        ArtistRepository.create(new Artist("O-Zone", "Romania"));
        
        var artist = ArtistRepository.findByName("O-Zone").get(0);
        
        AlbumRepository.create(new Album("DiscO-Zone", artist));
        
        var album = AlbumRepository.findByArtist(artist).get(0);
        
        System.out.printf("Album: %s\n", album.getName());
    }
}
