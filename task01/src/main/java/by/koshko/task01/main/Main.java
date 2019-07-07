package by.koshko.task01.main;

import by.koshko.task01.entity.InternetPlan;
import by.koshko.task01.entity.CommonPlan;
import by.koshko.task01.entity.SocialPlan;
import by.koshko.task01.factory.PlanFactory;
import by.koshko.task01.reader.FileToListReader;
import by.koshko.task01.writer.PlanToFileWriter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@SuppressWarnings("ALL")
public final class Main {
    private Main() {
    }
    /**
     * asd.
     * @param args asd.
     */
    public static void main(final String... args) {
        List<CommonPlan> plans =  PlanFactory.createPlans(new FileToListReader().read("info/plans.txt"));
        for (CommonPlan p : plans) {
            System.out.println(p);
        }


//        CommonPlan pl1 = new CommonPlan("Compadre Plus", 0.045, 0.02, 0.075);
//        CommonPlan pl2 = new CommonPlan("Compadre Minus", 0.05, 0.02, 0.075);
//        CommonPlan pl3 = new InternetPlan("Compadre Max", 0.067, 0.02, 0.075, 1000, 12.3);
//        CommonPlan pl4 = new InternetPlan("Compadre Min", 0.0345, 0.02, 0.075, 1000, 23.2);
//        CommonPlan pl5 = new InternetPlan("Compadre GetUp", 0.04, 0.02, 0.075, 1000, 23.34);
//        CommonPlan pl6 = new SocialPlan("Compadre Move Move Move", 0.15, 0.02, 0.075, 100, 200, 500, 23.5);
//        ArrayList<CommonPlan> list = new ArrayList<>();
//        list.add(pl1);
//        list.add(pl2);
//        list.add(pl3);
//        list.add(pl4);
//        list.add(pl5);
//        list.add(pl6);
//        final Comparator<CommonPlan> comparator = (plan1, plan2) -> {
//            if (plan1 == null && plan2 == null) {
//                throw new NullPointerException();
//            }
//            if (plan1.getCostMinute() < plan2.getCostMinute()) {
//                return -1;
//            } else if (plan1.getCostMinute() > plan2.getCostMinute()) {
//                return 1;
//            } else {
//                return 0;
//            }
//        };
//        PlanToFileWriter writer = new PlanToFileWriter();
//        writer.write(list, "info/report.txt");
//        long startTime = System.nanoTime();
//        List<CommonPlan> sortedPlans = planSortByCostMinute1(list, comparator);
//        long endTime = (System.nanoTime() - startTime);
//        System.out.println(endTime + " nanoseconds");
//        for (CommonPlan p : sortedPlans) {
//            System.out.println(p.toString());
//        }
    }
//    public static List<CommonPlan> planSortByCostMinute1(final List<CommonPlan> planList, final Comparator<CommonPlan> comparator) {
//        boolean flag = true;
//        CommonPlan temp = null;
//        int c;
//
//        while (flag) {
//            flag = false;
//            for (int i = 0; i < planList.size() - 1; i++) {
//                c = comparator.compare(planList.get(i), planList.get(i + 1));
//                if (c == 1) {
//                    temp = planList.get(i + 1);
//                    planList.set(i + 1, planList.get(i));
//                    planList.set(i, temp);
//                    flag = true;
//                }
//            }
//        }
//        return planList;
//    }


}
