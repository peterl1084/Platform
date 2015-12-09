package org.vaadin.platform.ui.navigation;

public interface UriFragmentResolver {

    boolean isFragmentValid(String uriFragment);

    String resolveView(String uriFragment);

    String resolveParameters(String uriFragment);
}
