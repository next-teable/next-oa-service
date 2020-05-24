package in.clouthink.nextoa.bl.openapi.controller;

import in.clouthink.daas.fss.mongodb.model.FileObject;
import in.clouthink.daas.fss.rest.ResultResponseBody;
import in.clouthink.nextoa.bl.model.User;
import in.clouthink.nextoa.bl.openapi.dto.DefaultFileObjectQueryParameter;
import in.clouthink.nextoa.bl.openapi.support.AdvancedFileObjectQueryRestSupport;
import in.clouthink.nextoa.security.SecurityContexts;
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
        User user = (User)SecurityContexts.getContext().requireUser();
        advancedFileObjectQueryRestSupport.deleteById(id, user);
    }
    
    @RequestMapping(value = "/files", method = RequestMethod.GET)
    @ResultResponseBody
    public Page<FileObject> listFileObject(DefaultFileObjectQueryParameter queryParameter) {
        User user = (User)SecurityContexts.getContext().requireUser();
        return advancedFileObjectQueryRestSupport.listFileObject(queryParameter,
                                                                 user);
    }
    
}
