package org.vaadin.platform.ui.navigation;

/**
 * UriFragmentResolver is used to evaluate new URI fragment coming from web
 * browser's address bar. Users of Platform can switch UriFragmentResolver by
 * defining it in @SpecializedConfiguration bean.
 */
public interface UriFragmentResolver {

    /**
     * @param uriFragment
     * @return true if given uriFragment should be considered valid, false
     *         otherwise.
     */
    boolean isFragmentValid(String uriFragment);

    /**
     * @param uriFragment
     * @return part of the URI fragment that is considered to resemble the
     *         target view name. (actual navigation target)
     */
    String resolveViewName(String uriFragment);

    /**
     * @param uriFragment
     * @return part of the URI fragment that is considered to resemble the
     *         parameter part that comes after the actual target view name.
     */
    String resolveParameters(String uriFragment);
}
