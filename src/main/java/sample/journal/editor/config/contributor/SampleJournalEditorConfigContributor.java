package sample.journal.editor.config.contributor;

import com.liferay.portal.kernel.editor.configuration.EditorConfigContributor;
import com.liferay.portal.kernel.editor.configuration.BaseEditorConfigContributor;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Component;

import java.util.Map;

/**
 * Editor configuration contributor for CKEditor.
 * Author: balazs.letai@liferay.com
 */

@Component(
    immediate = true,
    property = {
        "editor.config.key=contentEditor", "editor.config.key=rich_text",
        // "editor.config.key=translateEditor", // For Liferay 7.3
        "editor.config.key=defaultTranslateEditor", // For Liferay 7.4
        "editor.name=ckeditor", "editor.name=ckeditor_classic",
        "service.ranking:Integer=100"
    },
    service = EditorConfigContributor.class
)
public class SampleJournalEditorConfigContributor extends BaseEditorConfigContributor {

    private static final String EXTRA_PLUGINS = "showblocks,iframe,maximize,justify,colorbutton,basicstyles,indent,mathjax,smiley";
    private static final String MATH_JAX_LIB_URL = "//cdnjs.cloudflare.com/ajax/libs/mathjax/2.7.9/MathJax.js?config=TeX-MML-AM_CHTML";
    private static final String[] EXTRA_TOOLS = {
        "ShowBlocks", "Iframe", "Maximize", "JustifyLeft" , "JustifyCenter", 
        "JustifyRight" , "JustifyBlock", "TextColor", "BGColor", "RemoveFormat",
        "Strike", "Subscript", "Superscript", "Outdent", "Indent" , "Mathjax", "Smiley"
    };

    @Override
    public void populateConfigJSONObject(JSONObject jsonObject, Map<String, Object> inputEditorTaglibAttributes,
                                         ThemeDisplay themeDisplay, RequestBackedPortletURLFactory requestBackedPortletURLFactory) {

        addExtraPlugins(jsonObject);
        configureToolbar(jsonObject);
    }

    private void addExtraPlugins(JSONObject jsonObject) {
        String extraPlugins = jsonObject.getString("extraPlugins");

        if (Validator.isNotNull(extraPlugins)) {
            jsonObject.put("extraPlugins", extraPlugins + "," + EXTRA_PLUGINS);
        }
    }

    private void configureToolbar(JSONObject jsonObject) {
        JSONArray toolbarsLiferay = jsonObject.getJSONArray("toolbar_liferay");
        JSONArray extraToolsLiferay = JSONFactoryUtil.createJSONArray();

        if (Validator.isNotNull(toolbarsLiferay)) {
            extraToolsLiferay = toolbarsLiferay.getJSONArray(toolbarsLiferay.length() - 1);
        }

        for (String tool : EXTRA_TOOLS) {
            extraToolsLiferay.put("-");
            extraToolsLiferay.put(tool);
        }

        jsonObject.put("toolbar_liferay", extraToolsLiferay);
        jsonObject.put("mathJaxLib", MATH_JAX_LIB_URL);
    }
}
