//package com.example.demo.dataLoader;
//
//import com.example.demo.Entity.Role;
//import com.example.demo.Repository.RoleRepository;
//import com.example.demo.Repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DataLoader implements CommandLineRunner {
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    RoleRepository roleRepository;
//
//    @Override
//    public void run(String... strings) throws Exception {
//        System.out.println("Loading data . . .");
//
//
//        Role adminRole = roleRepository.findByRole("USER");
//        Role userRole = roleRepository.findByRole("MANAGER");
//        roleRepository.save(adminRole);
//        roleRepository.save(userRole);
//
//    }
//}