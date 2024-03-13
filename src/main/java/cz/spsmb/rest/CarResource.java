package cz.spsmb.rest;

import cz.spsmb.dao.CarRepository;
import cz.spsmb.dao.PersonRepository;
import cz.spsmb.dto.CarDTO;
import cz.spsmb.model.Car;
import cz.spsmb.model.Person;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@Path("/cars")
public class CarResource {

    @Inject
    CarRepository carRepository;

    @Inject
    PersonRepository personRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response list() {
        List<Car> cars = carRepository.listAll();
        return Response.ok().entity(cars).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getById(@PathParam("id") Long id) {
        Car car = carRepository.findById(id);
        return Response.ok().entity(car).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response deleteById(@PathParam("id") Long id) {
        carRepository.deleteById(id);
        return Response.ok().entity("ok").build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response save(CarDTO carDTO) {

        if (carDTO.getBrand() == null || carDTO.getYear() <= 0 || carDTO.getModel() == null) {
            return Response.status(400).entity("Person must have attributes \"name\" and \"age\".").build();
        }
        Car car = new Car();
        car.setBrand(carDTO.getBrand());
        car.setModel(carDTO.getModel());
        car.setYear(carDTO.getYear());
        Optional<Person> person = personRepository.listByName(carDTO.getPerson());
        if (person.isEmpty()) {
            return Response.status(400).entity("person doesnt exist").build();
        }
        car.setPerson(person.get());
        carRepository.persist(car);
        person.get().getCars().add(car);
        personRepository.persist(person.get());

        return Response.ok().entity("ok").build();

    }


}
