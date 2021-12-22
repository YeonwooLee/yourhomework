import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Scanner;


public class PlayMP3 implements Runnable{
    public void play() {

        String filename = "jgbell.mp3";
        MP3Player mp3Player = new MP3Player(filename);
        mp3Player.play();

        Scanner sc = new Scanner(System.in);

        System.out.println("Write stop to stop the music: ");

        if (sc.nextLine().equalsIgnoreCase("stop")) {
            mp3Player.close();
        }

    }

    @Override
    public void run() {
        String filename = "jgbell.mp3";
        MP3Player mp3Player = new MP3Player(filename);
        mp3Player.play();

        Scanner sc = new Scanner(System.in);

        System.out.println("Write stop to stop the music: ");

        if (sc.nextLine().equalsIgnoreCase("stop")) {
            mp3Player.close();
        }

    }
}
