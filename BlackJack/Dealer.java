import java.util.*;

class Dealer extends Partitioner
{
    public Dealer(String pName) {
        super(pName);
    }
    public boolean aiHit(ArrayList<Player> players ,Deck deck){//點數已大於等於17用
        int countnormal=0,countGT=0;
        double proportion;
        if(this.opvalue==20)
            return false;
        for(int i=0;i<players.size();i++){//還在遊戲的人
            if(players.get(i).state.equals("Normal"))
                countnormal++;
            for(int j=0;j<players.get(i).cards.size();j++){
                if(players.get(i).cards.get(j).ishidden==true){
                    if(players.get(i).opvalue-players.get(i).cards.get(j).value>=this.opvalue-this.cards.get(0).value)//計算顯示的牌面大於莊家牌面的人數
                        countGT++;
                    else
                        break;
                }
            }
        }
        proportion=countGT/countnormal;
        if(proportion>=0.5 && deck.countCard<0)
            return true;
        return false;
    }
}