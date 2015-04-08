import java.util.*;

class Partitioner
{
        int []countCard={0,0,1,1,1,1,1,0,0,0,-1,-1};
	public int acemount,value,opvalue;
	public String name;
        String state="Normal";//Normal,Bust,BlackJack,Lose,Win,5-Cross 
	ArrayList<Card> cards=new ArrayList<Card>();
	public Partitioner(String pName) {
		this.name = pName;
	}
	public void setName(String newName) {
		this.name = newName;
	}
	public void init()
	{
            state="Normal";
            acemount=0;value=0;opvalue=0;
            while(!cards.isEmpty())
            {
                cards.remove(0);
            }
	}
	public void hit(Card hitcard,boolean v,Deck deck)
	{
            hitcard.ishidden=v;
            deck.countCard+=this.countCard[hitcard.value];
            if(hitcard.face.equals("A"))
            {
                    acemount++;
            }
            value=value+hitcard.value;
            opvaluecount();
            if(opvalue>21){
                state="Bust";
                System.out.println(name+"  Bust !!!\n");
                try{
                    Thread.sleep(2000);
                }
                catch(Exception e){
                    ;
                }
            }
            if(opvalue==21){
                state="BlackJack";
                System.out.println("恭喜"+name+"! BlackJack !!!\n");
                try{
                    Thread.sleep(2000);
                }
                catch(Exception e){
                    ;
                }
            }
            if(cards.size()+1==5 && state.equals("Normal"))//過五關
                state="5-Cross";
            cards.add(hitcard);
	}
	public void opvaluecount()
	{
		if(this.value<=21)
			this.opvalue=this.value;
		else
		{
			int i;
			for(i=1;i<=acemount;i++)
			{
				if((value-(i*10)<=21))
				{
					opvalue=(value-(i*10));
					return;
				}
			}
			opvalue=value-(acemount*10);
		}
	}
	public void show(boolean v)//未格式化好
	{
            System.out.print("玩家:"+name);
            System.out.print("    狀態:"+state);
            if(v==true){
                System.out.print("    點數總和 : 才不告訴逆~    ");
            }
            else
                System.out.print("    點數總和 : "+String.format("%02d\t",opvalue));
            for(int i=0;i<cards.size();i++)
            {  
                 if(cards.get(i).ishidden==true && v==true)
                    System.out.print(String.format("%8s  ","--------")+String.format("%2s\t","--"));
                 else
                     System.out.print(String.format("%8s  ",cards.get(i).suit)+String.format("%2s\t",cards.get(i).face));
            }
            System.out.println();
	}
}