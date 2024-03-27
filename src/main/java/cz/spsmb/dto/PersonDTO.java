package cz.spsmb.dto;

public class PersonDTO {

    int age;
    String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
