import java.lang.*;
import java.util.*;
public class Blackjack {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int playercount,chips;
        try{
            System.out.print("玩家人數 : ");
            playercount=scanner.nextInt();
            Game game = new Game(playercount);//初始化人數
            game.dealer.setName("莊家");
            game.setPlayerName();//設定玩家名稱
            System.out.print("初始籌碼設定:");
            chips=scanner.nextInt();
            game.setAllChip(chips);
            System.out.println("\n\n遊戲開始 \n\n");
            Thread.sleep(2000);
            while(true){
                game.allWager();
                System.out.println("---------------------------------------------------------------"+game.deck.countCard+"\n");
                game.firstRound();
                game.afterRound();
                game.accounting();
                    game.kickPlayer();
                    String op;
                    while(true){
                        if(game.players.size()==0){
                            System.out.println("玩家籌碼均為零，結束遊戲\n");
                            op="2";
                            break;
                        }
                        System.out.print("是否繼續 : 1)yes 2)no :");
                        op=scanner.next();
                        if(op.equals("1") || op.equals("2"))
                            break;
                        else
                            System.out.println("輸入錯誤!");
                    }
                    if(op.equals("2")){
                        System.out.println("歡迎下次再來~");
                        break;
                    }
                    else
                        game.reStart();
            }
        }
        catch(Exception e){
            System.out.println("錯誤訊息 : "+e);
        }
    }
}