package reptile.dreamwings;

import com.google.gson.Gson;
import reptile.Link.Links;
import reptile.util.FileTool;
import reptile.util.Page;
import reptile.util.PageParserTool;
import reptile.util.TagDispose;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 作用：在千千驿站中拿到所有的图片url
 * https://www.dreamwings.cn/
 *
 * @author caohao 2018-03-30
 */
public class Main implements Runnable {
    private static String fileLoad = "C:\\Users\\caohao\\Desktop\\fileload";
    private static String[] seeds;
    //必须包含的路径
    private static String mustFile;
    private static Set<String> headList = new HashSet<>();
    private static List<String> valueList = new ArrayList<>();

    /**
     * 使用种子初始化 URL 队列
     *
     * @param seeds 种子 URL
     * @return
     */
    private void initCrawlerWithSeeds(String[] seeds) {
        for (int i = 0; i < seeds.length; i++) {
            Links.addUnvisitedUrlQueue(seeds[i]);
        }
    }

    /**
     * 抓取过程
     *
     * @param
     * @return
     */
    public void crawling() {
        //初始化 URL 队列
        initCrawlerWithSeeds(seeds);
        //循环条件：待抓取的链接不空且抓取的网页不多于 1000
        while (!Links.unVisitedUrlQueueIsEmpty() && Links.getVisitedUrlNum() <= 1000) {
            //先从待访问的序列中取出第一个；
            String visitUrl = (String) Links.removeHeadOfUnVisitedUrlQueue();
            if (visitUrl == null) {
                continue;
            }
            //处理url
            Page page = new Page();
            try {
                page = RequestAndResponseTool.sendRequestAndGetResponse(visitUrl);
                if (!page.checkPage()) {
                    continue;
                }
            } catch (Exception e) {
                System.out.println(visitUrl + "有问题");
                continue;
            }
            //对page中a标签处理，拿到a中的路径
            Set<String> links = new HashSet<>();
            //将已经访问过的链接放入已访问的链接中；
            Links.addVisitedUrlSet(visitUrl);
            //下载路径的资源
            FileTool.saveToLocal(page, fileLoad);
            links.addAll(PageParserTool.getLinks(page, "img"));
            links.addAll(TagDispose.getAhref(page));
            for (String link : links) {
                if (link.indexOf(mustFile) == -1) {
                    continue;
                }
                Links.addUnvisitedUrlQueue(link);
                System.out.println("新增爬取路径: " + link);
            }
        }
    }

    public static void main(String[] args) {
        seeds = new String[]{"https://movie.douban.com/subject/25829175/reviews?start=0"};
        fileLoad = "C:\\Users\\caohao\\Desktop\\fileload\\wangjuan";
        mustFile = "/subject/25829175/reviews?start=";
        Main maim = new Main();
        Thread t1 = new Thread(maim);
        t1.start();
    }

    @Override
    public void run() {
        crawling();
    }
}
