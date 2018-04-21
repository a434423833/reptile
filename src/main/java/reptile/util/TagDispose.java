package reptile.util;

import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 标签处理
 *
 * @author caohao 2018-04-02
 */
public class TagDispose {

    /**
     * a标签处理,拿到href
     *
     * @param page
     * @return
     */
    public static Set<String> getAhref(Page page) {
        Set<String> links = new HashSet<>();
        Elements es = PageParserTool.select(page, "a");
        for (int i = 0; i < es.size(); i++) {
            String str = es.get(i).attributes().toString();
            //拿到href=的位置
            int begin = str.indexOf('=');
            if (begin == -1) {
                continue;
            }
            str = str.substring(begin + 2, str.length());
            //拿到截取后“第一次出现的位置，即为href中的路径
            int end = str.indexOf('"');
            str = str.substring(0, end);
            if (str.startsWith("http")) {
                //links.add(str);
                continue;
            }
            links.add(es.get(i).baseUri() + str);
        }
        return links;
    }

    /**
     * 拿到标签中的值标签
     *
     * @param page
     */
    public static List<String> getTagValue(Page page, String tag) {
        List<String> list = new ArrayList<>();
        Elements es = PageParserTool.select(page, tag);
        List<String> strList = new ArrayList<>();
        for (Element e : es) {
            String s = getString(e.childNodes());
            strList.add(s);
        }
        return strList;
    }

    /**
     * 通过递归拿到标签中的值
     *
     * @param nodes
     */
    private static String getString(List<Node> nodes) {
        StringBuilder str = new StringBuilder("");
        for (Object object : nodes) {
            if (object instanceof TextNode) {
                str.append(((TextNode) object).text());
            }
            if (object instanceof Element) {
                //递归拿到子集所有的str
                str.append(getString(((Element) object).childNodes()));
            }
        }
        return str.toString();
    }

}
