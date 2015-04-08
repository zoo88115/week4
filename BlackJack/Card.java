import java.util.*;

public class Card {
    public String suit,face;//花色 點數
    int value;
    boolean ishidden;
    public Card(String fName, String sName) {
	this.face = fName;
	this.suit = sName;
	if(face.equals("J")==true||face.equals("Q")==true||face.equals("K")==true)
            this.value=10;
	else if(face.equals("A")==true)
	{
		this.value=11;
	}
	else
	{
		value=Integer.valueOf(face);
	}
    }
}
