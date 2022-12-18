import java.util.*;

public class FCFS {
    private static class PCB implements Comparable<PCB> {//直接设置泛型方便后面不同类型直接sort()
        String id;
        float reachTime;
        float needTime;
        float startTime;
        float finishTime;
        char state;

        public int compareTo(PCB b) {
            if (reachTime == b.reachTime) return 0;
            if (reachTime < b.reachTime) return -1;
            return 1;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("请输入进程数:");
        int num = sc.nextInt();
        PCB[] arr = new PCB[num];
        System.out.println("请依次输入进程ID,进程到达时间,进程运行时间:");
        for (int i = 0; i < num; i++) {
            arr[i] = new PCB();
            arr[i].id = sc.next();
            arr[i].reachTime = sc.nextFloat();
            arr[i].needTime = sc.nextFloat();
            arr[i].state = 'R';
        }
        Arrays.sort(arr);

        float lastTime = arr[0].reachTime;
        for (int i = 0; i < num; i++) {
            int p = i;
            if (arr[p].reachTime < lastTime) arr[p].startTime = lastTime;
            else arr[p].startTime = arr[p].reachTime;
            arr[p].finishTime = arr[p].startTime + arr[p].needTime;
            arr[p].state = 'F';

            lastTime = arr[p].finishTime;
        }

        float sum1 = 0.0f, sum2 = 0.0f;
        System.out.println("\n进程  到达时间  运行时间  开始时间  完成时间  周转时间  带权周转时间");
        for (PCB jcb : arr) {
            System.out.format("%4s  %8.2f  %8.2f  ", jcb.id, jcb.reachTime, jcb.needTime);
            System.out.format("%8.2f  %8.2f  ", jcb.startTime, jcb.finishTime);
            System.out.format("%8.2f  ", jcb.finishTime - jcb.reachTime);
            System.out.format("%12.2f\n", (jcb.finishTime - jcb.reachTime) / jcb.needTime);
            sum1 += jcb.finishTime - jcb.reachTime;
            sum2 += (jcb.finishTime - jcb.reachTime) / jcb.needTime;
        }
        System.out.format("平均周转时间: %.3f\n", (sum1 / num));
        System.out.format("平均带权周转时间: %.3f", (sum2 / num));
    }
}
