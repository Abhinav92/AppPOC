package com.poc.deliveryapp.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.poc.deliveryapp.controllers.ControllerMain;
import com.poc.deliveryapp.entity.EntityReleaseParameters;

public class Utility {

	ControllerMain servClass = new ControllerMain();

	public static void copyNonNullProperties(Object src, Object target) {
		BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
	}

	public static String[] getNullPropertyNames(Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

		Set<String> emptyNames = new HashSet<String>();
		for (java.beans.PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null)
				emptyNames.add(pd.getName());
		}
		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}

	/*
	 * public static void readPPT() throws FileNotFoundException, IOException {
	 * 
	 * XMLSlideShow ppt = new XMLSlideShow(new
	 * FileInputStream("QBRReferenceExample.pptx")); XSLFSlide slide =
	 * ppt.createSlide();
	 * 
	 * XSLFTextShape title = slide.getPlaceholder(0); title.clearText();
	 * 
	 * XSLFTextParagraph p = title.addNewTextParagraph(); XSLFTextRun r =
	 * p.addNewTextRun(); r.setText("Key Success Metrics & Achievements");
	 * r.setFontColor(Color.decode("#e08714")); r.setFontSize(50.);
	 * 
	 * FileOutputStream out = new FileOutputStream("QBRModified.pptx");
	 * ppt.write(out); out.close(); ppt.close(); }
	 */

	public static void insertAndSave(List<EntityReleaseParameters> result) throws IOException {
		deleteOldFile();
		writeToFile(result);
	}

	private static void deleteOldFile() throws IOException {
		File dataFile = new File("persistData.txt");
		if (dataFile.exists()) {
			System.out.println("The file already exists. Deleting the file now.");
			dataFile.delete();
			dataFile.createNewFile();
		} else {
			System.out.println("The file will be newly created one.");
			dataFile.createNewFile();
		}
	}

	private static void writeToFile(List<EntityReleaseParameters> result) throws IOException {
		FileWriter myWriter = new FileWriter("persistData.txt");
		for (int index = 0; index < result.size(); index++) {
			myWriter.write(result.get(index).getID());
			myWriter.write("\t");
			myWriter.write(result.get(index).getBusinessContact());
			myWriter.write("\r\n");
		}
		myWriter.close();
		System.out.println("Successfully wrote to the file.");
	}

	/*
	 * public static void insertAndSave2(List<EntityReleaseParameters> result)
	 * throws IOException { deleteOldPPT(); writeToPPT(result); }
	 */

	/*
	 * private static void deleteOldPPT() throws IOException {
	 * 
	 * XMLSlideShow pptFile = new XMLSlideShow(); XSLFSlideMaster defaultMaster =
	 * pptFile.getSlideMasters().get(0); XSLFSlideLayout layout =
	 * defaultMaster.getLayout(SlideLayout.TITLE_ONLY);
	 * 
	 * // firstSlide - to prepare a basic layout XSLFSlide firstSlide =
	 * pptFile.createSlide(layout); XSLFTextShape title =
	 * firstSlide.getPlaceholder(0); title.clearText();
	 * 
	 * XSLFTextParagraph p = title.addNewTextParagraph(); XSLFTextRun r =
	 * p.addNewTextRun(); r.setFontColor(Color.decode("#e08714"));
	 * r.setFontSize(40.); p.setLineSpacing(4.0);
	 * r.setText("Key Success Metrics & Achievements");
	 * 
	 * FileOutputStream out = new FileOutputStream("pptFormat.pptx");
	 * pptFile.write(out); out.close(); pptFile.close(); }
	 */
	/*
	 * private static void writeToPPT(List<EntityReleaseParameters> result) throws
	 * IOException {
	 * 
	 * XMLSlideShow pptFile = new XMLSlideShow(new
	 * FileInputStream("pptFormat.pptx")); XSLFSlideMaster defaultMaster =
	 * pptFile.getSlideMasters().get(0); XSLFSlideLayout layout =
	 * defaultMaster.getLayout(SlideLayout.TITLE_AND_CONTENT);
	 * 
	 * // secondSlide XSLFSlide secondSlide = pptFile.createSlide(layout);
	 * XSLFTextShape title = secondSlide.getPlaceholder(0); title.clearText();
	 * 
	 * XSLFTextParagraph p = title.addNewTextParagraph(); XSLFTextRun r =
	 * p.addNewTextRun(); r.setFontColor(Color.decode("#e08754"));
	 * r.setFontSize(30.); p.setLineSpacing(2.0); r.setText("Data from the table");
	 * 
	 * XSLFTextShape content = secondSlide.getPlaceholder(1); content.clearText();
	 * XSLFTextParagraph p1 = content.addNewTextParagraph(); XSLFTextRun r1 =
	 * p1.addNewTextRun(); r1.setFontColor(Color.decode("#f8754"));
	 * r1.setFontSize(20.); r1.setText("Name"); r1.setText("LOB");
	 * r1.setText("Business Contact"); for (int i = 0; i < result.size(); i++) {
	 * r1.setText(result.get(i).getName()); r1.setText(result.get(i).getLob());
	 * r1.setText(result.get(i).getBusinessContact());
	 * 
	 * }
	 * 
	 * FileOutputStream out = new FileOutputStream("pptFormat.pptx");
	 * pptFile.write(out); out.close(); pptFile.close();
	 * 
	 * }
	 */

	public static String getPropValue() throws IOException {
		String result = null;
		InputStream inputStream = null;
		try {
			Properties prop = new Properties();
			String propFileName = "counterDB.properties";
			inputStream = Utility.class.getClassLoader().getResourceAsStream(propFileName);
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
			result = prop.getProperty("counter");
		} finally {
			inputStream.close();
		}
		return result;
	}

	public static void setPropValue(String result) throws IOException, ConfigurationException, URISyntaxException {
		InputStream inputStream = null;
		String propFileName = "counterDB.properties";
		Properties props = new Properties();
		inputStream = Utility.class.getClassLoader().getResourceAsStream(propFileName);
		props.load(inputStream);
		inputStream.close();
		
		URL url = Utility.class.getResource("./counterDB.properties");
	    File fileObject = new File(url.toURI());
	    FileOutputStream out = new FileOutputStream(fileObject);
	    
		props.setProperty("counter", result);
		props.store(out, null);
		out.close();
	}
}
