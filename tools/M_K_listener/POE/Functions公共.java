package custom;

import base.IFunctions;
import base.ListenMouseKeyboard;
import base.MyThread;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static java.awt.event.KeyEvent.*;

public class Functions公共 extends IFunctions {
    public static boolean running = false;
    public static boolean tempStopRun = false;
    public static boolean b1 = false;


    @ListenMouseKeyboard(note="esc",value = 27,intercept = true,keyboardOrMouse = ListenMouseKeyboard.KeyboardOrMouse.Keyboard,extend = true)
    public static void esc(){
        myKeyRelease(VK_ALT);
        pause(50L);
        myKeyPress(VK_ESCAPE);
    }

    public static LocalDateTime 拾取start = LocalDateTime.now();
    @ListenMouseKeyboard(note="f",value = 70,intercept = true,timeInterval = 500L,keyboardOrMouse = ListenMouseKeyboard.KeyboardOrMouse.Keyboard,extend = true)
    public static void 拾取(){
        拾取start=LocalDateTime.now();
    }

    @ListenMouseKeyboard(note = "space", value = 32, intercept = true, keyboardOrMouse = ListenMouseKeyboard.KeyboardOrMouse.Keyboard)
    private static void 奔跑() {
        tempStopRun = false;
        running = true;
    }



    public static Thread t1 = new MyThread(MyThread.State.on) {
        @Override
        public void run() {
            while (true) {
                if (running == true) {
                    b1=true;
//                    if (tempStopRun == true) {
//                        robot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
//                        robot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
//                        pause(110);
//                        continue;
//                    }
//                    if(getKeyStatus(VK_R)==false){
//                        myKeyPress(VK_R);
//                    }
                    if(Duration.between(拾取start,LocalDateTime.now()).toMillis()<2000L){
                        System.out.println(LocalDateTime.now());
                        myMousePress(MouseEvent.BUTTON1_DOWN_MASK);
                        myMouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
                    }else {
                        if (getKeyStatus(MouseEvent.BUTTON1_DOWN_MASK) == false) {
                            myMousePress(MouseEvent.BUTTON1_DOWN_MASK);
                        }
                    }




                }else{
//                    if(getKeyStatus(VK_R)==true){
//                        myKeyRelease(VK_R);
//                    }

                    if (getKeyStatus(MouseEvent.BUTTON1_DOWN_MASK) == true) {
                        myMouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
                    }
                }
                pause(200L);
            }
        }
    };

    public static Thread t2 = new MyThread(MyThread.State.on) {
        @Override
        public void run() {
            while (true) {

                if (running == true) {
//                    if(getKeyStatus(VK_ALT)==false){
                        myKeyPress(VK_ALT);
//                    }
                }else{

                }
                pause(500L);
            }
        }
    };

    /**
     * 把文本设置到剪贴板（复制）
     */
    public static void setClipboardString(String text) {
        // 获取系统剪贴板
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        // 封装文本内容
        Transferable trans = new StringSelection(text);
        // 把文本内容设置到系统剪贴板
        clipboard.setContents(trans, null);
    }


    public static boolean trade = false;
    public static Integer count1 = 0;
    public static Integer count2 = 0;
    public static HashMap<Integer, String> map1 = new HashMap<>();

    static {
        map1.put(0, "头");
        map1.put(1, "胸");
        map1.put(2, "手套");
        map1.put(3, "鞋");
        map1.put(4, "腰带");
        map1.put(5, "戒指");
//        map1.put(6,"戒指");
        map1.put(6, "护身符");
        map1.put(7, "武器|格挡几率");

//        map1.put(100,"\"物品等级: [0-5][0-9]|普通|魔法|传奇|\"!未鉴定\"\"");
        map1.put(100, "物品等级:[0-5][0-9]|普通|魔法|传奇");
        map1.put(101, "!未鉴定");
        map1.put(102, "\"物品等级: ([6][0-9]|[7][0-4])\"");
    }

    public static void 在搜索框粘贴(Integer integer, String s) {
        if (trade == true) {

            setClipboardString(map1.get(integer) + s);

            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_F);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_F);

            pause(50);

            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);

            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_V);
        }
    }






    //f1
    @ListenMouseKeyboard(note="f1",value = 112, intercept = true,keyboardOrMouse = ListenMouseKeyboard.KeyboardOrMouse.Keyboard)
    private static void 按xxx开始处理成套装备模式() {
        trade = true;
        count1 = 0;
        count2 = 0;

        int x = MouseInfo.getPointerInfo().getLocation().x;
        int y = MouseInfo.getPointerInfo().getLocation().y;
        robot.mouseMove(491, 105);
        pause(50);
        robot.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
        pause(50);
        robot.mouseMove(x, y);
    }

    //f2
    @ListenMouseKeyboard(note = "f2", value = 113, intercept = true, keyboardOrMouse = ListenMouseKeyboard.KeyboardOrMouse.Keyboard)
    //esc
    @ListenMouseKeyboard(note = "esc", value = 27, keyboardOrMouse = ListenMouseKeyboard.KeyboardOrMouse.Keyboard)

    @ListenMouseKeyboard(note = "`", value = 192, keyboardOrMouse = ListenMouseKeyboard.KeyboardOrMouse.Keyboard)
    private static void 按xxx结束处理成套装备模式() {
        trade = false;
    }

    @ListenMouseKeyboard(value = 50, intercept = true, keyboardOrMouse = ListenMouseKeyboard.KeyboardOrMouse.Keyboard)
    private static void 整套装备预筛选() {
        if (trade == true) {
            在搜索框粘贴(count1 + 100, "");
            count1++;
            if (count1 >= 3) {
                count1 = 0;
            }
        } else {
            robot.keyPress(KeyEvent.VK_2);
            robot.keyRelease(KeyEvent.VK_2);
        }

    }

    @ListenMouseKeyboard(value = 51, intercept = true, keyboardOrMouse = ListenMouseKeyboard.KeyboardOrMouse.Keyboard)
    private static void 顺序获取整套装备() {

        if (trade == true) {
            在搜索框粘贴(count2, "");
            count2++;
            if (count2 >= 8) {
                count2 = 0;
            }
        } else {
            robot.keyPress(KeyEvent.VK_3);
            robot.keyRelease(KeyEvent.VK_3);
        }
    }

    @ListenMouseKeyboard(value = 53, intercept = true, keyboardOrMouse = ListenMouseKeyboard.KeyboardOrMouse.Keyboard)
    private static void 顺序获取整套装备60到74级() {

        在搜索框粘贴(count2, " 物品等级:\\s([6][0-9]|[7][0-4])");
        count2++;
        if (count2 >= 8) {
            count2 = 0;
        }

        if (trade == false) {
            robot.keyPress(KeyEvent.VK_5);
            robot.keyRelease(KeyEvent.VK_5);
        }
    }

    @ListenMouseKeyboard(value = 54, intercept = true, keyboardOrMouse = ListenMouseKeyboard.KeyboardOrMouse.Keyboard)
    private static void 顺序获取整套装备大于74级() {

        在搜索框粘贴(count2, " 物品等级:\\s([8-9][0-9]|[7][5-9])");
        count2++;
        if (count2 >= 8) {
            count2 = 0;
        }

        if (trade == false) {
            robot.keyPress(KeyEvent.VK_6);
            robot.keyRelease(KeyEvent.VK_6);
        }
    }


    @ListenMouseKeyboard(note = "1", value = 49, intercept = true, keyboardOrMouse = ListenMouseKeyboard.KeyboardOrMouse.Keyboard)
    private static void ctrl加左键连点() {
        if(getKeyStatus(VK_CONTROL)==false){
            myKeyPress(KeyEvent.VK_CONTROL);

        }

        myMousePress(MouseEvent.BUTTON1_DOWN_MASK);
        myMouseRelease(MouseEvent.BUTTON1_DOWN_MASK);

    }

    @ListenMouseKeyboard(note = "1", value = 49, press = false, intercept = false, keyboardOrMouse = ListenMouseKeyboard.KeyboardOrMouse.Keyboard)
    private static void 退出ctrl加左键连点() {

        myKeyRelease(VK_CONTROL);
    }


    @ListenMouseKeyboard(note = ".", value = 190, keyboardOrMouse = ListenMouseKeyboard.KeyboardOrMouse.Keyboard)
    private static void 市集下一页() {
        int x = MouseInfo.getPointerInfo().getLocation().x;
        int y = MouseInfo.getPointerInfo().getLocation().y;
        robot.mouseMove(541, 177);
        pause(50);
        robot.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
        pause(50);
        robot.mouseMove(x, y);
    }


    //y
    @ListenMouseKeyboard(note = "y", value = 89, intercept = true, keyboardOrMouse = ListenMouseKeyboard.KeyboardOrMouse.Keyboard)
    private static void 回城卷轴() {

        robot.keyPress(192);
        robot.keyRelease(192);

        int x = MouseInfo.getPointerInfo().getLocation().x;
        int y = MouseInfo.getPointerInfo().getLocation().y;
        robot.mouseMove(1228, 824);
        pause(50);
        robot.mousePress(MouseEvent.BUTTON3_DOWN_MASK);
        robot.mouseRelease(MouseEvent.BUTTON3_DOWN_MASK);
        pause(50);

        robot.keyPress(192);
        robot.keyRelease(192);
        robot.mouseMove(x, y);
    }

}
