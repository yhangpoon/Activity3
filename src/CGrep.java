import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Yin
 * @author sst8696
 * @author peter
 */
public class CGrep {

    private static final int NTHREDS = 10;

    /**
     * Main method
     * 
     * @param args
     *            - Input arguments
     */
    public static void main(String[] args) {

        if (args.length > 1) {
            ExecutorService executor = Executors.newFixedThreadPool(NTHREDS);
            List<Future<Found>> result = new ArrayList<Future<Found>>();
            for (int i = 1; i < args.length; i++) {
                String fileName = args[i].toString();
                File file = new File(fileName);
                Callable<Found> searcher = new FileSearch(file);
                Future<Found> submit = executor.submit(searcher);
                result.add(submit);
            }

            long sum = 0;
            System.out.println(result.size());
            // Now retrieve the result
            for (Future<Found> future : result) {
                /*
                 * try { sum += future.get(); } catch (InterruptedException e) {
                 * e.printStackTrace(); } catch (ExecutionException e) {
                 * e.printStackTrace(); }
                 */
            }
            System.out.println(sum);
            executor.shutdown();
        }
    }
}
