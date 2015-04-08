import java.util.*;
import java.util.*;

class Game
{
	ArrayList<Player> players;
	Deck deck=new Deck(0);
	Dealer dealer;
	Scanner scanner = new Scanner(System.in);
        
	public Game(int playerCount) {
		players = new ArrayList<Player>();
		for (int i = 0; i < playerCount; i++) {
			players.add(new Player(""));
		}
		dealer = new Dealer("");
	}
        public void setPlayerName(){
            for(int i=0;i<players.size();i++)
            {
                System.out.print("輸入玩家"+(i+1)+"名稱 : ");
                players.get(i).setName(scanner.next());
            }
        }
        public void firstRound()
        {
            dealer.hit(deck.split(),true,deck);//只有電腦莊家第一張看不到
            for(int i=0;i<players.size();i++)
            {
                players.get(i).hit(deck.split(),false,deck);
            }
            dealer.hit(deck.split(),false,deck);
            for(int i=0;i<players.size();i++)
            {
                players.get(i).hit(deck.split(),false,deck);
            }
            System.out.println();
        }
        public void testshow(){//測試
            for(int i=0;i<deck.cards.size();i++)
            {
                   System.out.println(deck.cards.get(i).suit+"\t"+deck.cards.get(i).face);
            }
            System.out.println("卡片總數: "+deck.cards.size());
        }
        public void cardShow(boolean d,boolean p)//delar,players
        {
            dealer.show(d);
            for(int i=0;i<players.size();i++)
            {
                players.get(i).show(p);
            }
            System.out.println();
        }
        public void reStart(){
            dealer.init();
            for(int i=0;i<players.size();i++)
                players.get(i).init();
        }
        public void setAllChip(int chips){
            for(int i=0;i<players.size();i++)
                players.get(i).setChips(chips);
        }
        public void allWager(){
            for(int i=0;i<players.size();i++)
                players.get(i).wager();
        }
        public void accounting(){
            for(int i=0;i<players.size();i++){
                if(players.get(i).state.equals("Win"))
                    players.get(i).betWin();
                else if(players.get(i).state.equals("Lose"))
                    players.get(i).betLose();
                else if(players.get(i).state.equals("Bust"))
                    players.get(i).betLose();
                else if(players.get(i).state.equals("BlackJack"))
                    players.get(i).betWin();
            }
        }
        public void kickPlayer(){
            for(int i=0;i<players.size();i++)
                if(players.get(i).chips==0)
                    players.remove(i);
        }
}