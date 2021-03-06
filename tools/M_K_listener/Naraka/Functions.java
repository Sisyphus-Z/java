import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Functions extends IFunctions {

    public static boolean temp1 = false;
    public static boolean temp2 = false;
    public static boolean temp3 = false;


    public static Thread t1;
    public static Thread t2;

    public static Long spaceDelay=Long.parseLong(Config.read("SpaceDelay"));
    public static Long EDelay=Long.parseLong(Config.read("EDelay"));

    static {
        t1=new CreateThread() {
            @Override
            public void myFunction() {
                while (true) {

//                    pause(Long.parseLong(Config.prop.getProperty("TotalDelay")));
                    if (temp1 == true) {
                        robot.keyRelease(KeyEvent.VK_SPACE);
                        robot.keyPress(KeyEvent.VK_SPACE);
                        pause(spaceDelay);
                    }
                    else{
                        t1.suspend();
                    }
                }
            }
        }.thread;

        t2=new CreateThread(){
            @Override
            public void myFunction() {
                while (true) {
//                    pause(Long.parseLong(Config.prop.getProperty("TotalDelay")));
					if (temp2 == true) {
						robot.keyRelease(KeyEvent.VK_E);
						robot.keyPress(KeyEvent.VK_E);
						robot.keyRelease(KeyEvent.VK_E);
						pause(EDelay);
					}else if(temp3==true){
                        pause(300);
                        robot.keyRelease(KeyEvent.VK_5);
                        robot.keyPress(KeyEvent.VK_5);
                        robot.keyRelease(KeyEvent.VK_5);
                        pause(20);
                        robot.keyRelease(KeyEvent.VK_4);
                        robot.keyPress(KeyEvent.VK_4);
                        robot.keyRelease(KeyEvent.VK_4);

                    }
					else{
                        t2.suspend();
                    }

				}
			}
        }.thread;
    }

    @ListenMouseKeyboard(value = 32, intercept = true)
    private static void space() {
        temp1 = true;
        t1.resume();

        temp3=false;
    }

    @ListenMouseKeyboard(value = 32, intercept = true, press = false)
    private static void space2() {
        temp1 = false;
    }



    //????????????
    @ListenMouseKeyboard(value = 69, intercept = true)
    private static void e() {
        temp2 = true;
        t2.resume();
    }
    @ListenMouseKeyboard(value = 69, press = false, intercept = true)
    private static void e2() {
        temp2 = false;
    }

    //?????? T
    @ListenMouseKeyboard(value = 84, intercept = true)
    private static void t() {
        robot.keyPress(KeyEvent.VK_E);
    }
    @ListenMouseKeyboard(value = 84, press = false, intercept = true)
    private static void t2() {
        robot.keyRelease(KeyEvent.VK_E);
    }

    //????????? G
    @ListenMouseKeyboard(value = 71, intercept = true)
    private static void g() {
        robot.keyRelease(KeyEvent.VK_TAB);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
        pause(40);
        robot.mouseMove(1794,622);
//        pause(1000);
        robot.mousePress(MouseEvent.BUTTON1_MASK);
        robot.mouseRelease(MouseEvent.BUTTON1_MASK);

        pause(40);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
    }


    public static boolean ctrl=false;
    @ListenMouseKeyboard(value = 162)
    private static void ctrl(){
        ctrl=true;
    }
    @ListenMouseKeyboard(value = 162,press = false)
    private static void ctrl2(){
        ctrl=false;
    }


    private static void writeProp(String s1,Long l1){
        if(ctrl==false){
        Config.write(s1,""+(Long.parseLong(Config.prop.getProperty(s1))+l1));}
        else{
            Config.write(s1,""+(Long.parseLong(Config.prop.getProperty(s1))-l1));
        }
        setClipboardString(Config.prop.toString());
    }

    /**
     * ???????????????????????????????????????
     */
    private static void setClipboardString(String text) {
        // ?????????????????????
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        // ??????????????????
        Transferable trans = new StringSelection(text);
        // ???????????????????????????????????????
        clipboard.setContents(trans, null);
    }

    //9
    @ListenMouseKeyboard(value = 57)
    private static void SpaceDelay(){
        writeProp("SpaceDelay", 50L);
        spaceDelay=Long.parseLong(Config.read("SpaceDelay"));
    }

    //8
    @ListenMouseKeyboard(value = 56)
    private static void EDelay(){
        writeProp("EDelay", 50L);
        spaceDelay=Long.parseLong(Config.read("EDelay"));
    }

    //4,5
    @ListenMouseKeyboard(value = 52)
    @ListenMouseKeyboard(value = 53)
    private static void yao(){
        temp3=true;
        t2.resume();
    }

//    @ListenMouseKeyboard(value = 18)
//    private static void yao2(){
//        System.out.println(1234);
//        temp3=false;
//    }


//    @ListenMouseKeyboard(value = 81)
//    private static void test(){
//        robot.keyPress(KeyEvent.VK_1);
//        robot.keyRelease(KeyEvent.VK_1);
//    }





}
