package com.example.SpringDTORestProject.controller;

import com.example.SpringDTORestProject.dto.UserDTO;
import com.example.SpringDTORestProject.entity.User;
import com.example.SpringDTORestProject.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@RequestBody UserDTO userDTO){
        Map<String, Object> jsonResponseMap = new LinkedHashMap<>();
        User user = modelMapper.map(userDTO, User.class);
        userService.saveUser(user);
        jsonResponseMap.put("status", 1);
        jsonResponseMap.put("message", "User record saved successfully in database via POST");
        return new ResponseEntity<>(jsonResponseMap, HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers(){
        Map<String, Object> jsonResponseMap = new LinkedHashMap<>();
        List<User> listOfAllUsers = userService.getAllUsersListFromDatabase();
        List<UserDTO> listOfUsersDTO= new ArrayList<>();
        if(!listOfAllUsers.isEmpty()){
            for(User user: listOfAllUsers){
                listOfUsersDTO.add(modelMapper.map(user, UserDTO.class));
            }
            jsonResponseMap.put("status",200);
            jsonResponseMap.put("data", listOfUsersDTO);
            return new ResponseEntity<>(jsonResponseMap, HttpStatus.OK);
        }else{
            jsonResponseMap.clear();
            jsonResponseMap.put("status",404);
            jsonResponseMap.put("message","Entire table is empty");
            return new ResponseEntity<>(jsonResponseMap, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Integer id){
        Map<String, Object> jsonResponseMap = new LinkedHashMap<>();
        try{
            User user = userService.findById(id);
            UserDTO userDTO = modelMapper.map(user, UserDTO.class);
            jsonResponseMap.put("status", 200);
            jsonResponseMap.put("data", userDTO);
            return new ResponseEntity<>(jsonResponseMap, HttpStatus.OK);
        } catch (Exception ex){
            jsonResponseMap.clear();
            jsonResponseMap.put("status", 404);
            jsonResponseMap.put("message", "Data with id - " + id + " not found");
            return new ResponseEntity<>(jsonResponseMap, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody UserDTO userDTO){
        Map<String, Object> jsonResponseMap = new LinkedHashMap<>();
        try{
            User user = userService.findById(id);
            user.setUser_name(userDTO.getUser_name());
            user.setEmail_id(userDTO.getEmail_id());
            user.setMobile_no(userDTO.getMobile_no());
            user.setCity(userDTO.getCity());
            user.setPassword(userDTO.getPassword());
            userService.saveUser(user);
            jsonResponseMap.put("status", 200);
            jsonResponseMap.put("data", userService.findById(id));
            return new ResponseEntity<>(jsonResponseMap, HttpStatus.OK);
        }catch (Exception ex){
            jsonResponseMap.clear();
            jsonResponseMap.put("status", 404);
            jsonResponseMap.put("message", "Date with id - " + id + " not found");
            return new ResponseEntity<>(jsonResponseMap, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id){
        Map<String, Object> jsonResponseMap = new HashMap<>();
        try{
            User user = userService.findById(id);
            userService.delete(user);
            jsonResponseMap.put("status", 204);
            jsonResponseMap.put("message", "Data with id - " + id + " deleted successfully");
            return new ResponseEntity<>(jsonResponseMap, HttpStatus.OK);
        } catch (Exception ex){
            jsonResponseMap.clear();
            jsonResponseMap.put("status", 404);
            jsonResponseMap.put("message", "Data with id - " + id + "not found !");
            return new ResponseEntity<>(jsonResponseMap, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getbynum/{mobile_no}")
    public ResponseEntity<?> findByMobile(@PathVariable String mobile_no){
        Map<String, Object> jsonResponseMap = new HashMap<>();
        try{
            User user = userService.findByMobile(mobile_no);
            UserDTO userDTO = modelMapper.map(user, UserDTO.class);
//            jsonResponseMap.put("status", 200);
//            jsonResponseMap.put("data",userDTO);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } catch (Exception ex){
//            jsonResponseMap.clear();
            jsonResponseMap.put("status", 404);
            jsonResponseMap.put("message", "Data with num - " + mobile_no + " not found");
            return new ResponseEntity<>(jsonResponseMap, HttpStatus.NOT_FOUND);
        }
    }


}


