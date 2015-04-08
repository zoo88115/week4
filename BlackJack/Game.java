import java.util.*;
import java.util.*;

class Game {

    ArrayList<Player> players;
    Deck deck = new Deck(0);
    Dealer dealer;
    Scanner scanner = new Scanner(System.in);

    public Game(int playerCount) {
        players = new ArrayList<Player>();
        for (int i = 0; i < playerCount; i++) {
            players.add(new Player(""));
        }
        dealer = new Dealer("");
    }

    public void setPlayerName() {
        for (int i = 0; i < players.size(); i++) {
            System.out.print("��J���a" + (i + 1) + "�W�� : ");
            players.get(i).setName(scanner.next());
        }
    }

    public void firstRound() {
        dealer.hit(deck.split(), true, deck);//�u���q�����a�Ĥ@�i�ݤ���
        for (int i = 0; i < players.size(); i++) {
            players.get(i).hit(deck.split(), false, deck);
        }
        dealer.hit(deck.split(), false, deck);
        for (int i = 0; i < players.size(); i++) {
            players.get(i).hit(deck.split(), false, deck);
        }
        System.out.println();
    }

    public void testshow() {//����
        for (int i = 0; i < deck.cards.size(); i++) {
            System.out.println(deck.cards.get(i).suit + "\t" + deck.cards.get(i).face);
        }
        System.out.println("�d���`��: " + deck.cards.size());
    }

    public void cardShow(boolean d, boolean p)//delar,players
    {
        dealer.show(d);
        for (int i = 0; i < players.size(); i++) {
            players.get(i).show(p);
        }
        System.out.println();
    }

    public void reStart() {
        dealer.init();
        for (int i = 0; i < players.size(); i++) {
            players.get(i).init();
        }
    }

    public void setAllChip(int chips) {
        for (int i = 0; i < players.size(); i++) {
            players.get(i).setChips(chips);
        }
    }

    public void allWager() {
        for (int i = 0; i < players.size(); i++) {
            players.get(i).wager();
        }
    }

    public void accounting() {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).state.equals("Win")) {
                players.get(i).betWin();
            } else if (players.get(i).state.equals("Lose")) {
                players.get(i).betLose();
            } else if (players.get(i).state.equals("Bust")) {
                players.get(i).betLose();
            } else if (players.get(i).state.equals("BlackJack")) {
                players.get(i).betWin();
            }
        }
    }

    public void kickPlayer() {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).chips == 0) {
                players.remove(i);
            }
        }
    }

    public void afterRound() {
        try {
            if (dealer.state.equals("BlackJack")) {                          //BlackJack���������C��
                cardShow(false, false);//true �������åd��
                System.out.println("���aBlackJack ���a����!!");
                for (int i = 0; i < players.size(); i++) {
                    players.get(i).state = "Lose";
                }
                //game.cardShow(false,false);
            } else {//���a��
                cardShow(true, false);//true �������åd��
                for (int i = 0; i < players.size(); i++) {
                    if (players.get(i).state.equals("Bust") || players.get(i).state.equals("BlackJack")) {
                        continue;
                    } else {
                        while (!(players.get(i).state.equals("Bust") || players.get(i).state.equals("BlackJack") || players.get(i).state.equals("5-Cross"))) {
                            System.out.print("���a:" + players.get(i).name + "    ��ܥ\�� 1)Hit 2)Stay  :");
                            String op = scanner.next();
                            if (op.equals("1")) {
                                Card temp = deck.split();
                                System.out.println("���a : " + players.get(i).name + " ����  " + temp.suit + "    " + temp.face);
                                Thread.sleep(2000);
                                players.get(i).hit(temp, false, deck);
                                System.out.println("---------------------------------------------------------------" + deck.countCard + "\n");
                                cardShow(true, false);
                            } else if (op.equals("2")) {
                                cardShow(true, false);
                                System.out.println("---------------------------------------------------------------" + deck.countCard + "\n");
                                break;
                            } else {
                                System.out.println("��J���~!");
                            }
                        }
                    }
                }//���a����
                this.dealerhit();
            }
        } catch (Exception e) {
        }
    }

    public void dealerhit() {
        try {
            int normalcount = 0;
            for (int i = 0; i < players.size(); i++) {
                if (players.get(i).state.equals("Normal")) {
                    normalcount++;
                }
            }
            while (dealer.state.equals("Normal") && normalcount != 0) {
                if (dealer.opvalue < 17 || dealer.aiHit(players, deck)) {
                    Card temp = deck.split();
                    System.out.println("���a���P�A����F  " + temp.suit + "    " + temp.face);
                    Thread.sleep(2000);
                    dealer.hit(temp, false, deck);
                    System.out.println("---------------------------------------------------------------" + deck.countCard + "\n");
                    cardShow(false, false);
                } else {
                    System.out.println("���a����P");
                    Thread.sleep(2000);
                    System.out.println("---------------------------------------------------------------" + deck.countCard + "\n");
                    cardShow(false, false);
                    System.out.println("---------------------------------------------------------------" + deck.countCard + "\n");
                    break;
                }
            }
            if (dealer.state.equals("BlackJack") && normalcount != 0) {
                for (int i = 0; i < players.size(); i++) {
                    if (players.get(i).state.equals("Normal")) {
                        System.out.println("���a : " + players.get(i).name + "  �]���aBlackJack�A��F�C��");
                        players.get(i).state = "Lose";
                    }
                }
            } else if (dealer.state.equals("Bust") && normalcount != 0) {
                for (int i = 0; i < players.size(); i++) {
                    if (players.get(i).state.equals("Normal")) {
                        System.out.println("���a : " + players.get(i).name + "  Ĺ�o�C��");
                        players.get(i).state = "Win";
                    }
                }
            } else if (normalcount != 0) {
                for (int i = 0; i < players.size(); i++) {
                    if (players.get(i).state.equals("Normal")) {
                        if (players.get(i).opvalue > dealer.opvalue) {
                            System.out.println("���a : " + players.get(i).name + "�I�Ƥj����a�AĹ�o�C��");
                            players.get(i).state = "Win";
                        } else if (players.get(i).opvalue == dealer.opvalue) {
                            System.out.println("���a : " + players.get(i).name + "�I�Ƶ�����a�A�ӳW�h�A���a��F�C��");
                            players.get(i).state = "Lose";
                        } else {
                            System.out.println("���a : " + players.get(i).name + "�I�Ƥp����a�A��F�C��");
                            players.get(i).state = "Lose";
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
    }
}