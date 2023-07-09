package org.example;

import org.example.type.ChildType;
import org.example.type.StudentType;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {
        int INACTIVE = 1<< 31;

        int i = ~INACTIVE;
        System.out.println(INACTIVE);
        System.out.println(Integer.toBinaryString(INACTIVE));
        System.out.println(i);
        System.out.println(Integer.toBinaryString(i));

//        explainType();
    }


    public static void explainType() throws Exception {
        TypeVariable<Class<StudentType>>[] typeParameters = StudentType.class.getTypeParameters();
        for (TypeVariable tv :        typeParameters) {
            System.out.println(tv.getName());
            System.out.println(tv.getBounds());
        }
        Type[] genericInterfaces = StudentType.class.getGenericInterfaces();
        if(genericInterfaces[0] instanceof ParameterizedType){
            ParameterizedType type = (ParameterizedType) genericInterfaces[0];
            Type rawType = type.getRawType();
            Type[] actualTypeArguments = type.getActualTypeArguments();
            Type actualTypeArgument = actualTypeArguments[0];
            Class clz = (Class)actualTypeArgument;
            System.out.println(actualTypeArgument);
            if(clz.isAssignableFrom(Integer.class)){
                System.out.println("aa");
            }
            System.out.println(rawType);
        }

//        System.out.println("////");
        Method getMM = StudentType.class.getMethod("getMM", Object.class);
        TypeVariable<Method>[] typeParameters1 = getMM.getTypeParameters();

        StudentType.class.getField("demoF").getType();
        StudentType.class.getField("demoF").getGenericType();

        Class<?>[] parameterTypes = getMM.getParameterTypes();
        for (Class tv :
                parameterTypes) {
            System.out.println(tv);
        }

        Type genericSuperclass = StudentType.class.getGenericSuperclass();
        System.out.println(genericSuperclass);

    }
}
