import java.awt.geom.Line2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
  private static final int circleX = 50;
  private static final int circleY = 50;
  private static final int circleRadius = 50;

  public static void main(String[] args) {
    ClassLoader classLoader = Main.class.getClassLoader();

    try {
      Scanner scanner = new Scanner(new File(classLoader.getResource("input.txt").getFile()));

      String firstLine = scanner.nextLine();
      int total = new Scanner(firstLine).nextInt();
      int count = 1;

      while (count <= total) {
        String line = scanner.nextLine();

        Scanner sc = new Scanner(line);
        int percentage = sc.nextInt();
        int x = sc.nextInt();
        int y = sc.nextInt();

        System.out.printf("Case #%s: %s\n", count, isInside(x, y, percentage));

        count++;
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static String isInside(int targetX, int targetY, double percentage) {
    double distance = Math.sqrt(
        (targetX - circleX) * (targetX - circleX) + (targetY - circleY) * (targetY - circleY));

    if (distance > circleRadius) {
      return "white";
    }

    float angle = getAngle(targetX, targetY);

    if (angle > (percentage / 100) * 360) {
      return "white";
    }

    return "black";
  }

  public static float getAngle(int targetX, int targetY) {
    float angle = (float) Math.toDegrees(Math.atan2(targetY - circleY, targetX - circleX));

    if (angle < 0) {
      angle += 360;
    }

    if (90 - angle > 0) {
      return 90 - angle;
    } else {
      return 360 + 90 - angle;
    }
  }
}
