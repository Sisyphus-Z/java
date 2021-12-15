import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RuntimeFunction {
    public static void main(String[] args) {

        System.out.print("current path is:");
        System.out.println(System.getProperty("user.dir"));

        Process proc;
        try {
            proc = Runtime.getRuntime().exec("python pythonmain.py aaa bbb");
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}