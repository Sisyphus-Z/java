package custom;

import base.*;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static java.awt.event.KeyEvent.*;

class Run extends MainClass {


}

public class Functions extends IFunctions {
    static {
        Controller.printKey = true;
    }


//    @ListenMouseKeyboard(note = "shift", value = 160, intercept = true, keyboardOrMouse = ListenMouseKeyboard.KeyboardOrMouse.Keyboard, timeInterval = 500L)
//    public static void 奔跑() {
//        myKeyRelease(VK_SHIFT);
//        myKeyPress(VK_SHIFT);
//    }

//    @ListenMouseKeyboard(note = "shift", press = false, value = 160, intercept = true, keyboardOrMouse = ListenMouseKeyboard.KeyboardOrMouse.Keyboard)
//    public static void 奔跑1() {
//    }
//
//    @ListenMouseKeyboard(note = "左键", value = 513, keyboardOrMouse = ListenMouseKeyboard.KeyboardOrMouse.Mouse)
//    @ListenMouseKeyboard(note = "右键", value = 516, keyboardOrMouse = ListenMouseKeyboard.KeyboardOrMouse.Mouse)
//    @ListenMouseKeyboard(note = "滚轮", value = 522, keyboardOrMouse = ListenMouseKeyboard.KeyboardOrMouse.Mouse)
//    @ListenMouseKeyboard(note = "esc", value = 27, keyboardOrMouse = ListenMouseKeyboard.KeyboardOrMouse.Keyboard)
//    public static void 松开shift() {
//        if (getKeyStatus(VK_SHIFT) == true) {
//            myKeyRelease(VK_SHIFT);
//        }
//    }

//    @ListenMouseKeyboard(note = "大写", value = 20, press = false, keyboardOrMouse = ListenMouseKeyboard.KeyboardOrMouse.Keyboard)
//    @ListenMouseKeyboard(note = "侧键", value = 523, keyboardOrMouse = ListenMouseKeyboard.KeyboardOrMouse.Mouse)
//    public static void 按下w() {
//        robot.keyRelease(VK_W);
//        robot.keyPress(VK_W);
//    }

        @ListenMouseKeyboard(note = "w", value = 87, press = false,keyboardOrMouse = ListenMouseKeyboard.KeyboardOrMouse.Keyboard)
    public static TaskResult 按下w() {
        if(getKeyStatus(VK_SHIFT)==true){
            return new TaskResult(true);
        }else {
            return null;
        }
    }

    @ListenMouseKeyboard(note = "shift", value = 160, keyboardOrMouse = ListenMouseKeyboard.KeyboardOrMouse.Keyboard)
    public static void shift() {
        setKeyStatus(VK_SHIFT,true);
    }

    @ListenMouseKeyboard(note = "shift", value = 160,press = false, keyboardOrMouse = ListenMouseKeyboard.KeyboardOrMouse.Keyboard)
    public static void shift1() {
        setKeyStatus(VK_SHIFT,false);
    }


}
