package HW4;

import jakarta.persistence.*;

@Entity
//эта аннотация указывает на то что мы определяем обьект-сущность
//Обьекты-сущности должны следовать спецификации POJO что означает что
// поля должны быть приватными, иметь геттеры и сеттеры, класс должен иметь пустой конструктор
@Table(name = "Students")
//С параметром name мы связываем класс с определенной схемой и таблицей.
//Таким образом мы создали класс-сущность и привязали его к таблице
public class Student {//описвываем сущность
    public Student() {}

    @Id
//@Id - является обязательной и определяет первичный ключ. В отличии от работы с БД через GDBC,
// где не требуется обязательного использования ключей, в ORM сущность определяется в классе java
// экземпляры которого хранятся в БД, и первичный ключ обязателен для сопостовления данных в таблице
// и обьектов в прложении.
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
// @GeneratedValue - определяет стратегию формирования ключа
// strategy =  GenerationType.IDENTITY - самый простой способ генерации ключа, который опирается
// на auto-increment.
    private Long id;
    @Column(name = "firstName")
//@Column - привязывает поле к колонке в БД.
    private String firstName;
    @Column(name = "secondName")
    private String secondName;
    @Column(name = "age")
    private Long age;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public Long getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "GDBCConnect{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", age=" + age +
                '}';
    }

}
