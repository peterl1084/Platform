package org.vaadin.platform.demo.view.customer;

import javax.annotation.PostConstruct;

import org.vaadin.platform.demo.Views;
import org.vaadin.platform.ui.navigation.IsNavigable;
import org.vaadin.platform.ui.view.HasPresenter;
import org.vaadin.platform.ui.view.ViewComposition;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;

@ViewComposition(name = Views.CUSTOMER_VIEW)
public class CustomerView extends CustomComponent implements IsNavigable, HasPresenter<CustomerViewPresenter> {
    private static final long serialVersionUID = -8457497740824005459L;

    private VerticalLayout layout;

    public CustomerView() {
        layout = new VerticalLayout();
        setCompositionRoot(layout);
    }

    @PostConstruct
    protected void initialize() {
        for (int i = 0; i < 10; i++) {
            CustomerViewPresenter presenter = getPresenter();
            System.out.println(presenter);
        }
    }

    @Override
    public Class<CustomerViewPresenter> getPresenterType() {
        return CustomerViewPresenter.class;
    }
}
