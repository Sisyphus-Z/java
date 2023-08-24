package custom;

import base.Config;
import base.IFunctions;
import base.ListenMouseKeyboard;
import base.MyThread;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Functions extends IFunctions {
    public static Thread t1;
    public static boolean t1Temp = false;
    public static Long time1 = Long.valueOf(Config.read("Time1"));

    static {
        t1 = new MyThread("off") {
            @Override
            public void run() {
                while (true) {
                    if (t1Temp == true) {
                        robot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
                        robot.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
//						robot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
                        pause(time1);
                    } else {
                        t1.suspend();
                    }
                }
            }
        };
    }

    @ListenMouseKeyboard(value = 516, keyboardOrMouse = 1)
    private static void 奔跑() {
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_SHIFT);
        // robot.keyRelease(KeyEvent.VK_F);
    }

    @ListenMouseKeyboard(value = 71, keyboardOrMouse = 0)
    private static void 修机() {
        robot.keyRelease(KeyEvent.VK_F);
        robot.keyPress(KeyEvent.VK_F);
    }

    // @ListenMouseKeyboard(value=160,immediately=true)
    // private static void 奔跑2() {
    // 	robot.keyRelease(KeyEvent.VK_F);
    // 	}

//	@ListenMouseKeyboard(value=70,immediately=true,userInput = false)
//	@ListenMouseKeyboard(value=70,immediately=true)
//	private static void 修机2() {
//		// TODO Auto-generated method stub
//		robot.keyRelease(KeyEvent.VK_SHIFT);
//		}

//	@ListenMouseKeyboard(value=192,immediately=true)
//	@ListenMouseKeyboard(value=191,immediately=true)
//	private static void 点血网() {
//		robot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
//		robot.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
//	}


    @ListenMouseKeyboard(value = 191, keyboardOrMouse = 0)
    private static void 点血网() {
        t1Temp = true;
        t1.resume();
    }

    @ListenMouseKeyboard(value = 190, keyboardOrMouse = 0)
    private static void 点血网1() {
        t1Temp = false;
    }

    @ListenMouseKeyboard(value = 84, keyboardOrMouse = 0)
    private static void 能力键1_space() {
        robot.keyRelease(KeyEvent.VK_SPACE);
        robot.keyPress(KeyEvent.VK_SPACE);
    }
}