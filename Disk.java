
// OS Project By Rafael Perez, Edison Blum, Christopher Estevez, Ramish Saqib, Brian Jamal Jones
package homework;
import java.util.Random;
import java.util.Arrays;
import java.util.Scanner;
class Disk{ //skeleton by Rafael Perez

   public static int[] takeOutNode(int[] arr, int index)
{
  

if (arr == null || index < 0 || index >= arr.length) {
return arr;
}


int[] ArrayTwo = new int[arr.length - 1];
  

for (int i = 0, k = 0; i < arr.length; i++) {
  

if (i == index) {
continue;
}

ArrayTwo[k++] = arr[i];
}
  

return ArrayTwo;
}
//FCFS GOES HERE - Ed
   int FCFS(int[] arr, int size){
       int totalmoves=0;
       for (int i=0;i<size-1;i++){
           totalmoves+=Math.abs(arr[i]-arr[i+1]);
       }
       return totalmoves;
   }
  
// SSTF GOES HERE - Chris & Ramish
   
	public int SSTF(int[] passedArr, int capacity, int firstNum){
	       int[] holder_arr=new int[capacity]; 
	       for (int i=0;i<capacity ;i++ ) { 
	           holder_arr[i]=passedArr[i];
	       }
	       int total_num_moves=0;
	       int totalNumScaned=0;
	       Arrays.sort(holder_arr); 
	       int index=0;
	       for (int i=0;i<capacity ;i++ ) {
	           if (holder_arr[i]==firstNum) {
	               index=i;
	               break;
	           }
	       }


	       while (totalNumScaned<capacity) {
	           if (index>0 && index<capacity-totalNumScaned-1) {
	               int leftDifference=Math.abs(holder_arr[index]-holder_arr[index-1]);
	               int rightDifference=Math.abs(holder_arr[index]-holder_arr[index+1]);

	             
	               if (leftDifference<rightDifference) { 
	                   totalNumScaned++;
	                   total_num_moves+=Math.abs(holder_arr[index]-holder_arr[index-1]);
	                   holder_arr=takeOutNode(holder_arr, index);
	                   index--;
	               }
	               else { 
	                   totalNumScaned++;
	                   total_num_moves+=Math.abs(holder_arr[index]-holder_arr[index+1]);
	                   holder_arr=takeOutNode(holder_arr ,index);
	               }
	           }
	           else if(index>0 && index<=capacity-totalNumScaned-1){
	               totalNumScaned++;
	               total_num_moves+=Math.abs(holder_arr[index]-holder_arr[index-1]);
	               holder_arr=takeOutNode(holder_arr ,index);
	               index--;
	           }
	           else if(index>=0 && index<capacity-totalNumScaned-1){
	               totalNumScaned++;
	               total_num_moves+=Math.abs(holder_arr[index]-holder_arr[index+1]);
	               holder_arr=takeOutNode(holder_arr ,index);
	           }
	           if (totalNumScaned==capacity-1) {
	               takeOutNode(holder_arr, index);
	               totalNumScaned++;
	           }
	       }
	       return total_num_moves;

	   }
   
   
   
// INSERT SCAN HERE - Brian
int SCAN(int[] arr, int size, int firstNum){
    int totalmoves = 0;
    Arrays.sort(arr);
    int index = 0;

    for (int i = 0; i<size ;i++ ) {
        if (arr[i]==firstNum) {
            index=i;
            break;
        }
    }

    if (index==0 || index==size-1) {
        return arr[size-1]-arr[0];
    }

    totalmoves+=arr[size-1]-arr[index];
    totalmoves+=arr[size-1]-arr[0];
    totalmoves+=arr[index-1]-arr[0];
    return totalmoves;
}

   
 //MAIN METHOD
   public static void main(String[] args) {
       int[] arr=new int[1000];
       Random rand = new Random();
       for (int i=0;i<arr.length;i++) {
           arr[i] = rand.nextInt(5000);
       }
       int firstNum=arr[0];

       Disk m = new Disk();
       System.out.print("Total Distance with FCFS: ");
       System.out.println(m.FCFS(arr,arr.length));
       System.out.print("Total Distance with SSTF: ");
       System.out.println(m.SSTF(arr,arr.length,firstNum));
       System.out.print("Total Distance with SCAN: ");
       System.out.println(m.SCAN(arr,arr.length,firstNum));
   }
}