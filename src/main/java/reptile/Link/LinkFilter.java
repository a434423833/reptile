package reptile.Link;

/**
 * 过滤
 *
 * @author caohao 2018-03-30
 */
public interface LinkFilter {
    public boolean accept(String url);
}