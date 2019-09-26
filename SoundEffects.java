import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import javazoom.jl.player.Player;

public class SoundEffects {

	public static void newGameSound() {

		// STRING COM O CAMINHO DO ARQUIVO MP3 A SER TOCADO
		String path = "sound/new_game.mp3";

		// INSTANCIA플O DO OBJETO FILE COM O ARQUIVO MP3
		File mp3File = new File(path);

		// INSTANCIA플O DO OBJETO MP3MUSICA DA CLASS INTERNA
		MP3Musica musica = new MP3Musica();
		musica.tocar(mp3File);

		// CHAMA O METODO QUE TOCA A MUSICA
		musica.start();
		System.out.println("[LOG] Tocando som " + path);
	}
	
	public static void gameOverSound() {

		// STRING COM O CAMINHO DO ARQUIVO MP3 A SER TOCADO
		String path = "sound/ouch.mp3";

		// INSTANCIA플O DO OBJETO FILE COM O ARQUIVO MP3
		File mp3File = new File(path);

		// INSTANCIA플O DO OBJETO MP3MUSICA DA CLASS INTERNA
		MP3Musica musica = new MP3Musica();
		musica.tocar(mp3File);

		// CHAMA O METODO QUE TOCA A MUSICA
		musica.start();
		System.out.println("[LOG] Tocando som " + path);
	}
	
	public static void eatSound() {

		// STRING COM O CAMINHO DO ARQUIVO MP3 A SER TOCADO
		String path = "sound/eat.mp3";

		// INSTANCIA플O DO OBJETO FILE COM O ARQUIVO MP3
		File mp3File = new File(path);

		// INSTANCIA플O DO OBJETO MP3MUSICA DA CLASS INTERNA
		MP3Musica musica = new MP3Musica();
		musica.tocar(mp3File);

		// CHAMA O METODO QUE TOCA A MUSICA
		musica.start();
		System.out.println("[LOG] Tocando som " + path);
	}

	/**
	 * ====================================================================
	 * CLASS INTERNA MP3 MUSICA QUE EXTENDE DE THREAD PARA TRABALHAR
	 * PERFEITAMENTE NA APLICA픈O SEM TRAVAMENTO NA EXECU플O
	 * ====================================================================
	 */
	public static class MP3Musica extends Thread {

		// OBJETO PARA O ARQUIVO MP3 A SER TOCADO
		private File mp3;

		// OBJETO PLAYER DA BIBLIOTECA JLAYER QUE TOCA O ARQUIVO MP3
		private Player player;

		/**
		 * CONSTRUTOR RECEBE O OBJETO FILE REFERECIANDO O ARQUIVO MP3 A SER
		 * TOCADO E ATRIBUI AO ATRIBUTO DA CLASS
		 *
		 * @param mp3
		 */
		public void tocar(File mp3) {
			this.mp3 = mp3;
		}

		/**
		 * ===============================================================
		 * ======================================METODO RUN QUE TOCA O MP3
		 * ===============================================================
		 */
		public void run() {
			try {
				FileInputStream fis = new FileInputStream(mp3);
				BufferedInputStream bis = new BufferedInputStream(fis);

				this.player = new Player(bis);
				//System.out.println("[LOG] Tocando som " + this.path);

				this.player.play();
				//System.out.println("Terminado Musica!");

			} catch (Exception e) {
				System.out.println("Problema ao tocar Musica" + mp3);
				e.printStackTrace();
			}
		}
	}

}