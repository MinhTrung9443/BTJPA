package vn.iotstar.controllers.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;
import vn.iotstar.Entity.Category;
import vn.iotstar.models.CategoryModel;
import vn.iotstar.services.ICategoryService;


@Controller
@RequestMapping("/admin/category")
public class CategoryController {
	@Autowired
	ICategoryService cateservice;
	@RequestMapping("")
	public String all(Model model) {
		List<Category> list = cateservice.findAll();
		model.addAttribute("list",list);
		return "admin/category/list";
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		CategoryModel cateModel = new CategoryModel();
		model.addAttribute("category",cateModel);
		return "admin/category/add";
	}
	@PostMapping("/save")
	public ModelAndView saveOrUpdate(ModelMap model, 
			@Valid @ModelAttribute("category") CategoryModel cateModel, BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("admin/category/add");
		}
		Category entity = new Category();
		BeanUtils.copyProperties(cateModel, entity);
		cateservice.save(entity);
		String message="";
		if (cateModel.getIsEdit() == true)
		{
			message = "Category is Edited!!!!!";
		}
		else {
			message = "Category is saved!!!!";
		}
		model.addAttribute("message",message);
		
		return new ModelAndView("forward:/admin/category",model);
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView edit(ModelMap model, @PathVariable("id") Long categoryId) {
		Optional<Category> optCategory = cateservice.findById(categoryId);
		CategoryModel cateModel = new CategoryModel();
		if (optCategory.isPresent())
		{
			Category entity = optCategory.get();
			
			BeanUtils.copyProperties(entity, cateModel);
			cateModel.setIsEdit(true);
			
			model.addAttribute("category",cateModel);
			
			return new ModelAndView("admin/category/add", model);
		}
		model.addAttribute("message","Category is not existed!!");
		return new ModelAndView("forward:/admin/category",model);
		
	}
	@GetMapping("/delete/{id}")
	public ModelAndView delete(ModelMap model, @PathVariable("id") Long categoryId) {
		Optional<Category> optCategory = cateservice.findById(categoryId);
		if (optCategory.isPresent())
		{
			cateservice.deleteById(categoryId);
			List<Category> list = cateservice.findAll();
			model.addAttribute("list",list);
			return new ModelAndView("admin/category/list", model);
		}
		model.addAttribute("message","Category is not existed!!");
		return new ModelAndView("forward:/admin/category",model);
		
	}
}
