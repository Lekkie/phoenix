package com.solab.iso8583;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import com.solab.iso8583.parse.ConfigParser;

public class IsoMessageFactory {

	public static MessageFactory<IsoMessage> createMessageFactory() throws IOException
	{
		return ConfigParser.createFromClasspathConfig("POSTBRIDGE_CONFIG.xml");
	}
	
	public static MessageFactory<IsoMessage> createMessageFactory(String filePath) throws IOException
	{
		return ConfigParser.createFromUrl(new File(filePath).toURI().toURL());
	}
}
