import java.util.*;
class Player extends Partitioner
{
    public int chips;
    public String bet; 
    public Player(String pName) {
            super(pName);
    }
    public void setChips(int chips){
        this.chips=chips;
    }
    public void wager(){//�U�`
        Scanner op=new Scanner(System.in);
        while(true){
            try{
                System.out.print("���a - "+name+" :�U�`(�i���w�X:"+this.chips+") :");
                this.bet=op.next();
                if(this.bet.matches("[0-9]+")){
                    if(Integer.valueOf(this.bet)<=this.chips)
                        break;
                    else
                        System.out.println("�W�X�d��!!!");
                }
                else{
                    System.out.println("��J���~!!!");
                } 
            }
            catch(Exception e){
                System.out.println("���~�T��:"+e);
            }
        }
    }
    public void betWin(){
        this.chips+=Integer.valueOf(this.bet);
    }
    public void betLose(){
        this.chips-=Integer.valueOf(this.bet);
    }
}