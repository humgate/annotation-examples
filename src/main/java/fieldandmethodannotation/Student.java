package fieldandmethodannotation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Отметим часть полей, которые участвуют в toString нашей аннотацией
 *
 */
public class Student {
    @MyFieldAnnotation
    private String name;

    @MyFieldAnnotation
    private String address;

    private String speciality;

    @MyMethodAnnotation
    private void formatData() {
        name = name.toUpperCase();
        address = address.toUpperCase();
        speciality = speciality.toUpperCase();
    }

    public Student(String name, String address, String speciality) {
        this.name = name;
        this.address = address;
        this.speciality = speciality;
    }

    /**
     * Выполняет метод(ы) отмеченные аннотацией @MyMethodAnnotation
     *
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private void postConstructInit() throws InvocationTargetException, IllegalAccessException {
        Class<?> clazz = getClass();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(MyMethodAnnotation.class)) {
                method.setAccessible(true);
                method.invoke(this);
            }
        }
    }

    /**
     *
     * @return toString состоящее только из отмеченных антоацией @MyFieldAnnotation полей
     */
    @Override
    public String toString() {
        Class<?> clazz = getClass();
        StringBuilder builder = new StringBuilder();

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(MyFieldAnnotation.class)) {
                try {
                    builder.append((String) field.get(this));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                builder.append(" ");
            }
        }
        return builder.toString().trim();
    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Student student = new Student("Alex", "Almaaty", "Business analysis");
        student.postConstructInit();
        System.out.println(student);
    }
}
