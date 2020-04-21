package com.github.myoa.controller;

import com.github.myoa.domain.Emp;
import com.github.myoa.dto.EmpDto;
import com.github.myoa.mapper.EmpMapper;
import com.github.myoa.service.EmpService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.annotation.Resource;

@Controller
@RequestMapping("/emp")
public class EmpController {

    @Resource
    EmpService empService;

    //id查询员工
    @GetMapping("/find")
    public String find(String id,Model model){
        EmpDto empDto = empService.find(id);
        model.addAttribute("emp",empDto);
        return "updateEmp";
    }

    @GetMapping("/findAll")
    public String findAll(Model model){
        model.addAttribute("emps",empService.findAll());
        return "emplist";
    }

    //保存员工
    @PostMapping("/save")
    public String save(EmpDto emp){
        empService.save(emp);
        return "redirect:/emp/findAll";
    }

    @GetMapping("/delete")
    public String delete(String id){
        empService.delete(id);
        return "redirect:/emp/findAll";
    }

    //更新员工信息方法
    @PostMapping("/update")
    public String update(EmpDto empDto){
        empService.update(empDto);
        return "redirect:/emp/findAll";
    }
}
