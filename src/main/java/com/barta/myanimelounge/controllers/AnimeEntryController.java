package com.barta.myanimelounge.controllers;

import com.barta.myanimelounge.exceptions.EntryNotFoundException;
import com.barta.myanimelounge.exceptions.RestrictedAccessException;
import com.barta.myanimelounge.models.AnimeEntry;
import com.barta.myanimelounge.models.User;
import com.barta.myanimelounge.repository.AnimeEntryRepository;
import com.barta.myanimelounge.repository.UserRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.http.HTTPException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(value = "/api/entries/anime")
public class AnimeEntryController {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    String[] statuses = new String[]{"planning", "watching", "watched"};

    private final AnimeEntryRepository animeEntryRepository;
    private final UserRepository userRepository;

    public AnimeEntryController(AnimeEntryRepository animeEntryRepository, UserRepository userRepository) {
        this.animeEntryRepository = animeEntryRepository;
        this.userRepository = userRepository;
    }

    @PostMapping()
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public AnimeEntry createAnimeEntryForCurrentUser(@Valid @RequestBody() AnimeEntry animeEntry) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean validStatus = false;
        for (String status : statuses) {
            if (status.equals(animeEntry.getStatus())) {
                validStatus = true;
                break;
            }
        }
        if (!validStatus) {
            throw new HTTPException(HttpStatus.BAD_REQUEST.value());
        }
        User user = this.userRepository.findByUsername(auth.getName()).orElseThrow(EntryNotFoundException::new);
        animeEntry.setUser(user);
        return this.animeEntryRepository.save(animeEntry);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public AnimeEntry updateAnimeEntryForCurrentUser(@Valid @RequestBody() AnimeEntry animeEntry, @PathVariable("id") String Id) throws ChangeSetPersister.NotFoundException {
        boolean validStatus = false;
        for (String status : statuses) {
            if (status.equals(animeEntry.getStatus())) {
                validStatus = true;
                break;
            }
        }
        if (!validStatus) {
            throw new HTTPException(HttpStatus.BAD_REQUEST.value());
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = this.userRepository.findByUsername(auth.getName()).orElseThrow(EntryNotFoundException::new);
        animeEntry.setUser(user);
        AnimeEntry entry = this.animeEntryRepository.findById(Long.valueOf(Id)).orElseThrow(EntryNotFoundException::new);
        if (!Objects.equals(entry.getUser().getUsername(), auth.getName())) {
            throw new RestrictedAccessException();
        }
        entry.setReview(animeEntry.getReview());
        entry.setStartDate(animeEntry.getStartDate());
        entry.setEndDate(animeEntry.getEndDate());
        entry.setRating(animeEntry.getRating());
        entry.setStatus(animeEntry.getStatus());
        return this.animeEntryRepository.save(entry);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public void deleteAnimeEntryForCurrentUser(@PathVariable("id") String Id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AnimeEntry entry = this.animeEntryRepository.findById(Long.valueOf(Id)).orElseThrow(EntryNotFoundException::new);
        if (!Objects.equals(entry.getUser().getUsername(), auth.getName())) {
            throw new RestrictedAccessException();
        }
        this.animeEntryRepository.deleteById(Long.valueOf(Id));
    }

    @GetMapping("/user/{userId}")
    public List<AnimeEntry> getUserAnimeEntries(@PathVariable String userId) {
        User user = this.userRepository.findById(Long.valueOf(userId)).orElseThrow(EntryNotFoundException::new);
        return user.getAnimeEntries();
    }

    @GetMapping("/mine")
    public List<AnimeEntry> getMyAnimeEntries() {
        User user = this.userRepository.findByUsername(SecurityContextHolder.getContext()
                .getAuthentication().getName()).orElseThrow(EntryNotFoundException::new);
        return user.getAnimeEntries();
    }
}
