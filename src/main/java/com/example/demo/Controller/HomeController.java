package com.example.demo.Controller;

import com.example.demo.Entity.Transaction;
import com.example.demo.Entity.User;
import com.example.demo.Repository.TransactionRepository;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class HomeController {


     @Autowired
    TransactionRepository transactionRepository;

     @Autowired
     UserService userService;
    @RequestMapping("/")
    public String index()
    {
        return "index";
    }

    @RequestMapping("/login")
    public String login()
    {
        return "login";
    }

    @RequestMapping("/list")
    public String displayTransactions(Model model)
    {
        model.addAttribute("transactions",transactionRepository.findAll());

        return "listtransaction";
    }

    @GetMapping("/depositroute")
    public String makedeposit(Model model)
    {
       model.addAttribute("transaction",new Transaction());

       return "deposit";
    }

    @GetMapping("withdrawalroute")
    public String makewithdrawal(Model model)
    {
        model.addAttribute("transaction",new Transaction());

        return "withdrawal";
    }
    @PostMapping("/withdrawalroute")
    public String doWithdraw(@Valid Transaction transaction,BindingResult result)
    {
        System.out.println(result);
        if(result.hasErrors())
        {
            return "withdrawal";
        }
        transactionRepository.save(transaction);

        return "listtransaction";
    }

    @PostMapping("/depositprocess")
    public String doDeposit(@Valid Transaction transaction, BindingResult result)
    {
        System.out.println(result);
        if(result.hasErrors())
        {
            return "deposit";
        }

        transactionRepository.save(transaction);

        return "listtransaction";


    }

    @RequestMapping("/detail/{id}")
    public String showTransaction(@PathVariable("id") long id, Model model)
    {
         model.addAttribute("transaction",transactionRepository.findOne(id));

         return "transactionhistory";
    }

    @GetMapping("/registration")
    public String showSignupForm(Model model)
    {
        model.addAttribute("user",new User());

        return "signupform";

    }

    @PostMapping("/registration")
    public String procesSignupForm(@Valid @ModelAttribute("user") User user ,BindingResult result,Model model)
    {
        System.out.println(result);

        if(result.hasErrors())
        {
            return "signupform";
        }
        userService.saveAccountholder(user);

        model.addAttribute("Message", "User Successfully Signed up!");

        return "index";


    }

    @RequestMapping("/balanceroute")
    public String displayBalance(Model model)
    {
        model.addAttribute("transactions",transactionRepository.findAll());

        float balance=0;

        for(Transaction transaction:transactionRepository.findAll())
        {
            if(transaction.getAction().equalsIgnoreCase("withdrawal"))
            {
                balance= balance-transaction.getAmount();
            }

            if(transaction.getAction().equalsIgnoreCase("deposit"))
            {
                balance=balance + transaction.getAmount();
            }
        }

        model.addAttribute("balance",balance);

        return "balance";

    }

}
