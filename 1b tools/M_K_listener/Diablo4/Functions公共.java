package custom;

import base.FunctionsAddition;
import base.IFunctions;
import base.ListenMouseKeyboard;
import base.MyThread;

import java.awt.*;
import java.time.LocalDateTime;

import static java.awt.event.KeyEvent.*;

public class Functions公共 extends IFunctions {

    public static boolean 自动喝药 = false;
    public static boolean t1Temp1 = false;
    public static float[] myHSB血量;
    public static FunctionsAddition.PixelColor pixelColor = new FunctionsAddition.PixelColor();
    public static MyThread t1 = new MyThread(MyThread.State.off) {
        @Override
        public void run() {
            while (true) {
                if (自动喝药 == true) {
                    myHSB血量 = pixelColor.getPixelColorHSB(625, 990);
                    if (
                            myHSB血量[1] < 0.5F
                    ) {
                        robot.keyRelease(VK_0);
                        robot.keyPress(VK_0);
                        robot.keyRelease(VK_0);
                        t1Temp1 = true;

                        if (t1Temp1 == true) {
                            pause(800L);
                            t1Temp1 = false;
                        }
                    }
                } else {
                    this.mySuspend();
                }
                pause(400L);
            }
        }
    };

    @ListenMouseKeyboard(note = "f5", value = 116, intercept = true, keyboardOrMouse = ListenMouseKeyboard.KeyboardOrMouse.Keyboard)
    public static void 自动喝药() {
        自动喝药 = true;
        t1.myResume();
    }

    @ListenMouseKeyboard(note = "f6", value = 117, intercept = true, keyboardOrMouse = ListenMouseKeyboard.KeyboardOrMouse.Keyboard)
    @ListenMouseKeyboard(note = "t", value = 84, keyboardOrMouse = ListenMouseKeyboard.KeyboardOrMouse.Keyboard)
    public static void 自动喝药1() {
        自动喝药 = false;
    }


    @ListenMouseKeyboard(note = "x", value = 88, keyboardOrMouse = ListenMouseKeyboard.KeyboardOrMouse.Keyboard)
    public static void 扔装备() {
        Point point = MouseInfo.getPointerInfo().getLocation();
        robot.mousePress(BUTTON1_DOWN_MASK);
        robot.mouseRelease(BUTTON1_DOWN_MASK);
        robot.mouseMove(1085, 699);
        robot.mousePress(BUTTON1_DOWN_MASK);
        robot.mouseRelease(BUTTON1_DOWN_MASK);
        robot.mouseMove(point.x, point.y);
    }

    @ListenMouseKeyboard(note = "b", value = 66, keyboardOrMouse = ListenMouseKeyboard.KeyboardOrMouse.Keyboard, intercept = true)
    public static void 人物详情() {
        robot.keyPress(VK_C);
        robot.keyRelease(VK_C);
        Point point = MouseInfo.getPointerInfo().getLocation();
        robot.mouseMove(1355, 264);
        robot.mousePress(BUTTON1_DOWN_MASK);
        robot.mouseRelease(BUTTON1_DOWN_MASK);
        robot.mouseMove(point.x, point.y);
    }

    @ListenMouseKeyboard(note = "f7", value = 118, keyboardOrMouse = ListenMouseKeyboard.KeyboardOrMouse.Keyboard, intercept = true)
    public static void f() {
        pixelColor.threadOn(1239,896);
    }

    @ListenMouseKeyboard(note = "f8", value = 119, keyboardOrMouse = ListenMouseKeyboard.KeyboardOrMouse.Keyboard, intercept = true)
    public static void f1() {
        pixelColor.threadOff();
    }


    public static boolean t右键连点是否左键 = false;
    public static LocalDateTime 计时器 = LocalDateTime.MIN;
    public static MyThread t鼠标连点 = new MyThread(MyThread.State.off) {
        @Override
        public void run() {
            while (true) {
                if (t右键连点是否左键 == false) {
                    robot.mousePress(BUTTON3_DOWN_MASK);
                    robot.mouseRelease(BUTTON3_DOWN_MASK);
                } else {
                    robot.mousePress(BUTTON1_DOWN_MASK);
                    robot.mouseRelease(BUTTON1_DOWN_MASK);
                }
                pause(200L);
            }
        }
    };

    @ListenMouseKeyboard(note = "=", value = 187, keyboardOrMouse = ListenMouseKeyboard.KeyboardOrMouse.Keyboard, intercept = true)
    public static void 右键连点() {
        if(LocalDateTime.now().getSecond()-计时器.getSecond()<2){
          t右键连点是否左键 = true;
        }else {
            t右键连点是否左键=false;
        }
        计时器=LocalDateTime.now();
        t鼠标连点.myResume();
    }

    @ListenMouseKeyboard(note = "-", value = 189, keyboardOrMouse = ListenMouseKeyboard.KeyboardOrMouse.Keyboard, intercept = true)
    public static void 右键连点_1() {
        t鼠标连点.mySuspend();
    }


}