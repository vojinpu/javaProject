package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

import app.Context;

public class Util {

	public static String PACKAGE_PATH_ICON = "/res/icons/";
	public static String PACKAGE_PATH_IMAGE = "/res/images/";

	/* Pozivate kao Util.loadIcon(getClass(), "ime.png"); */

	public static <T> ImageIcon loadIcon(Class<T> context, String name) {

		return new ImageIcon(context.getResource(PACKAGE_PATH_ICON.concat(name)));
	}

	public static <T> ImageIcon loadImage(Class<T> context, String name) {

		return new ImageIcon(context.getResource(PACKAGE_PATH_IMAGE.concat(name)));
	}

	public static ProcessingReport validateJSON(String json) {

		if (json == null) {
			System.out.println("Util json je null");
		}
		
		ProcessingReport report = null;

		try {

			ObjectMapper mapper = new ObjectMapper();

			final JsonNode meta_shema = mapper.readTree(json);
			final JsonNode meta_meta_shema = JsonLoader.fromResource(Context.META_META_SCHEME_PATH);
			final JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
			final JsonSchema schema = factory.getJsonSchema(meta_meta_shema);
			report = schema.validate(meta_shema);

			// System.out.println(report);
		} catch (ProcessingException e) {
			JOptionPane.showMessageDialog(null, "U\u010Ditavate neispravan JSON fajl!", "Neispravan JSON",
					JOptionPane.WARNING_MESSAGE);
			// e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Util - pokusano ucitavanje loseg JSON-a");
			return null;
		}

		return report;
	}

	public static boolean validateJSON(Path path) {
		
		byte[] bytes = new byte[0];
		String string = null;

		try {
			bytes = Files.readAllBytes(path);
			string = new String(bytes, "UTF-8");
		} catch (IOException e) {
			
			//e.printStackTrace();
			return false;
		}

		ProcessingReport report = validateJSON(string);
		
		if (report == null || !report.isSuccess()){
			return false;
		}
		
		return true;

	}
}
