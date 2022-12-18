class ProcessData {
    // 到达时间
    public int arriveTime;
    // 服务时间
    public int serviceTime;
    // 完成时间
    public int finishTime;
    // 周转时间
    public int turnTime;
    // 带权周转时间
    public double powerTime;

    public ProcessData(int arriveTime, int serviceTime) {
        this.arriveTime = arriveTime;
        this.serviceTime = serviceTime;
    }

    @Override
    public String toString() {
        return arriveTime + "\t" +
                serviceTime + "\t" +
                finishTime + "\t" +
                turnTime + "\t" +
                powerTime;
    }
}

public class SJF {
    public static double avgTotalTime;
    public static double avgPowerTime;

    public static void main(String[] args) {
        ProcessData[] processData = new ProcessData[4];
        processData[0] = new ProcessData(0, 20);
        processData[1] = new ProcessData(5, 15);
        processData[2] = new ProcessData(10, 5);
        processData[3] = new ProcessData(15, 10);
        SJF(processData);
    }

    private static void SJF(ProcessData[] processData) {
        int preFinished = 0;
        avgTotalTime = 0;
        avgPowerTime = 0;

        for (int i = 0; i < processData.length; i++) {
            processData[i].finishTime = 0;
            processData[i].turnTime = 0;
            processData[i].powerTime = 0;
        }

        int number = 0;
        for (int i = 0; i < processData.length; i++) {
            int min = 8888;
            for (int j = 0; j < processData.length; j++) {
                if (processData[j].serviceTime < min && processData[j].arriveTime <= preFinished && processData[j].finishTime == 0) {
                    min = processData[j].serviceTime;
                    number = j;
                }
            }
            processData[number].finishTime = preFinished + processData[number].serviceTime;
            preFinished = processData[number].finishTime;
            processData[number].turnTime = processData[number].finishTime - processData[number].arriveTime;
            processData[number].powerTime = processData[number].turnTime / processData[number].serviceTime;
        }
        System.out.println("短进程优先算法：");
        Display(processData);
    }

    private static void Display(ProcessData[] processData) {
        System.out.println("到达时间\t" + "服务时间\t" + "完成时间\t" + "周转时间\t" + "带权周转时间\t");
        for (int i = 0; i < processData.length; i++) {
            System.out.println(processData[i]);
            avgTotalTime += processData[i].turnTime;
            avgPowerTime += processData[i].powerTime;
        }

        avgTotalTime = avgTotalTime / processData.length;
        avgPowerTime = avgPowerTime / processData.length;

        System.out.println("平均周转时间：" + avgTotalTime);
        System.out.println("平均带权周转时间：" + avgPowerTime);
    }
}