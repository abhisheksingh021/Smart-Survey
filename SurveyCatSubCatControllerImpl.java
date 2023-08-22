package com.mind.surveyone.web.controller.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mind.surveyone.common.dto.SurveyCategoryDTO;
import com.mind.surveyone.common.dto.SurveySubCategoryDTO;
import com.mind.surveyone.common.dto.SurveyType;
import com.mind.surveyone.common.dto.UserDetails;
import com.mind.surveyone.logic.SurveyCatSubCatLogic;
import com.mind.surveyone.logic.SurveyMstLogic;
import com.mind.surveyone.web.controller.SurveyCatSubCatController;
@Controller
public class SurveyCatSubCatControllerImpl implements SurveyCatSubCatController{

	/** The app properties. */
	@Resource
	private Properties appProperties;
	
	/** The survey Master logic. */
	@Resource
	private SurveyCatSubCatLogic surveyCatSubCatLogic;
	
	@Override
	public ModelAndView surveyCategory(ModelMap model,
			HttpServletRequest request) {
		ModelAndView mv=new ModelAndView();
		mv.setViewName("survey/survey-category");
		return mv;
	}

	@Override
	public String saveSurveyCategory(ModelMap model, HttpServletRequest request) {
		String catname=request.getParameter("catname");
		String imageName=request.getParameter("imageName");
		String bgColor=request.getParameter("bgColor");
		
		System.out.println(request.getParameter("uploadButton"));
		
		String ipaddr=request.getRemoteAddr();
		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		String applicationName=userDetails.getApplicationName();
		SurveyCategoryDTO catDTO=new SurveyCategoryDTO();
		catDTO.setCatname(catname);
		catDTO.setImageName(imageName);
		catDTO.setBgColor(bgColor);
		catDTO.setIp_address(ipaddr);
		catDTO.setCreatedBy(userDetails.getUserId());
		catDTO.setApplicationName(applicationName);
		
		return surveyCatSubCatLogic.saveSurveyCategory(catDTO);
	}

	@Override
	public String updateSurveyCategory(ModelMap model,
			HttpServletRequest request) {
		String catname=request.getParameter("catname");
		String catid=request.getParameter("catid");
		String imageName=request.getParameter("imageName");
		String bgColor=request.getParameter("bgColor");
		String ipaddr=request.getRemoteAddr();
		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		SurveyCategoryDTO catDTO=new SurveyCategoryDTO();
		catDTO.setCatname(catname);
		catDTO.setCatid(Integer.parseInt(catid));
		catDTO.setIp_address(ipaddr);
		catDTO.setUpdatedBy(userDetails.getUserId());
		catDTO.setImageName(imageName);
		catDTO.setBgColor(bgColor);
		return surveyCatSubCatLogic.updateSurveyCategory(catDTO);
	}

	@Override
	public String deleteSurveyCategory(ModelMap model,
			HttpServletRequest request) {
		String catname=request.getParameter("catname");
		String catid=request.getParameter("catid");
		String ipaddr=request.getRemoteAddr();
		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		SurveyCategoryDTO catDTO=new SurveyCategoryDTO();
		catDTO.setCatname(catname);
		catDTO.setCatid(Integer.parseInt(catid));
		catDTO.setIp_address(ipaddr);
		catDTO.setUpdatedBy(userDetails.getUserId());
		return surveyCatSubCatLogic.deleteSurveyCategory(catDTO);
	}

	@Override
	public List<SurveyCategoryDTO> getSurveyCategoryList(Model model,
			HttpServletRequest request) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		return surveyCatSubCatLogic.getSurveyCategoryList(userDetails.getUserId());
	}

	@Override
	public ModelAndView surveySubCategory(ModelMap model,
			HttpServletRequest request) {
		ModelAndView mv=new ModelAndView();
		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		List<SurveyCategoryDTO> catlist= surveyCatSubCatLogic.getSurveyCategoryList(userDetails.getUserId());
		mv.addObject("catlist", catlist);
		mv.setViewName("survey/survey-subcategory");
		return mv;
	}

	@Override
	public String saveSurveySubCategory(ModelMap model,
			HttpServletRequest request) {
		String subcatname=request.getParameter("subcatname");
		String catid=request.getParameter("catid");
		String ipaddr=request.getRemoteAddr();
		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		SurveySubCategoryDTO catDTO=new SurveySubCategoryDTO();
		catDTO.setSubcatname(subcatname);
		catDTO.setCatid(Integer.parseInt(catid));
		catDTO.setIp_address(ipaddr);
		catDTO.setCreatedBy(userDetails.getUserId());
		
		return surveyCatSubCatLogic.saveSurveySubCategory(catDTO);
	}

	@Override
	public String updateSurveySubCategory(ModelMap model,
			HttpServletRequest request) {
		String subcatname=request.getParameter("subcatname");
		String catid=request.getParameter("catid");
		String subcatid=request.getParameter("subcatid");
		String ipaddr=request.getRemoteAddr();
		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		SurveySubCategoryDTO catDTO=new SurveySubCategoryDTO();
		catDTO.setSubcatname(subcatname);
		catDTO.setCatid(Integer.parseInt(catid));
		catDTO.setSubcatid(Integer.parseInt(subcatid));
		catDTO.setIp_address(ipaddr);
		catDTO.setUpdatedBy(userDetails.getUserId());
		return surveyCatSubCatLogic.updateSurveySubCategory(catDTO);
	}

	@Override
	public String deleteSurveySubCategory(ModelMap model,
			HttpServletRequest request) {
		String subcatname=request.getParameter("subcatname");
		String catid=request.getParameter("catid");
		String subcatid=request.getParameter("subcatid");
		String ipaddr=request.getRemoteAddr();
		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		SurveySubCategoryDTO catDTO=new SurveySubCategoryDTO();
		catDTO.setSubcatname(subcatname);
		catDTO.setCatid(Integer.parseInt(catid));
		catDTO.setSubcatid(Integer.parseInt(subcatid));
		catDTO.setIp_address(ipaddr);
		catDTO.setUpdatedBy(userDetails.getUserId());
		return surveyCatSubCatLogic.deleteSurveySubCategory(catDTO);
	}

	@Override
	public List<SurveySubCategoryDTO> getSurveySubCategoryList(Model model,
			HttpServletRequest request) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		return surveyCatSubCatLogic.getSurveySubCategoryList(userDetails.getUserId());
	}

	@Override
	public ModelAndView surveyType(ModelMap model, HttpServletRequest request) {
		ModelAndView mv=new ModelAndView();
		mv.setViewName("survey/survey-type");
		return mv;
	}

	@Override
	public String saveSurveyType(ModelMap model, HttpServletRequest request) {
		String typename=request.getParameter("typename");
		String ipaddr=request.getRemoteAddr();
		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		SurveyType catDTO=new SurveyType();
		catDTO.setType(typename);
		catDTO.setIp_address(ipaddr);
		catDTO.setCreatedBy(userDetails.getUserId());
		return surveyCatSubCatLogic.saveSurveyType(catDTO);
	}

	@Override
	public String updateSurveyType(ModelMap model, HttpServletRequest request) {
		String typename=request.getParameter("typename");
		String typeid=request.getParameter("typeid");
		String ipaddr=request.getRemoteAddr();
		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		SurveyType catDTO=new SurveyType();
		catDTO.setType(typename);
		catDTO.setId(Integer.parseInt(typeid));
		catDTO.setIp_address(ipaddr);
		catDTO.setUpdatedBy(userDetails.getUserId());
		return surveyCatSubCatLogic.updateSurveyType(catDTO);
	}

	@Override
	public String deleteSurveyType(ModelMap model, HttpServletRequest request) {
		String typename=request.getParameter("typename");
		String typeid=request.getParameter("typeid");
		String ipaddr=request.getRemoteAddr();
		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		SurveyType catDTO=new SurveyType();
		catDTO.setType(typename);
		catDTO.setId(Integer.parseInt(typeid));
		catDTO.setIp_address(ipaddr);
		catDTO.setUpdatedBy(userDetails.getUserId());
		return surveyCatSubCatLogic.deleteSurveyType(catDTO);
	}

	@Override
	public List<SurveyType> getSurveyTypeList(Model model,
			HttpServletRequest request) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		return surveyCatSubCatLogic.getSurveyTypeList(userDetails.getUserId());
	}
	
	//Banner upload part
			//@Override
			
			public String handlefileupload(MultipartFile file,MultipartFile newfile, HttpServletRequest request,HttpSession session) {
				
				String categoryName = request.getParameter("catname");
				System.out.println(categoryName);
				try{
					String filename = file.getOriginalFilename();
					String newfilename = newfile.getOriginalFilename();
					System.out.println("filename is >>>>> "+filename);
					System.out.println("newfilename is >>>> "+newfilename);
					
					
					String imageSaved = surveyCatSubCatLogic.saveImage(file,newfile,categoryName);
					
					
					byte[] data = file.getBytes();
			//		String path = "C:\\iSurvey Workspace\\surveyone-parent\\surveyone-web\\src\\main\\webapp\\resources\\resourcesx\\images\\";
					
//					String path=appProperties.getProperty("image.path");
					
				//	System.out.println("PATH===>>"+path);
					String rlPath = session.getServletContext().getRealPath("/")+File.separator+"resources"+File.separator+"resourcesx"
					                +File.separator+"images"+File.separator+filename;
					
					System.out.println("rlPath==>"+rlPath);
			//		String realPath = path+filename;
//					System.out.println(realPath);
					System.out.println("*********");
//					String path3 = this.getClass().getClassLoader().getResource("").getPath();
//					URL url = this.getClass().getClassLoader().getResource("../../resources/images/"); 
//					System.out.println(url);
					
//					File file2 = new File(rlPath+File.separator+filename);
//					System.out.println("nitish");
					
					//file writing code
					
					
				FileOutputStream fos = new FileOutputStream(rlPath);
				fos.write(data);
				fos.close();
					
					
					return "successful upload";
				}catch(IOException e){
					e.printStackTrace();
					return "upload failed";
				}
			}
			
			//second Image
			 @Override
			 public String handlefileuploadsecondImage(MultipartFile file,HttpServletRequest request,
						HttpSession session) {
					try {
						String categoryName = request.getParameter("catname");
//						 surveyCatSubCatLogic.saveimagebrowser(file,categoryName);
						
						String filename2 = file.getOriginalFilename();
						byte[] data = file.getBytes();
						String secondPath = session.getServletContext().getRealPath("/")+File.separator+"resources"+File.separator+"resourcesx"
				                +File.separator+"images"+File.separator+filename2;
						//String secondPath = session.getServletContext().getRealPath("/")+File.separator+"resources"+File.separator+"resourcesx"
				         //       +File.separator+"images"+File.separator+"users"+File.separator+filename2;
						
						/*String rlPath = session.getServletContext().getRealPath("/")+File.separator+"resources"+File.separator+"resourcesx"
				                +File.separator+"images"+File.separator+filename;*/
						System.out.println("secondPath==>"+secondPath);
						FileOutputStream fos = new FileOutputStream(secondPath);
						fos.write(data);
						fos.close();
						
						
						return "Successful Upload";
					} catch (Exception e) {
						e.printStackTrace();
						return "failed upload";
					}
					
				}

			@Override
			public String getmailimage(HttpServletRequest request,
					HttpSession session,HttpServletResponse response){
				String catname = request.getParameter("catname");
				response.setContentType("image/jpeg");
				Blob photo = surveyCatSubCatLogic.getImageByName(catname);
				String s = null;
				try {
					byte[] bytes = photo.getBytes(1, (int) photo.length());
//					InputStream inputStream = new ByteArrayInputStream(bytes);
//					System.out.println(inputStream);
					
					  s = new sun.misc.BASE64Encoder().encode(bytes);
//					IOUtils.copy(s, response.getOutputStream());
					 return s;
				} catch (Exception e) {
					e.printStackTrace();
				}
				return s;
			}

			@Override
			public String getbrowserimage(HttpServletRequest request,
					HttpSession session,HttpServletResponse response) {
				String catname = request.getParameter("catname");
				
				response.setContentType("image/jpeg");
				Blob photo = surveyCatSubCatLogic.getbrowserImageByName(catname);
				String s = null;
				try {
					byte[] bytes = photo.getBytes(1, (int) photo.length());
					  s = new sun.misc.BASE64Encoder().encode(bytes);
					 return s;
				} catch (Exception e) {
					e.printStackTrace();
				}
				return s;
			}


			/*@Override
			public String saveSurveyCategoryform(ModelMap model,
					HttpServletRequest request) {
				// TODO Auto-generated method stub
				return null;
			}*/

}
