package com.wight.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes({"email","weights"})
public class ViewController {

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
                         Model model) {
    // (Valida pass aquí si quieres)
    model.addAttribute("email", email);
    model.addAttribute("msg", "¡Registro exitoso!");
    return "redirect:/login";
  }

  // ---------- LOGIN ----------
  @GetMapping("/login")
  public String loginForm() { return "login"; }

  @PostMapping("/login")
  public String doLogin(@RequestParam String email,
                        @RequestParam String password,
                        Model model) {
    model.addAttribute("email", email);
    return "redirect:/dashboard";
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
    return "redirect:/dashboard";
  }

  // ---------- LOGOUT ----------
  @GetMapping("/logout")
  public String logout(SessionStatus status, HttpSession session) {
    status.setComplete();
    session.invalidate();
    return "redirect:/login";
  }

  // Opcional: redirigir raíz
  @GetMapping("/")
  public String root() { return "redirect:/login"; }
}
