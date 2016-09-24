package leetCode;

/**
 * User: xinyuwan, Date: 12/11/13, Time: 7:46 PM
 */
public class BestTimeToBuyStock
{
    public static void main(String[] args)
    {
        int[] p={18, 4, 2, 10, 29, 27, 22, 40, 32, 33, 30};
        Stock s=new Stock();
        Stock2 s2=new Stock2();
        Stock3 s3=new Stock3();
        System.out.println("Max profit for one transaction: "+s.maxProfit(p));
        System.out.println("Max profit for multi transactions: " + s2.maxProfit(p));
        System.out.println("Max profit for two transactions: " + s3.maxProfit(p));
    }
}

class Stock
{
    /*
     * Problem1: you have an array for which the ith element is the price
     * of a given stock on day i. If you were only permitted to complete at
     * most one transaction (ie, buy one and sell one share of the stock),
     * design an algorithm to find the maximum profit.
     */
    public int maxProfit(int[] prices)
    {
        int n=prices.length;
        if(n<=1) return 0;
        int minPrice=prices[0], maxProfit=0;
        for(int i=1; i<n; i++)
        {
            if(prices[i]<minPrice)
                minPrice=prices[i];
            int profit=prices[i]-minPrice;
            if(profit > maxProfit)
                maxProfit=profit;
        }
        return maxProfit;
    }
}

class Stock2
{
    /*
     * Problem2: Design an algorithm to find the maximum profit.
     * You may complete as many transactions as you like (ie, buy one and sell one
     * share of the stock multiple times). However, you may not engage in multiple
     * transactions at the same time (ie, you must sell the stock before you buy again).
     */
    public int maxProfit(int[] prices)
    {
        int maxProfit=0;
        for(int i=0; i<prices.length-1; i++)
        {
            int profit=prices[i+1]-prices[i];
            if(profit>0)
                maxProfit+=profit;
        }
        return maxProfit;
    }
}

class Stock3
{
    /*
     * Problem3: Design an algorithm to find the maximum profit.
     * You may complete at most two transactions. You may not engage in multiple transactions
     * at the same time (ie, you must sell the stock before you buy again).
     */
    public int maxProfit(int[] prices)
    {
        int n=prices.length;
        if(prices.length<=1) return 0;
        int[] firstTran=new int[n];
        int min=prices[0];
        for(int i=1; i<n; i++)
        {
            min=Math.min(min, prices[i]);
            firstTran[i]=Math.max(firstTran[i-1], prices[i]-min);
        }
        int[] secondTran=new int[n];
        int max=prices[n-1];
        for(int i=n-2; i>=0; i--)
        {
            max=Math.max(max, prices[i]);
            secondTran[i]=Math.max(secondTran[i+1], max-prices[i]);
        }
        int maxProfit=0;
        for(int i=0; i<n; i++)
        {
            maxProfit=Math.max(maxProfit, firstTran[i]+secondTran[i]);
        }
        return maxProfit;
    }
}