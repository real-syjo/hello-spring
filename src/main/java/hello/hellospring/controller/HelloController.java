package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

	//정적
	@GetMapping("hello")
	public String hello(Model model) {
		model.addAttribute("data", "What");
		return "hello";
	}
	
	//mvc 동적
	@GetMapping("hello-mvc")
	public String helloMvc(@RequestParam("name") String name, Model model) {
		model.addAttribute("name",name);
		return "hello-template";
	}
	
	//api   
	@GetMapping("hello-string")
	@ResponseBody  //@responseBody: html의 body에 return해주는 데이터를 직접 넣어주겠다.
	public String helloString(@RequestParam("name") String name) {
		return "hello"+name;
	}
	
	@GetMapping("hello-api")
	@ResponseBody
	public Hello helloApi(@RequestParam("name") String name) {
		Hello hello = new Hello();
		hello.setName(name);
		return hello;  //문자가 아니라 객체를줌 
	}
	
	//Getter Setter 자바빈 규약 
	//property 접근 방식
	static class Hello{
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
		
	}
}
