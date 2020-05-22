package battleship;

public class MessageRelay {
    private volatile static String name;
    private volatile static int currentTurn;
    private volatile static int humanPlayerNum;
    private volatile static int aiNum;
    private volatile static boolean player1Editable = true;
    private volatile static boolean player2Editable = false;
    private volatile static boolean p1TurnMade;
    private volatile static boolean p2TurnMade;
    private volatile static int oppXCoordAttack = -1;
    private volatile static int oppYCoordAttack = -1;
    private volatile static boolean coordSet = false;
    private volatile static boolean aiShotMade = false;
    private volatile static boolean gameStarted = false;
    private volatile static String attackCoord = "--";
    private volatile static String hitShipName = "nothing";
    
    public MessageRelay() {        
    }
    
    public MessageRelay(String name, int turn) {
        this.name = name;
        currentTurn = turn;
    }
    
    public String getTileName() {
        return name;
    }
    
    public void setHumanPlayerNum(int num) {
        humanPlayerNum = num;
    }
    
    public int getHumanPlayerNum() {
        return humanPlayerNum;
    }
    
    public void setAINum(int num) {
        aiNum = num;
    }
    
    public int getAINum() {
        return aiNum;
    }
    
    public void setPlayerEditable(boolean player1, boolean player2) {
        player1Editable = player1;
        player2Editable = player2;
    }
    
    public boolean getPlayer1Editable() {
        return player1Editable;
    }
    
    public boolean getPlayer2Editable() {
        return player2Editable;
    }
    
    public void setP1TurnMade(boolean turnMade) {
        this.p1TurnMade = turnMade;
    }
    
    public boolean getP1TurnMade() {
        return p1TurnMade;
    }
    
    public void setP2TurnMade(boolean turnMade) {
        this.p2TurnMade = turnMade;
    }
    
    public boolean getP2TurnMade() {
        return p2TurnMade;
    }
    
    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }
    
    public boolean getGameStarted() {
        return gameStarted;
    }
    
    // below are methods related to the AI
    public void setXYAttack(int x, int y) {
        oppXCoordAttack = x;
        oppYCoordAttack = y;
    }
    
    public int getXAttack() {
        return oppXCoordAttack;
    }
    
    public int getYAttack() {
        return oppYCoordAttack;
    }
    
    public boolean getCoordSet() {
        return coordSet;
    }
    
    public void setCoordSet(boolean set) {
        coordSet = set;
    }
    
    public void setAIShotMade(boolean asm) {
        aiShotMade = asm;
    }
    
    public boolean getAIShotMade() {
        return aiShotMade;
    }    
    
    // below are player related methods
    
    public void setCoordAttack(String AC) {
        attackCoord = AC;
    }
    
    public String getCoordAttack() {
        return attackCoord;
    }
    
    public void setHitShipName(String hsn) {
        hitShipName = hsn;
    }
    
    public String getHitShipName() {
        return hitShipName;
    }
}
