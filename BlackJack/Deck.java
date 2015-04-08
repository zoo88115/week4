import java.util.*;

class Deck
{
        Random ran = new Random();
        public int countCard=0;
	public ArrayList<Card> cards=new ArrayList<Card>();
	String []f={"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
	String []s={"Spades","Hearts","Diamonds","Clubs"};
	public Deck(int n) {
		for(int i=0;i<=n;i++)
		{
			for(int j=0;j<52;j++)
			{
				Card temp=new Card(this.f[j%13],this.s[j%4]);
				this.cards.add(temp);
			}
		}
                shuffle();
                //Card t=cards.get(0);//5-cross test
                //cards.set(0, cards.get(5));
                //cards.set(5,t);
	}
        public void shuffle(){
            for(int i=0;i<2000;i++)
            {
                int a=ran.nextInt(cards.size());
                int b=ran.nextInt(cards.size());
                Card temp=cards.get(a);
                Card temp2=cards.get(b);
                cards.set(a,temp2);
                cards.set(b,temp);
            }
        }
        public Card split(){
            if(cards.isEmpty()){
                cards=new Deck(5).cards;
                countCard=0;
            }
            return cards.remove(0);
        }
}