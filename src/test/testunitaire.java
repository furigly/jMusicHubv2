package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

import musichub.business.Album;
import musichub.business.Language;
import musichub.business.PlayList;
import musichub.business.Song;

public class testunitaire {
	@Test
	public void testalbum() throws Exception {
		Album album = new Album("test", "test2", 0, "1998-11-08");
		String atesté = album.getTitle();
		assertEquals("test",atesté);		
	  }
	public void testplaylist() throws Exception{
		PlayList playlist = new PlayList("test");
		String title = playlist.getTitle(); 
		assertEquals("test",title);
	}
	public void testsong() throws Exception{
		Song song = new Song("test", "test2", 0, "une grande chanson", "HIPHOP");
		String title = song.getTitle(); 
		assertEquals("test",title);
		String artiste = song.getArtist();
		assertEquals("test2",artiste);
		String genre = song.getGenre();
		assertEquals("HIPHOP",genre);
	}

}
