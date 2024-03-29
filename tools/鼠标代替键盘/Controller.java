import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.JIntellitype;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Controller {

    public static Robot robot;
    static {
        try {
            robot = new Robot();
            System.out.println("robot ready");
        } catch (AWTException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static int s = 27;
    public static int ss = 6;
    public static int delay = 60;

    public static boolean boolean1 = false;

    public static int int1=0;

    public static List<String> taskList1=new ArrayList();

    public static int mod1 = JIntellitype.MOD_SHIFT;
    public static int mod2 = JIntellitype.MOD_ALT;


    public static void mouseMove1(String direction) {
        int x = (int) MouseInfo.getPointerInfo().getLocation().getX();
        int y = (int) MouseInfo.getPointerInfo().getLocation().getY();
        switch (direction) {
            case "up":
                robot.mouseMove(x, y - s);
                break;
            case "left":
                robot.mouseMove(x - s, y);
                break;
            case "down":
                robot.mouseMove(x, y + s);
                break;
            case "right":
                robot.mouseMove(x + s, y);
                break;
        }
    }


    public static void mouseClick(int mouseKey, int modKey) {
        robot.keyRelease(modKey);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        robot.mousePress(mouseKey);
        robot.keyRelease(modKey);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        robot.mouseRelease(mouseKey);
        robot.keyPress(modKey);
    }

    public static void mouseWheel(int speed, int modKey) {
        robot.keyRelease(modKey);

        robot.mouseWheel(speed);

        robot.keyPress(modKey);
    }


    public static void run() {

        MyJFrame.setJFrame();


        JIntellitype.getInstance().registerHotKey(1, 0, 27);
        JIntellitype.getInstance().registerHotKey(2, 0, 112 );
        JIntellitype.getInstance().registerHotKey(3, 0, 113);
        JIntellitype.getInstance().registerHotKey(4, 0, 114);
        JIntellitype.getInstance().registerHotKey(5, 0, 115);

        JIntellitype.getInstance().registerHotKey(6, 0, 33);
        JIntellitype.getInstance().registerHotKey(7, 0, 34);





        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(70);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    String task1="";
                    if(taskList1.size()>0) {

                        try {
                            task1=taskList1.get(0);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        taskList1.remove(0);
                    }


                    switch (task1){
                        case "leftMouseClick":
                            System.out.println("start task: "+task1+" "+(++int1));
                            try {
                                Robot robot1=new Robot();
                                robot1.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
                                robot1.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
                            } catch (AWTException e) {
                                e.printStackTrace();
                            }
                            break;
                        case "rightMouseClick":
                            System.out.println("start task: "+task1+" "+(++int1));
                            try {
                                Robot robot1=new Robot();
                                robot1.mousePress(MouseEvent.BUTTON3_DOWN_MASK);
                                robot1.mouseRelease(MouseEvent.BUTTON3_DOWN_MASK);
                            } catch (AWTException e) {
                                e.printStackTrace();
                            }
                            break;

                        case "wheelUp":
                            System.out.println("start task: "+task1+" "+(++int1));
                            try {
                                Robot robot1=new Robot();
                                robot1.mouseWheel(-8);
                            } catch (AWTException e) {
                                e.printStackTrace();
                            }
                            break;

                        case "wheelDown":
                            System.out.println("start task: "+task1+" "+(++int1));
                            try {
                                Robot robot1=new Robot();
                                robot1.mouseWheel(8);
                            } catch (AWTException e) {
                                e.printStackTrace();
                            }
                            break;



                    }
                    task1="";

                }
            }
        }.start();


        // 添加热键监听器
        JIntellitype.getInstance().addHotKeyListener(new HotkeyListener() {
            public void onHotKey(int markCode) {
                switch (markCode) {
                    case 1:
                        taskList1.add("leftMouseClick");
                        break;
                    case 2:
                        taskList1.add("rightMouseClick");
                        break;
                    case 3:
                        robot.keyRelease(KeyEvent.VK_ENTER);
                        robot.keyPress(KeyEvent.VK_ENTER);
                        robot.keyRelease(KeyEvent.VK_ENTER);
                        break;
                    case 4:
                        robot.keyRelease(KeyEvent.VK_BACK_SPACE);
                        robot.keyPress(KeyEvent.VK_BACK_SPACE);
                        robot.keyRelease(KeyEvent.VK_BACK_SPACE);
                        break;
                    case 5:
                        robot.keyRelease(KeyEvent.VK_DELETE);
                        robot.keyPress(KeyEvent.VK_DELETE);
                        robot.keyRelease(KeyEvent.VK_DELETE);
                        break;

                    case 6:
                        taskList1.add("wheelUp");
                        break;
                    case 7:
                        taskList1.add("wheelDown");
                        break;


                }
            }

        });
    }

}
