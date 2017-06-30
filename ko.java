import java.lang.Object;
/**
 * Write a description of class KO here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ko
{
    int[][] deckModel = {{2,24},{3,24},{4,24},{5,24},{6,24},{7,24},{8,24},{9,24},{10,24},{10,24},{10,24},{10,24},{11,24}};
    int[][] deck = {{2,24},{3,24},{4,24},{5,24},{6,24},{7,24},{8,24},{9,24},{10,24},{10,24},{10,24},{10,24},{11,24}};
    int[] dealer = new int[13];
    int[] player = new int[13];
    int count = 16;
    int pt = 0;
    int dt =0;
    double betBasic = 1;
    double bet = 0;
    double nextBet = 0;
    double insurance = 0;
    double bank = 10000;
    int temp = 0;
    int tempArr[] = new int[13];
    int gdgp = 0;
    int tempPt = 0; 
    int paceCounter = 0;
    int daceCounter = 0; 
    public double getBank()
    {
        for(int i =0; i< 10000; i++)
        {
            dealCards();
        }
        return bank;
    }
    public void newHand()
    {
        for(int i = 0; i < 13; i++)
        {
            dealer[i]= 0;
            player[i]= 0;
        }
        pt = 0;
        dt = 0;
        temp = 0;
        clearArr(tempArr);
        gdgp = 0;
        tempPt = 0;
        insurance = 0;
        paceCounter=0;
        daceCounter=0;
    }
    public void dealCards()
    {
        getAdjustedBet();
        player[0] = getCard();
        countCard(player[0]);
        dealer[0]= getCard();
        countCard(dealer[0]);
        player[1] = getCard();
        countCard(player[1]);
        dealer[1] = getCard();
        getPlayerHandTotal();
        getDealerHandTotal();
        if(splitCheck())
        {
            temp = player[1];
            player[1] = 0;
            player[1] = getCard();
            countCard(player[1]);//reun bet 2x chnging player and pt
            getSplitBetandDealer();
            for(int n = 0; n < player.length; n++)
            {
                tempArr[n] = player[n];
            }
            tempPt = pt;
            clearArr(player);
            player[0] = temp;
            player[1] = getCard();
            countCard(player[1]);
            getBetandDealer();
            pt = tempPt;
            for(int i=0; i< player.length; i++)
            {
                player[i] = tempArr[i];
            }
            if(gdgp > 0)
            {
                betPay();
            }
        }
        else
        {
            getBetandDealer();
        }
        countCard(dealer[1]);
        newHand();
        if(getCardsLeft() < 52)
        {
            newShoe();
        }
    }
    public int[]getplay()
    {
        return player;
    }
    public int[] getdeal()
    {
        return dealer;
    }
    public double agetBank()
    {
        return bank;
    }
    public int getCount()
    {
        return count;
    }
    public int aagetPt()
    {
           return pt;
    }
    public int getCard()
    {
        int card = (int)(Math.random()*13);
        if(deck[card][1] == 0)
        {
            getCard();
        }
        deck[card][1] = deck[card][1]-1;
        return deck[card][0];
    }
    public void newShoe()
    {
        for(int i = 0; i < 13; i++)
        {
        for(int k = 0; k < 2; k++)
        {
            deck[i][k] = deckModel[i][k];
        }
        }
        count = 16;
    }
    public void clearArr(int[] array)
    {
        for(int i = 0; i < array.length; i++)
        {
            array[i] = 0;
        }
    }
    public int getCardsLeft()
    {
        int sum = 0;
        for(int i = 0; i < 13; i++)
        {
            sum += deck[i][1];
        }
        return sum;
    }
    public void countCard(int num)
    {
        if(num >= 2 && num <= 7)
        {
            count += 1;
        }
        else if(num >= 10)
        {
            count -= 1;
        }
    }
    
    
    public void getSplitBetandDealer()
    {
        if(pt == 21 && dt != 21)
        {
            bank = bank + 1.5 * bet;
        }
        else if(pt == 21 && dt == 21)
        {
            nextBet = bet;
        }
        else if(dealer[0]== 11)
        {
            if(count >= 39)
            {
                insurance = bet * 0.5;
            }
            if(dealer[1] == 10)
            {
                bank = bank - bet + 2 * insurance;
            }
            else
            {
                bank = bank - insurance;
                getPlayer();
                gdgp++;
            }
        }
        else
        {
            if(dealer[0] == 10 && dealer[1] == 11)
            {
                bank = bank - bet;
            }
            else
            {
                getPlayer();
                gdgp++;
            }
        }
        
    }
    public void getBetandDealer()
    {
        if(pt == 21 && dt != 21)
        {
            bank = bank + 1.5 * bet;
        }
        else if(pt == 21 && dt == 21)
        {
            nextBet = bet;
        }
        else if(dealer[0]== 11)
        {
            if(count >= 39)
            {
                insurance = bet * 0.5;
            }
            if(dealer[1] == 10)
            {
                bank = bank - bet + 2 * insurance;
            }
            else
            {
                bank = bank - insurance;
                getPlayer();
                getDealer();
                betPay();
            }
        }
        else
        {
            if(dealer[0] == 10 && dealer[1] == 11)
            {
                bank = bank - bet;
            }
            else
            {
                getPlayer();
                getDealer();
                betPay();
            }
        }
        
    }
    public void getAdjustedBet()
    {
        if(count < 32)
        {
            bet = 0 ;
        }
        else if(count == 32)
        {
            bet = 2* betBasic;
        }
        else if( count == 33)
        {
            bet = 2* betBasic;
        }
        else if(count == 34)
        {
            bet = 3* betBasic;
        }
        else if(count == 35)
        {
            bet = 4* betBasic;
        }
        else if(count == 36)
        {
            bet = 5* betBasic;
        }
        else if(count == 37)
        {
            bet = 6* betBasic;
        }
        else if(count == 38)
        {
            bet = 8* betBasic;
        }
        else if(count == 39)
        {
            bet = 9* betBasic;
        }
        else if(count >= 40)
        {
            bet = 10* betBasic;
        }
        if(nextBet > bet)
        {
            bet = nextBet;
        }
        nextBet = 0;
    }
    public void getPlayer()
    {
        if(aceDoubleCheck()||doubleNoAce())
            {
                bet = bet *2;
                player[nextOpen(player)] = getCard();
                countCard(player[nextOpen(player)-1]);
                getPlayerHandTotal();
                for(int i =0;i<player.length;i++)
                {
                    if(player[i]==11)
                    {
                        paceCounter++;
                    }
                }
                while(pt > 21 && paceCounter>0)
                {
                    pt = pt-10;
                    paceCounter--;
                }
        }
        else
            {
                basicHit();
            }
        }
    
    public void betPay()
    {
        int dc = player.length;
        int pc = dealer.length;
        if(pt>21)
        {
            bank = bank - bet;
        }
        else if(pt <21 && dt>21)
        {
            bank = bank + bet;
        }
        else if(pt <= 21 && dt <= 21 && pc > 2 && dc > 2)
        {
            if(pt > dt)
            {
                bank = bank + bet;
            }
            else if(dt> pt)
            {
                bank = bank - bet;
            }
            else if (dt == pt)
            {
                nextBet = bet; // implement this
            }
        }
    }
    {}
    public boolean doubleNoAce()
    {
        if(pt == 11 && dealer[0] < 11)
        {
            bet = bet * 2;
            return true;
        }
        else if(pt == 10 && dealer[0] < 10)
        {
            bet = bet * 2;
            return true;
        }
        else{
            return false;
        }
    }
    public void getPlayerHandTotal()
    {
        pt = 0;
        for(int i = 0; i < 13; i++)
        {
            pt += player[i];
        }
    }
       public void getDealerHandTotal()
    {
        dt = 0;
        for(int i = 0; i < 13; i++)
        {
            dt += dealer[i];
        }
    }
    public void basicHit()
    {
        for(int i = 0;i< player.length; i++)
        {
            if(player[i]==11)
            {
                paceCounter++;
            }
        }   
        while((((pt >= 12 && pt <= 14 && dealer[0] >= 7) ||( pt < 12 )||( pt == 12  && dealer[0] == 2) ||( pt == 12  && dealer[0] == 3)||
        ( pt == 16 && dealer[0]>= 7 && dealer[0] != 10 &&count >= 32 && count < 40)|| pt==15 && count >= 32 && count < 40||((pt == 16 && dealer[0]>= 7 && dealer[0] != 10 && dealer[0] != 9
        || pt == 15 && dealer[0]>= 7 && dealer[0] != 10) && count >=40)||(count <32 && pt == 15 || pt ==16))&& paceCounter ==0)|| paceCounter ==0 && pt<=17)
        {
            player[nextOpen(dealer)] = getCard();
            countCard(player[nextOpen(player)-1]);
            pt+= player[nextOpen(player)-1];
            if(player[nextOpen(player)-1]==11)
            {
                paceCounter++;
            }
            while(paceCounter>0 && pt>21)
            {
             pt = pt-10;
             paceCounter--;
            }
        }
        }
    public boolean aceCheck(int[] arr)
    {
        for(int i = 0; i < arr.length; i++)
        {
            if(arr[i] == 11)
            {
                return true;
            }
        }
        return false;
    }
    public boolean splitCheck()
    {
        if(player[0]==player[1])
        {
            if(player[0] == 2 || player[0] == 3 && dealer[0] <= 7 && dealer[0] >= 4)
            {
                return true;
            }
            else if(player[0] == 6 && dealer[0] <= 6 && dealer[0] != 2)
            {
                return true;
            }
            else if(player[0] == 9 && dealer[0] <= 9 && dealer[0] != 7)
            {
                return true;
            }
            else if(player[0] == 7 && dealer[0] <= 7)
            {
                return true;
            }
            else if(player[0] == 8)
            {
                return true;
            }
            else if(player[0] == 11)
            {
                return true;
            }
            else
            {
            return false;
            }
        }
        return false;
    }
    public boolean aceDoubleCheck()
    {
        if((pt == 17|| pt == 18) && dealer[0] >=3 && dealer[0] <= 6)
        {
            return true;
        }
        else if((pt == 15|| pt == 16) && dealer[0] >=4 && dealer[0] <= 6 )
        {
            return true;
        }
        else if((pt == 13|| pt == 14) && dealer[0] >=5 && dealer[0] <= 6 )
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public int nextOpen(int[] arr)
    {
        for(int i = 0; i < arr.length; i++)
        {
            if(arr[i] == 0)
            {
                return i;
            }
        }
        return arr.length;
    }
    public void getDealer()
    {
        int daceCounter = 0;
        for(int i = 0;i< dealer.length; i++)
        {
            if(dealer[i]==11)
            {
                daceCounter++;
            }
        }
        while(dt <= 17 && daceCounter>0 || dt <=16 && daceCounter==0)
        {
            dealer[nextOpen(dealer)] = getCard();
            countCard(dealer[nextOpen(dealer)-1]);
            dt+= dealer[nextOpen(dealer)-1];
            if(dealer[nextOpen(dealer)-1]==11)
            {
                daceCounter++;
            }
            while(daceCounter>0 && dt>21)
            {
             dt = dt-10;
             daceCounter--;
            }
        }
    }
}
