import java.util.*;

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
        int prefer[][] = new int[][] {
                // programmer preferences
                { 5, 6, 7, 8, 9 }, { 5, 6, 7, 8, 9 }, { 5, 6, 7, 8, 9 }, { 5, 6, 7, 8, 9 }, { 5, 6, 7, 8, 9 },

                // company preferences
                { 4, 0, 3, 1, 2 }, { 3, 4, 1, 0, 2 }, { 3, 1, 2, 4, 0 }, { 2, 1, 3, 0, 4 }, { 0, 3, 1, 2, 4 } 
            };
            
            match(prefer);
    }

    //private static Partner[]

    protected enum Role {COMPANY, PROGRAMMER};

    protected class Partner {
        public final Role role;
        public final String[] preferences;
        public final String name; // will likely be a number for programmers,
        // but it's a String because we're not doing math with it.
        
        // Dissatisfaction being minimized rather than satisfaction being
        // maximized looks weird, but it allows us to set a Partner's like
        // or dislike of whoever they're paired with to its position
        // in the preferences array.
        private int currentDissatisfaction;
        private Partner currentPartner;

        public Partner(String name, Role role, String[] preferences){
            this.name = name;
            this.role = role;
            this.preferences = preferences;
        }

        public void setNewPartner(Partner newPartner){
            currentPartner = newPartner;
            currentDissatisfaction = getDissatisfaction(newPartner);
        } 

        public int getCurrentDissatisfaction() {
            return currentDissatisfaction;
        }

        public int getDissatisfaction(String otherName) throws IllegalArgumentException{
            for(int i = 0; i < preferences.length; i++){
                if (preferences[i] == otherName){
                    return i;
                }
            }
            throw new IllegalArgumentException("Key " + otherName + " was not found in the list of preferences.");
        }

        public int getDissatisfaction(Partner other){
            return getDissatisfaction(other.name);
        }

        public boolean wouldPrefer(Partner other){
            return getDissatisfaction(other) < currentDissatisfaction;
        }



    }
}
