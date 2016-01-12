package org.vaadin.platform.ui.navigation;

/**
 * DefaultFragmentResolverBean is Platform default implementation of
 * UriFragmentResolver and is automatically configured by default configuration
 * bean.
 */
public class UriFragmentResolverBean implements UriFragmentResolver {
    private static final char VIEW_SEPARATOR_CHAR = '/';

    @Override
    public boolean isFragmentValid(String uriFragment) {
        return uriFragment != null && !uriFragment.isEmpty();
    }

    @Override
    public String resolveViewName(String uriFragment) {
        if (uriFragment.startsWith("!")) {
            uriFragment = uriFragment.substring(1);
        }

        int index = uriFragment.indexOf(VIEW_SEPARATOR_CHAR);
        if (index == -1) {
            return uriFragment;
        }

        return uriFragment.substring(0, index);
    }

    @Override
    public String resolveParameters(String uriFragment) {
        int index = uriFragment.indexOf(VIEW_SEPARATOR_CHAR);
        if (index == -1) {
            return "";
        }

        return uriFragment.substring(index + 1, uriFragment.length());
    }
}
