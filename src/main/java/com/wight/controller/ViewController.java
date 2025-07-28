package com.wight.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes({"email","weights"})
public class ViewController {

  private static final String REDIRECT_LOGIN = "redirect:/login";
  private static final String REDIRECT_DASHBOARD = "redirect:/dashboard";

  @ModelAttribute("weights")
  public List<String> weights() {
    return new ArrayList<>();
  }

  // ---------- SIGNUP ----------
  @GetMapping("/signup")
  public String signupForm() { return "signup"; }

    @PostMapping("/signup")
    public String doSignup(@RequestParam String email,
                        @RequestParam String password,
                        @RequestParam String confirmPassword,
                        RedirectAttributes ra) {
    ra.addFlashAttribute("msg", "¡Registro exitoso!");
    return REDIRECT_LOGIN;
    }

  // ---------- LOGIN ----------
  @GetMapping("/login")
  public String loginForm() { return "login"; }

  @PostMapping("/login")
  public String doLogin(@RequestParam String email,
                        @RequestParam String password,
                        Model model) {
    model.addAttribute("email", email);
    return REDIRECT_DASHBOARD;
  }

  // ---------- DASHBOARD ----------
  @GetMapping("/dashboard")
  public String dashboard(@ModelAttribute("weights") List<String> weights, Model model) {
    model.addAttribute("lastWeight", weights.isEmpty() ? "" : weights.get(weights.size()-1));
    return "dashboard";
  }

  @PostMapping("/weight")
  public String saveWeight(@RequestParam String weight,
                           @ModelAttribute("weights") List<String> weights) {
    weights.add(weight + " kg");
    return REDIRECT_DASHBOARD;
  }

  // ---------- LOGOUT ----------
  @GetMapping("/logout")
  public String logout(SessionStatus status, HttpSession session) {
    status.setComplete();
    session.invalidate();
    return REDIRECT_LOGIN;
  }

  // Opcional: redirigir raíz
  @GetMapping("/")
  public String root() { return REDIRECT_LOGIN; }
}
