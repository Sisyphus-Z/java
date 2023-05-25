import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class ScanFunction {
    public static void run(Class class1, Map<String, Utiliy> mapJna, Map<String, Utiliy> mapJintellitype, Map<Integer, String> mapListenBar, ArrayList<Thread> threadList){


        //Class<Functions> classFunctions = Functions.class;
        Method[] methods = class1.getMethods();


        try {
            Do.obj1 = class1.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("创建Functions实例对象失败");
            System.exit(0);
        }

        //按下还是松开,鼠标不适用
        Map<Boolean,String> map1=new HashMap();
        map1.put(true,"256");
        map1.put(false,"257");

        //是否是用户输入
        Map<Boolean,String> map2=new HashMap();
        map2.put(true,"userInput");
        map2.put(false,"!userInput");


        for (Method method : methods) {

            if (method.isAnnotationPresent(ListenMouseKeyboard.class)) {
                method.setAccessible(true);
                ListenMouseKeyboard k111 = method.getAnnotation(ListenMouseKeyboard.class);

                System.out.println("已扫描方法"+method.getName());
                Utiliy u111 = new Utiliy();
                u111.method = method;
                u111.immediately = k111.immediately();
                u111.intercept=k111.intercept();
                mapJna.put(k111.value()+"_"+map1.get(k111.press())+"_"+map2.get(k111.userInput()), u111);
            }

            //处理重复注解
            if (method.isAnnotationPresent(ListenMouseKeyboards.class)) {
                method.setAccessible(true);
                ListenMouseKeyboards ks111 = method.getAnnotation(ListenMouseKeyboards.class);

                for(ListenMouseKeyboard k111 : ks111.value()){
                    System.out.println("已扫描方法"+method.getName());
                    Utiliy u111 = new Utiliy();
                    u111.method = method;
                    u111.immediately = k111.immediately();
                    u111.intercept=k111.intercept();
                    mapJna.put(k111.value()+"_"+map1.get(k111.press())+"_"+map2.get(k111.userInput()), u111);
                }
            }

            //jintellitype部分
            if (method.isAnnotationPresent(JintellitypeListen.class)) {
                method.setAccessible(true);
                JintellitypeListen j111 = method.getAnnotation(JintellitypeListen.class);

                System.out.println("已扫描方法"+method.getName());
                Utiliy u111 = new Utiliy();
                u111.method = method;
                u111.immediately = j111.immediately();
                mapJintellitype.put(j111.modifier()+"_"+j111.keycode(), u111);
            }

            //处理重复
            if (method.isAnnotationPresent(JintellitypeListens.class)) {
                method.setAccessible(true);
                JintellitypeListens js111 = method.getAnnotation(JintellitypeListens.class);

                for(JintellitypeListen j111 : js111.value()){
                    System.out.println("已扫描方法"+method.getName());
                    Utiliy u111 = new Utiliy();
                    u111.method = method;
                    u111.immediately = j111.immediately();
                    mapJintellitype.put(j111.modifier()+"_"+j111.keycode(), u111);
                }
            }




        }
        System.out.println(mapJna);
        System.out.println(mapJintellitype);


        Field[] fieldsChild=class1.getDeclaredFields();
        Field[] fieldsParent=class1.getSuperclass().getDeclaredFields();
        Field[] fieldsChildAndParent = mergeFields(fieldsChild, fieldsParent);

        for(Field field:fieldsChildAndParent){
            if(field.isAnnotationPresent(ListenBar.class)){
                ListenBar listenBar =field.getAnnotation(ListenBar.class);
                try {
                    if(listenBar.off()==true&&listenBar.threadList()!=true){
                        mapListenBar.put(Integer.parseInt(field.get(class1).toString()),"off");
                    }else if(listenBar.off()==false&&listenBar.threadList()!=true){
                        mapListenBar.put(Integer.parseInt(field.get(class1).toString()),"on");
                    }
                }catch (Exception e){}

                try{
                    if(listenBar.threadList()==true){
                        threadList.addAll((List<Thread>)field.get(class1));
                    }
                }catch (Exception e){}
            }

        }

        System.out.println(mapListenBar);
        System.out.println(threadList);



    }

    private static Field[] mergeFields(Field[] arr1, Field[] arr2) {
        int length1 = arr1.length;
        int length2 = arr2.length;
        Field[] merged = Arrays.copyOf(arr1, length1 + length2);  // 创建一个新数组，长度为两个数组之和
        System.arraycopy(arr2, 0, merged, length1, length2);  // 将 arr2 数组的元素复制到 merged 数组中
        return merged;
    }
}
