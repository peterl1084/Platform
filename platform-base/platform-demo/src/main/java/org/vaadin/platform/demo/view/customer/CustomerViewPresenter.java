package org.vaadin.platform.demo.view.customer;

import org.vaadin.platform.ui.view.IsEnterAware;
import org.vaadin.platform.ui.view.Presenter;

@Presenter
public class CustomerViewPresenter implements IsEnterAware {

    @Override
    public void onEnter(String parameters) {
        System.out.println(getClass().getCanonicalName() + " entered with params " + parameters);
    }

}
