package org.vaadin.platform.demo.view.customer;

import javax.inject.Inject;

import org.vaadin.platform.ui.view.HasView;
import org.vaadin.platform.ui.view.IsEnterAware;
import org.vaadin.platform.ui.view.Presenter;

import com.vaadin.platform.data.DataService;

@Presenter
public class CustomerViewPresenter implements IsEnterAware, HasView<CustomerView> {

    @Inject
    private DataService dataService;

    @Override
    public void onEnter(String parameters) {
        System.out.println(getClass().getCanonicalName() + " entered with params " + parameters);

        getView().populateCustomers(dataService.getDtos(CustomerDTO.class));
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
