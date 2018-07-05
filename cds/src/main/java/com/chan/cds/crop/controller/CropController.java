package com.chan.cds.crop.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.chan.cds.common.constant.ViewMessageFlag;
import com.chan.cds.common.constant.ViewName;
import com.chan.cds.common.entity.Crop;
import com.chan.cds.crop.dto.CropModificationDto;
import com.chan.cds.crop.dto.CropRegisterationDto;
import com.chan.cds.crop.service.CropService;

@RestController
public class CropController {
	
	@Autowired
	private CropService cropService;
	
	private static final String CROPREGISTRATION_DTO = "cropRegisterationDto";
	
	private static final String CROPMODIFICATION_DTO = "cropModificationDto";
	
	private static final String CROP_LIST = "cropList";

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ModelAndView cropTest() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home");
		return modelAndView;
	}

	@RequestMapping(value = { "/cropList" }, method = RequestMethod.GET)
	public ModelAndView cropList() {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject(CROP_LIST, cropService.getAllCrop());  
		
		modelAndView.setViewName(ViewName.CROP_LIST);
		return modelAndView;
	}

	@RequestMapping(value = { "/createCrop" }, method = RequestMethod.GET)
	public ModelAndView createCrop(ModelMap model) {
		ModelAndView modelAndView = new ModelAndView();
		CropRegisterationDto cropRegisterationDto = new CropRegisterationDto();
		modelAndView.addObject(CROPREGISTRATION_DTO, cropRegisterationDto);
		modelAndView.setViewName(ViewName.CREATE_CROP);
		return modelAndView;
	}

	@RequestMapping(value = "/insertCrop")
	public RedirectView insertCrop(@Valid @Validated CropRegisterationDto cropRegisterationDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			return new RedirectView(ViewName.CREATE_CROP);
		} else {
			cropService.insertCrop(cropRegisterationDto);
			redirectAttributes.getFlashAttributes().clear();
			redirectAttributes.addFlashAttribute(ViewMessageFlag.SUCCESS_MESSAGE, "Crop has been inserted successfully.");
			
		}

		return new RedirectView(ViewName.CREATE_CROP);
	}
	
	@RequestMapping(value = "/editCrop")
	public ModelAndView editCrop(@RequestParam("cropId") Integer cropId, RedirectAttributes redirectAttributes) {
		Crop crop = cropService.getCropByPrimaryKey(cropId);
		ModelAndView modelAndView = new ModelAndView();
		if (null == crop) {
			redirectAttributes.getFlashAttributes().clear();
			redirectAttributes.addFlashAttribute(ViewMessageFlag.ERROR_MESSAGE, "Crop doesn't exist");
			modelAndView.setViewName(ViewName.REDIRECT+ ViewName.CROP_LIST);
			return modelAndView;
		} else {
			modelAndView.addObject(CROPMODIFICATION_DTO, crop);
			modelAndView.setViewName(ViewName.EDIT_CROP);
			return modelAndView;
		}
	}
	
	@RequestMapping(value = "/editCrop", method = RequestMethod.POST)
	public RedirectView editCrop(@Valid @Validated CropModificationDto cropModificationDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		return new RedirectView();
	}
	
	@RequestMapping(value = "/deleteCrop")
	public RedirectView deleteCrop(@RequestParam("cropId") Integer cropId, RedirectAttributes redirectAttributes) {
		cropService.deleteByPrimaryKey(cropId);
		redirectAttributes.getFlashAttributes().clear();
		redirectAttributes.addFlashAttribute(ViewMessageFlag.SUCCESS_MESSAGE, "Crop has been deleted successfully.");
		return new RedirectView(ViewName.CROP_LIST);
	}

}
