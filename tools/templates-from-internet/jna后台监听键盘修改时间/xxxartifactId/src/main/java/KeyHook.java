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
 
import java.io.IOException;
import java.util.ArrayList;
 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.*;
 
/** Sample implementation of a low-level keyboard hook on W32. */
public class KeyHook {
	private static volatile boolean quit;
	private static HHOOK hhk;
	private static LowLevelKeyboardProc keyboardHook;
	
	//设置按键组合的vkCode,具体vkCode与键盘的对照表可百度
	private static ArrayList<Integer> keyList=new ArrayList<Integer>(){{add(65); add(83);add(68);add(38);}};
	private static ArrayList<Integer> inputList=new ArrayList<Integer>();
	private static Long listSetTime;
	private static int min=1;    //每次增加一分钟
	
	static void setJframe(){   //窗口界面
		JFrame jframe=new JFrame("修改系统时间");
	    jframe.setSize(300,200);
        jframe.setLocation(550, 250);
	    jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    JLabel field=new JLabel();
	    field.setBounds(30, 20, 400, 100);
	    field.setText("同时按下A，S，D，↑ 系统时间+1分钟");
        jframe.setLayout(null);
		JButton button=new JButton("关闭");
	    jframe.add(field);
        jframe.add(button);
		button.setBounds(60,120,150,30);
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
               System.exit(0);
			}
			});     
		jframe.setLayout(null);
        jframe.setResizable(true);
        jframe.setVisible(true);
	}
	
	public static void setList(int code,ArrayList<Integer> list){
		
		if(!list.contains(code)){
		 	if(list.size()<=keyList.size()){
		 		list.add(code);
			}
			else{
		 		list.remove(0);
		 		list.add(code);
			}
		 }
    }
	
    public static boolean isGetAllKey(){
		boolean b=true;
		for(int i=0;i<keyList.size();i++){
			if(!inputList.contains(keyList.get(i))){
				b=false;
				break;
			}
		}
		return b;
	}
	public static void main(String[] args) {
		setJframe();
		final User32 lib = User32.INSTANCE;
		HMODULE hMod = Kernel32.INSTANCE.GetModuleHandle(null);
		keyboardHook = new LowLevelKeyboardProc() {
			@Override
			public LRESULT callback(int nCode, WPARAM wParam, KBDLLHOOKSTRUCT info) {
				if (nCode >= 0) {
					switch(wParam.intValue()) {
						case WinUser.WM_KEYUP:
						case WinUser.WM_KEYDOWN:
						case WinUser.WM_SYSKEYUP:
						case WinUser.WM_SYSKEYDOWN:
							if (!keyList.contains(info.vkCode)) {
								inputList.clear();
							//	System.out.println("key="+info.vkCode);
							} else {
								if(inputList.size()==0){
									listSetTime=System.currentTimeMillis();
								}
								setList(info.vkCode, inputList);
								//System.err.println("in callback, key=" + info.vkCode);
								if (isGetAllKey()) {
									if(System.currentTimeMillis()-listSetTime<50) {
										quit = true;
									}
									else{
										inputList.clear();
									}
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
		
		new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(200);
					} catch (Exception e) {
					}
 
					//System.err.println("unhook and exit");
					//lib.UnhookWindowsHookEx(hhk);
					if(quit) {
						try {
							changeTime.plus_min(min);
						} catch (IOException e) {
							e.printStackTrace();
						}
						quit = false;
						inputList.clear();
						//System.exit(0);
					}
				}
			}
		}.start();
 
		// This bit never returns from GetMessage
		int result;
		MSG msg = new MSG();
		while ((result = lib.GetMessage(msg, null, 0, 0)) != 0) {
			if (result == -1) {
			//	System.err.println("error in get message");
				break;
			}
			else {
			//	System.err.println("got message");
				lib.TranslateMessage(msg);
				lib.DispatchMessage(msg);
			}
		}
		lib.UnhookWindowsHookEx(hhk);
	}
}