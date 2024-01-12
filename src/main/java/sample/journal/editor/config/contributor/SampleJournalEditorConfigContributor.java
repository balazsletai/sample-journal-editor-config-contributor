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

@Component(
	immediate = true,
	property = {
		"editor.config.key=contentEditor", "editor.config.key=rich_text",
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
			extraPlugins = extraPlugins + ",maximize,showblocks,iframe,mathjax,pastefromword";
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
			
			jsonObject.put("mathJaxLib", "//cdnjs.cloudflare.com/ajax/libs/mathjax/2.7.9/MathJax.js?config=TeX-MML-AM_CHTML");	
		
		}
	}

	


}
