package com.rana.firstrest.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rana.firstrest.exceptions.ReaderNotFoundException;
import com.rana.firstrest.model.Comments;
import com.rana.firstrest.model.Readers;
import com.rana.firstrest.repo.CommentsRepository;
import com.rana.firstrest.repo.ReadersRepository;

@RestController
public class ReadersResource {
	@Autowired
	private ReadersRepository readersRepo;

	@Autowired
	private CommentsRepository commentsRepo;

	@GetMapping("/readers")
	public List<Readers> retrieveAllReader() {
		return readersRepo.findAll();
	}

	@GetMapping("readers/{id}")
	public Resource<Readers> getReader(@PathVariable int id) {
		Optional<Readers> reader = readersRepo.findById(id);
		if (!reader.isPresent()) {
			throw new ReaderNotFoundException("ID =" + id);
		}

		Resource<Readers> resource = new Resource<Readers>(reader.get());

		ControllerLinkBuilder linkAdded = linkTo(methodOn(this.getClass()).retrieveAllReader());

		resource.add(linkAdded.withRel("all~readers"));
		return resource;
	}

	@DeleteMapping
	public void deleteReader(@PathVariable int id) {
		readersRepo.deleteById(id);
	}

	@PostMapping("/readers")
	public ResponseEntity<Object> createReader(@Valid @RequestBody Readers reader) {
		Readers rdr = readersRepo.save(reader);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(rdr.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@GetMapping
	public List<Comments> retrieveAllComments(@PathVariable int id) {
		Optional<Readers> optionalReaders = readersRepo.findById(id);
		if (!optionalReaders.isPresent()) {
			throw new ReaderNotFoundException("ID = " + id);
		}
		return optionalReaders.get().getComments();
	}

	@PostMapping("/readers/{id}/comments")
	public ResponseEntity<Object> createComment(@PathVariable int id, @RequestBody Comments comments) {
		Optional<Readers> opReaders = readersRepo.findById(id);
		if (!opReaders.isPresent()) {
			throw new ReaderNotFoundException("ID =" + id);
		}

		Readers reader = opReaders.get();

		comments.setReaders(reader);

		commentsRepo.save(comments);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(comments.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
}
