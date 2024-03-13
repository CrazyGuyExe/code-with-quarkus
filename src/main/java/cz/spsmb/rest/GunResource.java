package cz.spsmb.rest;

import cz.spsmb.dao.GunRepository;
import cz.spsmb.dao.PersonRepository;
import cz.spsmb.dto.GunDTO;
import cz.spsmb.model.Gun;
import cz.spsmb.model.Person;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@Path("/guns")
public class GunResource {

    @Inject
    GunRepository gunRepository;

    @Inject
    PersonRepository personRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response list() {
        List<Gun> guns = gunRepository.listAll();
        return Response.ok().entity(guns).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getById(@PathParam("id") Long id) {
        Gun gun = gunRepository.findById(id);
        return Response.ok().entity(gun).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response deleteById(@PathParam("id") Long id) {
        gunRepository.deleteById(id);
        return Response.ok().entity("ok").build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response save(GunDTO gunDTO) {

        if (gunDTO.getBrand() == null || gunDTO.getModel() == null) {
            return Response.status(400).entity("Person must have attributes \"name\" and \"age\".").build();
        }
        Gun gun = new Gun();
        gun.setBrand(gunDTO.getBrand());
        gun.setModel(gunDTO.getModel());
        Optional<Person> person = personRepository.listByName(gunDTO.getPerson());
        if (person.isEmpty()){
           return Response.status(400).entity("person doesnt exist").build();
        }
        gun.setPerson(person.get());
        gunRepository.persist(gun);
        person.get().getGuns().add(gun);
        personRepository.persist(person.get());


        return Response.ok().entity("ok").build();


    }


}

