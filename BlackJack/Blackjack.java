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
                game.afterRound();
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
                    if(op.equals("2")){
                        System.out.println("�w��U���A��~");
                        break;
                    }
                    else
                        game.reStart();
            }
        }
        catch(Exception e){
            System.out.println("���~�T�� : "+e);
        }
    }
}