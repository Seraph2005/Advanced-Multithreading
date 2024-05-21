package sbu.cs.CalculatePi;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PiCalculator {
    static BigDecimal sum;

    public static class CalculatePi implements Runnable{
        MathContext mc = new MathContext(1005);
        int first;
        int step;

        public CalculatePi(int f, int step) {
            this.first = f;
            this.step = step;
        }

        @Override
        public void run() {
            BigDecimal s = new BigDecimal(0);
            for(int i = first; i < first + step; i++)
            {
                BigDecimal temp = new BigDecimal(0);
                BigDecimal sign = new BigDecimal(1);
                if(i%2 == 1)
                    sign = new BigDecimal(-1);
                BigDecimal x = new BigDecimal(2);
                BigDecimal x10n = x.pow(10*i);
                BigDecimal num1 = new BigDecimal(-32);
                num1 = num1.divide(BigDecimal.valueOf(4*i + 1), mc);
                BigDecimal num2 = new BigDecimal(-1);
                num2 = num2.divide(BigDecimal.valueOf(4*i + 3), mc);
                BigDecimal num3 = new BigDecimal(256);
                num3 = num3.divide(BigDecimal.valueOf(10*i + 1), mc);
                BigDecimal num4 = new BigDecimal(-64);
                num4 = num4.divide(BigDecimal.valueOf(10*i + 3), mc);
                BigDecimal num5 = new BigDecimal(-4);
                num5 = num5.divide(BigDecimal.valueOf(10*i + 5), mc);
                BigDecimal num6 = new BigDecimal(-4);
                num6 = num6.divide(BigDecimal.valueOf(10*i + 7), mc);
                BigDecimal num7 = new BigDecimal(1);
                num7 = num7.divide(BigDecimal.valueOf(10*i + 9), mc);
                temp = temp.add(num1);
                temp = temp.add(num2);
                temp = temp.add(num3);
                temp = temp.add(num4);
                temp = temp.add(num5);
                temp = temp.add(num6);
                temp = temp.add(num7);
                temp = temp.divide(x10n, mc);
                temp = temp.multiply(sign, mc);
                temp = temp.divide(BigDecimal.valueOf(64), mc);
                s = s.add(temp);
            }
            addToSum(s);
        }
    }

    public static synchronized void addToSum(BigDecimal x) {
        sum = sum.add(x);
    }

    public String calculate(int floatingPoint)
    {
        ExecutorService cachedTP = Executors.newCachedThreadPool();
        sum = BigDecimal.valueOf(0);

        for (int i = 0; i < 500; i++) {
            CalculatePi task = new CalculatePi(i * 50, 50);
            cachedTP.execute(task);
        }

        cachedTP.shutdown();

        try {
            cachedTP.awaitTermination(20000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String s = String.valueOf(sum);
        return s.substring(0, floatingPoint+2);
    }

    public static void main(String[] args) {
    }
}
