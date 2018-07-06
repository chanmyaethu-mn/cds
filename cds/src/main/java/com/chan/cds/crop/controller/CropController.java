package com.chan.cds.crop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.chan.cds.crop.dto.CropRegisterationDto;
import com.chan.cds.crop.service.CropService;

@RestController
public class CropController {
	
	@Autowired
	private CropService cropService;
	
	private static final String CROPREGISTRATION_DTO = "cropRegisterationDto";
	
	private static final String CROPMODIFICATION_DTO = "cropModificationDto";
	
	private static final String CROP_LIST = "cropList";
	
	int CROP_LIST_PAGE_SIZE = 10;
	String CROP_LIST_BASE_URL = "/cropList/page/";

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ModelAndView cropTest() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home");
		return modelAndView;
	}
	
	@RequestMapping(value = "/cropList")
	public ModelAndView pageList(HttpServletRequest request) {
		request.getSession().setAttribute(CROP_LIST, null);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(ViewName.REDIRECT + "cropList/page/1");
		return modelAndView;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/cropList/page/{pageNumber}")
	public ModelAndView showPagedProductPage(HttpServletRequest request, @PathVariable Integer pageNumber, @RequestParam(value = "isDelete", required = false) Boolean isDelete, Model model) {
		ModelAndView modelAndView = new ModelAndView();
		PagedListHolder<Crop> pagedListHolder = (PagedListHolder<Crop>)request.getSession().getAttribute(CROP_LIST);
		if (null == pagedListHolder || (null != isDelete && isDelete)) {
			pagedListHolder = new PagedListHolder<>(cropService.getAllCrop());
			pagedListHolder.setPageSize(CROP_LIST_PAGE_SIZE);
		} else {
			final int goToPage = pageNumber -1;
			if (goToPage <= pagedListHolder.getPageCount() && goToPage >= 0) {
				pagedListHolder.setPage(goToPage);
			}
		}
		
		request.getSession().setAttribute(CROP_LIST, pagedListHolder);
		
		int current = pagedListHolder.getPage()+ 1;
		int begin = Math.max(1, current - CROP_LIST_PAGE_SIZE);
		int end = Math.min(begin + 5, pagedListHolder.getPageCount());
		int totalPageCount = pagedListHolder.getPageCount();
		String baseUrl = CROP_LIST_BASE_URL;
		
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrl", baseUrl);
		
		modelAndView.addObject(CROP_LIST, pagedListHolder);
		
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
	
	@RequestMapping(value = "/cropList/page/editCrop")
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
	
	/*@RequestMapping(value = "/editCrop", method = RequestMethod.POST)
	public RedirectView editCrop(@Valid @Validated CropModificationDto cropModificationDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		return new RedirectView();
	}*/
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/cropList/page/deleteCrop")
	public ModelAndView deleteCrop(@RequestParam("cropId") Integer cropId, @RequestParam("currentIndex") Integer currentIndex, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		cropService.deleteByPrimaryKey(cropId);
		redirectAttributes.getFlashAttributes().clear();
		redirectAttributes.addFlashAttribute(ViewMessageFlag.SUCCESS_MESSAGE, "Crop has been deleted successfully.");
		
		PagedListHolder<Crop> pagedListHolder = (PagedListHolder<Crop>)request.getSession().getAttribute(CROP_LIST);
		
		if (null == pagedListHolder ) {
			return new ModelAndView(ViewName.REDIRECT);
		} else {
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName(ViewName.REDIRECT + "cropList/page/" + currentIndex + "?isDelete=true");
			return modelAndView;
		}
	}

}
