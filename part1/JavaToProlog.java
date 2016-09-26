
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

//***************************************************************************************************************************************************
public class JavaToProlog {
    //=================================================================================================================================================

    public static void convert(String fileName, Object... objects) throws Exception {
        // ...
        for (int i = 0; i < objects.length ; i++) {

            Class c1 = null;
            String name = null;
            String[] splitted = null;
            
            if (objects[i] instanceof Class) {
                c1 = (Class) objects[i];
                name = type(objects[i].toString());
                splitted = c1.toString().split(" ");
            } else if (objects[i] instanceof String) {
                try {
                    c1 = Class.forName(objects[i].toString());
                    name = type(c1.toString());
                    splitted = c1.toString().split(" ");
                } catch (ClassNotFoundException e) {
                    c1 = objects[i].getClass();
                    name = type(c1.toString());
                    splitted = c1.toString().split(" ");
                }
            }
            else
            { 
                c1 = objects[i].getClass();
                name = type(c1.toString());
                splitted = c1.toString().split(" ");
            }

            System.out.println(splitted[0] + "( " + name + " ) .");
            if (Modifier.isPublic(c1.getModifiers())) {
                System.out.println("public( " + name + " ) .");
            }
            if (Modifier.isFinal(c1.getModifiers())) {
                System.out.println("final( " + name + " ) .");
            }
            if (Modifier.isAbstract(c1.getModifiers())) {
                System.out.println("abstract( " + name + " ) .");
            }
            if (Modifier.isNative(c1.getModifiers())) {
                System.out.println("native( " + name + " ) .");
            }
            if (Modifier.isPrivate(c1.getModifiers())) {
                System.out.println("private( " + name + " ) .");
            }
            if (Modifier.isProtected(c1.getModifiers())) {
                System.out.println("protected( " + name + " ) .");
            }
            if (Modifier.isStatic(c1.getModifiers())) {
                System.out.println("static( " + name + " ) .");
            }
            if (Modifier.isStrict(c1.getModifiers())) {
                System.out.println("strict( " + name + " ) .");
            }
            if (Modifier.isSynchronized(c1.getModifiers())) {
                System.out.println("synchronized( " + name + " ) .");
            }
            if (Modifier.isTransient(c1.getModifiers())) {
                System.out.println("transient( " + name + " ) .");
            }
            if (Modifier.isVolatile(c1.getModifiers())) {
                System.out.println("volatile( " + name + " ) .");
            }
            if (c1.getSuperclass() != null) {
                System.out.println("extends( " + name + " , " + type(c1.getSuperclass().toString()) + " ) .");
            }
            for (int l = 0; l < c1.getInterfaces().length; l++) {
                if (!c1.isInterface()) {
                    System.out.println("implements( " + name + " , " + type(c1.getInterfaces()[l].toString()) + " ) .");
                } else {
                    System.out.println("extends( " + name + " , " + type(c1.getInterfaces()[l].toString()) + " ) .");
                }
            }
            Class[] classArray = c1.getClasses();
            if (classArray.length != 0) {
                System.out.println();
                for (int j = 0; j < classArray.length; j++) {
                    System.out.println("innerClass( " + name + " , " + type(classArray[j].getSimpleName()) + " ) .");
                }
            }

            System.out.println();

            if (c1.getConstructors().length != 0) {
                System.out.println("numberOfConstructors( " + name + " , " + c1.getConstructors().length + " ) .");
                System.out.println();
                Constructor[] cArray = c1.getConstructors();
                Field[] methods = c1.getDeclaredFields();

                for (int j = 0; j < methods.length; j++) {
                    System.out.println("field( " + name + " , " + type(methods[j].getName()) + " ) .");
                    if (Modifier.isPublic(methods[j].getModifiers())) {
                        System.out.println("public( " + name + " , " + type(methods[j].getName()) + " ) .");
                    }
                    if (Modifier.isProtected(methods[j].getModifiers())) {
                        System.out.println("protected( " + name + " , " + type(methods[j].getName()) + " ) .");
                    }
                    if (Modifier.isPrivate(methods[j].getModifiers())) {
                        System.out.println("private( " + name + " , " + type(methods[j].getName()) + " ) .");
                    }
                    if (Modifier.isSynchronized(methods[j].getModifiers())) {
                        System.out.println("synchronized( " + name + " , " + type(methods[j].getName()) + " ) .");
                    }
                    if (Modifier.isTransient(methods[j].getModifiers())) {
                        System.out.println("transient( " + name + " , " + type(methods[j].getName()) + " ) .");
                    }
                    if (Modifier.isVolatile(methods[j].getModifiers())) {
                        System.out.println("volatile( " + name + " , " + type(methods[j].getName()) + " ) .");
                    }
                    if (Modifier.isNative(methods[j].getModifiers())) {
                        System.out.println("native( " + name + " , " + type(methods[j].getName()) + " ) .");
                    }
                    if (Modifier.isStatic(methods[j].getModifiers())) {
                        System.out.println("static( " + name + " , " + type(methods[j].getName()) + " ) .");
                    }
                    if (Modifier.isStrict(methods[j].getModifiers())) {
                        System.out.println("strict( " + name + " , " + type(methods[j].getName()) + " ) .");
                    }
                    if (Modifier.isFinal(methods[j].getModifiers())) {
                        System.out.println("final( " + name + " , " + type(methods[j].getName()) + " ) .");
                    }
                    if (Modifier.isAbstract(methods[j].getModifiers())) {
                        System.out.println("abstract( " + name + " , " + type(methods[j].getName()) + " ) .");
                    }
                    if (Modifier.isInterface(methods[j].getModifiers())) {
                        System.out.println("interface( " + name + " , " + type(methods[j].getName()) + " ) .");
                    }
                    System.out.println("type( " + name + " , " + type(methods[j].getName()) + " , " + type(methods[j].getType().getSimpleName()) + " ) .");
                    System.out.println();
                }
            }

            Method[] mArray = c1.getDeclaredMethods();
            for (int j = 0; j < mArray.length; j++) {
                System.out.println("method( " + name + " , " + type(mArray[j].getName()) + " ) .");
                if (Modifier.isPublic((mArray[j].getModifiers()))) {
                    System.out.println("public( " + name + " , " + type(mArray[j].getName()) + " ) .");
                }
                if (Modifier.isStatic((mArray[j].getModifiers()))) {
                    System.out.println("static( " + name + " , " + type(mArray[j].getName()) + " ) .");
                }
                if (Modifier.isAbstract((mArray[j].getModifiers()))) {
                    System.out.println("abstract( " + name + " , " + type(mArray[j].getName()) + " ) .");
                }
                if (Modifier.isPrivate((mArray[j].getModifiers()))) {
                    System.out.println("private( " + name + " , " + type(mArray[j].getName()) + " ) .");
                }
                if (Modifier.isProtected((mArray[j].getModifiers()))) {
                    System.out.println("protected( " + name + " , " + type(mArray[j].getName()) + " ) .");
                }
                if (Modifier.isInterface((mArray[j].getModifiers()))) {
                    System.out.println("interface( " + name + " , " + type(mArray[j].getName()) + " ) .");
                }
                if (Modifier.isStrict((mArray[j].getModifiers()))) {
                    System.out.println("strict( " + name + " , " + type(mArray[j].getName()) + " ) .");
                }
                if (Modifier.isNative((mArray[j].getModifiers()))) {
                    System.out.println("native( " + name + " , " + type(mArray[j].getName()) + " ) .");
                }
                if (Modifier.isVolatile((mArray[j].getModifiers()))) {
                    System.out.println("volatile( " + name + " , " + type(mArray[j].getName()) + " ) .");
                }
                if (Modifier.isTransient((mArray[j].getModifiers()))) {
                    System.out.println("transient( " + name + " , " + type(mArray[j].getName()) + " ) .");
                }
                if (Modifier.isSynchronized((mArray[j].getModifiers()))) {
                    System.out.println("synchronized( " + name + " , " + type(mArray[j].getName()) + " ) .");
                }
                if (Modifier.isFinal((mArray[j].getModifiers()))) {
                    System.out.println("final( " + name + " , " + type(mArray[j].getName()) + " ) .");
                }
                System.out.println("type( " + name + " , " + type(mArray[j].getName()) + " , " + type(mArray[j].getReturnType().getSimpleName()) + " ) .");

                System.out.println("numberOfParameters( " + name + " , " + type(mArray[j].getName()) + " , " + mArray[j].getParameterTypes().length + " ) .");
                for (int k = 0; k < mArray[j].getParameterTypes().length; k++) {
                    System.out.println("type( " + name + " , " + type(mArray[j].getName()) + " , p" + k + " , " + type(mArray[j].getParameterTypes()[k].getSimpleName()) + " ) .");
                }
                System.out.println("numberOfExceptionsThrown( " + name + " , " + type(mArray[j].getName()) + " , " + mArray[j].getExceptionTypes().length + " ) .");
                for (int m = 0; m < mArray[j].getExceptionTypes().length; m++) {
                    System.out.println("throws( " + name + " , " + type(mArray[j].getName()) + " , " + type(mArray[j].getExceptionTypes()[m].getSimpleName()) + " ) .");
                }
                System.out.println();
            }

            System.out.println("%-------------------------------------");
        }
    }

  //=================================================================================================================================================
    public boolean equals(Object obj) {
        return (this == obj);
    }

    public static String lowerFirst(String str) {
        str = Character.toLowerCase(str.charAt(0)) + str.substring(1);
        return str;
    }

    public static String type(String str) {
        String[] blank = str.split(" ");
        if (blank.length == 1) {
            String[] type = str.split("\\.");
            if (type.length == 1) {
                String[] bracket = str.split("\\[");
                if (bracket.length == 1) {
                    return lowerFirst(str);
                } else if (bracket.length == 2) {
                    return lowerFirst(bracket[0]) + "ARRAY";
                } else {
                    return lowerFirst(bracket[0]) + "ARRAYARRAY";
                }
            } else if (type.length == 3) {
                String[] bracket = type[2].split("\\[");
                if (bracket.length == 1) {
                    return lowerFirst(type[2]);
                } else if (bracket.length == 2) {
                    return lowerFirst(bracket[0]) + "ARRAY";
                } else {
                    return lowerFirst(bracket[0]) + "ARRAYARRAY";
                }
            }
        } else if (blank.length == 2) {
            String[] type = blank[1].split("\\.");
            if (type.length == 1) {
                String[] bracket = blank[1].split("\\[");
                if (bracket.length == 1) {
                    return lowerFirst(blank[1]);
                } else if (bracket.length == 2) {
                    return lowerFirst(blank[1]) + "ARRAY";
                } else {
                    return lowerFirst(blank[1]) + "ARRAYARRAY";
                }
            } else if (type.length == 3) {
                String[] bracket = type[2].split("\\[");
                if (bracket.length == 1) {
                    return lowerFirst(type[2]);
                } else if (bracket.length == 2) {
                    return lowerFirst(bracket[0]) + "ARRAY";
                } else {
                    return lowerFirst(bracket[0]) + "ARRAYARRAY";
                }
            }
        }
    //str = Character.toLowerCase(str.charAt(0)) + str.substring(1);
        //return str;
        System.out.println(str);
        return null;
    }

    public static void main(String[] args) throws Exception {
        PrintStream out = new PrintStream(new FileOutputStream("output.pl"));
        System.setOut(out);
        //-----------------------------------------------------------------------------------------------------------------------------------------------

        if (args.length == 0) {
            args = new String[]{"DesignDescription.pl"};
        }

    //-----------------------------------------------------------------------------------------------------------------------------------------------
        String fileName = args[0];

        Object objects[] = {
                            "Marker", 
                            Unit.class, 
                            "Entity", 
                            Agent.class, 
                            new AutonomousAgent(), 
                            new KeyboardAgent(), 
                            "Target", 
                            new Obstacle(), 
                            Environment.class, 
                            "java.io.Serializable", 
                            "java.lang.Comparable",
                            "An ordinary string", 
                            1.1,
                            Thread.class
        };

        System.out.println("% " + objects.length + " objects have been described in Prolog syntax.");
        System.out.println("%------------------------------------------------------");

        convert(fileName, objects);

    //-----------------------------------------------------------------------------------------------------------------------------------------------
        //-----------------------------------------------------------------------------------------------------------------------------------------------
    }

  //=================================================================================================================================================
}
