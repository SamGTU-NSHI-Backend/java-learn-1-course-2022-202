package ru.nshi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nshi.error.MessageValidationException;
import ru.nshi.model.Error;
import ru.nshi.model.Message;
import ru.nshi.service.MessageService;

@RestController
@RequestMapping(SampleController.MAPPING)
public class SampleController {
    public static final String MAPPING = "/sample";

    @Autowired
    private MessageService service;

    //    @RequestMapping(path = "/hello", method = RequestMethod.GET)
    @GetMapping("/hello")
    public Message doGet() {
        Message message = new Message();
        message.setValue("Hello world");
        return message;
    }

    @GetMapping("/pathVar/{var1}")//http://localhost:8080/pathVar/var1Value
    public Message doPathVar(@PathVariable String var1) {
        Message message = new Message();
        message.setValue(var1);
        return message;
    }

    @GetMapping("/queryParam")
    public Message doQueryParam(
        @RequestParam(required = false, defaultValue = "defParam") String param1) {
        Message message = new Message();
        message.setValue(param1);
        return message;
    }

    @GetMapping("/header")
    public Message doHeader(@RequestHeader("test-header") String header) {
        Message message = new Message();
        message.setValue(header);
        return message;
    }

    @PostMapping
    public Message createMessage(@RequestBody(required = false) Message message) {
        if (message == null || message.getValue() == null) {
            throw new MessageValidationException("message is null");
        }
        message = service.doHandleMessage(message);
        return message;
    }

    @ExceptionHandler(MessageValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handleValidationException(MessageValidationException ex) {
        return new Error(ex.getMessage());
    }


    @GetMapping("/exception/{var1}")
    public ResponseEntity<?> doException(@PathVariable Integer var1) {
        if (var1.equals(0)) {
            return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("You are forbidden!");
        }
        Message message = new Message();
        message.setValue(var1.toString());
        return ResponseEntity.ok(message);
    }
}
