public class Test {
    public static void main(final String... programArguments) {
        final long start = System.currentTimeMillis();
        // Start of the code to test.

        System.out.println(Primes.findNthPrime(10000000));

        // End of the code to test.
        final long finish = System.currentTimeMillis();
        final long runningTime = finish - start;
        System.out.println(
            "Program was running " + runningTime + " milliseconds."
        );
    }
}