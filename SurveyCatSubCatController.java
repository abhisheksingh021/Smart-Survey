package com.mind.surveyone.web.controller;

import java.sql.Blob;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mind.surveyone.common.dto.SurveyCategoryDTO;
import com.mind.surveyone.common.dto.SurveySubCategoryDTO;
import com.mind.surveyone.common.dto.SurveyType;

public interface SurveyCatSubCatController {
	@RequestMapping(value ="/survey/survey-category", method = {RequestMethod.GET,RequestMethod.POST})
	ModelAndView surveyCategory(ModelMap model,HttpServletRequest request);
	
	@RequestMapping(value ="/survey/saveSurveyCategory", method=RequestMethod.GET)
	@ResponseBody String  saveSurveyCategory(ModelMap model,HttpServletRequest request);
	
	@RequestMapping(value ="/survey/updateSurveyCategory", method=RequestMethod.GET)
	@ResponseBody String  updateSurveyCategory(ModelMap model,HttpServletRequest request);
	
	@RequestMapping(value ="/survey/deleteSurveyCategory", method=RequestMethod.GET)
	@ResponseBody String  deleteSurveyCategory(ModelMap model,HttpServletRequest request);
	
	@RequestMapping(value ="/survey/surveyCategoryList", method = RequestMethod.GET)
	public   @ResponseBody List<SurveyCategoryDTO> getSurveyCategoryList(Model model, HttpServletRequest request);
	
	@RequestMapping(value ="/survey/survey-Subcategory", method = {RequestMethod.GET,RequestMethod.POST})
	ModelAndView surveySubCategory(ModelMap model,HttpServletRequest request);
	
	@RequestMapping(value ="/survey/saveSurveySubCategory", method=RequestMethod.GET)
	@ResponseBody String  saveSurveySubCategory(ModelMap model,HttpServletRequest request);
	
	@RequestMapping(value ="/survey/updateSurveySubCategory", method=RequestMethod.GET)
	@ResponseBody String  updateSurveySubCategory(ModelMap model,HttpServletRequest request);
	
	@RequestMapping(value ="/survey/deleteSurveySubCategory", method=RequestMethod.GET)
	@ResponseBody String  deleteSurveySubCategory(ModelMap model,HttpServletRequest request);
	
	@RequestMapping(value ="/survey/surveySubCategoryList", method = RequestMethod.GET)
	public   @ResponseBody List<SurveySubCategoryDTO> getSurveySubCategoryList(Model model, HttpServletRequest request);
	
	@RequestMapping(value ="/survey/survey-type", method = {RequestMethod.GET,RequestMethod.POST})
	ModelAndView surveyType(ModelMap model,HttpServletRequest request);
	
	@RequestMapping(value ="/survey/saveSurveyType", method=RequestMethod.GET)
	@ResponseBody String  saveSurveyType(ModelMap model,HttpServletRequest request);
	
	@RequestMapping(value ="/survey/updateSurveyType", method=RequestMethod.GET)
	@ResponseBody String  updateSurveyType(ModelMap model,HttpServletRequest request);
	
	@RequestMapping(value ="/survey/deleteSurveyType", method=RequestMethod.GET)
	@ResponseBody String  deleteSurveyType(ModelMap model,HttpServletRequest request);
	
	@RequestMapping(value ="/survey/surveyTypeList", method = RequestMethod.GET)
	public   @ResponseBody List<SurveyType> getSurveyTypeList(Model model, HttpServletRequest request);

	//changes made
	@RequestMapping(value ="/survey/upload", method=RequestMethod.POST)
	@ResponseBody String handlefileupload(@RequestParam("file") MultipartFile file,@RequestParam("newFile") MultipartFile newFile,HttpServletRequest request,HttpSession session);

	@RequestMapping(value ="/survey/secondimageupload", method=RequestMethod.POST)
	@ResponseBody String handlefileuploadsecondImage(MultipartFile file,HttpServletRequest request, HttpSession session);
	
	@RequestMapping(value ="/survey/getmailimage", method=RequestMethod.POST)
	@ResponseBody String getmailimage(HttpServletRequest request, HttpSession session,HttpServletResponse response);
	
	@RequestMapping(value ="/survey/getbrowserimage", method=RequestMethod.POST)
	@ResponseBody String getbrowserimage(HttpServletRequest request, HttpSession session,HttpServletResponse response);
	
}
