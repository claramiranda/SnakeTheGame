import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import javazoom.jl.player.Player;

/**
 * Classe para manipulação de audio e efeitos sonoros
 * 
 * @author Clara Anna
 *
 */
public class SoundEffects {

	/**
	 * Executa áudio ao iniciar uma partida.
	 */
	public void newGameSound() {

		// STRING COM O CAMINHO DO ARQUIVO MP3 A SER TOCADO
		String path = "sound/new_game.mp3";
		File mp3File = new File(path);

		// INSTANCIAÇÃO DO OBJETO MP3MUSICA DA CLASS INTERNA
		MP3audio musica = new MP3audio();
		musica.tocar(mp3File);

		// CHAMA O METODO QUE TOCA A MUSICA
		musica.start();
		System.out.println("[LOG] Tocando som " + path);
	}
	
	/**
	 * Executa áudio ao finalizar uma partida.
	 */
	public void gameOverSound() {

		String path = "sound/ouch.mp3";

		File mp3File = new File(path);

		MP3audio musica = new MP3audio();
		musica.tocar(mp3File);

		musica.start();
		System.out.println("[LOG] Tocando som " + path);
	}
	
	/**
	 * Executa áudio ao comer uma frutinha
	 */
	public void eatSound() {
		String path = "sound/eat.mp3";
		
		File mp3File = new File(path);
		
		MP3audio musica = new MP3audio();
		
		musica.tocar(mp3File);
		musica.start();
		
		System.out.println("[LOG] Tocando som " + path);
	}

	/**
	 * ====================================================================
	 * Classe interna que extende Thead para conseguir executar os sons
	 * sem travar o jogo
	 * ====================================================================
	 */
	public static class MP3audio extends Thread {

		// Objeto pra tocar o mp3
		private File mp3;

		// Objeto Player importado da biblioteca JPlayer
		private Player player;
		public void tocar(File mp3) {
			this.mp3 = mp3;
		}

		/**
		 * Método responsável pela execução do .mp3
		 * @throws MP3 Exceptions
		 */
		public void run() {
			try {
				FileInputStream fis = new FileInputStream(mp3);
				BufferedInputStream bis = new BufferedInputStream(fis);
				this.player = new Player(bis);
				this.player.play();

			} catch (Exception e) {
				System.out.println("[LOG] Problema ao tocar Musica" + mp3);
				e.printStackTrace();
			}
		}
	}

}