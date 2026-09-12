package week1.assigment_problems;

public class SeatDuplicationChecker {

    public static void checkDuplicateSeats(int[] seatNumbers) {
        boolean duplicateFound = false;

        for (int i = 0; i < seatNumbers.length; i++) {
            for (int j = i + 1; j < seatNumbers.length; j++) {
                if (seatNumbers[i] == seatNumbers[j]) {
                    System.out.println("Duplicate Seat Number Found: " + seatNumbers[i]);
                    duplicateFound = true;
                    break;
                }
            }
        }

        if (!duplicateFound) {
            System.out.println("No Duplicate Seats Found");
        }
    }

    public static void main(String[] args) {
        int[] hall1 = {101, 102, 103, 102, 105};
        int[] hall2 = {101, 102, 103, 104, 105};

        System.out.print("Hall 1: ");
        checkDuplicateSeats(hall1);

        System.out.print("Hall 2: ");
        checkDuplicateSeats(hall2);
    }
}