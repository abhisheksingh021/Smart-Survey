package com.mind.surveyone.logic.impl;

import java.io.IOException;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.mind.surveyone.common.dto.MenuListDTO;
import com.mind.surveyone.common.dto.SurveyCategoryDTO;
import com.mind.surveyone.common.dto.SurveySubCategoryDTO;
import com.mind.surveyone.common.dto.SurveyType;
import com.mind.surveyone.dao.SurveyCatSubCatDao;
import com.mind.surveyone.dao.SurveyMstDao;
import com.mind.surveyone.dao.bean.Menu;
import com.mind.surveyone.dao.bean.MenuList;
import com.mind.surveyone.dao.bean.SurveyCategoryBean;
import com.mind.surveyone.dao.bean.SurveySubCategoryBean;
import com.mind.surveyone.dao.bean.SurveyTypeBean;
import com.mind.surveyone.logic.SurveyCatSubCatLogic;
@Component
public class SurveyCatSubCatLogicImpl implements SurveyCatSubCatLogic{

	/** The logger. */
	Logger logger = Logger.getLogger(SurveyRequestLogicImpl.class);
	/** The scurvey request dao. */
	@Resource
	private SurveyCatSubCatDao surveyCatSubCatDao;
	@Override
	public String saveSurveyCategory(SurveyCategoryDTO catDTO) {
		SurveyCategoryBean bean = new SurveyCategoryBean();
		BeanUtils.copyProperties(catDTO, bean);
		return surveyCatSubCatDao.saveSurveyCategory(bean);
	}

	@Override
	public String updateSurveyCategory(SurveyCategoryDTO catDTO) {
		SurveyCategoryBean bean = new SurveyCategoryBean();
		BeanUtils.copyProperties(catDTO, bean);
		return surveyCatSubCatDao.updateSurveyCategory(bean);
	}

	@Override
	public String deleteSurveyCategory(SurveyCategoryDTO catDTO) {
		SurveyCategoryBean bean = new SurveyCategoryBean();
		BeanUtils.copyProperties(catDTO, bean);
		return surveyCatSubCatDao.deleteSurveyCategory(bean);
	}

	@Override
	public List<SurveyCategoryDTO> getSurveyCategoryList(long userid) {
		List<SurveyCategoryBean> surveyCategoryList = surveyCatSubCatDao.getSurveyCategoryList(userid);
		List<SurveyCategoryDTO> surveyCategoryDTOList = null;
		SurveyCategoryDTO surveyCategoryDTO = null;
		if (surveyCategoryList != null && !surveyCategoryList.isEmpty()) {
			surveyCategoryDTOList = new ArrayList<SurveyCategoryDTO>();
			for (SurveyCategoryBean bean : surveyCategoryList) {
				surveyCategoryDTO = new SurveyCategoryDTO();
				BeanUtils.copyProperties(bean, surveyCategoryDTO);
				surveyCategoryDTOList.add(surveyCategoryDTO);
			}
			return surveyCategoryDTOList;
		}
		return Collections.emptyList();
	}

	@Override
	public String saveSurveySubCategory(SurveySubCategoryDTO catDTO) {
		SurveySubCategoryBean bean = new SurveySubCategoryBean();
		BeanUtils.copyProperties(catDTO, bean);
		return surveyCatSubCatDao.saveSurveySubCategory(bean);
	}

	@Override
	public String updateSurveySubCategory(SurveySubCategoryDTO catDTO) {
		SurveySubCategoryBean bean = new SurveySubCategoryBean();
		BeanUtils.copyProperties(catDTO, bean);
		return surveyCatSubCatDao.updateSurveySubCategory(bean);
	}

	@Override
	public String deleteSurveySubCategory(SurveySubCategoryDTO catDTO) {
		SurveySubCategoryBean bean = new SurveySubCategoryBean();
		BeanUtils.copyProperties(catDTO, bean);
		return surveyCatSubCatDao.deleteSurveySubCategory(bean);
	}

	@Override
	public List<SurveySubCategoryDTO> getSurveySubCategoryList(long userid) {
		List<SurveySubCategoryBean> surveySubCategoryList = surveyCatSubCatDao.getSurveySubCategoryList(userid);
		List<SurveySubCategoryDTO> surveySubCategoryDTOList = null;
		SurveySubCategoryDTO surveySubCategoryDTO = null;
		if (surveySubCategoryList != null && !surveySubCategoryList.isEmpty()) {
			surveySubCategoryDTOList = new ArrayList<SurveySubCategoryDTO>();
			for (SurveySubCategoryBean bean : surveySubCategoryList) {
				surveySubCategoryDTO = new SurveySubCategoryDTO();
				BeanUtils.copyProperties(bean, surveySubCategoryDTO);
				surveySubCategoryDTOList.add(surveySubCategoryDTO);
			}
			return surveySubCategoryDTOList;
		}
		return Collections.emptyList();
	}

	@Override
	public String saveSurveyType(SurveyType catDTO) {
		SurveyTypeBean bean = new SurveyTypeBean();
		BeanUtils.copyProperties(catDTO, bean);
		return surveyCatSubCatDao.saveSurveyType(bean);
	}

	@Override
	public String updateSurveyType(SurveyType catDTO) {
		SurveyTypeBean bean = new SurveyTypeBean();
		BeanUtils.copyProperties(catDTO, bean);
		return surveyCatSubCatDao.updateSurveyType(bean);
	}

	@Override
	public String deleteSurveyType(SurveyType catDTO) {
		SurveyTypeBean bean = new SurveyTypeBean();
		BeanUtils.copyProperties(catDTO, bean);
		return surveyCatSubCatDao.deleteSurveyType(bean);
	}

	@Override
	public List<SurveyType> getSurveyTypeList(long userid) {
		List<SurveyTypeBean> surveyTypeList = surveyCatSubCatDao.getSurveyTypeList(userid);
		List<SurveyType> surveyTypeDtolist = null;
		SurveyType surveyTypeDto = null;
		if (surveyTypeList != null && !surveyTypeList.isEmpty()) {
			surveyTypeDtolist = new ArrayList<SurveyType>();
			for (SurveyTypeBean bean : surveyTypeList) {
				surveyTypeDto = new SurveyType();
				BeanUtils.copyProperties(bean, surveyTypeDto);
				surveyTypeDtolist.add(surveyTypeDto);
			}
			return surveyTypeDtolist;
		}
		return Collections.emptyList();
	}

	@Override
	public String saveImage(MultipartFile file,MultipartFile newfile,String name) {
		MultipartFile file2 = file;
		MultipartFile file3 = newfile;
		 surveyCatSubCatDao.saveImagetodb(file2,newfile,name);
		return null;
	}

	/*@Override
	public String saveimagebrowser(MultipartFile file, String name) {
		MultipartFile file2 = file;
		 surveyCatSubCatDao.saveimageforbrowser(file2,name);
		return null;
	}*/

	@Override
	public Blob getImageByName(String catname) {
		String catName = catname;
		Blob photo = surveyCatSubCatDao.getmailImagebyName(catName);
		return photo;
	}

	@Override
	public Blob getbrowserImageByName(String catname) {
		String catName = catname;
		Blob photo = surveyCatSubCatDao.getbrowserImagebyName(catName);
		return photo;
	}
	
}
