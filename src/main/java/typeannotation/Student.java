package typeannotation;

/**
 * Example of using type annotation
 *
 */
@MyTypeAnnotation(studentName = "Alex", stuAddress = "Aaa str. 1")
public class Student {
    {
        Class<?> clazz = getClass();
        if (!clazz.isAnnotationPresent(MyTypeAnnotation.class)) {
            throw new RuntimeException("The class "
                    + clazz.getSimpleName()
                    + " is not annotated with @typeannotation.MyAnnotation");
        }

        MyTypeAnnotation annotation = clazz.getAnnotation(MyTypeAnnotation.class);
        name = annotation.studentName();
        address = annotation.stuAddress();
    }

    private final String name;
    private final String address;

    public static void main(String[] args) {
        Student student = new Student();
        System.out.println("name: " + student.name + "; address: " + student.address);
    }
}
