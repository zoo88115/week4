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
    public void wager(){//下注
        Scanner op=new Scanner(System.in);
        while(true){
            try{
                System.out.print("玩家 - "+name+" :下注(可用籌碼:"+this.chips+") :");
                this.bet=op.next();
                if(this.bet.matches("[0-9]+")){
                    if(Integer.valueOf(this.bet)<=this.chips)
                        break;
                    else
                        System.out.println("超出範圍!!!");
                }
                else{
                    System.out.println("輸入錯誤!!!");
                } 
            }
            catch(Exception e){
                System.out.println("錯誤訊息:"+e);
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