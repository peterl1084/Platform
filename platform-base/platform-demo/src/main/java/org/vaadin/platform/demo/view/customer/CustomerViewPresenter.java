package org.vaadin.platform.demo.view.customer;

import org.vaadin.platform.ui.view.HasView;
import org.vaadin.platform.ui.view.IsEnterAware;
import org.vaadin.platform.ui.view.Presenter;

@Presenter
public class CustomerViewPresenter implements IsEnterAware, HasView<CustomerView> {

    @Override
    public void onEnter(String parameters) {
        System.out.println(getClass().getCanonicalName() + " entered with params " + parameters);
    }

    @Override
    public Class<CustomerView> getViewType() {
        return CustomerView.class;
    }

    public void onButtonClicked() {
        System.out.println(this + " button click received");
        getView().showNotification();
    }
}
