


import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) {


        ExecutorService executorService = Executors.newCachedThreadPool(new NamedThreadFactory());


        Future<Map<Integer, Integer>> result1 = executorService.submit(new DivisorTask(1, 10000, 2000));
        Future<Map<Integer, Integer>> result2 = executorService.submit(new DivisorTask(10000, 20000, 2000));
        Future<Map<Integer, Integer>> result3 = executorService.submit(new DivisorTask(20000, 30000, 2000));
        Future<Map<Integer, Integer>> result4 = executorService.submit(new DivisorTask(30000, 40000, 100));
        Future<Map<Integer, Integer>> result5 = executorService.submit(new DivisorTask(40000, 50000, 25));
        Future<Map<Integer, Integer>> result6 = executorService.submit(new DivisorTask(50000, 60000, 2000));
        Future<Map<Integer, Integer>> result7 = executorService.submit(new DivisorTask(60000, 70000, 10));
        Future<Map<Integer, Integer>> result8 = executorService.submit(new DivisorTask(70000, 80000, 2000));
        Future<Map<Integer, Integer>> result9 = executorService.submit(new DivisorTask(80000, 90000, 0));
        Future<Map<Integer, Integer>> result10 = executorService.submit(new DivisorTask(90000, 100000, 0));
        Future<Map<Integer, Integer>> result11 = executorService.submit(new DivisorTask(1, 100000, 5000));



        executorService.shutdown();
        try {
            Map<Integer, Integer> newResultMap = new HashMap<>();
            newResultMap.putAll(result1.get());
            newResultMap.putAll(result2.get());
            newResultMap.putAll(result3.get());
            newResultMap.putAll(result4.get());
            newResultMap.putAll(result5.get());
            newResultMap.putAll(result6.get());
            newResultMap.putAll(result7.get());
            newResultMap.putAll(result8.get());
            newResultMap.putAll(result9.get());
            newResultMap.putAll(result10.get());

            LinkedHashMap<Integer, Integer> reverseSortedResult = new LinkedHashMap<>();

            newResultMap.entrySet().stream().sorted(Map.Entry.comparingByKey((Comparator.reverseOrder())))
                    .forEachOrdered(x -> reverseSortedResult.put(x.getKey(), x.getValue()));

            System.out.println(reverseSortedResult);
            Map<Integer, Integer> newResultMapFromOneThread = new HashMap<>(result11.get());
            System.out.println(newResultMapFromOneThread);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
