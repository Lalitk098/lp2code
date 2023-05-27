import java.util.*;

public class selectsort {
    public static void SelectionSort(int arr[]){

        for(int i=0; i<arr.length-1; i++){  // runs n-1 times
            int smallest = i;       // store index of smallest element
            for(int j=i+1; j<arr.length; j++){
                if(arr[smallest]>arr[j]){
                    smallest=j;
                }
            }
            int temp = arr[smallest];
            arr[smallest] = arr[i];
            arr[i] = temp;
        }
        System.out.print("After sorting with Selection Sort method : ");
        for(int i=0; i<arr.length; i++){
            System.out.print(arr[i] + " ");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int arr[] = new int[size];
        for(int i=0; i<size; i++){
            arr[i] = sc.nextInt();
        }

        SelectionSort(arr);
        sc.close();
    }
}
