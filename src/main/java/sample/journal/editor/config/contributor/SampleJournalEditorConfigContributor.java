package sample.journal.editor.config.contributor;

import com.liferay.portal.kernel.editor.configuration.EditorConfigContributor;
import com.liferay.portal.kernel.editor.configuration.BaseEditorConfigContributor;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import org.osgi.service.component.annotations.Component;

import java.util.Map;

/**
 * @author balazs.letai@liferay.com
 */

@Component(
	immediate = true,
	property = {
		"editor.config.key=contentEditor", "editor.config.key=rich_text",

		// For Liferay 7.3
		"editor.config.key=translateEditor",
		// For Liferay 7.4
		// "editor.config.key=defaultTranslateEditor",

		"editor.name=ckeditor", "editor.name=ckeditor_classic",
		"service.ranking:Integer=100"
	},
	service = EditorConfigContributor.class
)
public class SampleJournalEditorConfigContributor
	extends BaseEditorConfigContributor {

	@Override
	public void populateConfigJSONObject(JSONObject jsonObject, Map<String, Object> inputEditorTaglibAttributes,
			ThemeDisplay themeDisplay, RequestBackedPortletURLFactory requestBackedPortletURLFactory) {

		jsonObject.put("pasteFromWordRemoveFontStyles", Boolean.TRUE);
		jsonObject.put("pasteFromWordRemoveStyles", Boolean.TRUE);

		String extraPlugins = jsonObject.getString("extraPlugins");

		if (Validator.isNotNull(extraPlugins)) {
			extraPlugins = extraPlugins + ",maximize,showblocks,iframe,mathjax,pastefromword,justify,colorbutton,basicstyles,indent";
			jsonObject.put("extraPlugins", extraPlugins);

			JSONArray toolbarsLiferay = jsonObject.getJSONArray("toolbar_liferay");
			JSONArray extraTools_liferay = JSONFactoryUtil.createJSONArray();

			if (Validator.isNotNull(toolbarsLiferay)) {
				extraTools_liferay = toolbarsLiferay.getJSONArray(toolbarsLiferay.length() - 1);
			}

			extraTools_liferay.put("-");
			extraTools_liferay.put("ShowBlocks");
			extraTools_liferay.put("-");
			extraTools_liferay.put("Iframe");
			extraTools_liferay.put("-");
			extraTools_liferay.put("Maximize");
			extraTools_liferay.put("-");
			extraTools_liferay.put("Mathjax");
			extraTools_liferay.put("-");
			extraTools_liferay.put("PasteFromWord");
			extraTools_liferay.put("-");
			extraTools_liferay.put("JustifyRight");
			extraTools_liferay.put("-");
			extraTools_liferay.put("JustifyCenter");
			extraTools_liferay.put("-");
			extraTools_liferay.put("JustifyLeft");
			extraTools_liferay.put("-");
			extraTools_liferay.put("JustifyBlock");
			extraTools_liferay.put("-");
			extraTools_liferay.put("TextColor");
			extraTools_liferay.put("-");
			extraTools_liferay.put("BGColor");
			extraTools_liferay.put("-");
			extraTools_liferay.put("Superscript");
			extraTools_liferay.put("-");
			extraTools_liferay.put("RemoveFormat");
			extraTools_liferay.put("-");
			extraTools_liferay.put("Strike");
			extraTools_liferay.put("-");
			extraTools_liferay.put("Subscript");
			extraTools_liferay.put("-");
			extraTools_liferay.put("Outdent");
			extraTools_liferay.put("-");
			extraTools_liferay.put("Indent");
			extraTools_liferay.put("-");

			jsonObject.put("mathJaxLib", "//cdnjs.cloudflare.com/ajax/libs/mathjax/2.7.9/MathJax.js?config=TeX-MML-AM_CHTML");

		}

	}

}
