package me.donglin.leetcode.nft;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author donglin
 * @since 2023-03-20
 */
public class Mock {


    public static void main(String[] args) {
        Summary summary = new Summary();
        System.out.println(summary);
        int g = 100;
        for (int i = 0; i < 100; i++) {
            summary.next(g);
            System.out.println(summary);
            if (g == 900) {
                g = 100;
            } else {
                g += 100;
            }
        }
    }

    private static final double CONSUME_C_RATE = 0.5;
    private static final int C_TO_A_RATE = 2;
    private static final int B_TIME = 5;
    private static final double A_INTEREST_RATE = 0.5;
    private static final int INTEREST_WAIT = 2;

    private static class Summary {
        private int i = 0;
        private int a = 10000;
        private int b;
        private int c;
        private int interest;
        private final List<Record> records = new ArrayList<>();

        public Record next(int g) {
            Record record = new Record();
            record.a = new Pair();
            record.b = new Pair();
            record.c = new Pair();
            if (c > 0) {
                record.c.reduce = (int) (c * CONSUME_C_RATE);
            }
            if (b > 0 && (i % INTEREST_WAIT) == 0) {
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
            interest = interest + record.interest;
        }

        @Override
        public String toString() {
            return String.format("[%d] a: %d, b: %d, c: %d, interest: %d", i, a, b, c, interest);
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
