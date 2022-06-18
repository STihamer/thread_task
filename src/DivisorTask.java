

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class DivisorTask implements Callable<Map<Integer,Integer>> {
    private static int count = 0;
    private final int a;
    private final int b;
    private final long sleepTime;

    private final int instanceNumber;
    private final String taskId;


    public DivisorTask(int a, int b, long sleepTime) {
        this.a = a;
        this.b = b;
        this.sleepTime = sleepTime;
        this.instanceNumber = ++count;
        this.taskId = "DivisorTask-" + instanceNumber;

    }


    @Override
    public Map<Integer,Integer> call() throws Exception {
        Map<Integer,Integer> resultMap = new HashMap<>();
        String currentThreadName = Thread.currentThread().getName();
        int keyCounterMain = 0;

        int valueCounter = 0;
        for (int i = a; i <= b; i++) {
            int keyCounter = 0;
            System.out.println("##### [" + currentThreadName + "] <" + taskId);
            for (int j = 1; j <= i; j++) {

                if (i % j == 0) {
                    keyCounter++;

                }

            }
            valueCounter = i;

            if(keyCounter > keyCounterMain){
                resultMap.clear();
                keyCounterMain = keyCounter;
                resultMap.put(valueCounter,keyCounterMain);
            }if(keyCounter == keyCounterMain){
                keyCounterMain = keyCounter;
                resultMap.put(valueCounter,keyCounterMain);
            }

        }

        return resultMap;
    }


}


