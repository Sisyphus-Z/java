package base;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HMODULE;
import com.sun.jna.platform.win32.WinDef.LRESULT;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.platform.win32.WinDef.LPARAM;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.platform.win32.WinUser.HHOOK;
import com.sun.jna.platform.win32.WinUser.KBDLLHOOKSTRUCT;
import com.sun.jna.platform.win32.WinUser.LowLevelKeyboardProc;

import com.sun.jna.platform.win32.WinUser.MSG;

import java.util.*;

import static base.Controller.printKey;
import static base.Controller.recorder;


/**
 * Sample implementation of a low-level keyboard hook on W32.
 */
public class KeyboardHook {
    private HHOOK hhk;
    private LowLevelKeyboardProc keyboardHook;
    private InputInfo inputInfoActualTemp = new InputInfo();
    private StringBuilder printText = new StringBuilder();
    private Set<Integer> userInput = new HashSet<>(Arrays.asList(0, 1, 32, 33, 128, 129));
    private Set<Integer> press = new HashSet<>(Arrays.asList(256, 260));

    public void run() {

        final User32 lib = User32.INSTANCE;
        HMODULE hMod = Kernel32.INSTANCE.GetModuleHandle(null);

        keyboardHook = new LowLevelKeyboardProc() {
            @Override
            public LRESULT callback(int nCode, WPARAM wParam, KBDLLHOOKSTRUCT info) {

                if (nCode == 0) {


                    if (printKey == true) {
                        printText.setLength(0);
                        printText.append("--------------------").append("\n");
                        if (info.flags == 0 || info.flags == 1 || info.flags == 32 || info.flags == 128 || info.flags == 129) {
                        } else {
                            printText.append("(not user input) ");
                        }
                        printText.append("KeyboardKey: ").append(info.vkCode);
                        printText.append("\n").append("info.flags: ").append(info.flags);
                        printText.append("\n").append("wParam.intValue(): ").append(wParam.intValue());
                        System.out.println(printText);
                    }


                    //开关相关
                    if (Controller.switchMmap.containsKey(info.vkCode)) {
                        if (Controller.switchMmap.get(info.vkCode).equals(ListenBar.OnOrOff.off)) {
                            for (MyThread thread : Controller.threadList) {
                                thread.mySuspend();
                            }
                            Controller.listenSwitch = false;

                            System.out.println("program off");
                            return new LRESULT(1);
                        } else if (Controller.switchMmap.get(info.vkCode).equals(ListenBar.OnOrOff.on)) {
                            for (MyThread thread : Controller.threadList) {
                                if (thread.defaultState == MyThread.State.on) {
                                    thread.myResume();
                                }
                            }
                            Controller.listenSwitch = true;

                            System.out.println("program on");
                            return new LRESULT(1);
                        }
                    }
                    if (Controller.listenSwitch == false) {
                        return null;
                    }

                    inputInfoActualTemp.resetProperty();
                    inputInfoActualTemp.value = info.vkCode;
                    inputInfoActualTemp.hookInputInfo.flags = info.flags;
                    inputInfoActualTemp.keyboardOrMouse = ListenMouseKeyboard.KeyboardOrMouse.Keyboard;

                    if (userInput.contains(info.flags)) {
                        inputInfoActualTemp.userInput = true;
                    } else {
                        inputInfoActualTemp.userInput = false;
                    }
                    if (press.contains(wParam.intValue())) {
                        inputInfoActualTemp.press = true;
                    } else {
                        inputInfoActualTemp.press = false;
                    }

                    if (recorder != null) {
                        recorder.inputInfoActualTemp = inputInfoActualTemp;
                        Controller.do1.doTask(recorder);
                        if (recorder.taskResult != null && recorder.taskResult.intercept == true) {
                            return new LRESULT(1);
                        }
                    }

                    if (Controller.taskMmap.containsKey(inputInfoActualTemp)) {
                        List<TaskInfo> taskInfoList = Controller.taskMmap.get(inputInfoActualTemp);
                        for (TaskInfo taskInfo : taskInfoList) {
                            taskInfo.inputInfoActualTemp = inputInfoActualTemp;
                            Controller.do1.doTask(taskInfo);
                        }
                        for (TaskInfo taskInfo : taskInfoList) {
                            if (taskInfo.intercept == true) {
                                return new LRESULT(1);
                            }
                            if(taskInfo.taskResult!=null&&taskInfo.taskResult.intercept==true){
                                return new LRESULT(1);
                            }
                        }
                    }
                }


                Pointer ptr = info.getPointer();
                long peer = Pointer.nativeValue(ptr);
                return lib.CallNextHookEx(hhk, nCode, wParam, new LPARAM(peer));
            }
        };

        hhk = lib.SetWindowsHookEx(WinUser.WH_KEYBOARD_LL, keyboardHook, hMod, 0);


        // This bit never returns from GetMessage
        MSG msg = new MSG();
        lib.GetMessage(msg, null, 0, 0);

        System.out.println("base.KeyboardHook run() Method finished");
    }


}