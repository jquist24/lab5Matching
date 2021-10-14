import java.util.*;
 
class matching
{
 
// Number of Programmers or Companies
static int N = 5;
 
// This function returns true if Company
// 'company' prefers Programmer 'p1' over Programmer 'p'
static boolean companyPrefersP1OverP(int prefer[][], int company, int p, int p1)
{
    // Check if current company prefers programmer over
    // current match p1
    for (int i = 0; i < N; i++)
    {
        // If p1 comes before p in the company's preferences,
        // then company prefers current match,
        // don't do anything
        if (prefer[company][i] == p1)
            return true;
        // If p comes before p1 in company's list,
        // then free p1 and match with p
        if (prefer[company][i] == p)
        return false;
    }
    return false;
}
 
// Prints match for N programmers and N companies
static void match(int prefer[][])
{
    // Stores programmer of company
    int companyProgrammer[] = new int[N];
 
    // An array to store availability of programmers.
    // If pMatched[i] is false, programmer 'p' is
    // free, otherwise they are matched.
    boolean pMatched[] = new boolean[N];
 
    // Initialize all programmers and Companies as free
    Arrays.fill(companyProgrammer, -1);
    int freeCount = N;
 
    // While there are free programmers
    while (freeCount > 0)
    {
        // Pick the first free programmer
        int p;
        for (p = 0; p < N; p++)
            if (pMatched[p] == false)
                break;
        // One by one go to all Companies
        // according to p's preferences.
        // Here p is the picked free programmer
        for (int i = 0; i < N && pMatched[p] == false; i++)
        {
            int company = prefer[p][i];
            // If the company of p's preference is free,
            // company "company" and p become matched for now.
            if (companyProgrammer[company - N] == -1)
            {
                companyProgrammer[company - N] = p;
                pMatched[p] = true;
                freeCount--;
            }
            else // If company is not free
            {
                // Find current match for company
                int p1 = companyProgrammer[company - N];
                // If company prefers programmer p over current match p1,
                // then break the match between company and p1 and
                // match p with company.
                if (companyPrefersP1OverP(prefer, company, p, p1) == false)
                {
                    companyProgrammer[company - N] = p;
                    pMatched[p] = true;
                    pMatched[p1] = false;
                }
            }
        }
    }
 
 
// print matches
for (int i = 0; i < N; i++)
{
    System.out.print(" ");
    System.out.println("Company " + (i + N) + " matched with Programmer " + companyProgrammer[i]);
}
}
 
// Driver
public static void main(String[] args)
{
    int prefer[][] = new int[][]{
    // The first array is the programmer's company preferences; all same
    {5,6,7,8,9},
    {5,6,7,8,9},
    {5,6,7,8,9},
    {5,6,7,8,9},
    {5,6,7,8,9},

    // more sets test data - randomly generated
    // {9, 6, 5, 7, 8},
    // {5, 8, 6, 9, 7},
    // {5, 9, 8, 7, 6},
    // {7, 8, 6, 5, 9},
    // {8, 7, 5, 9, 6},

    // The second array is always the company's programmer preferences 
    // {0,1,2,3,4},
    // {0,1,2,3,4},
    // {0,1,2,3,4},
    // {0,1,2,3,4},
    // {0,1,2,3,4}

    //more sets of test data - randomly generated
    {4, 1, 3, 0, 2},
    {2, 4, 3, 1, 0},
    {0, 4, 2, 1, 3},
    {0, 2, 1, 4, 3},
    {1, 0, 4, 3, 2}
   };
    match(prefer);
}
}

// 1. The algorithm develops satisfactory pairs.

// 2. The test cases are in the main file.

// 3. The code works by looking at each programmer, and matching
// them with their first preference. If their preference is taken,
// they check if that company would prefer the programmer in question
// rather than the company's current match. If they do, the programmer
// is matched with that company, and the programmer who used to be matched
// is set as free. This makes sure there is no satisfactory pairing left 
// unchecked, since it loops through each programmer's preferences and
// when there may be a more satisfactory pairing, it checks the company's
// preferences as well.
// It stops when it has looped over each programmer's
// preferences and for each programmer, does not find a company 
// that both prefers them, and that they prefer.

// 4. Algorithm efficiency is n^2. It looks at every programmer,
// and then checks the array for the companies as well;
// the worst case is when the algorithm has to compare each programmer's preference;
// so the worst case is when every programmer has the same preferences 
// for companies and company has the same preferences for programmers.

// Potential improvement:
// It could be possible to store programmers and companies in more specialized object types.
// This could allow for a good deal of efficiency, by having programmers remember where they
// "interviewed at" and thus not repeat interviews.  This would improve efficiency in the
// worst case, but it would still remain O(n^2).
