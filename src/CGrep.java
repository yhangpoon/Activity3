import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Main class to start and run the program.
 * 
 * @author Yin
 * @author sst8696
 * @author peter
 */
public class CGrep {

    /**
     * Thread Pool Size.
     */
    private static final int THREADPOOLAMOUNT = 3;

    /**
     * Main method to start the program.
     * 
     * @param args
     *            - Input arguments
     */
    public static void main(String[] args) {

        ExecutorService executor = Executors
                .newFixedThreadPool(THREADPOOLAMOUNT);

        String pattern = args[0].toString();

        // Check argument length
        if (args.length > 1) {

            for (int i = 1; i < args.length; i++) {
                String argument = args[i].toString();
                File file = new File(argument);

                // Search File
                if (file.exists()) {
                    try {
                        Callable<Found> searcher = new FileSearch(file,
                                pattern);
                        Future<Found> result = executor.submit(searcher);
                        System.out.println(result.get());
                    } catch (FileNotFoundException e) {
                        System.err.print(e.toString());
                    } catch (InterruptedException e) {
                        System.err.print(e.toString());
                    } catch (ExecutionException e) {
                        System.err.print(e.toString());
                    }
                }
            }

        } else if (args.length == 1) {
            InputStream input = System.in;
            Callable<Found> searcher = new FileSearch(input, pattern);
            Future<Found> result = executor.submit(searcher);
            try {
                System.out.println(result.get());
            } catch (InterruptedException e) {
                System.err.print(e.toString());
            } catch (ExecutionException e) {
                System.err.print(e.toString());
            }
        } else {
            System.out.println("Usage: java CGrep [pattern] [file...]");
            System.out.println("OR");
            System.out.println("Usage: java CGrep [pattern]");
        }

        executor.shutdown();
    }
}
