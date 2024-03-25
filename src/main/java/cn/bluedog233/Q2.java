package cn.bluedog233;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Q2 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        ArrayList<Integer> arr=new ArrayList();
        int index=0;
        while((n--)!=0){
            arr.add(scanner.nextInt());
            index++;
        }
        int find=scanner.nextInt();
        int to=scanner.nextInt();
        int ind=arr.indexOf(find);
        arr.add(ind,to);
        String string[]=new String[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            string[i]=String.valueOf(arr.get(i));
        }
        System.out.println(String.join(" ",string));

    }
}
