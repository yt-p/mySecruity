package com.yt;

/**
 * @Description:
 * @Auther: yt
 * @Date: 2020/4/6 0006 16:08
 */
public class Test {
    public static void main(String[] args) {


        System.out.println(MyFunction(3));

    }
    public static int MyFunction(int num){
        if(num<1)
        {
            return 1;
        }
        else
        {
            return MyFunction(num-1)*2;
        }
    }
}
