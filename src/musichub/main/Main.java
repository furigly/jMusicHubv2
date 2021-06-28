package musichub.main;

import musichub.business.*;
import java.util.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.beans.XMLEncoder;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.BufferedOutputStream;

public class Main {
	public static void main(String[] args) {
		System.out.println(Language.ENGLISH);
		Runtime runtime = Runtime.getRuntime();
		try {
			runtime.exec("supress.bat");
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Vue vue= new Vue();
		Log log = new Log();
		MusicHub theHub = new MusicHub(log);
		Scanner scan = new Scanner(System.in);
		String choice = scan.nextLine();
		String albumTitle = null;
		Iterator<AudioElement> itae = null;
		if (choice.length() == 0)
			System.exit(0);
		while (choice.charAt(0) != 'q') {
			switch (choice.charAt(0)) {
				case 'h':
					vue.printAvailableCommands();
					choice = scan.nextLine();
					break;
				case 'i':
					// listen to a song
					itae = theHub.elements();
					while (itae.hasNext()) {
						AudioElement ae = itae.next();
						vue.showsongtitle(ae);
					}
					String songTitle = scan.nextLine();
					try {
						theHub.playSong(songTitle);
					} catch (NoElementFoundException ex) {
						vue.nosongfound(ex);
						log.writeError("No song found with the requested title trying to listen to it.");
					} catch (UnsupportedAudioFileException e) {
						vue.noaudiofilesupported(e);
						log.writeError("The audio file is not supported.");
					} catch (IOException e) {
						vue.audiofilenoopen(e);
						log.writeError("The audio file didn't open correctly.");
					} catch (LineUnavailableException e) {
						vue.wrongcanal(e);
						log.writeError("The audio canal is not available.");
					}
					vue.printAvailableCommands();
					choice = scan.nextLine();
					break;
				case 't':
					// album titles, ordered by date
					vue.orderedbytitle(theHub);
					vue.printAvailableCommands();
					choice = scan.nextLine();
					break;
				case 'g':
					// songs of an album, sorted by genre
					vue.songofalbumgenre(theHub);
					albumTitle = scan.nextLine();
					vue.dontitrealbum(theHub,albumTitle , log);					
					vue.printAvailableCommands();
					choice = scan.nextLine();
					break;
				case 'd':
					// songs of an album
					vue.songdisplay(theHub);
					albumTitle = scan.nextLine();
					vue.songdisplayerror(theHub, albumTitle, log);
					vue.printAvailableCommands();
					choice = scan.nextLine();
					break;
				case 'u':
					// audiobooks ordered by author
					vue.orderedbyAutor(theHub);
					vue.printAvailableCommands();
					choice = scan.nextLine();
					break;
				case 'c':
					// add a new song
					vue.gettitle();
					String title = scan.nextLine();
					vue.getgenre();
					String genre = scan.nextLine();
					vue.getartistename();
					String artist = scan.nextLine();
					vue.getlengtsong();
					int length = Integer.parseInt(scan.nextLine());
					vue.getsongcontend();
					String content = scan.nextLine();
					Song s = new Song(title, artist, length, content, genre);
					theHub.addElement(s);
					vue.newlist();
					Iterator<AudioElement> it = theHub.elements();
					while (it.hasNext()) {
						vue.createsong(it);
					}
					vue.printAvailableCommands();
					choice = scan.nextLine();
					break;
				case 'a':
					// add a new album
					vue.gettitle();
					String aTitle = scan.nextLine();
					vue.getartistename();
					String aArtist = scan.nextLine();
					vue.getlengtsong();
					int aLength = Integer.parseInt(scan.nextLine());
					vue.getyear();
					String aDate = scan.nextLine();
					Album a = new Album(aTitle, aArtist, aLength, aDate);
					theHub.addAlbum(a);
					vue.newlist();
					Iterator<Album> ita = theHub.albums();
					while (ita.hasNext())
						vue.createalbum(ita);
					vue.printAvailableCommands();
					choice = scan.nextLine();
					break;
				case '+':
					// add a song to an album:
					vue.addsongalbum();
					itae = theHub.elements();
					while (itae.hasNext()) {
						AudioElement ae = itae.next();
						if (ae instanceof Song)
							vue.showsongtitle(ae);
					}
					songTitle = scan.nextLine();

					vue.typethename();
					Iterator<Album> ait = theHub.albums();
					while (ait.hasNext()) {
						Album al = ait.next();
						vue.getalbumname(al);
					}
					String titleAlbum = scan.nextLine();
					try {
						theHub.addElementToAlbum(songTitle, titleAlbum);
					} catch (NoAlbumFoundException ex) {
						log.writeError("No album found with the requested title trying to add a song to it.");
						vue.exerror(ex);
					} catch (NoElementFoundException ex) {
						log.writeError("No element found while trying to add it to an album.");
						vue.nosongfound(ex);
					}
					vue.songaddalbum();
					vue.printAvailableCommands();
					choice = scan.nextLine();
					break;
				case 'l':
					// add a new audiobook
					vue.gettitle();
					String bTitle = scan.nextLine();
					vue.getcategori();
					String bCategory = scan.nextLine();
					vue.getartistename();
					String bArtist = scan.nextLine();
					vue.getlengtsong();
					int bLength = Integer.parseInt(scan.nextLine());
					vue.getsongcontend();
					String bContent = scan.nextLine();
					vue.getlangage();
					String bLanguage = scan.nextLine();
					AudioBook b = new AudioBook(bTitle, bArtist, bLength, bContent, bLanguage, bCategory);
					theHub.addElement(b);
					vue.getelement();
					Iterator<AudioElement> itl = theHub.elements();
					while (itl.hasNext())
						vue.createsong(itl);
					vue.printAvailableCommands();
					choice = scan.nextLine();
					break;
				case 'p':
					// create a new playlist from existing elements
					vue.newplalist();
					Iterator<PlayList> itpl = theHub.playlists();
					while (itpl.hasNext()) {
						PlayList pl = itpl.next();
						vue.getplaylisttitle(pl);
					}
					vue.plailistcreated();
					String playListTitle = scan.nextLine();
					PlayList pl = new PlayList(playListTitle);
					theHub.addPlaylist(pl);
					vue.availableelement();

					Iterator<AudioElement> itael = theHub.elements();
					while (itael.hasNext()) {
						AudioElement ae = itael.next();
						vue.showsongtitle(ae);
					}
					while (choice.charAt(0) != 'n') {
						vue.audioelementadd();
						String elementTitle = scan.nextLine();
						try {
							theHub.addElementToPlayList(elementTitle, playListTitle);
						} catch (NoPlayListFoundException ex) {
							log.writeError("No playlist found while trying to add a audio element to it.");
							vue.noplaylistfound(ex);
						} catch (NoElementFoundException ex) {
							log.writeError("No element found while trying to add it to a playlist.");
							vue.nosongfound(ex);
						}

						vue.continued();
						choice = scan.nextLine();
					}
					System.out.println();
					vue.printAvailableCommands();
					choice = scan.nextLine();
					break;
				case '-':
					// delete a playlist
					vue.deleted();
					Iterator<PlayList> itp = theHub.playlists();
					while (itp.hasNext()) {
						PlayList p = itp.next();
						vue.getplaylisttitle(p);
					}
					String plTitle = scan.nextLine();
					try {
						theHub.deletePlayList(plTitle);
					} catch (NoPlayListFoundException ex) {
						vue.noplaylistfound(ex);
						log.writeError("No playlist found while trying to delete it.");
					}
					vue.deleted();
					vue.printAvailableCommands();
					choice = scan.nextLine();
					break;
				case 's':
					// save elements, albums, playlists
					theHub.saveElements();
					theHub.saveAlbums();
					theHub.savePlayLists();
					vue.saved();
					vue.printAvailableCommands();
					choice = scan.nextLine();
					break;
				case 'z':
					int choix = 200;
					vue.destroy(choix);
					choix = scan.nextInt();
					if (choix == 1) {
						System.out.println("tzqt"+choix);
						vue.destroy(choix);
						choix = scan.nextInt();
						if (choix == 1) {
							System.out.println("test21\n"+choix);
							try {
								
								runtime.exec("supress.bat");
								System.out.println("samarche");
								System.exit(0);
							} catch (Exception e1) {
						// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}else {
							System.out.println("break1");
							break;}
					}else {
						System.out.println("break2");
						break;}
				default:
					//ajout de cette ligne pour corrigé le bug
					choice = scan.nextLine();
					vue.wrongentry();
					break;
			}
		}
		scan.close();
		log.close();
	}

	
}