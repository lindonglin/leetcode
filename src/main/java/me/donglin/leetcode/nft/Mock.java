package me.donglin.leetcode.nft;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author donglin
 * @since 2023-03-20
 */
public class Mock {

    private static final double CONSUME_C_RATE = 0.5;
    private static int C_TO_A_RATE = 1;
    private static int B_TIME = 1;
    private static double A_INTEREST_RATE = 0.1;
    private static int INTEREST_WAIT = 1;

    public static void main(String[] args) {
        while (C_TO_A_RATE <= 5) {
            B_TIME = 1;
            while (B_TIME <= 10) {
                A_INTEREST_RATE = 0.1;
                while (A_INTEREST_RATE < 1) {
                    INTEREST_WAIT = 1;
                    while (INTEREST_WAIT < 5) {
                        if (invoke()) {
                            System.out.println();
                        }
                        INTEREST_WAIT++;
                    }
                    A_INTEREST_RATE += 0.1;
                }
                B_TIME++;
            }
            C_TO_A_RATE++;
        }
    }

    private static boolean invoke() {
        Summary summary = new Summary();
        int g = 100;
        for (int i = 0; i < 100; i++) {
            summary.next(g);
            if (!summary.allow()) {
                System.out.println("not allow " + summary);
                return false;
            }
            if (g == 900) {
                g = 100;
            } else {
                g += 100;
            }
        }
        System.out.println("allow " + summary);
        System.out.printf("C_TO_A_RATE: %d,  B_TIME: %d, A_INTEREST_RATE: %.2f, INTEREST_WAIT: %d\n\n",
                C_TO_A_RATE, B_TIME, A_INTEREST_RATE, INTEREST_WAIT);
        return true;
    }


    private static class Summary {
        private int i = 0;
        private int a = 10000;
        private int b;
        private int c;
        private int interest;
        private int preInterest;
        private final List<Record> records = new ArrayList<>();

        public Record next(int g) {
            Record record = new Record();
            record.a = new Pair();
            record.b = new Pair();
            record.c = new Pair();
            if (c > 0) {
                record.c.reduce = (int) (c * CONSUME_C_RATE);
            }
            if (i % INTEREST_WAIT == 0) {
                record.c.increase = b;
                record.interest = (int) (a * A_INTEREST_RATE) - b;
            }
            record.a.increase = record.c.reduce / C_TO_A_RATE;
            record.a.reduce = g;
            record.b.increase = g / 2;
            if (records.size() > B_TIME) {
                record.b.reduce = records.get(records.size() - B_TIME).b.increase;
            }
            calculateSummary(record);
            i++;
            return record;
        }

        private void calculateSummary(Record record) {
            records.add(record);
            a = a + record.a.increase - record.a.reduce;
            b = b + record.b.increase - record.b.reduce;
            c = c + record.c.increase - record.c.reduce;
            preInterest = interest;
            interest = interest + record.interest;
        }

        @Override
        public String toString() {
            return String.format("[%d] a: %d, b: %d, c: %d, interest: %d, increaseRate: %.2f", i, a, b, c, interest, 0.1 * interest / i);
        }

        public boolean allow() {
            return a >= 0 && interest >= 0 && (preInterest == 0 || Math.abs(1.0 * (interest - preInterest) / preInterest) <= 1);
        }
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Record {
        private Pair a;
        private Pair b;
        private Pair c;

        private int interest;

    }

    @Getter
    @Setter
    private static class Pair {

        private int increase;
        private int reduce;
    }
}
