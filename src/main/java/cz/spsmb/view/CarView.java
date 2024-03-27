package cz.spsmb.view;

import cz.spsmb.dao.CarRepository;
import cz.spsmb.dao.GunRepository;
import cz.spsmb.dao.PersonRepository;
import cz.spsmb.dto.CarDTO;
import cz.spsmb.dto.PersonDTO;
import cz.spsmb.model.Car;
import cz.spsmb.model.Person;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;


import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class CarView {
    @Inject
    PersonRepository personRepository;

    @Inject
    CarRepository carRepository;

    List<Car> cars;
    List<Person> persons;
    CarDTO newCar;
    PersonDTO newPerson;
    String selectedPerson;

    @PostConstruct
    public void init(){

        persons = personRepository.listAll();
        System.out.println(persons);

        Optional<Person> person = personRepository.listByName("Krátké vtipy");
        if (person.isPresent()){
           // cars = carRepository.findByCategoryName("Krátké vtipy");
        }
        else {
            cars = carRepository.listAll();
        }
        newCar = new CarDTO();
        newPerson = new PersonDTO();
    }


    @Transactional
    public void saveCar(){
        Car car = new Car();
        car.setBrand(newCar.getBrand());
        car.setModel(newCar.getModel());
        car.setYear(newCar.getYear());
        Optional<Person> personOptional = personRepository.listByName(selectedPerson);

            car.setPerson(personOptional.get());


        carRepository.persist(car);

        init();
    }

    @Transactional
    public void savePerson(){
        Person person = new Person();
        person.setName(newPerson.getName());
        person.setAge(newPerson.getAge());

        personRepository.persist(person);

        init();
    }


    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public CarDTO getNewCar() {
        return newCar;
    }

    public void setNewCar(CarDTO newCar) {
        this.newCar = newCar;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public List<String> getPersonsName (){
        return persons.stream().map(Person::getName).collect(Collectors.toList());
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public PersonDTO getNewPerson() {
        return newPerson;
    }

    public void setNewPerson(PersonDTO newPerson) {
        this.newPerson = newPerson;
    }

    public String getSelectedPerson() {
        return selectedPerson;
    }

    public void setSelectedPerson(String selectedPerson) {
        this.selectedPerson = selectedPerson;
    }
}