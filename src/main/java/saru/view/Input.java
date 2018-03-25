package saru.view;

import saru.domain.*;

import java.util.*;

public class Input {
    private static final String INPUT_USER_INPUT = "좌표를 입력하세요.";
    private static final String INPUT_RANGE_ERROR = "범위가 잘못 되었습니다. 다시 입력하세요";
    private static final int FIRST_INDEX = 1;
    private static final int SECOND_INDEX = 2;
    private static final int LINE_POINT_COUNT = 2;
    private static final String REGEX = "[\\(,\\)]";

    private static Scanner scanner = new Scanner(System.in);

    private Input() {
        // empty
    }

    // 라인수
    public static List<Point> getUserInputProc() {
        // 유저에게 텍스트 입력 받는다
        List<Point> points = null;
        boolean isSuccess;

        do {
            String userInputString = getUserInput();

            try {
                points = getSplitedUserInputString(userInputString);
                isSuccess = true;
            } catch (IllegalArgumentException e) {
                isSuccess = false;
            }
        } while (!isSuccess);

        return points;
    }

    private static String getUserInput() {
        System.out.println();
        System.out.println(INPUT_USER_INPUT);
        return scanner.nextLine();
    }

    private static void loopAssignmentProc(String[] lineSplitArr, List<Point> points) {
        for (int i = 0; i < LINE_POINT_COUNT; i++) {
            String pointSplitArr[] = lineSplitArr[i].split(REGEX);
            assignmentSplitString(points, pointSplitArr);
        }
    }

    private static void assignmentSplitString(List<Point> points, String[] pointSplitArr) {
        try {
            // 첫 문자가 구분자라서 0번 인덱스는 ""(empty string)
            points.add(new Point(Double.parseDouble(pointSplitArr[FIRST_INDEX]),
                    Double.parseDouble(pointSplitArr[SECOND_INDEX]),
                    true));
        } catch (IllegalArgumentException e) {
            System.out.println(INPUT_RANGE_ERROR);
            throw new IllegalArgumentException(e);
        }
    }

    public static List<Point> getSplitedUserInputString(String userInputString) {
        String[] lineSplitArr = userInputString.split("-");
        List<Point> points = new ArrayList<>();

        loopAssignmentProc(lineSplitArr, points);

        return points;
    }
}
