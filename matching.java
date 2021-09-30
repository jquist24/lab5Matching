import java.util.Scanner;
import java.util.Arrays;

class matching {

    // Number of Programmers or Companies
    static int numProgrammers = 5;

    // This function returns true if Company
    // 'w' prefers Programmer 'm1' over Programmer 'm'
    static boolean wPrefersM1OverM(int prefer[][], int company, int programmer, int programmer1) {
        // Check if w prefers m over
        // her current engagment m1
        for (int i = 0; i < numProgrammers; i++) {
            // If m1 comes before m in list of w,
            // then w prefers her current engagement,
            // don't do anything
            if (prefer[company][i] == programmer1)
                return true;

            // If m comes before m1 in w's list,
            // then free her current engagement
            // and engage her with m
            if (prefer[company][i] == programmer)
                return false;
        }
        return false;
    }

    // Prints match for N programmers and N companies
    static void match(int prefer[][]) {
        // Stores partner of company
        int cPartner[] = new int[numProgrammers];

        // An array to store availability of programmers.
        // If pFree[i] is false, then programmer 'i' is
        // free, otherwise engaged.
        boolean pFree[] = new boolean[numProgrammers];

        // Initialize all programmers and Companies as free
        Arrays.fill(cPartner, -1);
        int freeCount = numProgrammers;

        // While there are free programmers
        while (freeCount > 0) {
            // Pick the first free programmer
            // (we could pick any)
            int m;
            for (m = 0; m < numProgrammers; m++)
                if (pFree[m] == false)
                    break;

            // One by one go to all Companies
            // according to m's preferences.
            // Here m is the picked free programmer
            for (int i = 0; i < numProgrammers && pFree[m] == false; i++) {
                int w = prefer[m][i];

                // The company of preference is free,
                // w and m become partners (Note that
                // the partnership maybe changed later).
                if (cPartner[w - numProgrammers] == -1) {
                    cPartner[w - numProgrammers] = m;
                    pFree[m] = true;
                    freeCount--;
                }

                else // If w is not free
                {
                    // Find current engagement of w
                    int m1 = cPartner[w - numProgrammers];

                    // company prefers a different programmer, swap
                    if (wPrefersM1OverM(prefer, w, m, m1) == false) {
                        cPartner[w - numProgrammers] = m;
                        pFree[m] = true;
                        pFree[m1] = false;
                    }
                } // End of Else
            } // End of the for loop that goes
              // to all Companies in m's list
        } // End of main while loop

        // Print the solution
        System.out.println("Company Programmer");
        for (int i = 0; i < numProgrammers; i++) {
            System.out.print(" ");
            System.out.println(i + numProgrammers + "     " + cPartner[i]);
        }
    }

    // Driver Code
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input number of programmers and companies (same for both): ");
        int numPartners = scanner.nextInt();
        System.out.println("Input preferences as numbers.  The first " + numPartners + " elements will be "+
        "programmers.  Each element should be provided as " + numPartners + " numbers representing partners;"+
        "be sure to have each preference in the first half be from the second and vice versa!");

        Partner[] programmers = new Partner[numPartners];
        Partner[] companies = new Partner[numPartners];

        for(int i = 0; i < 2 * numPartners; i++){
            Partner[] partnerTypeArray = (i < numPartners ? programmers : companies);
            String[] prefArray = new String[numPartners];
            for(int j = 0; j < numPartners; j++){
                prefArray[j] = Integer.toString(scanner.nextInt());
            }
            partnerTypeArray[i] = new Partner(Integer.toString(i), (i < numPartners ? Role.PROGRAMMER : Role.COMPANY), prefArray);
        }

        int prefer[][] = new int[][] {
                // programmer preferences
                { 5, 6, 7, 8, 9 }, { 5, 6, 7, 8, 9 }, { 5, 6, 7, 8, 9 }, { 5, 6, 7, 8, 9 }, { 5, 6, 7, 8, 9 },

                // company preferences
                { 4, 0, 3, 1, 2 }, { 3, 4, 1, 0, 2 }, { 3, 1, 2, 4, 0 }, { 2, 1, 3, 0, 4 }, { 0, 3, 1, 2, 4 } 
            };
            
            match(prefer);
    }

    //private static Partner[]

}
