package lw04.Unguided;

public class Main {
 
    static void sortCards(int[] arr, int passes) {
        for (int i = 0; i < passes; i++) {
            int minIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }

            int tmp = arr[i];
            arr[i] = arr[minIdx];
            arr[minIdx] = tmp;
        }
    }
 
 
    static int findCard(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }
 
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("cards.txt"));
 
       
        int n = input.nextInt();
        int[] cards = new int[n];
        for (int i = 0; i < n; i++) {
            cards[i] = input.nextInt();
        }
 
    
        int m = input.nextInt();
        int[] asked = new int[m];
        for (int i = 0; i < m; i++) {
            asked[i] = input.nextInt();
        }
        input.close();
 
        sortCards(cards, 2);
 
    
        for (int i = 0; i < n; i++) {
            System.out.print(cards[i]);
            if (i != n - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
 
    
        for (int i = 0; i < m; i++) {
            int pos = findCard(cards, asked[i]);
            if (pos == -1) {
                System.out.println(asked[i] + ": NOT FOUND");
            } else {
                System.out.println(asked[i] + ": FOUND at index " + pos);
            }
        }
    }
}

