import java.util.*;

class Dealer extends Partitioner
{
    public Dealer(String pName) {
        super(pName);
    }
    public boolean aiHit(ArrayList<Player> players ,Deck deck){//�I�Ƥw�j�󵥩�17��
        int countnormal=0,countGT=0;
        double proportion;
        if(this.opvalue==20)
            return false;
        for(int i=0;i<players.size();i++){//�٦b�C�����H
            if(players.get(i).state.equals("Normal"))
                countnormal++;
            for(int j=0;j<players.get(i).cards.size();j++){
                if(players.get(i).cards.get(j).ishidden==true){
                    if(players.get(i).opvalue-players.get(i).cards.get(j).value>=this.opvalue-this.cards.get(0).value)//�p����ܪ��P���j����a�P�����H��
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