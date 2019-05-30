import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class KillerGame {
    private static boolean endOfGame = false;
    private int playersAmount;
    private List<Player> playersList = new ArrayList<>();
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void main (String[] args) throws Exception {
        KillerGame killerGame = new KillerGame();
        killerGame.howManyPlayer();
        killerGame.playerInitialization();
        while (!endOfGame){
            killerGame.playersStrikes();
            killerGame.checkStrikeGoal();
            killerGame.removeLosers();
            killerGame.winner();
            killerGame.playersAfterRound();
        }
    }

    private void howManyPlayer() throws Exception {
        do {
            System.out.print("Введите количество игроков (от 2 до 10): ");
            playersAmount = Integer.parseInt(reader.readLine());
            if (playersAmount <= 1 || playersAmount > 10) {
                System.out.println("Вы ввели неправильное количество, повторите попытку!");
            }
        }
        while (!(playersAmount > 1 && playersAmount <= 10));
    }
    private boolean checkName (String name){
        if(playersList.size() != 0) {
            for (int i = 0; i < playersList.size(); i++) {
                String playerName = playersList.get(i).getName();
                if (name.equals(playerName)) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean checkFirstScore (int score){
        if(playersList.size() != 0) {
            for (int i = 0; i < playersList.size(); i++) {
                int playerFirstScore = playersList.get(i).getFirstScore();
                if (score == playerFirstScore) {
                    return true;
                }
            }
        }
        return false;
    }
    private void playerInitialization() throws Exception {
        for (int i = 0; i < playersAmount; i++) {
            String playerName;
            int firstScore;
            System.out.print("Игрок " + (1+i) + " введите имя: ");
            playerName = reader.readLine();
            while(checkName(playerName)) {
                System.out.println("К сожелению такое имя уже существует, придумайте новое.");
                System.out.print("Введите имя снова: ");
                playerName = reader.readLine();
            }
            System.out.print("Введите ваше первое попадание: ");
            firstScore = Integer.parseInt(reader.readLine());
            while (checkFirstScore(firstScore))
            {
                System.out.println("К сожелению такоей результат уже существует, бросьте еще раз.");
                System.out.print("Введите результат снова: ");
                firstScore = Integer.parseInt(reader.readLine());
            }
            playersList.add(new Player(playerName,firstScore));
        }
    }
    private void playersStrikes() throws Exception{
        for(int i = 0; i < playersList.size(); i++) {
            Player player = playersList.get(i);
            int nowScore;
            String isCorrect;
            do{
                System.out.print(player.getName()+ " введите ваше попадание: ");
                nowScore = Integer.parseInt(reader.readLine());
                System.out.print("Нажмите на \"Y\", чтобы подтвердить ввод или \"N\", чтобы переписать: ");
                isCorrect = reader.readLine();
                //english and russian "Y"
                if(isCorrect.equals("y") || isCorrect.equals("Y") || isCorrect.equals("н") || isCorrect.equals("Н")){
                    player.setNowScore(nowScore);
                }
            }
            while (!(isCorrect.equals("y") || isCorrect.equals("Y") || isCorrect.equals("н") || isCorrect.equals("Н")));
        }
    }
    private void checkStrikeGoal(){
        for (int i = 0; i < playersList.size(); i++){
            Player player = playersList.get(i);
            for(int j = 0; j < playersList.size(); j++) {
                Player playerInList = playersList.get(j);
                if (player.getNowScore() == playerInList.getFirstScore() && !player.getName().equals(playerInList.getName())){
                    playerInList.removeOneLife();
                }
            }
        }
    }
    public void removeLosers(){
        for (int i = 0; i < playersList.size(); i++){
            Player player = playersList.get(i);
            if(player.getLife() == 0)
            {
                playersList.remove(i);
                i--;
            }
        }
    }
    private void winner(){
        if(playersList.size() == 1){
            System.out.println("Поздравляем вас " + playersList.get(0).getName() + " вы победили!");
            endOfGame = true;
        }
        else
        if(playersList.size() == 0){
            System.out.println("draw");
            endOfGame = true;
        }
    }
    private void playersAfterRound(){
        if(playersList.size() > 1){
            for (Player player: playersList
                 ) {
                System.out.println("Игрок " + player.getName() + ", оставшиеся жизни: " + player.getLife());
            }
        }
    }
}
