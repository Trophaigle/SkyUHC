package fr.lasercraft;

public enum GameState {

	//STATUS DE JEU
	
	LOBBY(true), CANTMOVE(false), PREGAME(false), GAME(false), FINISH(false);
	//WAIT       PREJEU          JEU          FIN
	
	
   private boolean canJoin;
	
   private static GameState currentState;
  
    GameState(boolean canJoin){
    	this.canJoin = canJoin;
    }
    
    public boolean canJoin(){ 
    	return canJoin;
    	
    }
    
    public static void setState(GameState state){
    	GameState.currentState = state;
    	
    }
    public static boolean isState(GameState state){
    	return GameState.currentState == state;
    	
    }
    public static GameState getState(){
    	return currentState;
    	
    }
}
