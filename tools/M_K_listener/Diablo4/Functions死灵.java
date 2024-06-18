package custom;

import addition.FunctionsAddition;
import base.CommonUtil;
import base.ListenMouseKeyboard;
import base.MyThread;
import base.enty.TaskResult;

import java.awt.event.MouseEvent;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.awt.event.KeyEvent.*;


public class Functions死灵 extends Functions公共 {
    static {
    }


    private static FunctionsAddition.PixelColor pixelColor = new FunctionsAddition.PixelColor();


    public static boolean 需要骷髅祭司 = false;
    public static boolean 需要骷髅法师 = false;
    public static boolean 需要骷髅战士 = false;

    public static boolean 判断骷髅() {
//        需要骷髅战士 = pixelColor.getPixelColorHSB(1376, 997)[1] < 0.06F;
        需要骷髅战士 = pixelColor.getPixelColorHSB(1394, 976)[1] < 0.11333F;
        需要骷髅法师 = pixelColor.getPixelColorHSB(1400, 1025)[1] < 0.11F;
        需要骷髅祭司 = pixelColor.getPixelColorHSB(776, 969)[1] < 0.61F;


        return 需要骷髅祭司 || 需要骷髅法师 || 需要骷髅战士;
    }


    public static List<Integer> list = new ArrayList<>(Arrays.asList(VK_6, VK_7, VK_8, VK_9, VK_E));
    public static int len = list.size();

    public static MyThread t1 = new MyThread(MyThread.State.on) {
        @Override
        public void run() {
            int i = 0;

            while (true) {
                if (战斗 == true && b拾取 == false) {


                    if (i == len - 1) {
                        robot.keyPress(VK_SPACE);
                    }
                    robot.keyPress(list.get(i));
                    robot.keyRelease(list.get(i));
                    if (i == len - 1) {
                        pause(100L);
                        robot.keyRelease(VK_SPACE);
                    }

                    i++;

                    if (i >= len) {
                        i = 0;
                    }


                    pause(200L);


                } else {
                }
                pause(BaseDelay);
            }
        }
    };


    public static LocalDateTime 右键time = LocalDateTime.now();

    @ListenMouseKeyboard(key = "右键按下", userInput = false, keyboardOrMouse = CommonUtil.KeyboardOrMouse.Mouse, timeInterval = 200L)
    @ListenMouseKeyboard(key = "右键按下", keyboardOrMouse = CommonUtil.KeyboardOrMouse.Mouse, timeInterval = 200L)
    public static TaskResult 右键() {
        if (getKeyStatus(516) == false) {
            右键time = LocalDateTime.now();
        }


        setKeyStatus(516, true);
        if (战斗 == true) {
            robot.keyPress(VK_G);
            return new TaskResult(true);
        } else {
            return new TaskResult(false);
        }
    }

    @ListenMouseKeyboard(key = "右键松开", userInput = false, keyboardOrMouse = CommonUtil.KeyboardOrMouse.Mouse)
    @ListenMouseKeyboard(key = "右键松开", keyboardOrMouse = CommonUtil.KeyboardOrMouse.Mouse)
    public static TaskResult 右键1() {
        setKeyStatus(516, false);
//        if (Duration.between(右键time, LocalDateTime.now()).toMillis() >= 1000L) {
//            q();
//        }


        if (战斗 == true) {
            robot.keyRelease(VK_G);
            return new TaskResult(true);
        } else {
            return new TaskResult(false);
        }

    }


    @ListenMouseKeyboard(key = "g", keyboardOrMouse = CommonUtil.KeyboardOrMouse.Keyboard, timeInterval = 200L)
    @ListenMouseKeyboard(key = "g", userInput = false, keyboardOrMouse = CommonUtil.KeyboardOrMouse.Keyboard, timeInterval = 200L)
    @ListenMouseKeyboard(key = "左键按下", userInput = false, keyboardOrMouse = CommonUtil.KeyboardOrMouse.Mouse, timeInterval = 200L)
    public static void g() {
        if (战斗 == true) {
            b拾取 = true;
        }
    }

    @ListenMouseKeyboard(key = "g", press = false, keyboardOrMouse = CommonUtil.KeyboardOrMouse.Keyboard)
    @ListenMouseKeyboard(key = "g", userInput = false, press = false, keyboardOrMouse = CommonUtil.KeyboardOrMouse.Keyboard)
    public static void g1() {
        if (战斗 == true) {

            b拾取 = false;
        }
    }

    public static boolean 战斗 = false;

    @ListenMouseKeyboard(key = "q", keyboardOrMouse = CommonUtil.KeyboardOrMouse.Keyboard)
    @ListenMouseKeyboard(key = "q", userInput = false, keyboardOrMouse = CommonUtil.KeyboardOrMouse.Keyboard)
    @ListenMouseKeyboard(key = "中键按下",  keyboardOrMouse = CommonUtil.KeyboardOrMouse.Mouse)
    @ListenMouseKeyboard(key = "中键按下", userInput = false, keyboardOrMouse = CommonUtil.KeyboardOrMouse.Mouse)
    public static void q() {
        自动喝药开始(null, null, false);
        战斗 = true;
        b拾取 = true;
    }


    @ListenMouseKeyboard(key = "t", keyboardOrMouse = CommonUtil.KeyboardOrMouse.Keyboard, extend = true)
    @ListenMouseKeyboard(key = "t", userInput = false, keyboardOrMouse = CommonUtil.KeyboardOrMouse.Keyboard, extend = true)
    public static void t() {
        自动喝药结束();
        战斗 = false;
        b拾取 = false;
    }


}
