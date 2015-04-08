import java.lang.*;
import java.util.*;
public class Blackjack {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int playercount,chips;
        try{
            System.out.print("���a�H�� : ");
            playercount=scanner.nextInt();
            Game game = new Game(playercount);//��l�ƤH��
            game.dealer.setName("���a");
            game.setPlayerName();//�]�w���a�W��
            System.out.print("��l�w�X�]�w:");
            chips=scanner.nextInt();
            game.setAllChip(chips);
            System.out.println("\n\n�C���}�l \n\n");
            Thread.sleep(2000);
            while(true){
                game.allWager();
                System.out.println("---------------------------------------------------------------"+game.deck.countCard+"\n");
                game.firstRound();
                if(game.dealer.state.equals("BlackJack")){                          //BlackJack���������C��
                    game.cardShow(false,false);//true �������åd��
                    System.out.println("���aBlackJack ���a����!!");
                    for(int i=0;i<game.players.size();i++)
                        game.players.get(i).state="Lose";
                    //game.cardShow(false,false);
                }
                else{//���a��
                    game.cardShow(true,false);//true �������åd��
                    for(int i=0;i<game.players.size();i++)
                    {
                        if(game.players.get(i).state.equals("Bust") || game.players.get(i).state.equals("BlackJack"))
                            continue;
                        else
                        {
                            while(!(game.players.get(i).state.equals("Bust") || game.players.get(i).state.equals("BlackJack") || game.players.get(i).state.equals("5-Cross"))){
                                System.out.print("���a:"+game.players.get(i).name+"    ��ܥ\�� 1)Hit 2)Stay  :");
                                String op=scanner.next();
                                if(op.equals("1")){
                                    Card temp=game.deck.split();
                                    System.out.println("���a : "+game.players.get(i).name+" ����  "+temp.suit +"    "+temp.face);
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
                                        System.out.println("��J���~!");
                            }
                        }
                    }//���a����
                    int normalcount=0;
                    for(int i=0;i<game.players.size();i++)
                        if(game.players.get(i).state.equals("Normal"))
                            normalcount++;
                    while(game.dealer.state.equals("Normal") && normalcount!=0){
                        if(game.dealer.opvalue<17 || game.dealer.aiHit(game.players,game.deck)){
                            Card temp=game.deck.split();
                            System.out.println("���a���P�A����F  "+temp.suit +"    "+temp.face);
                            Thread.sleep(2000);
                            game.dealer.hit(temp,false,game.deck);
                            System.out.println("---------------------------------------------------------------"+game.deck.countCard+"\n");
                            game.cardShow(false,false);
                        }
                        else{
                            System.out.println("���a����P");
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
                                System.out.println("���a : "+game.players.get(i).name+"  �]���aBlackJack�A��F�C��");
                                game.players.get(i).state="Lose";
                            }
                    }
                    else if(game.dealer.state.equals("Bust") && normalcount!=0){
                        for(int i=0;i<game.players.size();i++)
                            if(game.players.get(i).state.equals("Normal")){
                                System.out.println("���a : "+game.players.get(i).name+"  Ĺ�o�C��");
                                game.players.get(i).state="Win";
                            }
                    }
                    else if(normalcount!=0)
                    {
                        for(int i=0;i<game.players.size();i++)
                            if(game.players.get(i).state.equals("Normal")){
                                if(game.players.get(i).opvalue>game.dealer.opvalue){
                                    System.out.println("���a : "+game.players.get(i).name+"�I�Ƥj����a�AĹ�o�C��");
                                    game.players.get(i).state="Win";
                                }
                                else if(game.players.get(i).opvalue==game.dealer.opvalue){
                                    System.out.println("���a : "+game.players.get(i).name+"�I�Ƶ�����a�A�ӳW�h�A���a��F�C��");
                                    game.players.get(i).state="Lose";   
                                }
                                else{
                                    System.out.println("���a : "+game.players.get(i).name+"�I�Ƥp����a�A��F�C��");
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
                            System.out.println("���a�w�X�����s�A�����C��\n");
                            op="2";
                            break;
                        }
                        System.out.print("�O�_�~�� : 1)yes 2)no :");
                        op=scanner.next();
                        if(op.equals("1") || op.equals("2"))
                            break;
                        else
                            System.out.println("��J���~!");
                    }
                    if(op.equals("2"))
                        break;
                    else
                        game.reStart();
            }
        }
        catch(Exception e){
            System.out.println("���~�T�� : "+e);
        }
    }
}