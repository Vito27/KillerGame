public class Player {
    private String name;
    private int firstScore;
    private int nowScore;
    private int life = 3;


    public Player(String name, int firstScore) {
        this.name = name;
        this.firstScore = firstScore;
    }

    public String getName() {
        return name;
    }

    public int getFirstScore() { return firstScore; }


    public int getNowScore() { return nowScore; }

    public int getLife() { return life; }

    public void setNowScore(int nowScore) { this.nowScore = nowScore; }

    public void removeOneLife(){
        if(life != 0){
            life--;
        }
    }
}
