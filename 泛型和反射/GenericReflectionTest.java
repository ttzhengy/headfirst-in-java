import java.lang.reflect.*;
import java.util.*;

public class GenericReflectionTest {
    public static void main(String[] args) {        //从控制台获得类的名称 
        String name = null;
        if(args.length>0){
            name = args[0];
        }else{
            try (Scanner in = new Scanner(System.in)) {
                System.out.println("Enter class name (e.g. java.util.Collection)");
                name = in.nextLine();
            } catch (Exception e) {
                //TODO: handle exception
                e.printStackTrace();
            }
        }

        try {
            Class<?> cl = Class.forName(name);      //返回类的名称所对应的类
            printClass(cl);
            for (Method m : cl.getDeclaredMethods()) {      //返回包含当前类声明的方法的数组
                printMethod(m);
            }
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
    }

    public static void printClass(Class<?> cl) {        //打印类名
        System.out.print(cl);
        printTypes(cl.getTypeParameters(), "<", ",", ">", true);    //返回包含声明变量的类型的数组
        Type sc = cl.getGenericSuperclass();        //返回声明为超类的泛型类型
        if(sc != null){
            System.out.print(" extends ");
            printType(sc, false);
        }
        printTypes(cl.getGenericInterfaces(), "implements", ",", "", false);    //返回被声明为接口的泛型类型
        System.out.println();
    }

    public static void printMethod(Method m) {
        String name = m.getName();
        System.out.print(Modifier.toString(m.getModifiers()));      //打印方法的访问修饰符
        System.out.print(" ");
        printTypes(m.getParameterTypes(), "<", ",", ">", true);     //打印参数类型

        printType(m.getGenericReturnType(), false);     //打印返回的参数类型
        System.out.print(" ");
        System.out.print(name);
        System.out.print("(");
        printTypes(m.getGenericParameterTypes(), "", ",", "", false);       //打印方法的参数
        System.out.println(")");
    }

    public static void printTypes(Type[] types, String pre, String sep, String suf, boolean isDefinition) {
        if(pre.equals(" extends ") && Arrays.equals(types, new Type[]{Object.class})){
            return;         //如果该类直接继承Object类，则不需要打印后续内容
        }
        if(types.length>0){
            System.out.print(pre);      //前缀
        }
        for (int i = 0; i < types.length; i++) {
            if(i>0){
                System.out.print(sep);      //中缀
            }
            printType(types[i], isDefinition);
        }
        if(types.length>0){
            System.out.print(suf);      //后缀
        }
    }

    /*
    @param isDefinition 
    */
    public static void printType(Type type, boolean isDefinition) {
        if(type instanceof Class){              //类类型
            Class<?> t = (Class<?>) type;
            System.out.print(t.getName());
        }else if(type instanceof TypeVariable){     //类型变量类型
            TypeVariable<?> t = (TypeVariable<?>) type;
            System.out.print(t.getName());
            if(isDefinition){
                printTypes(t.getBounds(), " extends ", "&", "", false);     //返回类型变量的上限范围
            }
        }else if(type instanceof WildcardType){     //通配符类型
            WildcardType t = (WildcardType) type;
            System.out.print("?");
            printTypes(t.getUpperBounds(), " extends ", "&", "", false);
            printTypes(t.getLowerBounds(), " super ", "&", "", false);
        }else if(type instanceof ParameterizedType){    //参数化类型
            ParameterizedType t = (ParameterizedType) type;
            Type owner = t.getOwnerType();          //返回（当前）内部类的外部类类型
            if(owner != null){                  //若存在外部类，则先打印外部类
                printType(owner, false);       
                System.out.print(".");
            }
            printType(t.getRawType(), false);   //返回原始类型
            printTypes(t.getActualTypeArguments(), "<", ",", ">", false);   //返回实际声明时的类型参数
        }else if(type instanceof GenericArrayType){     //参数化类型或类型变量的数组容器类型
            GenericArrayType t = (GenericArrayType) type;
            System.out.print("");
            printType(t.getGenericComponentType(), isDefinition);       //返回数组容器类型
            System.out.print("[]");
        }
    }
}
