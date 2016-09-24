package leetCode.gimmick;

/**
 * User: xinyuwan, Date: 1/3/14, Time: 10:40 PM
 */
public class GasStation
{
    public static void  main(String[] args)
    {
        GasStation gs=new GasStation();
        int[] gas={1, 2};
        int[] cost={2, 1};
        System.out.println(gs.canCompleteCircuit(gas, cost));
        System.out.println(gs.canCompleteCircuit2(gas, cost));
    }

    /*
     * Problem: There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
     * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.
     * Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
     * Note: The solution is guaranteed to be unique.
     */

   /*
    * Analysis: consider the case that, if started at station i, and when goes to the station j,
    * there is not enough gas to go the j+1 station. What happened now? For the brutal force method,
    * we go back to the station i+1 and do the same thing. But, actually, if the accumulative gas
    * cannot make it from j to j+1, then the stations from i to j are all not the start station.
    * That is because, (1)the tank is unlimited, every time arrive to the station, the tank will
    * fuel the max gas here, and consume the cost to go to the next. (2)There can not be negative
    * tank when arriving a station, at least the tank is empty. So, if i to j cannot go to j+1, then
    * i+1 to j still cannot go to j+1... In this way, the next starting station we will try is not
    * i+1, but the j+1. And after a single loop from i to j, we can find the result!
    */
    public int canCompleteCircuit2(int[] gas, int[] cost)
    {
         int i=0,j=0;
         int netGas=0;
         int tank=0;
         int n=gas.length;
         while(j<=n)
         {
             if(i+j<n)
             {
                 netGas=gas[i+j]-cost[i+j];
             }
             if(i+j>=n)
             {
                 netGas=gas[i+j-n]-cost[i+j-n];
             }
             tank=tank+netGas;
             if(tank<0)
             {
                 i=i+j+1;
                 //failure
                 if(i>=n)
                     return -1;
                 tank=0;
                 j=0;
             }
             else{
                 j++;
             }
         }
        //success
         return i;
    }

   /*
    * AnalysisHere we maintain two variable sum, total.
    * 1.total is used to check whether the car can travel around the circuit. It is an accumulative value with is
    * the diff between Sum(gas[i]) and Sum(cost[i]). No matter which index we start from, total will work.
    * 2.sum is used to find the start index, when we find the sum is less than 0, it means that the
    * index from startIndex to i can not travel around the circuit, and startIndex should be set to i+1 for further
    * check. This is because that if we cannot go from startIndex to i, we cannot go from any index between startIndex
    * to i. Because we can go from startIndex to startIndex+1, it means gas[startIndex]-cost[startIndex]>=0,
    * then if we cannot go from startIndex to i, it means we definitely cannot go from startIndex+1 to i.
    *
    */
    public int canCompleteCircuit(int[] gas, int[] cost)
    {
        int sum=0,total=0,startIndex=0;
        for(int i=0;i<gas.length;i++)
        {
            int diff=gas[i]-cost[i];
            sum+=diff;
            total+=diff;
            if(sum<0)
            {
                sum=0;
                startIndex=i+1;
            }
        }
        return total>=0? startIndex : -1;
    }
}

