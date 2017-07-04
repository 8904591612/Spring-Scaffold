package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.model.Data;
import com.example.service.DataService;

@Controller
@RequestMapping("/datas")
public class DataController {

	private static final String MSG_SUCESS_INSERT = "Data inserted successfully.";
	private static final String MSG_SUCESS_UPDATE = "Data successfully changed.";
	private static final String MSG_SUCESS_DELETE = "Deleted Data successfully.";
	private static final String MSG_ERROR = "Error.";

	@Autowired
	private DataService dataService;

	@GetMapping
	public String index(Model model) {
		List<Data> all = dataService.findAll();
		model.addAttribute("listData", all);
		return "data/index";
	}
	
	@GetMapping("/{id}")
	public String show(Model model, @PathVariable("id") Integer id) {
		if (id != null) {
			Data data = dataService.findOne(id);
			model.addAttribute("data", data);
		}
		return "data/show";
	}

	@GetMapping(value = "/new")
	public String create(Model model, @ModelAttribute Data entity) {
		model.addAttribute("data", entity);
		return "data/form";
	}
	
	@PostMapping
	public String create(@Valid @ModelAttribute Data entity, BindingResult result, RedirectAttributes redirectAttributes) {
		Data data = null;
		try {
			data = dataService.save(entity);
			redirectAttributes.addFlashAttribute("success", MSG_SUCESS_INSERT);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
			e.printStackTrace();
		}
		return "redirect:/datas/" + data.getId();
	}
	
	@GetMapping("/{id}/edit")
	public String update(Model model, @PathVariable("id") Integer id) {
		try {
			if (id != null) {
				Data entity = dataService.findOne(id);
				model.addAttribute("data", entity);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return "data/form";
	}
	
	@PutMapping
	public String update(@Valid @ModelAttribute Data entity, BindingResult result, RedirectAttributes redirectAttributes) {
		Data data = null;
		try {
			data = dataService.save(entity);
			redirectAttributes.addFlashAttribute("success", MSG_SUCESS_UPDATE);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
			e.printStackTrace();
		}
		return "redirect:/datas/" + data.getId();
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		try {
			if (id != null) {
				Data entity = dataService.findOne(id);
				dataService.delete(entity);
				redirectAttributes.addFlashAttribute("success", MSG_SUCESS_DELETE);
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
			throw new ServiceException(e.getMessage());
		}
		return "redirect:/datas/index";
	}

}
