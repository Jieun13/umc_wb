//package umc.wb.web.controller;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import umc.wb.service.MemberService.MemberCommandService;
//import umc.wb.web.dto.MemberRequest;
//
//@Controller
//@RequiredArgsConstructor
//public class MemberViewController {
//
//    private final MemberCommandService memberCommandService;
//
//    @GetMapping("/login")
//    public String loginPage(){
//        return "login";
//    }
//
//    @GetMapping("/signup")
//    public String signupPage(Model model){
//        model.addAttribute("memberJoinDto", new MemberRequest.JoinRequest());
//        return "signup";
//    }
//
//    @GetMapping("/home")
//    public String home(){
//        return "home";
//    }
//
//    @GetMapping("/admin")
//    public String admin(){
//        return "admin";
//    }
//
//    @PostMapping("/members/signup")
//    public String joinMember(@ModelAttribute("memberJoinDto") MemberRequest.JoinRequest request,
//                             BindingResult bindingResult,
//                             Model model) {
//        if (bindingResult.hasErrors()) {
//            return "signup";
//        }
//
//        try {
//            memberCommandService.joinMember(request);
//            return "redirect:/login";
//        } catch (Exception e) {
//            model.addAttribute("error", e.getMessage());
//            return "signup";
//        }
//    }
//}
//
