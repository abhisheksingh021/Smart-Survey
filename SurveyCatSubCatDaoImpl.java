package com.mind.surveyone.dao.impl;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.mind.surveyone.dao.BaseDAO;
import com.mind.surveyone.dao.SurveyCatSubCatDao;
import com.mind.surveyone.dao.bean.SurveyCategoryBean;
import com.mind.surveyone.dao.bean.SurveySubCategoryBean;
import com.mind.surveyone.dao.bean.SurveyTypeBean;
@Repository
public class SurveyCatSubCatDaoImpl extends BaseDAO implements SurveyCatSubCatDao {
	/** The logger. */
	Logger logger = Logger.getLogger(SurveyMstDaoImpl.class);
	@Override
	public String saveSurveyCategory(SurveyCategoryBean catBean) {
		String str="insert into  CATEGORY_MST(Name,Status_ID,IP_address,C_User_ID,C_Date,U_User_ID,U_Date,BG_FILE_NAME,BG_COLOR,Application_Type)"
				+ " values(?,?,?,?,getdate(),?,?,?,?,?)";
		int st= jdbcTemplate.update(str,new Object[]{catBean.getCatname(),"10",catBean.getIp_address(),catBean.getCreatedBy(),0,"1900-01-01 00:00:00.000",catBean.getImageName(),catBean.getBgColor(),catBean.getApplicationName()});
		System.out.println(st);
		System.out.println("end");
		return "Category added successfully.";
	}

	@Override
	public String updateSurveyCategory(SurveyCategoryBean catBean) {
		String sql="update CATEGORY_MST set NAME=?,U_User_ID=?,U_Date=getdate(),IP_address=?,BG_FILE_NAME=?,BG_COLOR=? where Category_ID=?";
		int st= jdbcTemplate.update(sql,new Object[]{catBean.getCatname(),catBean.getUpdatedBy(),catBean.getIp_address(),catBean.getImageName(),catBean.getBgColor(),catBean.getCatid()});
		return "Category updated successfully.";
	}

	@Override
	public String deleteSurveyCategory(SurveyCategoryBean catBean) {
		String qr="select count(*) from SUB_CATEGORY_MST where Category_ID=? and Status_ID=10";
		int countQ= jdbcTemplate.queryForObject(qr, new Object[]{catBean.getCatid()},Integer.class);
		String qr2="select count(*) from CFMS_PROJECT_MST where Category_ID=? and Status_ID=10";
		int countQ2= jdbcTemplate.queryForObject(qr2, new Object[]{catBean.getCatid()},Integer.class);
		if(countQ<=0 && countQ2<=0){
			String sql="update CATEGORY_MST set Status_ID=30,U_User_ID=?,U_Date=getdate(),IP_address=? where  Category_ID=?";
			int st= jdbcTemplate.update(sql,new Object[]{catBean.getUpdatedBy(),catBean.getIp_address(),catBean.getCatid()});
			return "Category deleted successfully.";	
		}else{
			return "Category can not be deleted, reference exist in another Sub category";
		}
	}

	@Override
	public List<SurveyCategoryBean> getSurveyCategoryList(long userid) {
		String sql="select CATEGORY_MST.Category_ID,CATEGORY_MST.NAME "+
" ,ISNULL(CONVERT(VARCHAR(20),CATEGORY_MST.C_Date),'-') C_Date  "+
" ,User_Info_Mst.First_Name,User_Info_Mst.Last_Name,ISNULL(CATEGORY_MST.BG_FILE_NAME,'') BG_FILE_NAME,ISNULL(CATEGORY_MST.BG_COLOR,'') BG_COLOR from CATEGORY_MST  "+ 
" left join User_Info_Mst on User_Info_Mst.User_Info_ID=CATEGORY_MST.C_User_ID  "+
" where CATEGORY_MST.Status_ID=10 and Application_Type<>'Q1' order by CATEGORY_MST.NAME";
		List <SurveyCategoryBean> surveyCategorylist=jdbcTemplate.query(sql, new SurveyCategoryMapper());
		return surveyCategorylist;
	}
	private final class SurveyCategoryMapper implements RowMapper<SurveyCategoryBean>{
		public long uniqueid=1;
		@Override
		public SurveyCategoryBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			SurveyCategoryBean bean = new SurveyCategoryBean();
			bean.setCatid(rs.getInt("Category_ID"));
			bean.setCatname(rs.getString("Name"));
			bean.setCreatedOnStr(rs.getString("C_Date"));
			bean.setCreatedByStr(rs.getString("First_Name")==null?"-":rs.getString("First_Name")+" "+rs.getString("Last_Name"));
			bean.setImageName(rs.getString("BG_FILE_NAME"));
			bean.setBgColor(rs.getString("BG_COLOR"));
			return bean;
		}
	}
	

	@Override
	public String saveSurveySubCategory(SurveySubCategoryBean catBean) {
		String str="insert into  SUB_CATEGORY_MST(Category_ID,Name,Status_ID,IP_address,C_User_ID,C_Date,U_User_ID,U_Date)"
				+ " values(?,?,?,?,?,getdate(),?,?)";
		int st= jdbcTemplate.update(str,new Object[]{catBean.getCatid(),catBean.getSubcatname(),"10",catBean.getIp_address(),catBean.getCreatedBy(),0,"1900-01-01 00:00:00.000"});
		return "Sub-Category added successfully.";
	}

	@Override
	public String updateSurveySubCategory(SurveySubCategoryBean catBean) {
		String sql="update SUB_CATEGORY_MST set Category_ID=?,NAME=?,U_User_ID=?,U_Date=getdate(),IP_address=? where Sub_Category_ID=?";
		int st= jdbcTemplate.update(sql,new Object[]{catBean.getCatid(),catBean.getSubcatname(),catBean.getUpdatedBy(),catBean.getIp_address(),catBean.getSubcatid()});
		return "Sub-Category updated successfully.";
	}

	@Override
	public String deleteSurveySubCategory(SurveySubCategoryBean catBean) {
		
		String qr2="select count(*) from CFMS_PROJECT_MST where Sub_Category_ID=? and Status_ID=10";
		int countQ2= jdbcTemplate.queryForObject(qr2, new Object[]{catBean.getSubcatid()},Integer.class);
		if(countQ2<=0){
			String sql="update SUB_CATEGORY_MST set Status_ID=30,U_User_ID=?,U_Date=getdate(),IP_address=? where  Sub_Category_ID=?";
			int st= jdbcTemplate.update(sql,new Object[]{catBean.getUpdatedBy(),catBean.getIp_address(),catBean.getSubcatid()});
			return "Sub-Category deleted successfully.";	
		}else{
			return "Sub-Category can not be deleted, reference exist in another Survey";
		}
		
	}

	@Override
	public List<SurveySubCategoryBean> getSurveySubCategoryList(long userid) {
		String sql="select SUB_CATEGORY_MST.Sub_Category_ID , SUB_CATEGORY_MST.Category_ID,  "+
" CATEGORY_MST.Name CAT_NAME,SUB_CATEGORY_MST.Name SUB_CAT_NAME,User_Info_Mst.First_Name,User_Info_Mst.Last_Name  "+ 
" ,ISNULL(CONVERT(VARCHAR(20),SUB_CATEGORY_MST.C_Date),'-') C_Date  "+
" from SUB_CATEGORY_MST  "+
" inner join CATEGORY_MST on SUB_CATEGORY_MST.Category_ID= CATEGORY_MST.Category_ID  "+ 
" left join User_Info_Mst on User_Info_Mst.User_Info_ID=SUB_CATEGORY_MST.C_User_ID  "+
" where SUB_CATEGORY_MST.Status_ID=10 and Application_Type<>'Q1' order by SUB_CATEGORY_MST.NAME";
		List <SurveySubCategoryBean> surveyCategorylist=jdbcTemplate.query(sql, new SurveySubCategoryMapper());
		return surveyCategorylist;
	}
	private final class SurveySubCategoryMapper implements RowMapper<SurveySubCategoryBean>{
		public long uniqueid=1;
		@Override
		public SurveySubCategoryBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			SurveySubCategoryBean bean = new SurveySubCategoryBean();
			bean.setCatid(rs.getInt("Category_ID"));
			bean.setSubcatid(rs.getInt("Sub_Category_ID"));
			bean.setCatname(rs.getString("CAT_NAME"));
			bean.setSubcatname(rs.getString("SUB_CAT_NAME"));
			bean.setCreatedOnStr(rs.getString("C_Date"));
			bean.setCreatedByStr(rs.getString("First_Name")==null?"-":rs.getString("First_Name")+" "+rs.getString("Last_Name"));
			return bean;
		}
	}

	@Override
	public String saveSurveyType(SurveyTypeBean catBean) {
		String str="insert into  SurveyType(TYPE_NAME,Status_ID,IP_address,C_User_ID,C_Date,U_User_ID,U_Date)"
				+ " values(?,?,?,?,getdate(),?,?)";
		int st= jdbcTemplate.update(str,new Object[]{catBean.getType(),"10",catBean.getIp_address(),catBean.getCreatedBy(),0,"1900-01-01 00:00:00.000"});
		return "Type added successfully.";
	}

	@Override
	public String updateSurveyType(SurveyTypeBean catBean) {
		String sql="update SurveyType set TYPE_NAME=?,U_User_ID=?,U_Date=getdate(),IP_address=? where ID=?";
		int st= jdbcTemplate.update(sql,new Object[]{catBean.getType(),catBean.getUpdatedBy(),catBean.getIp_address(),catBean.getId()});
		return "Type updated successfully.";
	}

	@Override
	public String deleteSurveyType(SurveyTypeBean catBean) {
		String sql="update SurveyType set Status_ID=30,U_User_ID=?,U_Date=getdate(),IP_address=? where  ID=?";
		int st= jdbcTemplate.update(sql,new Object[]{catBean.getUpdatedBy(),catBean.getIp_address(),catBean.getId()});
		return "Type deleted successfully.";
	}

	@Override
	public List<SurveyTypeBean> getSurveyTypeList(long userid) {
		String sql="select SurveyType.ID,SurveyType.TYPE_NAME,User_Info_Mst.First_Name,User_Info_Mst.Last_Name,  "+
" ISNULL(CONVERT(VARCHAR(20),SurveyType.C_Date),'-') C_Date from SurveyType (nolock) "+
" left join User_Info_Mst (nolock) on User_Info_Mst.User_Info_ID=SurveyType.C_User_ID "+
" where SurveyType.Status_ID=10 order by SurveyType.TYPE_NAME";
						List <SurveyTypeBean> surveyTypelist=jdbcTemplate.query(sql, new SurveyTypeMapper());
						return surveyTypelist;
	}
	private final class SurveyTypeMapper implements RowMapper<SurveyTypeBean>{
			public long uniqueid=1;
			@Override
			public SurveyTypeBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				SurveyTypeBean bean = new SurveyTypeBean();
				bean.setId(rs.getInt("ID"));
				bean.setType(rs.getString("TYPE_NAME"));
				bean.setCreatedOnStr(rs.getString("C_Date"));
				bean.setCreatedByStr(rs.getString("First_Name")==null?"-":rs.getString("First_Name")+" "+rs.getString("Last_Name"));
				return bean;
	        }
	}


	@Override
	public String saveImagetodb(MultipartFile file,MultipartFile newfile,String name) {
		
		try {
			String filename = file.getOriginalFilename();
			String newfilename = newfile.getOriginalFilename();
			
			byte[] photoBytes = file.getBytes();
			byte[] photoBytestwo = newfile.getBytes();
			
			String sql = "INSERT INTO BANNERIMAGES(PICTURE_FILENAME,PICTURE_MAIL,PICTURE_BROWSER) VALUES (?,?,?)";

			 int fileSaved = jdbcTemplate.update(sql, new Object[] { name, photoBytes,photoBytestwo });
			 
			 //named saved in CATEGORY_MST table
			 String sqlquery = "UPDATE CATEGORY_MST SET BG_FILE_NAME = ?, BG_FILE_NAME2 = ?  WHERE name=?";
			 int namesaved = jdbcTemplate.update(sqlquery, new Object[] { filename, newfilename,name });
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*@Override
	public String saveimageforbrowser(MultipartFile file, String name) {

		try {
			String filename = file.getOriginalFilename();
			byte[] photoBytes = file.getBytes();
			String sql = "UPDATE BANNERIMAGES SET PICTURE_BROWSER= ?  WHERE picture_filename= ? ";
			 int fileSaved = jdbcTemplate.update(sql, new Object[] { photoBytes,name });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}*/

	@Override
	public Blob getmailImagebyName(String catName) {
		
        String name = catName;
		String query = "select picture_mail from bannerimages where picture_filename=?";

		Blob photo = jdbcTemplate.queryForObject(query, new Object[] { name }, Blob.class);

		return photo;
	}

	@Override
	public Blob getbrowserImagebyName(String catName) {

		String name = catName;
		String query = "select picture_browser from bannerimages where picture_filename=?";

		Blob photo = jdbcTemplate.queryForObject(query, new Object[] { name }, Blob.class);

		return photo;
	}

}
