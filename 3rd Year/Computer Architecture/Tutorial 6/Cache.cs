using System;
using System.Collections.Generic;
using System.Linq;

namespace Cache
{
    class Cache
    {
        static void Main(string[] args)
        {
            //string input = Console.ReadLine();
            string input = "0000 => 0004 => 000c => 2200 => 00d0 => 00e0 => 1130 => 0028 => 113c = > 2204 = > 0010 = > 0020 = > 0004 = > 0040 = > 2208 = > 0008 = > 00a0 = > 0004 = > 1104 = > 0028 = > 000c = > 0084 = > 000c = > 3390 = > 00b0 = > 1100 = > 0028 = > 0064 = > 0070 = > 00d0 = > 0008 = > 3394";
            List<string> data = input.Replace(" ", "").Split("=>").ToList();
            List<string> cleaned = new List<string>();

            //Process data
            data.ForEach(item =>
            {
                int intermediary = Convert.ToInt32(item, 16);
                string binaryString = Convert.ToString(intermediary, 2).PadLeft(16, '0');
                cleaned.Add(binaryString);
            });
            //End processing

            int[] num_ways = { 1, 2, 4, 8 };
            int[] bytes_cache = { 128, 128, 128, 128 };
            int[] bytes_line = { 16, 16, 16, 16 };

            for(int i=0; i < 4; i++)
            {
                int L = bytes_line[i];
                int N = bytes_cache[i] / (bytes_line[i]*num_ways[i]);
                int K = num_ways[i];
 
                int offsetBits = (int)Math.Log(L,2);
                int setBits = (int)(Math.Log(N, 2));
                int tagBits = L-offsetBits-setBits;

                List<Dictionary<int, long>> cache = new List<Dictionary<int, long>>(new Dictionary<int, long>[N]);
                for(int s = 0; s < N; s++)
                {
                    cache[s] = new Dictionary<int, long>();
                }

                int hits = 0, misses = 0;

                foreach (string item in cleaned)
                {
                    int tagVal = Convert.ToInt32(item.Substring(0, tagBits), 2);
                    int setVal = (setBits == 0) ? 0 : Convert.ToInt32(item.Substring(tagBits, setBits), 2);

                    if (evaluateIfHit(tagVal,setVal, K, cache))
                    {
                        hits++;
                        Console.WriteLine("Item: " + item + ", HIT");
                    }
                    else
                    {
                        misses++;
                        Console.WriteLine("Item: " + item + ", MISS");
                    }
                }

                Console.Write("L:" + L + ", N:" + N + ", K:" + K + ". ");
                Console.WriteLine("hits: " + hits + ", misses: " + misses + "\n");
            }
        }

        private static bool evaluateIfHit(int tagVal, int setVal, int numTags, List<Dictionary<int,long>> cache)
        {
            long currentTime = DateTime.Now.Ticks;

            if(cache[setVal].ContainsKey(tagVal))
            {
                //Update time if present
                cache[setVal][tagVal] = currentTime;
                return true;
            }
            else
            {
                //Replacement policy if set is full
                if (cache[setVal].Count < numTags)
                {
                    cache[setVal][tagVal] = currentTime;
                }
                else
                {
                    var key = cache[setVal].Aggregate((l, r) => l.Value < r.Value ? l : r).Key;
                    cache[setVal].Remove(key);
                    cache[setVal][tagVal] = currentTime;
                }
                
                return false;
            }
        }
    }
}
