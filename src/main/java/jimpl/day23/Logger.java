package jimpl.day23;

public class Logger {

    public static void log(String msg, Object... args) {
        System.out.printf(msg + "%n", args);
    }
}
