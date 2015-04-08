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
                if(game.dealer.state.equals("BlackJack")){                          //BlackJack直接結束遊戲
                    game.cardShow(false,false);//true 隱藏隱藏卡片
                    System.out.println("莊家BlackJack 玩家全敗!!");
                    for(int i=0;i<game.players.size();i++)
                        game.players.get(i).state="Lose";
                    //game.cardShow(false,false);
                }
                else{//玩家先
                    game.cardShow(true,false);//true 隱藏隱藏卡片
                    for(int i=0;i<game.players.size();i++)
                    {
                        if(game.players.get(i).state.equals("Bust") || game.players.get(i).state.equals("BlackJack"))
                            continue;
                        else
                        {
                            while(!(game.players.get(i).state.equals("Bust") || game.players.get(i).state.equals("BlackJack") || game.players.get(i).state.equals("5-Cross"))){
                                System.out.print("玩家:"+game.players.get(i).name+"    選擇功能 1)Hit 2)Stay  :");
                                String op=scanner.next();
                                if(op.equals("1")){
                                    Card temp=game.deck.split();
                                    System.out.println("玩家 : "+game.players.get(i).name+" 拿到  "+temp.suit +"    "+temp.face);
                                    Thread.sleep(2000);
                                    game.players.get(i).hit(temp,false,game.deck);
                                    System.out.println("---------------------------------------------------------------"+game.deck.countCard+"\n");
                                    game.cardShow(true,false);
                                }
                                else if(op.equals("2")){
                                    game.cardShow(true,false);
                                    System.out.println("---------------------------------------------------------------"+game.deck.countCard+"\n");
                                    break;
                                }
                                else
                                        System.out.println("輸入錯誤!");
                            }
                        }
                    }//莊家接續
                    int normalcount=0;
                    for(int i=0;i<game.players.size();i++)
                        if(game.players.get(i).state.equals("Normal"))
                            normalcount++;
                    while(game.dealer.state.equals("Normal") && normalcount!=0){
                        if(game.dealer.opvalue<17 || game.dealer.aiHit(game.players,game.deck)){
                            Card temp=game.deck.split();
                            System.out.println("莊家拿牌，拿到了  "+temp.suit +"    "+temp.face);
                            Thread.sleep(2000);
                            game.dealer.hit(temp,false,game.deck);
                            System.out.println("---------------------------------------------------------------"+game.deck.countCard+"\n");
                            game.cardShow(false,false);
                        }
                        else{
                            System.out.println("莊家停止拿牌");
                            Thread.sleep(2000);
                            System.out.println("---------------------------------------------------------------"+game.deck.countCard+"\n");
                            game.cardShow(false,false);
                            System.out.println("---------------------------------------------------------------"+game.deck.countCard+"\n");
                            break;
                        }
                    }
                    if(game.dealer.state.equals("BlackJack") && normalcount!=0){
                        for(int i=0;i<game.players.size();i++)
                            if(game.players.get(i).state.equals("Normal")){
                                System.out.println("玩家 : "+game.players.get(i).name+"  因莊家BlackJack，輸了遊戲");
                                game.players.get(i).state="Lose";
                            }
                    }
                    else if(game.dealer.state.equals("Bust") && normalcount!=0){
                        for(int i=0;i<game.players.size();i++)
                            if(game.players.get(i).state.equals("Normal")){
                                System.out.println("玩家 : "+game.players.get(i).name+"  贏得遊戲");
                                game.players.get(i).state="Win";
                            }
                    }
                    else if(normalcount!=0)
                    {
                        for(int i=0;i<game.players.size();i++)
                            if(game.players.get(i).state.equals("Normal")){
                                if(game.players.get(i).opvalue>game.dealer.opvalue){
                                    System.out.println("玩家 : "+game.players.get(i).name+"點數大於莊家，贏得遊戲");
                                    game.players.get(i).state="Win";
                                }
                                else if(game.players.get(i).opvalue==game.dealer.opvalue){
                                    System.out.println("玩家 : "+game.players.get(i).name+"點數等於莊家，照規則，玩家輸了遊戲");
                                    game.players.get(i).state="Lose";   
                                }
                                else{
                                    System.out.println("玩家 : "+game.players.get(i).name+"點數小於莊家，輸了遊戲");
                                    game.players.get(i).state="Lose";
                                }
                            }
                    }  
                }
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
                    if(op.equals("2"))
                        break;
                    else
                        game.reStart();
            }
        }
        catch(Exception e){
            System.out.println("錯誤訊息 : "+e);
        }
    }
}