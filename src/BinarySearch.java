import java.util.ArrayList;

public class BinarySearch {

    private BinarySearch() {}

    public static int indexOf(int[] listOfStops, int key)
    {
        int lo = 0;
        int hi = listOfStops.length -1;
        while (lo <= hi)
        {
            int mid = lo + (hi - lo)/2;

            if (key < listOfStops[mid])
            {
                hi = mid -1;
            }
            else if (key > listOfStops[mid])
            {
                lo = mid + 1;
            }
            else
            {
                return mid;
            }

        }
        return -1;
    }




    static int [] quickSort (int a[]){

        a = shuffleArray(a);
        quickSort(a, 0, a.length-1);
        return a;
    }//end quicksort

    private static int[] quickSort(int[] a, int low, int high)
    {
        if (high <= low)
        {
            return a;
        }

        int j = partition(a, low, high);

        quickSort(a, low, j-1);
        quickSort(a,j+1,high);

        return a;

    }


    static int[] shuffleArray(int a[])
    {
        ArrayList<Integer> positionArray = new ArrayList<Integer>();

        for (int count = 0; count < a.length; count++)
        {
            positionArray.add(count);
        }


        for (int count2 = 0; count2 < a.length; count2++)
        {
            int position = (int) (Math.random()*positionArray.size());
            //System.out.println("Swap "+count2+" and "+position);
            int tempElement = a[position];
            a[position] = a[count2];
            a[count2] = tempElement;

        }

        return a;

    }

    private static int partition(int[] a, int low, int high)
    {
        int i = low;
        int j = high + 1;
        double temp = a[low];
        double swap = 0;

        while (true){

            while (lessThan(Double.valueOf(a[++i]), temp))
            {
                if (i == high)
                {
                    break;
                }
            }

            while (lessThan(temp, Double.valueOf(a[--j])))
            {
                if (j == low)
                {
                    break;
                }
            }

            if (i >= j)
            {
                break;
            }

            swap = a[i];
            a[i] = a[j];
            a[j] = (int) swap;
        }

        swap = a[low];
        a[low] = a[j];
        a[j] = (int) swap;

        return j;
    }

    public static boolean lessThan(Double first, Double second)
    {
        if (first == second || Double.compare(first,second) > 0)
        {
            return false;
        }

        else
        {
            return true;
        }
    }
}
