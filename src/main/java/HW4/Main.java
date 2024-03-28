package HW4;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        Configuration configuration = new Configuration().configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {

            List<Student> students = new ArrayList<>();

            try (Session session = sessionFactory.openSession()) {

                Query<Student> query = session.createQuery("select p from Student p where age > 20", Student.class);

                List<Student> resultList = query.getResultList();

                Transaction transaction = session.beginTransaction();
                for (Student student : resultList) {
                    student.setFirstName("UPDATED");
                }
                transaction.commit();

                students.addAll(session.createQuery("from Student p", Student.class).getResultList());
                System.out.println(students);
            }

        }

    }
    private static void FindStudent(SessionFactory sessionFactory){
        try (Session session = sessionFactory.openSession()) {
            Student student = session.find(Student.class, 2L);
            System.out.println(student);

            Transaction transaction = session.beginTransaction();

            transaction.commit();
        }
    }

    private static void insertStudent(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            Student student = new Student();

            student.setId(2L);
            student.setFirstName("Valentin");
            student.setSecondName("Petrov");
            student.setAge(23L);

            session.persist(student);

            transaction.commit();
        }
    }
    private static void removeStudent(SessionFactory sessionFactory){
        try (Session session = sessionFactory.openSession()) {
            Student student = session.find(Student.class, 2L);
            System.out.println(student);

            Transaction transaction = session.beginTransaction();
            session.remove(student);

            transaction.commit();
        }
    }

}
