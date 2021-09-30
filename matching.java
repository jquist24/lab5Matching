import java.util.*;
 
class matching
{
 
// Number of Programmers or Companies
static int N = 5;
 
// This function returns true if Company
// 'w' prefers Programmer 'm1' over Programmer 'm'
static boolean wPrefersM1OverM(int prefer[][], int w,
                               int m, int m1)
{
    // Check if w prefers m over
    // her current engagment m1
    for (int i = 0; i < N; i++)
    {
        // If m1 comes before m in list of w,
        // then w prefers her current engagement,
        // don't do anything
        if (prefer[w][i] == m1)
            return true;
 
        // If m comes before m1 in w's list,
        // then free her current engagement
        // and engage her with m
        if (prefer[w][i] == m)
        return false;
    }
    return false;
}
 
// Prints match for N programmers and N companies
static void match(int prefer[][])
{
    // Stores partner of company
    int cPartner[] = new int[N];
 
    // An array to store availability of programmers.
    // If pFree[i] is false, then man 'i' is
    // free, otherwise engaged.
    boolean pFree[] = new boolean[N];
 
    // Initialize all programmers and Companies as free
    Arrays.fill(cPartner, -1);
    int freeCount = N;
 
    // While there are free programmers
    while (freeCount > 0)
    {
        // Pick the first free programmer
        // (we could pick any)
        int m;
        for (m = 0; m < N; m++)
            if (pFree[m] == false)
                break;
 
        // One by one go to all Companies
        // according to m's preferences.
        // Here m is the picked free programmer
        for (int i = 0; i < N &&
                        pFree[m] == false; i++)
        {
            int w = prefer[m][i];
 
            // The woman of preference is free,
            // w and m become partners (Note that
            // the partnership maybe changed later).
            // So we can say they are engaged not married
            if (cPartner[w - N] == -1)
            {
                cPartner[w - N] = m;
                pFree[m] = true;
                freeCount--;
            }
 
            else // If w is not free
            {
                // Find current engagement of w
                int m1 = cPartner[w - N];
 
                // If w prefers m over her current engagement m1,
                // then break the engagement between w and m1 and
                // engage m with w.
                if (wPrefersM1OverM(prefer, w, m, m1) == false)
                {
                    cPartner[w - N] = m;
                    pFree[m] = true;
                    pFree[m1] = false;
                }
            } // End of Else
        } // End of the for loop that goes
          // to all Companies in m's list
    } // End of main while loop
 
 
// Print the solution
System.out.println("Company Programmer");
for (int i = 0; i < N; i++)
{
    System.out.print(" ");
    System.out.println(i + N + "     " +
                           cPartner[i]);
}
}
 
// Driver Code
public static void main(String[] args)
{
    int prefer[][] = new int[][]{
    // programmer preferences
    {5,6,7,8,9},
    {5,6,7,8,9},
    {5,6,7,8,9},
    {5,6,7,8,9},
    {5,6,7,8,9},

    // company preferences 
    {4, 0, 3, 1, 2},
    {3, 4, 1, 0, 2},
    {3, 1, 2, 4, 0},
    {2, 1, 3, 0, 4},
    {0, 3, 1, 2, 4}
   };
    match(prefer);
}
}

