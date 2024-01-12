package sample.journal.editor.config.contributor;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.servlet.taglib.DynamicInclude;
import com.liferay.portal.kernel.util.StreamUtil;

@Component(
	immediate = true, 
	service = DynamicInclude.class
)
public class CKEditorAdditionalResourcesDynamicInclude implements DynamicInclude {

	@Override
	public void include(HttpServletRequest request, HttpServletResponse response, String key)
		throws IOException {

		StreamUtil.transfer(generateStream(), response.getOutputStream(), false);
	}

	public static InputStream generateStream()
	{
		String jsString = "<script>CKEDITOR.dtd.$removeEmpty['i'] = false;</script>";
		return new ByteArrayInputStream(jsString.getBytes());
	}

	@Override
	public void register(
		DynamicInclude.DynamicIncludeRegistry dynamicIncludeRegistry) {

		dynamicIncludeRegistry.register("com.liferay.frontend.editor.ckeditor.web#ckeditor#additionalResources");
	}

}
