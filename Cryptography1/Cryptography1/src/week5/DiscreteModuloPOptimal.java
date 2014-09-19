package week5;
 import java.math.BigInteger;  
 import java.util.HashMap;  
 import java.util.Map;  
   
   
 public class DiscreteModuloPOptimal {  
        
      //sample numbers. Note we MUST use BigIntegers  
      static BigInteger h = new BigInteger("3239475104050450443565264378728065788649097520952449527834792452971981976143292558073856937958553180532878928001494706097394108577585732452307673444020333");  
      static BigInteger g = new BigInteger("11717829880366207009516117596335367088558084999998952205599979459063929499736583746670572176471460312928594829675428279466566527115212748467589894601965568");  
      static BigInteger p = new BigInteger("13407807929942597099574024998205846127479365820592393377723561443721764030073546976801874298166903427690031858186486050853753882811946569946433649006084171");  
      static long B = 1048576;//2^20  
        
      //build hashtable of all possible h/(g^x1) for x1 in 0..B  
      private static Map<BigInteger, Long> leftHash(){  
           Map<BigInteger, Long> m = new HashMap<BigInteger, Long>();    
           BigInteger ginv = g.modInverse(p);
           BigInteger current = h.multiply(ginv).mod(p);
           for(long i=1; i<B; i++) {   
                m.put(current, i);
                current = current.multiply(ginv).mod(p);
           }  
           System.out.println("Hashtable done");  
           return m;  
      }  
        
      //compute n = g^B^x0 for x0 in 0..B, then check if n is in hashtable. If it is, we found (x0, x1) and can compute x as x0*B+x1  
      private static long computeDiscreteLog(Map<BigInteger, Long> m){  
           long res = 0;  
           //compute g^B  
           BigInteger gB = g.modPow(new BigInteger(B+""), p);
           BigInteger current = gB;
           for(long i=1; i<B; i++){  
                //compute g^B^x0  
                Long value = m.get(current);
                if(value != null){  
                     res = i*B+value;  
                     break;  
                }
                current = current.multiply(gB).mod(p);
           }  
           return res;  
      }  
        
      public static void main(String [] args){
    	   long startTime = System.currentTimeMillis();
           Map<BigInteger, Long> m = leftHash();  
           long res = computeDiscreteLog(m);  
           long endTime = System.currentTimeMillis();
           System.out.println("time taken:" + (endTime - startTime));
           System.out.println("Found "+res);  
      }  
   
 }  