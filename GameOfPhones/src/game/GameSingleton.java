package game;

public class GameSingleton {
	static GameData game;
	static Player player;
	static GameSingleton gameSingle;

	private GameSingleton() {

	}
	public static void setPlayer(Player player){
		GameSingleton.player = player;
	}
	public static Player getPlayer(){
		return GameSingleton.player;
	}
	public static void setGame(GameData game){
		GameSingleton.game = game;
	}
	public static GameData getGame(){
		return game;
	}
	public static GameSingleton getGameSingle() {
		if (gameSingle == null) {
			gameSingle = new GameSingleton();
		}
		return gameSingle;

	}
}
