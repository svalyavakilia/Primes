/**
 * This is a utility class which helps work with the prime numbers.
 *
 * @author svalyavakilia
 */
public class Primes {
    /**
     * This method finds out how many prime numbers are LESS
     * than the passed argument.
     *
     * @param ceiling the value up to which
     *                prime numbers are counted (exclusive).
     * @return number of prime numbers LESS than the passed argument.
     * @author svalyavakilia
     */
    public static int countPrimesLessThanThisNumber(final int ceiling) {
        // There are no prime numbers before 2.
        if (ceiling <= 2) {
            return 0;
        }

        /*
          The size is [ceiling + 1] so that every cell is responsible for
          the value of its index
          (i.e. sieve[12] is responsible for the value 12).
          If the value is prime then the cell with its index contains "false".
          If the value is not prime then the cell with its index holds "true".
        */
        final boolean[] sieve = new boolean[ceiling + 1];
        // 0 and 1 are not prime numbers.
        sieve[0] = true;
        sieve[1] = true;

        // Cross out all multiples of 2 apart from 2.
        for (int toCrossOut = 4; toCrossOut <= ceiling; toCrossOut += 2) {
            sieve[toCrossOut] = true;
        }

        // Check all possible numbers for primality.
        for (int toCheck = 3; toCheck * toCheck <= ceiling; toCheck += 2) {
            // If the number is prime
            if (sieve[toCheck] == false) {
                // then cross out all its multiples.
                for (int toCrossOut = toCheck * toCheck;
                     toCrossOut <= ceiling;
                     toCrossOut += toCheck) {
                    sieve[toCrossOut] = true;
                }
            }
        }

        int numberOfPrimeNumbers = 0;

        // Count prime numbers starting from 2.
        for (int index = 2; index < sieve.length; index++) {
            // I like this form better then (!sieve[index]).
            if (sieve[index] == false) {
                numberOfPrimeNumbers++;
            }
        }

        /*
          If the last number is prime
          then return numberOfPrimeNumbers - 1
          as we want to count all the prime numbers LESS
          then the given one.
        */
        if (sieve[sieve.length - 1] == false) {
            return numberOfPrimeNumbers - 1;
        } else {
            return numberOfPrimeNumbers;
        }
    }

    /**
     * This method finds out if the given number is prime or not.
     *
     * @param toCheck the number to check.
     * @return {@code true} if the number is prime, otherwise {@code false}.
     * @author svalyavakilia
     */
    public static boolean isPrime(final int toCheck) {
        // 2 is a prime number.
        if (toCheck == 2) {
            return true;
        }

        // If the number is less than 2 or is even then it is not prime.
        if ((toCheck < 2) || ((toCheck & 1) == 0)) {
            return false;
        }

        // Check for primality.
        for (int divider = 3; divider * divider <= toCheck; divider += 2) {
            // If divider divides our argument then it is not prime.
            if ((toCheck % divider) == 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * This method finds n-th prime number.
     *
     * @param number the number of the prime number.
     * @return n-th prime number.
     * @author svalyavakilia
     */
    public static int findNthPrime(final int number) {
        if (number <= 0) {
            throw (new IllegalArgumentException
                    ("The number must be greater than 0."));
        }

        // The first prime number is 2 (it's also the only even prime number).
        if (number == 1) {
            return 2;
        }

        // It is equal to 1 because we already included 2.
        int numberOfPrimeNumbersFound = 1;
        int toCheck = 1;

        while (numberOfPrimeNumbersFound != number) {
            toCheck += 2;

            if (isPrime(toCheck)) {
                numberOfPrimeNumbersFound++;
            }
        }

        return toCheck;
    }
}