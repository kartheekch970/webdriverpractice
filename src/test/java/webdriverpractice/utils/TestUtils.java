package webdriverpractice.utils;

public class TestUtils {

    public static void explicitWait() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}