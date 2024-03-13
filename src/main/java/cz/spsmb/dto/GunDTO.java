package cz.spsmb.dto;

public class GunDTO {
    String model;
    String person;
    String brand;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "GunDTO{" +
                "model='" + model + '\'' +
                ", person='" + person + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }
}
