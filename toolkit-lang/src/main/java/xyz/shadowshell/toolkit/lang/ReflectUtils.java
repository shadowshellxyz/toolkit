/*
 * Copyright (c) 2010-2014 lijunlin All Rights Reserved.
 * 本软件源代码版权归作者所有,未经许可不得任意复制与传播.
 */
package xyz.shadowshell.toolkit.lang;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ReflectUtils
 *
 * @author shadow
 */
public final class ReflectUtils {

    /**
     * Empty class array.
     */
    public static final Class<?>[] NO_PARAMETERS = new Class[0];

    public static final String METHOD_GET_PREFIX = "get";
    public static final String METHOD_IS_PREFIX = "is";
    public static final String METHOD_SET_PREFIX = "set";

    private static Method _getMethod0;

    static {
        try {
            _getMethod0 = Class.class.getDeclaredMethod("getMethod0", String.class, Class[].class);
            _getMethod0.setAccessible(true);
        } catch (Exception ignore) {
            try {
                _getMethod0 = Class.class.getMethod("getMethod", String.class, Class[].class);
            } catch (Exception ignored) {
                _getMethod0 = null;
            }
        }
    }

    private ReflectUtils() {
    }

    /**
     * Invokes private <code>Class.getMethod0()</code> without throwing <code>NoSuchMethodException</code>.
     * Returns only public methods or <code>null</code> if method not found. Since no exception is
     * throwing, it works faster.
     *
     * @param clazz          class to inspect
     * @param name           name of method to find
     * @param parameterTypes parameter types
     * @return founded method, or null
     */
    public static Method getMethod0(Class<?> clazz, String name, Class<?>... parameterTypes) {
        try {
            return (Method) _getMethod0.invoke(clazz, name, parameterTypes);
        } catch (Exception ignore) {
            return null;
        }
    }

    /**
     * Returns method from an object, matched by name. This may be considered as
     * a slow operation, since methods are matched one by one.
     * Returns only accessible methods.
     * Only first method is matched.
     *
     * @param clazz      class to examine
     * @param methodName Full name of the method.
     * @return null if method not found
     */
    public static Method findMethod(Class<?> clazz, String methodName) {
        return findDeclaredMethod(clazz, methodName, true);
    }

    /**
     * @see #findMethod(Class, String)
     */
    public static Method findDeclaredMethod(Class<?> clazz, String methodName) {
        return findDeclaredMethod(clazz, methodName, false);
    }

    private static Method findDeclaredMethod(Class<?> clazz, String methodName, boolean publicOnly) {
        if ((methodName == null) || (clazz == null)) {
            return null;
        }
        Method[] ms = publicOnly ? clazz.getMethods() : clazz.getDeclaredMethods();
        for (Method m : ms) {
            if (m.getName().equals(methodName)) {
                return m;
            }
        }
        return null;
    }

    /**
     * Returns classes from array of specified objects.
     */
    public static Class<?>[] getClasses(Object... objects) {
        if (objects == null) {
            return null;
        }
        Class<?>[] result = new Class[objects.length];
        for (int i = 0; i < objects.length; i++) {
            if (objects[i] != null) {
                result[i] = objects[i].getClass();
            }
        }
        return result;
    }

    /**
     * Invokes accessible method of an object.
     *
     * @param clazz        class that contains method
     * @param obj          object to execute
     * @param methodName   method name to invoke
     * @param paramClasses classes of parameters
     * @param params       parameters
     */
    public static Object invoke(Class<?> c, Object obj, String methodName, Class<?>[] paramClasses, Object[] params)
            throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Method method = c.getMethod(methodName, paramClasses);
        return method.invoke(obj, params);
    }

    /**
     * Invokes static method.
     */
    public static Object invoke(Class<?> c, String methodName, Class<?>[] paramClasses, Object[] params)
            throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Method m = c.getMethod(methodName, paramClasses);
        return m.invoke(null, params);
    }

    /**
     * Invokes accessible method of an object.
     *
     * @param obj          object
     * @param methodName   name of the objects method
     * @param params       method parameters
     * @param paramClasses method parameter types
     */
    public static Object invoke(Object obj, String methodName, Class<?>[] paramClasses, Object[] params)
            throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Method m = obj.getClass().getMethod(methodName, paramClasses);
        return m.invoke(obj, params);
    }

    /**
     * Invokes accessible method of an object without specifying parameter types.
     *
     * @param obj        object
     * @param methodName method of an object
     * @param params     method parameters
     */
    public static Object invoke(Object obj, String methodName, Object... params)
            throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class<?>[] paramClass = getClasses(params);
        return invoke(obj, methodName, paramClass, params);
    }

    public static Object invoke(Class<?> c, Object obj, String method, Object... params)
            throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class<?>[] paramClass = getClasses(params);
        return invoke(c, obj, method, paramClass, params);
    }

    /**
     * Invokes static method.
     */
    public static Object invoke(Class<?> c, String method, Object... params)
            throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class<?>[] paramClass = getClasses(params);
        return invoke(c, null, method, paramClass, params);
    }

    /**
     * Invokes any method of a class, even private ones.
     *
     * @param c            class to examine
     * @param obj          object to inspect
     * @param method       method to invoke
     * @param paramClasses parameter types
     * @param params       parameters
     */
    public static Object invokeDeclared(Class<?> c, Object obj, String method, Class<?>[] paramClasses, Object[] params)
            throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Method m = c.getDeclaredMethod(method, paramClasses);
        m.setAccessible(true);
        return m.invoke(obj, params);
    }

    public static Object invokeDeclared(Class<?> c, String method, Class<?>[] paramClasses, Object[] params)
            throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Method m = c.getDeclaredMethod(method, paramClasses);
        m.setAccessible(true);
        return m.invoke(null, params);
    }

    /**
     * Invokes any method of a class suppressing java access checking.
     *
     * @param obj          object to inspect
     * @param method       method to invoke
     * @param paramClasses parameter types
     * @param params       parameters
     */
    public static Object invokeDeclared(Object obj, String method, Class<?>[] paramClasses, Object[] params)
            throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Method m = obj.getClass().getDeclaredMethod(method, paramClasses);
        m.setAccessible(true);
        return m.invoke(obj, params);
    }

    public static Object invokeDeclared(Object obj, String method, Object... params)
            throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class<?>[] paramClass = getClasses(params);
        return invokeDeclared(obj, method, paramClass, params);
    }

    public static Object invokeDeclared(Class<?> c, Object obj, String method, Object... params)
            throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class<?>[] paramClass = getClasses(params);
        return invokeDeclared(c, obj, method, paramClass, params);
    }

    public static Object invokeDeclared(Class<?> c, String method, Object... params)
            throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class<?>[] paramClass = getClasses(params);
        return invokeDeclared(c, null, method, paramClass, params);
    }

    /**
     * Determines if first class match the destination and simulates kind
     * of <code>instanceof</code>. All subclasses and interface of first class
     * are examined against second class. Method is not symmetric.
     */
    public static boolean isSubclass(Class<?> thisClass, Class<?> target) {
        if (target.isInterface()) {
            return isInterfaceImpl(thisClass, target);
        }
        for (Class<?> x = thisClass; x != null; x = x.getSuperclass()) {
            if (x == target) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns <code>true</code> if provided class is interface implementation.
     */
    public static boolean isInterfaceImpl(Class<?> thisClass, Class<?> targetInterface) {
        for (Class<?> x = thisClass; x != null; x = x.getSuperclass()) {
            Class<?>[] interfaces = x.getInterfaces();
            for (Class<?> i : interfaces) {
                if (i == targetInterface) {
                    return true;
                }
                if (isInterfaceImpl(i, targetInterface)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Dynamic version of <code>instanceof</code>.
     *
     * @param o      object to match
     * @param target target class
     * @return <code>true</code> if object is an instance of target class
     */
    public static boolean isInstanceOf(Object o, Class<?> target) {
        return isSubclass(o.getClass(), target);
    }

    /**
     * Returns array of all methods that are accessible from given class.
     *
     * @see #getAccessibleMethods(Class, Class)
     */
    public static Method[] getAccessibleMethods(Class<?> clazz) {
        return getAccessibleMethods(clazz, Object.class);
    }

    /**
     * Returns array of all methods that are accessible from given class, upto limit
     * (usually <code>Object.class</code>). Abstract methods are ignored.
     */
    public static Method[] getAccessibleMethods(Class<?> clazz, Class<?> limit) {
        Package topPackage = clazz.getPackage();
        List<Method> methodList = new ArrayList<Method>();
        int topPackageHash = topPackage == null ? 0 : topPackage.hashCode();
        boolean top = true;
        do {
            if (clazz == null) {
                break;
            }
            Method[] declaredMethods = clazz.getDeclaredMethods();
            for (Method method : declaredMethods) {
                if (Modifier.isVolatile(method.getModifiers())) {
                    continue;
                }
                //				if (Modifier.isAbstract(method.getModifiers())) {
                //					continue;
                //				}
                if (top == true) {                // add all top declared methods
                    methodList.add(method);
                    continue;
                }
                int modifier = method.getModifiers();
                if (Modifier.isPrivate(modifier) == true) {
                    continue;                                        // ignore super private methods
                }
                if (Modifier.isAbstract(modifier) == true) {        // ignore super abstract methods
                    continue;
                }
                if (Modifier.isPublic(modifier) == true) {
                    addMethodIfNotExist(methodList, method);        // add super public methods
                    continue;
                }
                if (Modifier.isProtected(modifier) == true) {
                    addMethodIfNotExist(methodList, method);        // add super protected methods
                    continue;
                }
                // add super default methods from the same package
                Package pckg = method.getDeclaringClass().getPackage();
                int pckgHash = pckg == null ? 0 : pckg.hashCode();
                if (pckgHash == topPackageHash) {
                    addMethodIfNotExist(methodList, method);
                }
            }
            top = false;
        } while ((clazz = clazz.getSuperclass()) != limit);

        Method[] methods = new Method[methodList.size()];
        for (int i = 0; i < methods.length; i++) {
            methods[i] = methodList.get(i);
        }
        return methods;
    }

    private static void addMethodIfNotExist(List<Method> allMethods, Method newMethod) {
        for (Method m : allMethods) {
            if (compareSignatures(m, newMethod) == true) {
                return;
            }
        }
        allMethods.add(newMethod);
    }

    public static Field[] getAccessibleFields(Class<?> clazz) {
        return getAccessibleFields(clazz, Object.class);
    }

    public static Field[] getAccessibleFields(Class<?> clazz, Class<?> limit) {
        Package topPackage = clazz.getPackage();
        List<Field> fieldList = new ArrayList<Field>();
        int topPackageHash = topPackage == null ? 0 : topPackage.hashCode();
        boolean top = true;
        do {
            if (clazz == null) {
                break;
            }
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field field : declaredFields) {
                if (top == true) {                // add all top declared fields
                    fieldList.add(field);
                    continue;
                }
                int modifier = field.getModifiers();
                if (Modifier.isPrivate(modifier) == true) {
                    continue;                                        // ignore super private fields
                }
                if (Modifier.isPublic(modifier) == true) {
                    addFieldIfNotExist(fieldList, field);            // add super public methods
                    continue;
                }
                if (Modifier.isProtected(modifier) == true) {
                    addFieldIfNotExist(fieldList, field);            // add super protected methods
                    continue;
                }
                // add super default methods from the same package
                Package pckg = field.getDeclaringClass().getPackage();
                int pckgHash = pckg == null ? 0 : pckg.hashCode();
                if (pckgHash == topPackageHash) {
                    addFieldIfNotExist(fieldList, field);
                }
            }
            top = false;
        } while ((clazz = clazz.getSuperclass()) != limit);

        Field[] fields = new Field[fieldList.size()];
        for (int i = 0; i < fields.length; i++) {
            fields[i] = fieldList.get(i);
        }
        return fields;
    }

    private static void addFieldIfNotExist(List<Field> allFields, Field newField) {
        for (Field f : allFields) {
            if (compareSignatures(f, newField) == true) {
                return;
            }
        }
        allFields.add(newField);
    }

    public static Method[] getSupportedMethods(Class<?> clazz) {
        return getSupportedMethods(clazz, Object.class);
    }

    /**
     * Returns a <code>Method</code> array of the methods to which instances of the specified
     * respond except for those methods defined in the class specified by limit
     * or any of its superclasses. Note that limit is usually used to eliminate
     * them methods defined by <code>java.lang.Object</code>. If limit is <code>null</code> then all
     * methods are returned.
     */
    public static Method[] getSupportedMethods(Class<?> clazz, Class<?> limit) {
        ArrayList<Method> supportedMethods = new ArrayList<Method>();
        for (Class<?> c = clazz; c != limit; c = c.getSuperclass()) {
            Method[] methods = c.getDeclaredMethods();
            for (Method method : methods) {
                boolean found = false;
                for (Method supportedMethod : supportedMethods) {
                    if (compareSignatures(method, supportedMethod)) {
                        found = true;
                        break;
                    }
                }
                if (found == false) {
                    supportedMethods.add(method);
                }
            }
        }
        return supportedMethods.toArray(new Method[supportedMethods.size()]);
    }

    public static Field[] getSupportedFields(Class<?> clazz) {
        return getSupportedFields(clazz, Object.class);
    }

    public static Field[] getSupportedFields(Class<?> clazz, Class<?> limit) {
        ArrayList<Field> supportedFields = new ArrayList<Field>();
        for (Class<?> c = clazz; c != limit; c = c.getSuperclass()) {
            Field[] fields = c.getDeclaredFields();
            for (Field field : fields) {
                boolean found = false;
                for (Field supportedField : supportedFields) {
                    if (compareSignatures(field, supportedField)) {
                        found = true;
                        break;
                    }
                }
                if (found == false) {
                    supportedFields.add(field);
                }
            }
        }
        return supportedFields.toArray(new Field[supportedFields.size()]);
    }

    /**
     * Compares method declarations: signature and return types.
     */
    public static boolean compareDeclarations(Method first, Method second) {
        if (first.getReturnType() != second.getReturnType()) {
            return false;
        }
        return compareSignatures(first, second);
    }

    /**
     * Compares method signatures: names and parameters.
     */
    public static boolean compareSignatures(Method first, Method second) {
        if (first.getName().equals(second.getName()) == false) {
            return false;
        }
        return compareParameters(first.getParameterTypes(), second.getParameterTypes());
    }

    /**
     * Compares constructor signatures: names and parameters.
     */
    public static boolean compareSignatures(Constructor<?> first, Constructor<?> second) {
        if (first.getName().equals(second.getName()) == false) {
            return false;
        }
        return compareParameters(first.getParameterTypes(), second.getParameterTypes());
    }

    public static boolean compareSignatures(Field first, Field second) {
        return first.getName().equals(second.getName());
    }

    /**
     * Compares method or ctor parameters.
     */
    public static boolean compareParameters(Class<?>[] first, Class<?>[] second) {
        if (first.length != second.length) {
            return false;
        }
        for (int i = 0; i < first.length; i++) {
            if (first[i] != second[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Suppress access check against a reflection object. SecurityException is silently ignored.
     * Checks first if the object is already accessible.
     */
    public static void forceAccess(AccessibleObject accObject) {
        if (accObject.isAccessible()) {
            return;
        }
        try {
            accObject.setAccessible(true);
        } catch (SecurityException sex) {
            // ignore
        }
    }

    /**
     * Returns <code>true</code> if class member is public.
     */
    public static boolean isPublic(Member member) {
        return Modifier.isPublic(member.getModifiers());
    }

    /**
     * Returns <code>true</code> if class member is public and if its declaring class is also public.
     */
    public static boolean isPublicPublic(Member member) {
        if (Modifier.isPublic(member.getModifiers()) == true) {
            if (Modifier.isPublic(member.getDeclaringClass().getModifiers())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns <code>true</code> if class is public.
     */
    public static boolean isPublic(Class<?> c) {
        return Modifier.isPublic(c.getModifiers());
    }

    /**
     * Creates new instances including for common mutable classes that do not have a default constructor.
     * more user-friendly. It examines if class is a map, list,
     * String, Character, Boolean or a Number. Immutable instances are cached and not created again.
     * Arrays are also created with no elements. Note that this bunch of <code>if</code> blocks
     * is faster then using a <code>HashMap</code>.
     */
    @SuppressWarnings("rawtypes")
    public static Object newInstance(Class<?> type) throws IllegalAccessException, InstantiationException {
        if (type.isPrimitive()) {
            if (type == int.class) {
                return Integer.valueOf(0);
            }
            if (type == long.class) {
                return Long.valueOf(0);
            }
            if (type == boolean.class) {
                return Boolean.FALSE;
            }
            if (type == float.class) {
                return Float.valueOf(0);
            }
            if (type == double.class) {
                return Double.valueOf(0);
            }
            if (type == byte.class) {
                return Byte.valueOf((byte) 0);
            }
            if (type == short.class) {
                return Short.valueOf((short) 0);
            }
            if (type == char.class) {
                return Character.valueOf((char) 0);
            }
            throw new IllegalArgumentException("Invalid primitive type: " + type);
        }
        if (type == Integer.class) {
            return Integer.valueOf(0);
        }
        if (type == String.class) {
            return StringPool.EMPTY;
        }
        if (type == Long.class) {
            return Long.valueOf(0);
        }
        if (type == Boolean.class) {
            return Boolean.FALSE;
        }
        if (type == Float.class) {
            Float.valueOf(0);
        }
        if (type == Double.class) {
            Double.valueOf(0);
        }

        if (type == Map.class) {
            return new HashMap();
        }
        if (type == List.class) {
            return new ArrayList();
        }
        if (type == Set.class) {
            return new LinkedHashSet();
        }
        if (type == Collection.class) {
            return new ArrayList();
        }

        if (type == Byte.class) {
            return Byte.valueOf((byte) 0);
        }
        if (type == Short.class) {
            return Short.valueOf((short) 0);
        }
        if (type == Character.class) {
            return Character.valueOf((char) 0);
        }

        if (type.isEnum() == true) {
            return type.getEnumConstants()[0];
        }

        if (type.isArray() == true) {
            return Array.newInstance(type.getComponentType(), 0);
        }

        return type.newInstance();
    }

    /**
     * Returns <code>true</code> if the first member is accessible from second one.
     */
    public static boolean isAssignableFrom(Member member1, Member member2) {
        return member1.getDeclaringClass().isAssignableFrom(member2.getDeclaringClass());
    }

    /**
     * Returns all superclasses.
     */
    public static Class<?>[] getSuperclasses(Class<?> type) {
        int i = 0;
        for (Class<?> x = type.getSuperclass(); x != null; x = x.getSuperclass()) {
            i++;
        }
        Class<?>[] result = new Class[i];
        i = 0;
        for (Class<?> x = type.getSuperclass(); x != null; x = x.getSuperclass()) {
            result[i] = x;
            i++;
        }
        return result;
    }

    /**
     * Returns <code>true</code> if method is user defined and not defined in <code>Object</code> class.
     */
    public static boolean isUserDefinedMethod(final Method method) {
        return method.getDeclaringClass() != Object.class;
    }

    /**
     * Returns <code>true</code> if method defined in <code>Object</code> class.
     */
    public static boolean isObjectMethod(final Method method) {
        return method.getDeclaringClass() == Object.class;
    }

    /**
     * Returns <code>true</code> if method is a bean property.
     */
    public static boolean isBeanProperty(Method method) {
        if (isObjectMethod(method)) {
            return false;
        }
        String methodName = method.getName();
        Class<?> returnType = method.getReturnType();
        Class<?>[] paramTypes = method.getParameterTypes();
        if (methodName.startsWith(METHOD_GET_PREFIX)) {        // getter method must starts with 'get' and it is not getClass()
            if ((returnType != null) && (paramTypes.length == 0)) {    // getter must have a return type and no arguments
                return true;
            }
        } else if (methodName.startsWith(METHOD_IS_PREFIX)) {            // ister must starts with 'is'
            if ((returnType != null) && (paramTypes.length == 0)) {    // ister must have return type and no arguments
                return true;
            }
        } else if (methodName.startsWith(METHOD_SET_PREFIX)) {    // setter must start with a 'set'
            if (paramTypes.length == 1) {                        // setter must have just one argument
                return true;
            }
        }
        return false;
    }

    /**
     * Returns <code>true</code> if method is bean getter.
     */
    public static boolean isBeanPropertyGetter(Method method) {
        return getBeanPropertyGetterPrefixLength(method) != 0;
    }

    private static int getBeanPropertyGetterPrefixLength(Method method) {
        if (isObjectMethod(method)) {
            return 0;
        }
        String methodName = method.getName();
        Class<?> returnType = method.getReturnType();
        Class<?>[] paramTypes = method.getParameterTypes();
        if (methodName.startsWith(METHOD_GET_PREFIX)) {                // getter method must starts with 'get' and it is not getClass()
            if ((returnType != null) && (paramTypes.length == 0)) {    // getter must have a return type and no arguments
                return 3;
            }
        } else if (methodName.startsWith(METHOD_IS_PREFIX)) {            // ister must starts with 'is'
            if ((returnType != null) && (paramTypes.length == 0)) {    // ister must have return type and no arguments
                return 2;
            }
        }
        return 0;
    }

    /**
     * Returns property name from a getter method.
     * Returns <code>null</code> if method is not a real getter.
     */
    public static String getBeanPropertyGetterName(Method method) {
        int prefixLength = getBeanPropertyGetterPrefixLength(method);
        if (prefixLength == 0) {
            return null;
        }
        String methodName = method.getName().substring(prefixLength);
        return StringUtils.decapitalize(methodName);
    }

    /**
     * Returns <code>true</code> if method is bean setter.
     */
    public static boolean isBeanPropertySetter(Method method) {
        return getBeanPropertySetterPrefixLength(method) != 0;
    }

    private static int getBeanPropertySetterPrefixLength(Method method) {
        if (isObjectMethod(method)) {
            return 0;
        }
        String methodName = method.getName();
        Class<?>[] paramTypes = method.getParameterTypes();
        if (methodName.startsWith(METHOD_SET_PREFIX)) {            // setter must start with a 'set'
            if (paramTypes.length == 1) {                        // setter must have just one argument
                return 3;
            }
        }
        return 0;
    }

    /**
     * Returns beans property setter name or <code>null</code> if method is not a real setter.
     */
    public static String getBeanPropertySetterName(Method method) {
        int prefixLength = getBeanPropertySetterPrefixLength(method);
        if (prefixLength == 0) {
            return null;
        }
        String methodName = method.getName().substring(prefixLength);
        return StringUtils.decapitalize(methodName);
    }

    /**
     * Returns single component type.
     */
    public static Class<?> getComponentType(Type type) {
        return getComponentType(type, null);
    }

    /**
     * Returns single component type.
     */
    public static Class<?> getComponentType(Type type, Class<?> implClass) {
        Class<?>[] componentTypes = getComponentTypes(type, implClass);
        if (componentTypes == null) {
            return null;
        }
        return componentTypes[componentTypes.length - 1];
    }

    public static Class<?>[] getComponentTypes(Type type) {
        return getComponentTypes(type, null);
    }

    /**
     * Returns the component types of the given type.
     * Returns <code>null</code> if given type does not have a single
     * component type. For example the following types all have the
     * component-type MyClass:
     * <ul>
     * <li>MyClass[]</li>
     * <li>List&lt;MyClass&gt;</li>
     * <li>Foo&lt;? extends MyClass&gt;</li>
     * <li>Bar&lt;? super MyClass&gt;</li>
     * <li>&lt;T extends MyClass&gt; T[]</li>
     * </ul>
     */
    public static Class<?>[] getComponentTypes(Type type, Class<?> implClass) {
        if (type instanceof Class) {
            Class<?> clazz = (Class<?>) type;
            if (clazz.isArray()) {
                return new Class[]{clazz.getComponentType()};
            }
        } else if (type instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) type;

            Type[] generics = pt.getActualTypeArguments();

            if (generics.length == 0) {
                return null;
            }

            Class<?>[] types = new Class[generics.length];

            for (int i = 0; i < generics.length; i++) {
                types[i] = getRawType(generics[i], implClass);
            }
            return types;
        } else if (type instanceof GenericArrayType) {
            GenericArrayType gat = (GenericArrayType) type;

            Class<?> rawType = getRawType(gat.getGenericComponentType(), implClass);
            if (rawType == null) {
                return null;
            }

            return new Class[]{rawType};
        }
        return null;
    }

    /**
     * @see #getComponentTypes(Type)
     */
    public static Class<?>[] getGenericSupertypes(Class<?> type) {
        return getComponentTypes(type.getGenericSuperclass());
    }

    public static Class<?> getGenericSupertype(Class<?> type) {
        Class<?>[] componentTypes = getComponentTypes(type.getGenericSuperclass());

        if (componentTypes == null) {
            return null;
        }

        return componentTypes[0];
    }

    /**
     * Returns raw class for given <code>type</code>. Use this method with both
     * regular and generic types.
     *
     * @param type the type to convert
     * @return the closest class representing the given <code>type</code>
     * @see #getRawType(Type, Class)
     */
    public static Class<?> getRawType(Type type) {
        return getRawType(type, null);
    }

    /**
     * Returns raw class for given <code>type</code> when implementation class is known
     * and it makes difference.
     *
     * @see #resolveVariable(TypeVariable, Class)
     */
    public static Class<?> getRawType(Type type, Class<?> implClass) {
        if (type instanceof Class) {
            return (Class<?>) type;
        }
        if (type instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType) type;
            return getRawType(pType.getRawType(), implClass);
        }
        if (type instanceof WildcardType) {
            WildcardType wType = (WildcardType) type;

            Type[] lowerTypes = wType.getLowerBounds();
            if (lowerTypes.length > 0) {
                return getRawType(lowerTypes[0], implClass);
            }

            Type[] upperTypes = wType.getUpperBounds();
            if (upperTypes.length != 0) {
                return getRawType(upperTypes[0], implClass);
            }

            return Object.class;
        }
        if (type instanceof GenericArrayType) {
            Type genericComponentType = ((GenericArrayType) type).getGenericComponentType();
            Class<?> rawType = getRawType(genericComponentType, implClass);
            // this is sort of stupid, but there seems no other way (consider don't creating new instances each time)...
            return Array.newInstance(rawType, 0).getClass();
        }
        if (type instanceof TypeVariable) {
            TypeVariable<?> varType = (TypeVariable<?>) type;
            if (implClass != null) {
                Type resolvedType = resolveVariable(varType, implClass);
                if (resolvedType != null) {
                    return getRawType(resolvedType, null);
                }
            }
            Type[] boundsTypes = varType.getBounds();
            if (boundsTypes.length == 0) {
                return Object.class;
            }
            return getRawType(boundsTypes[0], implClass);
        }
        return null;
    }

    /**
     * Resolves <code>TypeVariable</code> with given implementation class.
     */
    public static Type resolveVariable(TypeVariable<?> variable, final Class<?> implClass) {
        final Class<?> rawType = getRawType(implClass, null);

        int index = ArraysUtils.indexOf(rawType.getTypeParameters(), variable);
        if (index >= 0) {
            return variable;
        }

        final Class<?>[] interfaces = rawType.getInterfaces();
        final Type[] genericInterfaces = rawType.getGenericInterfaces();

        for (int i = 0; i <= interfaces.length; i++) {
            Class<?> rawInterface;

            if (i < interfaces.length) {
                rawInterface = interfaces[i];
            } else {
                rawInterface = rawType.getSuperclass();
                if (rawInterface == null) {
                    continue;
                }
            }

            final Type resolved = resolveVariable(variable, rawInterface);
            if (resolved instanceof Class || resolved instanceof ParameterizedType) {
                return resolved;
            }

            if (resolved instanceof TypeVariable) {
                final TypeVariable<?> typeVariable = (TypeVariable<?>) resolved;
                index = ArraysUtils.indexOf(rawInterface.getTypeParameters(), typeVariable);

                if (index < 0) {
                    throw new IllegalArgumentException("Invalid type variable:" + typeVariable);
                }

                final Type type = i < genericInterfaces.length ? genericInterfaces[i] : rawType.getGenericSuperclass();

                if (type instanceof Class) {
                    return Object.class;
                }

                if (type instanceof ParameterizedType) {
                    return ((ParameterizedType) type).getActualTypeArguments()[index];
                }

                throw new IllegalArgumentException("Unsupported type: " + type);
            }
        }
        return null;
    }

    /**
     * Converts <code>Type</code> to a <code>String</code>. Supports successor interfaces:
     * <ul>
     * <li><code>java.lang.Class</code> - represents usual class</li>
     * <li><code>java.lang.reflect.ParameterizedType</code> - class with generic parameter (e.g. <code>List</code>)</li>
     * <li><code>java.lang.reflect.TypeVariable</code> - generic type literal (e.g. <code>List</code>, <code>T</code> - type variable)</li>
     * <li><code>java.lang.reflect.WildcardType</code> - wildcard type (<code>List&lt;? extends Number&gt;</code>, <code>"? extends Number</code> - wildcard type)</li>
     * <li><code>java.lang.reflect.GenericArrayType</code> - type for generic array (e.g. <code>T[]</code>, <code>T</code> - array type)</li>
     * </ul>
     */
    public static String typeToString(Type type) {
        StringBuilder sb = new StringBuilder();
        typeToString(sb, type, new HashSet<Type>());
        return sb.toString();
    }

    private static void typeToString(StringBuilder sb, Type type, Set<Type> visited) {
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            final Class<?> rawType = (Class<?>) parameterizedType.getRawType();
            sb.append(rawType.getName());
            boolean first = true;
            for (Type typeArg : parameterizedType.getActualTypeArguments()) {
                if (first) {
                    first = false;
                } else {
                    sb.append(", ");
                }
                sb.append('<');
                typeToString(sb, typeArg, visited);
                sb.append('>');
            }
        } else if (type instanceof WildcardType) {
            WildcardType wildcardType = (WildcardType) type;
            sb.append('?');

            // According to JLS(http://java.sun.com/docs/books/jls/third_edition/html/typesValues.html#4.5.1):
            // - Lower and upper can't coexist: (for instance, this is not allowed: <? extends List<String> & super MyInterface>)
            // - Multiple bounds are not supported (for instance, this is not allowed: <? extends List<String> & MyInterface>)

            final Type bound;
            if (wildcardType.getLowerBounds().length != 0) {
                sb.append(" super ");
                bound = wildcardType.getLowerBounds()[0];
            } else {
                sb.append(" extends ");
                bound = wildcardType.getUpperBounds()[0];
            }
            typeToString(sb, bound, visited);
        } else if (type instanceof TypeVariable<?>) {
            TypeVariable<?> typeVariable = (TypeVariable<?>) type;
            sb.append(typeVariable.getName());

            // prevent cycles in case: <T extends List<T>>

            if (!visited.contains(type)) {
                visited.add(type);
                sb.append(" extends ");
                boolean first = true;
                for (Type bound : typeVariable.getBounds()) {
                    if (first) {
                        first = false;
                    } else {
                        sb.append(" & ");
                    }
                    typeToString(sb, bound, visited);
                }
                visited.remove(type);
            }
        } else if (type instanceof GenericArrayType) {
            GenericArrayType genericArrayType = (GenericArrayType) type;
            typeToString(genericArrayType.getGenericComponentType());
            sb.append(genericArrayType.getGenericComponentType());
            sb.append("[]");
        } else if (type instanceof Class) {
            Class<?> typeClass = (Class<?>) type;
            sb.append(typeClass.getName());
        } else {
            throw new IllegalArgumentException("Unsupported type: " + type);
        }
    }

    /**
     * Reads annotation value. Returns <code>null</code> on error
     * (e.g. when value name not found).
     */
    public static Object readAnnotationValue(Annotation annotation, String name) {
        try {
            Method method = annotation.annotationType().getDeclaredMethod(name);
            return method.invoke(annotation);
        } catch (Exception ignore) {
            return null;
        }
    }

    private static class ReflectUtilSecurityManager extends SecurityManager {
        public Class<?> getCallerClass(int callStackDepth) {
            return getClassContext()[callStackDepth + 1];
        }
    }

    private static ReflectUtilSecurityManager SECURITY_MANAGER;

    static {
        try {
            SECURITY_MANAGER = new ReflectUtilSecurityManager();
        } catch (Exception ex) {
            SECURITY_MANAGER = null;
        }
    }

    /**
     * Emulates <code>Reflection.getCallerClass</code> using standard API.
     * This implementation uses custom <code>SecurityManager</code>
     * and it is the fastest. Other implementations are:
     * <ul>
     * <li><code>new Throwable().getStackTrace()[callStackDepth]</code></li>
     * <li><code>Thread.currentThread().getStackTrace()[callStackDepth]</code> (the slowest)</li>
     * </ul>
     * <p>
     * In case when usage of <code>SecurityManager</code> is not allowed,
     * this method fails back to the second implementation.
     * <p>
     * Note that original <code>Reflection.getCallerClass</code> is way faster
     * then any emulation.
     */
    public static Class<?> getCallerClass(int framesToSkip) {
        if (SECURITY_MANAGER != null) {
            return SECURITY_MANAGER.getCallerClass(framesToSkip);
        }

        StackTraceElement[] stackTraceElements = new Throwable().getStackTrace();

        if (framesToSkip >= 2) {
            framesToSkip += 4;
        }

        String className = stackTraceElements[framesToSkip].getClassName();

        try {
            return Thread.currentThread().getContextClassLoader().loadClass(className);
        } catch (ClassNotFoundException cnfex) {
            throw new UnsupportedOperationException(className + " not found.");
        }
    }

    public static Class<?> getTypeClass(String typeName) {
        if ("boolean".equalsIgnoreCase(typeName)) {
            return Boolean.class;
        } else if ("byte".equalsIgnoreCase(typeName)) {
            return Byte.class;
        } else if ("char".equalsIgnoreCase(typeName)) {
            return Character.class;
        } else if ("double".equalsIgnoreCase(typeName)) {
            return Double.class;
        } else if ("float".equalsIgnoreCase(typeName)) {
            return Float.class;
        } else if ("integer".equalsIgnoreCase(typeName)) {
            return Integer.class;
        } else if ("long".equalsIgnoreCase(typeName)) {
            return Long.class;
        } else if ("short".equalsIgnoreCase(typeName)) {
            return Short.class;
        } else if ("object".equalsIgnoreCase(typeName)) {
            return Object.class;
        } else if ("string".equalsIgnoreCase(typeName)) {
            return String.class;
        } else if ("void".equalsIgnoreCase(typeName)) {
            return Void.class;
        }
        return null;
    }
}