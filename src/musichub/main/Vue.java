package musichub.main;

import java.io.IOException;
import java.util.Iterator;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import musichub.business.Album;
import musichub.business.AudioElement;
import musichub.business.MusicHub;
import musichub.business.NoAlbumFoundException;
import musichub.business.NoElementFoundException;
import musichub.business.NoPlayListFoundException;
import musichub.business.PlayList;
import musichub.business.Song;
import musichub.business.Log;

public class Vue {
	public Vue() {
		System.out.println("Type h for available commands");
	}

	public void printAvailableCommands() {
		System.out.println("t: display the album titles, ordered by date");
		System.out.println("g: display songs of an album, ordered by genre");
		System.out.println("d: display songs of an album");
		System.out.println("u: display audiobooks ordered by author");
		System.out.println("c: add a new song");
		System.out.println("a: add a new album");
		System.out.println("+: add a song to an album");
		System.out.println("l: add a new audiobook");
		System.out.println("p: create a new playlist from existing songs and audio books");
		System.out.println("-: delete an existing playlist");
		System.out.println("s: save elements, albums, playlists");
		System.out.println("i: listen to a song");
		System.out.println("q: quit program");
		System.out.println("z: to destroy project");
	}

	public void printSpecificCommands() {
		System.out.println("p: pause the song");
		System.out.println("r: resume the song");
		System.out.println("s: stop the music");
	}

	public void showsongtitle(AudioElement ae) {
		if (ae instanceof Song) {
			System.out.println(ae.getTitle());
		}
	}

	public void nosongfound(NoElementFoundException ex) {
		System.out.println("No element found with the requested title : " + ex.getMessage());
	}

	public void noaudiofilesupported(UnsupportedAudioFileException e) {
		System.out.println("The audio file is not supported : " + e.getMessage());
	}

	public void audiofilenoopen(IOException e) {
		System.out.println("The audio file didn't open correctly : " + e.getMessage());
	}

	public void wrongcanal(LineUnavailableException e) {
		System.out.println("The audio canal is not available : " + e.getMessage());
	}

	public void orderedbyAutor(MusicHub theHub) {
		System.out.println(theHub.getAudiobooksTitlesSortedByAuthor());
	}

	public void orderedbytitle(MusicHub theHub) {
		System.out.println(theHub.getAlbumsTitlesSortedByDate());
	}

	public void songofalbumgenre(MusicHub theHub) {
		System.out.println(
				"Songs of an album sorted by genre will be displayed; enter the album name, available albums are:");
		System.out.println(theHub.getAlbumsTitlesSortedByDate());
	}

	public void dontitrealbum(MusicHub theHub, String albumTitle, Log log) {
		try {
			System.out.println(theHub.getAlbumSongsSortedByGenre(albumTitle));
		} catch (NoAlbumFoundException ex) {
			System.out.println("No album found with the requested title " + ex.getMessage());
			log.writeError("No album found with the requested title.");
		}

	}

	public void songdisplay(MusicHub theHub) {
		System.out.println("Songs of an album will be displayed; enter the album name, available albums are:");
		System.out.println(theHub.getAlbumsTitlesSortedByDate());
	}

	public void songdisplayerror(MusicHub theHub, String albumTitle, Log log) {
		try {
			System.out.println(theHub.getAlbumSongs(albumTitle));
		} catch (NoAlbumFoundException ex) {
			System.out.println("No album found with the requested title " + ex.getMessage());
			log.writeError("No album found with the requested title.");
		}
	}

	public void gettitle() {
		System.out.println("Enter a new title: ");
	}

	public void getgenre() {
		System.out.println("Song genre (jazz, classic, hiphop, rock, pop, rap):");
	}

	public void getartistename() {
		System.out.println("artist: ");
	}

	public void getlengtsong() {
		System.out.println("length in seconds: ");
	}

	public void getsongcontend() {
		System.out.println("content: ");
	}

	public void newlist() {
		System.out.println("New element list: ");
	}

	public void createsong(Iterator<AudioElement> it) {
		System.out.println(it.next().getTitle());
		System.out.println("created!");
	}

	public void getyear() {
		System.out.println("Album date as YYYY-DD-MM: ");
	}

	public void createalbum(Iterator<Album> ita) {
		System.out.println(ita.next().getTitle());
		System.out.println("created!");
	}

	public void addsongalbum() {
		System.out.println("Add an existing song to an existing album");
		System.out.println("Type the name of the song you wish to add. Available songs: ");
	}

	public void typethename() {
		System.out.println("Type the name of the album you wish to enrich. Available albums: ");
	}

	public void getalbumname(Album al) {
		System.out.println(al.getTitle());
	}

	public void exerror(NoAlbumFoundException ex) {
		System.out.println(ex.getMessage());
	}

	public void songaddalbum() {
		System.out.println("Song added to the album!");
	}

	public void getlangage() {
		System.out.println("AudioBook language (french, english, italian, spanish, german)");
	}

	public void getcategori() {
		System.out.println("AudioBook category (youth, novel, theater, documentary, speech)");

	}

	public void getelement() {
		System.out.println("Audiobook created! New element list: ");

	}

	public void newplalist() {
		System.out.println("Add an existing song or audiobook to a new playlist\nExisting playlists:");
	}

	public void getplaylisttitle(PlayList pl) {
		System.out.println(pl.getTitle());
	}

	public void plailistcreated() {
		System.out.println("Type the name of the playlist you wish to create:");
	}

	public void availableelement() {
		System.out.println("Available elements: ");
	}

	public void audioelementadd() {
		System.out.println("Type the name of the audio element you wish to add or 'n' to exit:");
	}

	public void noplaylistfound(NoPlayListFoundException ex) {
		System.out.println(ex.getMessage());
	}

	public void continued() {
		System.out.println("Playlist created!\nType y to add a new one, n to end");
	}

	public void saved() {
		System.out.println("Elements, albums and playlists saved!");
	}

	public void deleted() {
		System.out.println("Delete an existing playlist. Available playlists:");
	}

	public void wrongentry() {
		System.out.println("wrong input");
	}

	public void destroy(int choix) {
		int i=0;
			
			if(choix== 200){
				System.out.println(i);
				i++;
				System.out.println("the app will be suppress do you want to ? (press 1 for yes)");
			}
			else if(choix==1) { 
				System.out.println(i);
				i++;
				System.out.println("are you sure ?(press 1 for yes)");
			}
			else { 
				System.out.println(i);
				i++;
				System.out.println("Noting will happen ");
			}
		}
}
