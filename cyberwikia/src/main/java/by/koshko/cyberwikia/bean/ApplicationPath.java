package by.koshko.cyberwikia.bean;

public class ApplicationPath {
    private static String root;

    public static String getRoot() {
        return root;
    }

    public static void setRoot(final String rootPath) {
        root = rootPath;
    }
}
