package in.clouthink.nextoa.openapi.controller;

import in.clouthink.nextoa.model.User;
import in.clouthink.nextoa.openapi.dto.DefaultFileObjectQueryParameter;
import in.clouthink.nextoa.openapi.security.SecurityContexts;
import in.clouthink.nextoa.openapi.support.AdvancedFileObjectQueryRestSupport;
import in.clouthink.daas.fss.mongodb.model.FileObject;
import in.clouthink.daas.fss.rest.ResultResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 */
@RestController
@RequestMapping(value = "/api")
public class AdvancedFileObjectRestController {
    
    @Autowired
    private AdvancedFileObjectQueryRestSupport advancedFileObjectQueryRestSupport;
    
    @RequestMapping(value = "/files/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable String id) throws IOException {
        User user = SecurityContexts.getContext().requireUser();
        advancedFileObjectQueryRestSupport.deleteById(id, user);
    }
    
    @RequestMapping(value = "/files", method = RequestMethod.GET)
    @ResultResponseBody
    public Page<FileObject> listFileObject(DefaultFileObjectQueryParameter queryParameter) {
        User user = SecurityContexts.getContext().requireUser();
        return advancedFileObjectQueryRestSupport.listFileObject(queryParameter,
                                                                 user);
    }
    
}
