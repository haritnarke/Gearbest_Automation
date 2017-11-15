package com.test.automation.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadDataFromPropertiesFile {

	public String readdata(String key) {

		File propertiesfile = new File("C:\\Users\\madhav\\eclipse-workspace\\Gearbest\\testdata.properties");

		FileInputStream loadingInputFile = null;

		try {
			loadingInputFile = new FileInputStream(propertiesfile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Properties loadingPropertiesData = new Properties();

		try {
			loadingPropertiesData.load(loadingInputFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String keyvalue = loadingPropertiesData.getProperty(key);
		return keyvalue;

	}
}
