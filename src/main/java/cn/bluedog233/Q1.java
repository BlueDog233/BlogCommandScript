package cn.bluedog233;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Q1 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        List<Integer> arr=new ArrayList<>();
        int n=Integer.parseInt(scanner.nextLine());
        String[] sn=scanner.nextLine().split(" ");
        int m=Integer.parseInt(scanner.nextLine());
        String[] sm=scanner.nextLine().split(" ");
        for (String s : sn) {
            arr.add(Integer.parseInt(s));
        }
        for (String s : sm) {
            arr.add(Integer.parseInt(s));
        }
        arr.sort((a,b)->a-b);
        System.out.println(String.join(" ",(String[]) arr.stream().map(String::valueOf).toArray(String[]::new)));
    }
}
