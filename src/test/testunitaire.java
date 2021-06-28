package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import musichub.business.Language;
import musichub.business.Song;

public class testunitaire {
	@Test
	public void test() throws Exception {
		Song song = new Song("test", "test2", 200, "0", "lon,g","JAZZ");
	    assertEquals("JAZZ",song.setGenre("JAZZ"));
	  }

}
