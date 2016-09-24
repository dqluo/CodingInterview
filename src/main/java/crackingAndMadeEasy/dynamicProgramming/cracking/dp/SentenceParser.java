package crackingAndMadeEasy.dynamicProgramming.cracking.dp;

import util.Trie;
import java.util.HashMap;

/**
 * User: xinyuwan, Date: 12/6/13, Time: 12:31 AM
 */
public class SentenceParser
{
    public static void main(String[] args)
    {
        String s="Iwanttoknowifyoucangivemeanoffer";
        String s2="thit";
        Trie d=new Trie();
        d.insert("want");
        d.insert("to");
        d.insert("know");
        d.insert("if");
        d.insert("you");
        d.insert("can");
        d.insert("give");
        d.insert("me");
        // d.insert("a");
        d.insert("an");
        d.insert("offer");
        d.insert("I");
        d.insert("hit");
        d.insert("it");
        d.insert("hi");
        SentenceParser sp=new SentenceParser(s, d);
        Result result=sp.parse(0, 0);
        System.out.println("Invalid char number: "+result.invalid);
        System.out.println("Parsed sentence: "+result.parsed);

        for(Integer i: sp.cacheReal.keySet())
            System.out.println(i+" "+sp.cacheReal.get(i).parsed);
    }


    public String sentence;
    public Trie dictionary;
    public HashMap<Integer , Result> cacheReal=new HashMap<Integer, Result>();
    public HashMap<Integer , Integer> cache=new HashMap<Integer, Integer>();
    public SentenceParser(String s, Trie d)
    {
        sentence=s;
        dictionary=d;
    }

    /*
     * Simplification: only returns the min number of invalid words
     * Method1: recurse through the string, at each point, the optimal parsing is
     * the better of two possible decisions:
     * 1. inserting a space after this character
     * 2. not inserting a space after this character
     *
     * Method2: two major improvements on method1:
     * 1. some recursive cases overlap, so resultSet the result using DP
     * 2. using a trie to eliminate cases where no words begins with some
     * prefix: for example: when try to parse xten, we don't parse xt+p(en)and xte+p(n)
     * since after looking up in trie, we quickly know that there're no strings begining with xt
     *
     */
    public int parseSimple(int wordStart, int wordEnd)
    {
        if(wordEnd >= sentence.length())
            return wordEnd-wordStart;
        //break current word with the rest of the sentence
        int bestExact=parseSimple(wordEnd + 1, wordEnd + 1);
        String currentWord=sentence.substring(wordStart, wordEnd + 1);
        if(!dictionary.search(currentWord))
            bestExact+=wordEnd-wordStart+1;
        //extend current word with the rest of the sentence
        int bestExtend=parseSimple(wordStart, wordEnd+1);
        //find the best
        return Math.min(bestExact, bestExtend);
    }

    public int parseOptimized(int wordStart, int wordEnd)
    {
        if(cache.containsKey(wordStart))
            return cache.get(wordStart);
        if(wordEnd>=sentence.length())
        {
            cache.put(wordStart,  wordEnd-wordStart);
            return wordEnd-wordStart;
        }
        String word=sentence.substring(wordStart, wordEnd + 1);
        //check if there's any word starting with prefix word
        boolean wordPrefixExists=dictionary.searchPrefix(word);
        int bestExact=parseOptimized(wordEnd+1, wordEnd+1);
        if(!wordPrefixExists || !dictionary.search(word))
            bestExact+=word.length();
        //extend current word with the rest of the sentence
        int bestExtend=Integer.MAX_VALUE;
        if(wordPrefixExists)
            bestExtend=parseOptimized(wordStart, wordEnd+1);
        //find the best
        int result=Math.min(bestExact, bestExtend);
        cache.put(wordStart, result);
        return result;
    }

    public Result parse(int wordStart, int wordEnd)
    {
        if(cacheReal.containsKey(wordStart))
            return cacheReal.get(wordStart).clone();
        Result res=null;
        if(wordEnd >= sentence.length())
        {
            res=new Result(wordEnd-wordStart,
                    sentence.substring(wordStart).toUpperCase());
            cacheReal.put(wordStart, res.clone());
            return res;
        }
        Result bestExact=parse(wordEnd+1, wordEnd+1);
        String currentWord=sentence.substring(wordStart, wordEnd+1);
        boolean prefixExists=dictionary.searchPrefix(currentWord);
        if(!prefixExists || !dictionary.search(currentWord))
        {
            bestExact.invalid+=currentWord.length();
            bestExact.parsed=currentWord.toUpperCase()+" "+bestExact.parsed;
        }
        else
            bestExact.parsed=currentWord+" "+bestExact.parsed;

        Result bestExtend=null;
        if(prefixExists)
            bestExtend=parse(wordStart, wordEnd+1);
        res=min(bestExact, bestExtend);
        cacheReal.put(wordStart, res.clone());
        return res;
    }
    private Result min(Result r1, Result r2)
    {
        if(r1==null)
            return r2;
        if(r2==null)
            return r1;
        return r1.invalid<=r2.invalid? r1 : r2;
    }
    //inner class Result to return both the number of invalied characters and the optimal string
    class Result
    {
        public int invalid=Integer.MAX_VALUE;
        public String parsed="";
        public Result(int inv, String p)
        {
            invalid=inv;
            parsed=p;
        }
        public Result clone()
        {
            return new Result(this.invalid, this.parsed);
        }
    }
}
