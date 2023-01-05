package javaapplication1;

import java.util.Arrays;
import java.util.Stack;

public class JavaApplication1 {

    //1:牆壁 0:路 9:終點
    public static int[][] maze = {
        {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
        {2, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2},
        {2, 2, 2, 0, 2, 2, 0, 0, 0, 0, 2, 2},
        {2, 2, 2, 0, 2, 2, 0, 2, 2, 0, 2, 2},
        {2, 2, 2, 0, 0, 0, 0, 2, 2, 0, 2, 2},
        {2, 2, 2, 0, 2, 2, 0, 2, 2, 0, 2, 2},
        {2, 2, 2, 0, 2, 2, 0, 2, 2, 0, 2, 2},
        {2, 2, 2, 2, 2, 2, 0, 2, 2, 0, 2, 2},
        {2, 2, 0, 0, 0, 0, 0, 0, 2, 0, 0, 2},
        {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}};
    public static Stack<int[]> record = new Stack<>();

    public static void main(String[] args) {
        int[] user = {1, 1};
        while (user[0] != 8 && user[1] != 10) {
            int[] nextLoc = move(user[0], user[1]);
            System.out.printf("user form %d,%d to %d,%d\n", user[0], user[1], nextLoc[0], nextLoc[1]);
            user = nextLoc;
        }
        System.out.println("get goal!");
    }

    public static int[] move(int y, int x) {
        //周遭分數
        int[] nextAllStep = {maze[y - 1][x], maze[y + 1][x], maze[y][x - 1], maze[y][x + 1]};

        //找出下一步
        int[] nextStep = {y, x};
        int min = minIndex(nextAllStep);
        switch (min) {
            case 0 ->
                nextStep[0] -= 1;
            case 1 ->
                nextStep[0] += 1;
            case 2 ->
                nextStep[1] -= 1;
            case 3 ->
                nextStep[1] += 1;
            default -> {
            }
        }

        int sum = Arrays.stream(nextAllStep).sum();
        if (sum == 6) {
            //只有一種走法
            record.push(new int[]{x, y});
            maze[y][x] = 2;
        } else if (sum < 6) {
            //遇到岔路
            record.push(new int[]{x, y});
            maze[y][x] = 1;
        } else if (sum == 7) {
            //走錯路碰到盡頭，回頭
            record.pop();
            maze[y][x] = 2;
        }
        return nextStep;
    }

    public static int minIndex(int[] array) {
        int index = 0;
        int min = array[index];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
                index = i;
            }
        }
        return index;
    }
}
